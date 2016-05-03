//Michael Bowen
//CS1550 Tues/Thurs 2:30-3:45pm  Recitation: Fri 12:00pm
//
//Project 4 - file system implementation with fuse. Directory submission.

/*
	FUSE: Filesystem in Userspace
	Copyright (C) 2001-2007  Miklos Szeredi <miklos@szeredi.hu>

	This program can be distributed under the terms of the GNU GPL.
	See the file COPYING.

	gcc -Wall `pkg-config fuse --cflags --libs` cs1550.c -o cs1550
*/

#define	FUSE_USE_VERSION 26

#include <fuse.h>
#include <stdio.h>
#include <string.h>
#include <errno.h>
#include <fcntl.h>

//size of a disk block
#define	BLOCK_SIZE 512

//number of blocks on .disk - 5242880 / 512
#define NUM_BLOCKS 10240

//size of three disk blocks - used to find the bitmap
//also the number of blocks for the BITMAP
#define BITMAP_OFFSET BLOCK_SIZE * 3
#define BITMAP_BLOCKS 3

//we'll use 8.3 filenames
#define	MAX_FILENAME 8
#define	MAX_EXTENSION 3
#define MAX_FILE_WITH_EXT 13 //max length of file name with extension - including . and \0

//How many files can there be in one directory?
#define	MAX_FILES_IN_DIR (BLOCK_SIZE - (MAX_FILENAME + 1) - sizeof(int)) / \
	((MAX_FILENAME + 1) + (MAX_EXTENSION + 1) + sizeof(size_t) + sizeof(long))

//How much data can one block hold?
#define	MAX_DATA_IN_BLOCK (BLOCK_SIZE - sizeof(unsigned long))

struct cs1550_directory_entry
{
	int nFiles;	//How many files are in this directory.
				//Needs to be less than MAX_FILES_IN_DIR

	struct cs1550_file_directory
	{
		char fname[MAX_FILENAME + 1];	//filename (plus space for nul)
		char fext[MAX_EXTENSION + 1];	//extension (plus space for nul)
		size_t fsize;					//file size
		long nStartBlock;				//where the first block is on disk
	} __attribute__((packed)) files[MAX_FILES_IN_DIR];	//There is an array of these

	//This is some space to get this to be exactly the size of the disk block.
	//Don't use it for anything.  
	char padding[BLOCK_SIZE - MAX_FILES_IN_DIR * sizeof(struct cs1550_file_directory) - sizeof(int)];
} ;

typedef struct cs1550_directory_entry cs1550_directory_entry;

#define MAX_DIRS_IN_ROOT (BLOCK_SIZE - sizeof(int)) / ((MAX_FILENAME + 1) + sizeof(long))

struct cs1550_root_directory
{
	int nDirectories;	//How many subdirectories are in the root
						//Needs to be less than MAX_DIRS_IN_ROOT
	struct cs1550_directory
	{
		char dname[MAX_FILENAME + 1];	//directory name (plus space for nul)
		long nStartBlock;				//where the directory block is on disk
	} __attribute__((packed)) directories[MAX_DIRS_IN_ROOT];	//There is an array of these

	//This is some space to get this to be exactly the size of the disk block.
	//Don't use it for anything.
	char padding[BLOCK_SIZE - MAX_DIRS_IN_ROOT * sizeof(struct cs1550_directory) - sizeof(int)];
} ;

typedef struct cs1550_root_directory cs1550_root_directory;

struct cs1550_disk_block
{
	//The first 4 bytes will be the value 0xF113DA7A 
	unsigned long magic_number;
	//And all the rest of the space in the block can be used for actual data
	//storage.
	char data[MAX_DATA_IN_BLOCK];
};

typedef struct cs1550_disk_block cs1550_disk_block;

//How many pointers in an inode?
#define NUM_POINTERS_IN_INODE (BLOCK_SIZE - sizeof(unsigned int) - sizeof(unsigned long))/sizeof(unsigned long)

struct cs1550_inode
{
	//The first 4 bytes will be the value 0xFFFFFFFF
	unsigned long magic_number;
	//The number of children this node has (either other inodes or data blocks)
	unsigned int children;
	//An array of disk pointers to child nodes (either other inodes or data)
	unsigned long pointers[NUM_POINTERS_IN_INODE];
};

typedef struct cs1550_inode cs1550_inode;


//function declarations
static int getRootDirectory(cs1550_root_directory *);
static int getDirIndex(const char *);
static int getFileIndex(const char *, const char *, const char *);
static int getDirectory(cs1550_directory_entry *, const char *);
static long findFreeBlock(void);
static int allocateBitmap(long);
static int unallocateBitmap(long);
static int updateDirectory(cs1550_directory_entry *, long);
static int updateRoot(cs1550_root_directory *);
static int createDirectory(const char *);
static int createFile(const char *, const char *, const char *);
static int writeBlock(void *, long);
static int readBlock(void *, long);

//stores the root directory in the rootDir parameter
//returns 0 on success, -1 on error
static int getRootDirectory(cs1550_root_directory *rootDir) {
	FILE *f = fopen(".disk", "rb");
	
	if(f == NULL) {
		//problem opening file
		return -1;
	}
	
	//root directory always in first node
	fseek(f, 0, SEEK_SET);
	if(fread(rootDir, sizeof(cs1550_root_directory), 1, f) != 1) {
		//problem reading
		return -1;
	}
	
	fclose(f);
	return 0;
} //ends getRootDirectory

//function that gets the index of the directory
//returns -1 if it does not exist
//returns the index of the directory if it does exist
static int getDirIndex(const char *directory) {
	int i, index = -1;
	cs1550_root_directory rootDir;
	
	if(getRootDirectory(&rootDir)) {
		//problem getting root directory
		return -1;
	}
	
	for(i = 0; i < rootDir.nDirectories && index < 0; i++) {
		if(strcmp(directory, rootDir.directories[i].dname) == 0) {
			//directory name found, update index
			index = i;
		}
	}	
	
	return index;
} //ends getDirIndex

//returns the index of the file in the given directory dirName
//returns -1 on failure (file does not exist)
static int getFileIndex(const char *dirName, const char *filename, const char *extension) {
	int i, index = -1;
	cs1550_directory_entry dir;

	if(getDirectory(&dir, dirName) == -1) {
		//problem getting directory or directory does not exist
		return index;
	}
	else {
		for(i = 0; i < dir.nFiles && index < 0; i++) {
			if(strcmp(filename, dir.files[i].fname) == 0) {
				//file name matches, check extension - could do this in one if statement but separated
				//them for readability.
				if(strcmp(extension, dir.files[i].fext) == 0) {
					//matching file found - update index
					index = i;
				}
			}
		}
	}

	return index;
} //ends getFileIndex

//stores the directory with name directory in the directory entry dir
//returns 0 on success, -1 on error
static int getDirectory(cs1550_directory_entry *dir, const char *directory) {
	cs1550_root_directory rootDir;
	long blockLocation = 0;
	int index = -1;
	FILE *f;
	
	//get the root directory
	if(getRootDirectory(&rootDir)) {
		//problem getting root directory
		return -1;
	}
	
	//find the block location of the directory
	index = getDirIndex(directory);
	if(index == -1) {
		//directory did not exist
		return index;
	}	
	blockLocation = rootDir.directories[index].nStartBlock;
	
	f = fopen(".disk", "rb");
	if(f == NULL) {
		//there was a problem opening file
		return -1;
	}
	else {
		fseek(f, blockLocation * BLOCK_SIZE, SEEK_SET);
		if(fread(dir, BLOCK_SIZE, 1, f) != 1) {
			//directory not read from disk
			fclose(f);
			return -1;
		}		
	}
	
	fclose(f);
	return 0;	
} //ends getDirectory

//returns the index of the first free block on the .disk
//returns -1 on failure/no blocks free
//NOTE - bitmap reads from least significant to most
//This means that a value of 0000 0001 means only the first block of that
//8 block set is allocated. Value of 0000 0010 means only the second block is allocated, etc.
static long findFreeBlock(void) {
	FILE *f;
	long index = -1;
	int i = 0, j = 0, k = 0;
	cs1550_disk_block bitmapBlocks[BITMAP_BLOCKS];	//array of blocks used by the bitmap
	
	//open the file and seek to the beginning of the bitmap
	f = fopen(".disk", "rb");
	if(f == NULL) {
		//problem opening file
		return index;
	}
	if(fseek(f, -BITMAP_OFFSET, SEEK_END) != 0) {
		//problem seeking
		return index;
	}
	
	//read the bitmap from the .disk
	for(i = 0; i < BITMAP_BLOCKS; i++) {
		if(fread(&bitmapBlocks[i], BLOCK_SIZE, 1, f) != 1) {
			//blocks not read from disk
			fclose(f);
			return index;
		}	
	}
	
	//ensure that the first block is allocated - for the root directory
	//bit of a hack - this will always be marked allocated once the bitmap is updated
	//but this ensures that the algorithm ignores the first block if this function is
	//called before any other update to the bitmap.
	bitmapBlocks[0].data[0] = bitmapBlocks[0].data[0] | 0x1;
	
 	for(i = 0; i < BITMAP_BLOCKS && index < 0; i++){
		for(j = 0; j < MAX_DATA_IN_BLOCK && index < 0; j++) {
			unsigned char c = (unsigned char) bitmapBlocks[i].data[j];
			if(c != 0xFF) {
				//current byte has a free block in it - find the bit
				for(k = 0; k < 8 && index < 0; k++) {
					if(!((c >> k) & 0x1)) {
						index = (i * MAX_DATA_IN_BLOCK) + (j * 8) + k;
					}
				}
			}
		}		
	} 
	
	fclose(f);	
	if(index >= NUM_BLOCKS) {
		//index was past the max number of blocks - reset to -1
		index = -1;
	}
	
	return index;
} //ends findFreeBlock

//function that marks the block at blockNum allocated in the bitmap
//returns -1 on failure
static int allocateBitmap(long blockNum) {
	FILE *f;
	long bitmapBlockNumber;
	int byteIndex, bitIndex;
	cs1550_disk_block bitmapBlock;
	
	if(blockNum >= NUM_BLOCKS) {
		//cannot allocate block - block num is past number of blocks
		return -1;
	}
	
	//open the file and seek to the beginning of the bitmap
	f = fopen(".disk", "rb+");
	if(f == NULL) {
		//problem opening file
		return -1;
	}
	
	//seek to appropriate block
	bitmapBlockNumber = blockNum / (MAX_DATA_IN_BLOCK * 8);
	if(fseek(f, -BITMAP_OFFSET + (bitmapBlockNumber * BLOCK_SIZE), SEEK_END) != 0) {
		//problem seeking
		fclose(f);
		return -1;
	}
	
	//read the bitmap block to be updated from the .disk
	if(fread(&bitmapBlock, BLOCK_SIZE, 1, f) != 1) {
		//blocks not read from disk
		fclose(f);
		return -1;
	}
	
	//update appropriate bit to allocated
	byteIndex = blockNum / 8;
	bitIndex = blockNum % 8;
	bitmapBlock.data[byteIndex] |= 1 << bitIndex;
	
	//ensure that the first bit is set to 1 - root directory block is always taken
	//will occur each time the first bitmap block is to be updated
	if(bitmapBlockNumber == 0) {
		bitmapBlock.data[0] |= 1;
	}
	
	//write the block back to the disk
	if(fseek(f, -BITMAP_OFFSET + (bitmapBlockNumber * BLOCK_SIZE), SEEK_END) != 0) {
		//problem seeking
		fclose(f);
		return -1;
	}
	if(fwrite(&bitmapBlock, BLOCK_SIZE, 1, f) != 1) {
		//block not written to disk
		fclose(f);
		return -1;
	} 
	
	fclose(f);
	return 1;
} //ends allocateBitmap

//function that unallocates a block on the bitmap
//returns -1 on failure
static int unallocateBitmap(long blockNum) {
	FILE *f;
	long bitmapBlockNumber;
	int byteIndex, bitIndex;
	char bits;
	cs1550_disk_block bitmapBlock;
	
	if(blockNum >= NUM_BLOCKS || blockNum <= 0) {
		//cannot allocate block - block num is past number of blocks
		//or the block number is 0 (root directory) or less than 0 (invalid)
		return -1;
	}
	
	//open the file and seek to the beginning of the bitmap
	f = fopen(".disk", "rb+");
	if(f == NULL) {
		//problem opening file
		return -1;
	}
	
	//seek to appropriate block
	bitmapBlockNumber = blockNum / (MAX_DATA_IN_BLOCK * 8);
	if(fseek(f, -BITMAP_OFFSET + (bitmapBlockNumber * BLOCK_SIZE), SEEK_END) != 0) {
		//problem seeking
		fclose(f);
		return -1;
	}
	
	//read the bitmap block to be updated from the .disk
	if(fread(&bitmapBlock, BLOCK_SIZE, 1, f) != 1) {
		//blocks not read from disk
		fclose(f);
		return -1;
	}
	
	//update appropriate bit to unallocated
	byteIndex = blockNum / 8;
	bitIndex = blockNum % 8;
	bits = 1 << bitIndex;
	bitmapBlock.data[byteIndex] &= ~(bits);

	//write the block back to the disk
	if(fseek(f, -BITMAP_OFFSET + (bitmapBlockNumber * BLOCK_SIZE), SEEK_END) != 0) {
		//problem seeking
		fclose(f);
		return -1;
	}
	if(fwrite(&bitmapBlock, BLOCK_SIZE, 1, f) != 1) {
		//block not written to disk
		fclose(f);
		return -1;
	} 

	fclose(f);
	return 1;
} //ends unallocateBitmap

//write the given directory to the .disk at block blockNum - returns -1 on failure
static int updateDirectory(cs1550_directory_entry *newDir, long blockNum) {
	FILE *f = fopen(".disk", "rb+");
	
	if(f == NULL) {
		//problem opening
		return -1;
	}
	
	//write directory to .disk
	if(fseek(f, blockNum * BLOCK_SIZE, SEEK_SET) != 0) {
		//problem seeking
		fclose(f);
		return -1;
	}
	if(fwrite(newDir, sizeof(cs1550_directory_entry), 1, f) != 1) {
		//problem writing
		fclose(f);
		return -1;
	}
	
	fclose(f);
	return 0;
} //ends updateDirectory

//write the given root to the .disk - returns -1 on failure
static int updateRoot(cs1550_root_directory *rootDir) {
	FILE *f = fopen(".disk", "rb+");
	
	if(f == NULL) {
		//problem opening
		return -1;
	}
	
	//write root to disk - root is always first block
	if(fseek(f, 0, SEEK_SET) != 0) {
		//problem seeking
		fclose(f);
		return -1;
	}
	if(fwrite(rootDir, sizeof(cs1550_root_directory), 1, f) != 1) {
		//problem writing
		fclose(f);
		return -1;
	}
	
	fclose(f);
	return 0;
} //ends updateRoot

//creates a new directory
static int createDirectory(const char *directory) {
	cs1550_directory_entry newDir;
	cs1550_root_directory rootDir;
	long freeBlock;
	
	freeBlock = findFreeBlock();

	if(getRootDirectory(&rootDir)) {
		//problem getting root directory
		return -1;
	}
	
	//make changes to root directory
	strcpy(rootDir.directories[rootDir.nDirectories].dname, directory);
	rootDir.directories[rootDir.nDirectories].nStartBlock = freeBlock;
	rootDir.nDirectories++;
	if(updateRoot(&rootDir) == -1) {
		//problem writing root to .disk
		return -1;
	}
	
	//make changes to directory
	newDir.nFiles = 0;
	if(updateDirectory(&newDir, freeBlock) == -1) {
		//problem writing directory to .disk
		return -1;
	}
	
	//mark block as allocated
	if(allocateBitmap(freeBlock) == -1) {
		//problem updating bitmap on .disk
		return -1;
	}

	return 1;	
} //ends createDirectory

//create necessary inode/data nodes for file creation and update .disk accordingly
//return error code or -1 on failure, 0 on success
static int createFile(const char *dirName, const char *filename, const char *extension) {
	long inodeFreeBlock, filedataFreeBlock;
	int dirIndex;
	cs1550_directory_entry dir;
	cs1550_inode inode;
	cs1550_disk_block fileBlock;
	cs1550_root_directory rootDir;

	if(getDirectory(&dir, dirName) == -1) {
		//problem getting directory or directory does not exist
		printf("problem getting directory in createFile\n");
		return -1;
	}

	if(dir.nFiles >= MAX_FILES_IN_DIR) {
		//directory already contains max files - return no space error
		printf("max files in directories in createFile\n");
		return -ENOSPC;
	}

	//freeblock for the inode
	inodeFreeBlock = findFreeBlock();
	if(inodeFreeBlock == -1) {
		//no space found - throw error
		printf("problem getting inodeFreeBlock in createFile\n");
		return -ENOSPC;
	}
	//mark block allocated
	if(allocateBitmap(inodeFreeBlock) == -1) {
		//problem writing to bitmap
		printf("problem allocating inode block in createFile\n");
		return -1;
	}

	//make changes to directory
	strcpy(dir.files[dir.nFiles].fname, filename);
	strcpy(dir.files[dir.nFiles].fext, extension);
	dir.files[dir.nFiles].fsize = 0;
	dir.files[dir.nFiles].nStartBlock = inodeFreeBlock;
	dir.nFiles++;

	//freeblock for the file
	filedataFreeBlock = findFreeBlock();
	if(filedataFreeBlock == -1) {
		//no space found - throw error
		unallocateBitmap(inodeFreeBlock);
		return -ENOSPC;
	}
	//mark block allocated
	if(allocateBitmap(filedataFreeBlock) == -1) {
		//problem writing to bitmap
		unallocateBitmap(inodeFreeBlock);
		return -1;
	}

	//update the inode/file data
	inode.magic_number = 0xFFFFFFFF;
	inode.children = 1;
	inode.pointers[0] = filedataFreeBlock;
	fileBlock.magic_number = 0xF113DA7A;

	//write the inode and data blocks to .disk
	if(writeBlock(&inode, inodeFreeBlock) == -1) {
		//problem writing inode
		unallocateBitmap(inodeFreeBlock);
		unallocateBitmap(filedataFreeBlock);
		return -1;
	}
	if(writeBlock(&fileBlock, filedataFreeBlock) == -1) {
		//problem writing filedata
		unallocateBitmap(inodeFreeBlock);
		unallocateBitmap(filedataFreeBlock);
		return -1;
	}

	//update the directory index
	if(getRootDirectory(&rootDir)) {
		//problem getting root directory
		return -1;
	}	
	dirIndex = getDirIndex(dirName);
	if(dirIndex == -1) {
		//problem getting directory index
		return -1;
	}	
	if(updateDirectory(&dir, rootDir.directories[dirIndex].nStartBlock) == -1) {
		//problem updating directory on .disk
		return -1;
	}	
	
	return 0;
} //ends createFile

//writes a file block or inode block to the .disk at blockNum
//returns -1 on failure
static int writeBlock(void *block, long blockNum) {
	FILE *f = fopen(".disk", "rb+");
	
	if(f == NULL) {
		//problem opening
		return -1;
	}
	
	//write directory to .disk
	if(fseek(f, blockNum * BLOCK_SIZE, SEEK_SET) != 0) {
		//problem seeking
		fclose(f);
		return -1;
	}
	if(fwrite(block, BLOCK_SIZE, 1, f) != 1) {
		//problem writing
		fclose(f);
		return -1;
	}

	fclose(f);
	return 0;
} //ends writeBlock

//reads a file block or inode block from the .disk at blockNum
//returns -1 on failure
static int readBlock(void *block, long blockNum) {
	FILE *f = fopen(".disk", "rb");
	
	if(f == NULL) {
		//problem opening
		return -1;
	}
	
	//read directory from .disk
	if(fseek(f, blockNum * BLOCK_SIZE, SEEK_SET) != 0) {
		//problem seeking
		fclose(f);
		return -1;
	}
	if(fread(block, BLOCK_SIZE, 1, f) != 1) {
		//problem writing
		fclose(f);
		return -1;
	}

	fclose(f);
	return 0;
} //ends writeBlock

/*
 * Called whenever the system wants to know the file attributes, including
 * simply whether the file exists or not. 
 *
 * man -s 2 stat will show the fields of a stat structure
 */
static int cs1550_getattr(const char *path, struct stat *stbuf)
{
	int i;
	int res = 0;
	cs1550_root_directory rootDir;
	//strings to hold the directory, file name an file extension
	char directory[MAX_FILENAME + 1], filename[MAX_FILENAME + 1], extension[MAX_EXTENSION + 1];

	//make sure each string starts out with null character.
	directory[0] = '\0';		
	filename[0] = '\0';
	extension[0] = '\0';

	memset(stbuf, 0, sizeof(struct stat));
	
	if(getRootDirectory(&rootDir)) {
		//problem getting root directory
		return -ENOENT;
	}
   
	//is path the root dir?
	if (strcmp(path, "/") == 0) {
		//root directory
		stbuf->st_mode = S_IFDIR | 0755;
		stbuf->st_nlink = 2;
	} 
	else {
		int dirIndex;
		
		//parse the path
		sscanf(path, "/%[^/]/%[^.].%s", directory, filename, extension);
		dirIndex = getDirIndex(directory);
		
		if(dirIndex != -1) {
			//directory was found			
			if(filename[0] == '\0') {
				//file name not supplied and path was an existing directory
				//Might want to return a structure with these fields
				stbuf->st_mode = S_IFDIR | 0755;
				stbuf->st_nlink = 2;
				res = 0; 							//no error
			}
			else {
				//file name was supplied - make sure it is a valid file
				cs1550_directory_entry dir;
				if(getDirectory(&dir, directory)) {
					return -ENOENT; 			//problem getting directory
				}
				
				res = -ENOENT;					//set res to error - if file is found, will be set to 0
				for(i = 0; i < dir.nFiles; i++) {
					if(strcmp(dir.files[i].fname, filename) == 0) {
						//file name matches, check extension
						if(strcmp(dir.files[i].fext, extension) == 0) {
							//file name and extension both match
							stbuf->st_mode = S_IFREG | 0666; 
							stbuf->st_nlink = 1; 					//file links
							stbuf->st_size = dir.files[i].fsize; 	//file size - make sure you replace with real size!
							res = 0; 								// no error
						}
					}
				}
			}
		}
		else {
			//directory not found
			res = -ENOENT;
		}
	}

	return res;
}

/* 
 * Called whenever the contents of a directory are desired. Could be from an 'ls'
 * or could even be when a user hits TAB to do autocompletion
 */
static int cs1550_readdir(const char *path, void *buf, fuse_fill_dir_t filler,
			 off_t offset, struct fuse_file_info *fi)
{
	//strings to hold the directory, file name an file extension
	char directory[MAX_FILENAME + 1], filename[MAX_FILENAME + 1], extension[MAX_EXTENSION + 1];
	cs1550_root_directory rootDir;
	cs1550_directory_entry currDir;
	char fullFileName[MAX_FILE_WITH_EXT];
	int i;
	//Since we're building with -Wall (all warnings reported) we need
	//to "use" every parameter, so let's just cast them to void to
	//satisfy the compiler
	(void) offset;
	(void) fi;
	
	//make sure directory/filename/extension start out as null strings, then parse
	directory[0] = '\0';		
	filename[0] = '\0';
	extension[0] = '\0';	
	sscanf(path, "/%[^/]/%[^.].%s", directory, filename, extension);

	if(strcmp(path, "/") == 0) {
		//root path - get root and display all subdirectories
		if(getRootDirectory(&rootDir)) {
		//problem getting root directory
			return -ENOENT;
		}
		
		//display each directory in root
		filler(buf, ".", NULL, 0);
		filler(buf, "..", NULL, 0);
		for(i = 0; i < rootDir.nDirectories; i++) {
			filler(buf, rootDir.directories[i].dname, NULL, 0);
		}
	}
	else {
		if(filename[0] != '\0' || extension[0] != '\0') {
			//path is a file name - invalid. can't ls a file
			return -ENOENT;
		}
		
		if(getDirIndex(directory) != -1) {
			//directory exists
			getDirectory(&currDir, directory);
			
			//print each file in the directory
			filler(buf, ".", NULL, 0);
			filler(buf, "..", NULL, 0);
			for(i = 0; i < currDir.nFiles; i++) {
				strcpy(fullFileName, currDir.files[i].fname);
				strcat(fullFileName, ".");
				strcat(fullFileName, currDir.files[i].fext);
				filler(buf, fullFileName, NULL, 0);
			}
		}
		else {
			//directory does not exit
			return -ENOENT;
		}
	}

	/*
	//add the user stuff (subdirs or files)
	//the +1 skips the leading '/' on the filenames
	filler(buf, newpath + 1, NULL, 0);
	*/
	return 0;
}

/* 
 * Creates a directory. We can ignore mode since we're not dealing with
 * permissions, as long as getattr returns appropriate ones for us.
 */
static int cs1550_mkdir(const char *path, mode_t mode)
{
	//strings to hold the directory, file name an file extension
	char directory[MAX_FILENAME + 1], filename[MAX_FILENAME + 1], extension[MAX_EXTENSION + 1];
	cs1550_root_directory rootDir;
	
	//(void) path;
	(void) mode;	
	
	//make sure directory/filename/extension start out as null strings, then parse
	directory[0] = '\0';		
	filename[0] = '\0';
	extension[0] = '\0';
	sscanf(path, "/%[^/]/%[^.].%s", directory, filename, extension);
	
	if(strlen(directory) > MAX_FILENAME) {
		//directory name too long - return error
		return -ENAMETOOLONG;
	}

	if(filename[0] != '\0') {
		//directory not under the root directory - return error
		return -EPERM;
	}
	
	if(getDirIndex(directory) != -1) {
		//directory already exists - return error
		return -EEXIST;
	}
	
	//directory has valid name, does not yet exist - try to created
	if(getRootDirectory(&rootDir) != 0) {
		//error getting root directory
		return -EACCES;
	}
	
	if(rootDir.nDirectories >= MAX_DIRS_IN_ROOT) {
		//max directories already exist - return error
		return -ENOSPC;
	}
	else {
		//can create directory - need to find an empty block
		if(createDirectory(directory) == -1) {
			//problem creating directory
			return -EACCES;
		}
	}
	
	return 0;
} //ends mkdir

/* 
 * Removes a directory.
 */
static int cs1550_rmdir(const char *path)
{
	(void) path;
    return 0;
}

/* 
 * Does the actual creation of a file. Mode and dev can be ignored.
 *
 */
static int cs1550_mknod(const char *path, mode_t mode, dev_t dev)
{
	//strings to hold the directory, file name an file extension
	char directory[MAX_FILENAME + 1], filename[MAX_FILENAME + 1], extension[MAX_EXTENSION + 1];
	int status;
	
	(void) mode;
	(void) dev;
	
	//make sure directory/filename/extension start out as null strings, then parse
	directory[0] = '\0';		
	filename[0] = '\0';
	extension[0] = '\0';
	sscanf(path, "/%[^/]/%[^.].%s", directory, filename, extension);

	if(strlen(filename) > MAX_FILENAME || strlen(extension) > MAX_EXTENSION) {
		//file name and/or extension length too long - throw error
		return -ENAMETOOLONG;
	}

	if(strlen(filename) == 0) {
		//trying to create file in root directory - throw error
		return -EPERM;
	}

	if(getFileIndex(directory, filename, extension) != -1) {
		//file already exists - throw error
		return -EEXIST;
	} 

	status = createFile(directory, filename, extension);
	return status;
}

/*
 * Deletes a file
 */
static int cs1550_unlink(const char *path)
{
	//strings to hold the directory, file name an file extension
	char directory[MAX_FILENAME + 1], filename[MAX_FILENAME + 1], extension[MAX_EXTENSION + 1];
	int fileIndex, dirIndex, i;
	cs1550_directory_entry dir;
	cs1550_root_directory rootDir;
	cs1550_inode inode;

  //make sure directory/filename/extension start out as null strings, then parse
	directory[0] = '\0';		
	filename[0] = '\0';
	extension[0] = '\0';
	sscanf(path, "/%[^/]/%[^.].%s", directory, filename, extension);

	if(strlen(filename) == 0) {
		//path is a directory - throw error
		return -EISDIR;
	}

	fileIndex = getFileIndex(directory, filename, extension);
	if(fileIndex == -1) {
		//file does not exist - throw error
		return -ENOENT;
	}

	if(getDirectory(&dir, directory) == -1) {
		//directory does not exist - throw error
		return -ENOENT;
	}

	if(getRootDirectory(&rootDir) == -1) {
		//problem getting root
		return -1;
	}

	if(readBlock(&inode, dir.files[fileIndex].nStartBlock) == -1) {
		//problem reading the inode
		return -1;
	}

	i = 0;
	while(i < inode.children) {
		long blockNum = inode.pointers[i];
		if(i == (NUM_POINTERS_IN_INODE - 1)) {
			//have reached another inode - update inode and i
			if(readBlock(&inode, blockNum) == -1) {
				return -1;
			}
			i = 0;
		}
		else {
			i++;
		}
		unallocateBitmap(blockNum);
	}
	unallocateBitmap(dir.files[fileIndex].nStartBlock);

	for(i = fileIndex; i < (dir.nFiles - 1); i++) {
		//shift all files in the directory's file array down
		strcpy(dir.files[i].fname, dir.files[i + 1].fname);
		strcpy(dir.files[i].fext, dir.files[i + 1].fext);
		dir.files[i].fsize = dir.files[i + 1].fsize;
		dir.files[i].nStartBlock = dir.files[i + 1].nStartBlock;
	}
	dir.nFiles--;

	//update the directory entry
	dirIndex = getDirIndex(directory);
	if(dirIndex == -1) {
		//problem getting directory index
		return -1;
	}
	if(updateDirectory(&dir, rootDir.directories[dirIndex].nStartBlock) == -1) {
		//problem updating directory
		return -1;
	}

  return 0;
}

/* 
 * Read size bytes from file into buf starting from offset
 *
 */
static int cs1550_read(const char *path, char *buf, size_t size, off_t offset,
			  struct fuse_file_info *fi)
{
	//strings to hold the directory, file name an file extension
	char directory[MAX_FILENAME + 1], filename[MAX_FILENAME + 1], extension[MAX_EXTENSION + 1];
	cs1550_directory_entry dir;
	cs1550_inode inode;
	int fileIndex, i, inodeBlockNumber;
	long startBlock;
	off_t tempOffset;
	size_t sizeToCopy;

	(void) fi;

	//make sure directory/filename/extension start out as null strings, then parse
	directory[0] = '\0';		
	filename[0] = '\0';
	extension[0] = '\0';
	sscanf(path, "/%[^/]/%[^.].%s", directory, filename, extension);

	if(strlen(filename) == 0) {
		//path is a directory - throw error
		return -EISDIR;
	}

	fileIndex = getFileIndex(directory, filename, extension);
	if(fileIndex == -1) {
		//file does not exist - throw error
		return -ENOENT;
	}

	if(getDirectory(&dir, directory) == -1) {
		//directory does not exist - throw error
		return -ENOENT;
	}

	if(offset > dir.files[fileIndex].fsize) {
		//offset greater than file size - throw error
		return -EFBIG;
	}

	//read the inode from disk
	if(readBlock(&inode, dir.files[fileIndex].nStartBlock) == -1) {
		//problem reading the inode
		return -1;
	}

	//find the block from which to start reading - starts at first inode got from the directory
	tempOffset = offset;
	inodeBlockNumber = dir.files[fileIndex].nStartBlock;
	startBlock = offset / MAX_DATA_IN_BLOCK;
	while(startBlock > (NUM_POINTERS_IN_INODE - 2)) {
		tempOffset -= (NUM_POINTERS_IN_INODE - 1) * MAX_DATA_IN_BLOCK;
		startBlock = tempOffset / MAX_DATA_IN_BLOCK;
		
		//shift to the next inode
		inodeBlockNumber = inode.pointers[NUM_POINTERS_IN_INODE - 1];
		if(readBlock(&inode, inodeBlockNumber) == -1) {
			//problem reading
			return -1;
		}
	}
	i = startBlock;
	startBlock = inode.pointers[i];

	//start size at 0 and loop through the file
	size = 0;
	while(size < (dir.files[fileIndex].fsize - offset)) {
		cs1550_disk_block fileBlock;
		int dataIndex;

		if(size == 0) {
			//first time through the loop, start at offset
			dataIndex = offset % MAX_DATA_IN_BLOCK;
		}
		else {
			//subsequent times through loop will just want to read entire disk block
			dataIndex = 0;
		}

		//read the fileBlock from the disk
		if(readBlock(&fileBlock, startBlock) == -1) {
			//problem reading the block
			return -1;
		}

		//if fileBlock is an inode, update inode and get next fileBlock
		if(fileBlock.magic_number == 0xFFFFFFFF) {
			if(readBlock(&inode, startBlock) == -1) {
				//problem reading the inode
				return -1;
			}

			i = 0;
			startBlock = inode.pointers[i];
			//read the fileBlock from the disk
			if(readBlock(&fileBlock, startBlock) == -1) {
				//problem reading the block
				return -1;
			}
		}

		//determine the amount of data to read from the file
		if((dir.files[fileIndex].fsize - size) > MAX_DATA_IN_BLOCK) {
			sizeToCopy = MAX_DATA_IN_BLOCK;
		}
		else {
			sizeToCopy = dir.files[fileIndex].fsize - size;
		}

		//read data and update buf and size
		memcpy(buf, ((fileBlock.data) + dataIndex), sizeToCopy - dataIndex);
		buf += sizeToCopy;
		size += sizeToCopy;

		i++;
		startBlock = inode.pointers[i];
	}

	//check to make sure path exists
	//check that size is > 0
	//check that offset is <= to the file size
	//read in data
	//set size and return, or error

	return size;
}

/* 
 * Write size bytes from buf into file starting from offset
 *
 */
static int cs1550_write(const char *path, const char *buf, size_t size, 
			  off_t offset, struct fuse_file_info *fi)
{
	//strings to hold the directory, file name an file extension
	char directory[MAX_FILENAME + 1], filename[MAX_FILENAME + 1], extension[MAX_EXTENSION + 1];
	cs1550_directory_entry dir;
	cs1550_inode inode;
	cs1550_disk_block fileBlock;
	int fileIndex, i;
	long startBlock, currBlock, inodeBlockNumber;
	off_t tempOffset;
	size_t sizeToWrite;

	(void) fi;

	//make sure directory/filename/extension start out as null strings, then parse
	directory[0] = '\0';		
	filename[0] = '\0';
	extension[0] = '\0';
	sscanf(path, "/%[^/]/%[^.].%s", directory, filename, extension);

	if(strlen(filename) == 0) {
		//path is a directory - throw error
		return -EISDIR;
	}

	if(size <= 0) {
		//no data to write, throw error
		return -1;
	}

	fileIndex = getFileIndex(directory, filename, extension);
	if(fileIndex == -1) {
		//file does not exist - throw error
		return -ENOENT;
	}

	if(getDirectory(&dir, directory) == -1) {
		//directory does not exist - throw error
		return -EEXIST;
	}

	if(offset > dir.files[fileIndex].fsize) {
		//offset greater than file size - throw error
		return -EFBIG;
	}

	if(readBlock(&inode, dir.files[fileIndex].nStartBlock) == -1) {
		//problem reading the inode
		return -1;
	}

	//**************************************************************************
	//I will be daisy chaining inodes together. The last child of every inode
	//will point to another inode if the file needs to be bigger
	//I know it would be better to set up a tree of inodes, but I simply ran out of
	//time for writing this program. Also, it was not apparent from the project description
	//that the system needed to be able to handle files larger than what one inode could
	//contain. Finding out about that on the Friday before it is due means that the code is 
	//not going to be quite optimal.
	//**************************************************************************

	//find the data block to start writing at - starts at first inode got from the directory
	tempOffset = offset;
	inodeBlockNumber = dir.files[fileIndex].nStartBlock;
	startBlock = offset / MAX_DATA_IN_BLOCK;
	while(startBlock > (NUM_POINTERS_IN_INODE - 2)) {
		tempOffset -= (NUM_POINTERS_IN_INODE - 1) * MAX_DATA_IN_BLOCK;
		startBlock = tempOffset / MAX_DATA_IN_BLOCK;
		
		//shift to the next inode
		inodeBlockNumber = inode.pointers[NUM_POINTERS_IN_INODE - 1];
		if(readBlock(&inode, inodeBlockNumber) == -1) {
			//problem reading
			return -1;
		}
	}

	//get the disk blocks that contain the fileblocks to be read
	startBlock = inode.pointers[startBlock];
	currBlock = startBlock;

	if(readBlock(&fileBlock, startBlock) == -1) {
		//problem reading starting file block
		return -1;
	}

	//loop through the buffer and write data to file
	i = offset;
	while(i < (offset + size)) {
	//for(i = offset; i < (offset + size); i++) {
			if((i % MAX_DATA_IN_BLOCK) == 0 && i != 0) {
				//we have reached the end of the current block
				long newBlockStart;

				//write current block back to disk
				if(writeBlock(&fileBlock, currBlock) == -1) {
					//problem writing file
					return -1;
				}

				//get new block allocation and allocate
				newBlockStart = findFreeBlock();
				if(newBlockStart == -1) {
					//no free block found
					return -ENOSPC;
				}
				currBlock = newBlockStart;
				if(allocateBitmap(newBlockStart) == -1) {
					//unable to allocate
					return -1;
				}

				//reset the fileBlocks data to all 0s
				memset(&(fileBlock.data), 0, sizeof(MAX_DATA_IN_BLOCK));
				fileBlock.magic_number = 0xF113DA7A;

				//update inode
				if(inode.children == (NUM_POINTERS_IN_INODE - 1)) {
					//theres only one space left in the inode - need to create a new inode
					cs1550_inode newInode;
					long newInodeLocation = findFreeBlock();					
					if(newInodeLocation == -1) {
						//no free block found
						return -ENOSPC;
					}
					if(allocateBitmap(newInodeLocation) == -1) {
						//unable to allocate
						return -1;
					}
					
					newInode.magic_number = 0xFFFFFFFF;
					newInode.pointers[0] = newBlockStart;
					newInode.children = 1;
					if(writeBlock(&newInode, newInodeLocation) == -1) {
						//problem writing inode
						return -1;
					}
					
					inode.pointers[inode.children] = newInodeLocation;
					inode.children++;
					if(writeBlock(&inode, inodeBlockNumber) == -1) {
						//problem writing inode
						return -1;
					}

					//point inode to the new inode
					memcpy(&inode, &newInode, sizeof(cs1550_inode));
					inodeBlockNumber = newInodeLocation;
				}
				else {
					//still room on this inode
					inode.pointers[inode.children] = newBlockStart;
					inode.children++;
					if(writeBlock(&inode, inodeBlockNumber) == -1) {
						//problem writing inode
						return -1;
					}
				}
			}

			//determine how much data needs to be written this cycle
			if((i % MAX_DATA_IN_BLOCK) == 0) {
				sizeToWrite = size - i;
				if(sizeToWrite > MAX_DATA_IN_BLOCK) {
					sizeToWrite = MAX_DATA_IN_BLOCK;
				}
			}
			else {
				sizeToWrite = MAX_DATA_IN_BLOCK - (i % MAX_DATA_IN_BLOCK); 
			}

			//write data to fileBlock and update buf and loop counter accordingly
			//updating i to one less due to the for loop adding one after loop completion
			memcpy((fileBlock.data) + (i % MAX_DATA_IN_BLOCK), buf, sizeToWrite);
			buf += sizeToWrite;
			i += sizeToWrite;
	}

	//write the fileBlock to disk
	if(writeBlock(&fileBlock, currBlock) == -1) {
		//problem writing
		return -1;
	}

	//update the directory and write to disk
	if((offset + size) > dir.files[fileIndex].fsize) {
		//need to get the root directory to find where the directory is stored
		cs1550_root_directory rootDir;
		int dirIndex;
		if(getRootDirectory(&rootDir) == -1) {
			//problem getting root
			return -1;
		}
		dirIndex = getDirIndex(directory);
		if(dirIndex == -1) {
			//problem getting directory index 
			return -1;
		}

		dir.files[fileIndex].fsize = offset + size;
		if(updateDirectory(&dir, rootDir.directories[dirIndex].nStartBlock) == -1)  {
			//problem writing directory
			return -1;
		}
	}

	//check to make sure path exists
	//check that size is > 0
	//check that offset is <= to the file size
	//write data
	//set size (should be same as input) and return, or error

	return size;
}

/******************************************************************************
 *
 *  DO NOT MODIFY ANYTHING BELOW THIS LINE
 *
 *****************************************************************************/

/*
 * truncate is called when a new file is created (with a 0 size) or when an
 * existing file is made shorter. We're not handling deleting files or 
 * truncating existing ones, so all we need to do here is to initialize
 * the appropriate directory entry.
 *
 */
static int cs1550_truncate(const char *path, off_t size)
{
	(void) path;
	(void) size;

    return 0;
}


/* 
 * Called when we open a file
 *
 */
static int cs1550_open(const char *path, struct fuse_file_info *fi)
{
	(void) path;
	(void) fi;
    /*
        //if we can't find the desired file, return an error
        return -ENOENT;
    */

    //It's not really necessary for this project to anything in open

    /* We're not going to worry about permissions for this project, but 
	   if we were and we don't have them to the file we should return an error

        return -EACCES;
    */

    return 0; //success!
}

/*
 * Called when close is called on a file descriptor, but because it might
 * have been dup'ed, this isn't a guarantee we won't ever need the file 
 * again. For us, return success simply to avoid the unimplemented error
 * in the debug log.
 */
static int cs1550_flush (const char *path , struct fuse_file_info *fi)
{
	(void) path;
	(void) fi;

	return 0; //success!
}


//register our new functions as the implementations of the syscalls
static struct fuse_operations hello_oper = {
    .getattr	= cs1550_getattr,
    .readdir	= cs1550_readdir,
    .mkdir	= cs1550_mkdir,
	.rmdir = cs1550_rmdir,
    .read	= cs1550_read,
    .write	= cs1550_write,
	.mknod	= cs1550_mknod,
	.unlink = cs1550_unlink,
	.truncate = cs1550_truncate,
	.flush = cs1550_flush,
	.open	= cs1550_open,
};

//Don't change this.
int main(int argc, char *argv[])
{
	return fuse_main(argc, argv, &hello_oper, NULL);
}
