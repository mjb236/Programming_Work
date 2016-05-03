//Michael Bowen
//CS1550 Tues/Thurs 2:30pm Recitation: Fri 12:00pm
//
//Project 3 - virtual memory simulation

#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>
#include <time.h>
#include <string.h>

#define NUM_PAGES 0x100000			//2^20 pages in the system - is used with < so the values are 0x0 - 0xFFFFF
#define PAGE_NUM_SHIFT 12				//to get page number, need to shift the memory reference right by 12
#define VALID_TEST 1 << 20			//bitwise AND to determine if page is valid, bitwise OR to set as valid
#define REF_TEST 1 << 21				//bitwise AND to determine if page is referenced, bitwise OR to set as referenced
#define DIRTY_TEST 1 << 22			//bitwise AND to determine if page is dirty, bitwise OR to set as dirty
#define CLEAR_BITS 0xFF8FFFFF		//bitwise AND to clear the valid, ref and dirty bits
#define CLEAR_REF 0xFFDFFFFF		//bitwise AND to clear the ref bit
#define AGING_REF 0x80					//bitwise OR to push 1 onto the age of the frame
#define MAX_UINT 0xFFFFFFFF			//max value of unsigned int
#define FRAME_BITS 0xFFFFF			//bitwise AND to get the frame number from a page table entry
#define ZERO 0x0								//used to set bits to 
#define NINE_BITS 0x101					//sentinel for aging algorithm - is bigger than any 8 bit number

typedef uint32_t bit32;					//define the bit32 type to be an unsigned 32 bit int - should save some typing

//linked list structure that stores the memory references read from the file
//reference is the address, mode is R/W for read/write
struct memRef {
	bit32 reference;
	char mode;
	struct memRef *next;
};

//linked list structure that stores the time of the next use of a particular page
//used for OPT algorithm
struct nextUse {
	bit32 time;
	struct nextUse *next;			
	struct nextUse *tail;			//allows for fast adding to end of list for that particular page
};

//frame structure for OPT that includes the page number and a link to that page's next use
struct optFrame {
	bit32 pageNo;
	struct nextUse *next;
};

//circular queue structure for the clock algorithm
struct clockFrame {
	bit32 frameNo;
	bit32 pageNo;
	struct clockFrame *next;
};

//frames for use with the aging algorithm - includes age byte
struct agingFrame {
	bit32 pageNo;
	unsigned char age;
};

//read the memory reference list in from the file
void readFile(struct memRef **memList, const char *traceFile) {
	FILE *inFile;
	bit32 tempRef;
	char tempMode;
	struct memRef *curr;
	
	//open the filelength
	inFile = fopen(traceFile, "r");
	
	//read the memory references from the file - store in linked list
	//memList points to the start of the list
	*memList = (struct memRef *) malloc(sizeof(struct memRef));
	(*memList)->next = NULL;
	curr = *(memList);
	fscanf(inFile, "%x %c", &tempRef, &tempMode);
	curr->reference = tempRef;
	curr->mode = tempMode;
	while(fscanf(inFile, "%x %c", &tempRef, &tempMode) == 2) {
		curr->next = (struct memRef *) malloc(sizeof(struct memRef));
		curr = curr->next;
		curr->reference = tempRef;
		curr->mode = tempMode;
		curr->next = NULL;
	}

	//close the file
	fclose(inFile);
} //ends readFile

//returns the page number of the given memory reference
bit32 getPageNumber(bit32 ref) {
	return ref >> PAGE_NUM_SHIFT;
}

//returns true(non-zero) if the Reference Bit is set
bit32 isRef(bit32 *pageTable, bit32 pageNo) {
	return pageTable[pageNo] & REF_TEST;
}

//returns true(non-zero) if the Dirty Bit is set
bit32 isDirty(bit32 *pageTable, bit32 pageNo) {
	return pageTable[pageNo] & DIRTY_TEST;
}

//returns true(non-zero) if the Valid Bit is set
bit32 isValid(bit32 *pageTable, bit32 pageNo) {
	return pageTable[pageNo] & VALID_TEST;
}

//sets the REF and DIRTY bits appropriately on a page hit - page will already be valid by definition
void pageHitSetBits(bit32 *pageTable, bit32 pageNo, unsigned char mode) {
		pageTable[pageNo] = pageTable[pageNo] | REF_TEST;				//set the ref bit
		if(mode == 'W') {
			pageTable[pageNo] = pageTable[pageNo] | DIRTY_TEST;		//set the dirty bit
		}	
} //ends pageHitSetBits

//returns the frame number in which the pageNo is placed
bit32 getFrameNumber(bit32 *pageTable, bit32 pageNo) {
	return pageTable[pageNo] & FRAME_BITS;
} //ends getFrameNumber

//activate the page in the given frame
void placePageInFrame(bit32 *pageTable, bit32 pageNo, bit32 frameNo, unsigned char mode) {
	//update the page table entry
	pageTable[pageNo] = frameNo;													//base of page table entry is the frame number
	pageTable[pageNo] = pageTable[pageNo] | VALID_TEST;		//page is now valid
	pageTable[pageNo] = pageTable[pageNo] | REF_TEST;			//page is now referenced
	if(mode == 'W') {
		pageTable[pageNo] = pageTable[pageNo] | DIRTY_TEST;	//page is now dirty
	}		
} //ends placePageInFrame

//effectively evicts the page by unsetting the VALID, REF and DIRTY bits
void evictPage(bit32 *pageTable, bit32 index) {
	pageTable[index] = pageTable[index] & CLEAR_BITS;
} //ends evictPage

//read the file in for the opt algorithm
//also stores future uses of the pages in the future array.
void readOptFile(struct memRef **memList, struct nextUse **future, const char *fileName) {
	FILE *inFile;								//file pointer
	bit32 tempRef, i = 0;				//reference read from file and loop control
	char tempMode;							//read/write mode read from file
	struct memRef *curr;				//current reference being read
	struct nextUse *futureUse;	//used to add this read to future use
	
	//open the file
	inFile = fopen(fileName, "r");
	
	//read the memory references from the file - store in linked list
	//memList points to the start of the list
	*memList = (struct memRef *) malloc(sizeof(struct memRef));
	(*memList)->next = NULL;
	curr = *(memList);
	while(fscanf(inFile, "%x %c", &tempRef, &tempMode) == 2) {
		struct memRef *temp = (struct memRef *) malloc(sizeof(struct memRef));
		bit32 pageNo = getPageNumber(tempRef);
		temp->reference = tempRef;
		temp->mode = tempMode;
		curr->next = temp;
		curr = curr->next;
		
		futureUse = *(future + pageNo);
		if(futureUse->tail == NULL) {
			//first instance of this page - create node but no data. don't need to store the first instance
			futureUse->time = i;
			futureUse->next = NULL;
			futureUse->tail = futureUse;
		}
		else {
			futureUse->tail->next = (struct nextUse *) malloc(sizeof(struct nextUse));
			futureUse->tail = futureUse->tail->next;
			futureUse->tail->time = i;
			futureUse->tail->next = NULL;
			futureUse->tail->tail = NULL;				//tail only used for first node in the list, all other tails can be NULL
		}
		
		i++;
	}
	
	//remove first node of memList - bit sloppy but it works
	curr = *(memList);
	*memList = curr->next;
	free(curr);

	//close the file
	fclose(inFile);
}

//run the OPT algorithm simulation
void simulateOpt(bit32 numFrames, const char *fileName) {
	struct memRef *memList, *curr;										//list of memory references
	bit32 *pageTable;																	//page table
	struct nextUse **future;													//array of nextUse structures
	struct nextUse *tempFuture;												//used to deallocate nodes of future use
	bit32 numFaults = 0, numWrites = 0, numRefs = 0;	//stat counters
	struct optFrame *frames;													//frame structure
	bit32 i, pageNo, frameNo, usedFrames = 0;					//loop control, page number, frame number, and number of used frames
	
	//set up page table and ensure all bits are 0
	pageTable = (bit32 *) malloc(NUM_PAGES * sizeof(bit32));
	memset(pageTable, 0, sizeof(bit32) * NUM_PAGES);	
	
	//set up the future array
	future = (struct nextUse **) malloc(sizeof(struct nextUse *) * NUM_PAGES);
	for(i = 0; i < NUM_PAGES; i++) {
		struct nextUse *temp = (struct nextUse *) malloc(sizeof(struct nextUse));
		temp->time = MAX_UINT;
		temp->next = NULL;
		temp->tail = NULL;
		*(future + i) = temp;
	}
	
	//read the file in and store the references and future uses of the references
	readOptFile(&memList, future, fileName);

	//set up memory frames
	frames = (struct optFrame *) malloc(sizeof(struct optFrame) * numFrames);
	for(i = 0; i < numFrames; i++) {
		frames[i].pageNo = MAX_UINT;
		frames[i].next = *(future + i);
	}
	
	curr = memList;
	while(curr != NULL) {
		pageNo = getPageNumber(curr->reference);
		
		if(isValid(pageTable, pageNo)) {
			//page already already valid/in a frame - hit
			pageHitSetBits(pageTable, pageNo, curr->mode);	//set appropriate bits
			frameNo = getFrameNumber(pageTable, pageNo);		//get the frame number for this page
			tempFuture = future[pageNo];										//store current use for deallocation
			future[pageNo] = future[pageNo]->next;					//move the next use down one
			frames[frameNo].next = future[pageNo];					//update the future array with next use of this page
			free(tempFuture);																//deallocate
			printf("%i\thit\n", numRefs + 1);
		}
		else {
			//page is not in a frame yet - miss
			numFaults++;
			
			//compulsory miss - will only happen numFrames times
			if(usedFrames < numFrames) {
				frames[usedFrames].pageNo = pageNo;								//store the page number in the frame
				tempFuture = future[pageNo];											//store current use for deallocation
				future[pageNo] = future[pageNo]->next;						//move the next use of this page down
				frames[usedFrames].next = future[pageNo];					//update the link to future use in the frame table
				free(tempFuture);																	//deallocate the current use
				
				placePageInFrame(pageTable, pageNo, usedFrames, curr->mode);	//map current page to frame
				usedFrames++;		
				
				printf("%i\tpage fault - no eviction\n", numRefs + 1);
			}
			else {
				//capacity miss - evict page not used until furthest in future
				bit32 furthest = 0;								//index of the page with the furthest future use
				bit32 ptIndex;										//page number of the page used furthest in the future
				
				i = 0;
				if(frames[i].next == NULL) {
					//there is not future use of this page - we were lucky, can avoid loop
					furthest = i;
				}
				else {
					for(i = 1; i < numFrames; i++) {
						if(frames[i].next == NULL) {
							//there is no future use of this page - can safely remove
							furthest = i;
							//printf("\tNULL FOUND: furthest = %i", furthest);
							i = numFrames;		//exit loop
						}
						else {
							//find the furthest future use of a page
							if(frames[i].next->time > frames[furthest].next->time) {
								furthest = i;
							} 
						}
					}
				}
				
				//evict page in frame[furthest] - page used furthest in future
				ptIndex = frames[furthest].pageNo;
				if(isDirty(pageTable, ptIndex)) {
					numWrites++;
					printf("%i\tpage fault - evict dirty\n", numRefs + 1);
				}
				else {
					printf("%i\tpage fault - evict clean\n", numRefs + 1);
				}
				evictPage(pageTable, ptIndex);
				
				frames[furthest].pageNo = pageNo;				//store the page number in the frame
				tempFuture = future[pageNo];						//store current use for deallocation
				future[pageNo] = future[pageNo]->next;	//move future use down to next use
				frames[furthest].next = future[pageNo];	//update the frames reference to the future table
				free(tempFuture);												//deallocate memory
				
				placePageInFrame(pageTable, pageNo, furthest, curr->mode);	//map current page to frame
			}
		}
		
		//increment number of references and move to next reference
		numRefs++;
		curr = curr->next;
	}
	
	//print stats
	printf("\nNRU\n");
	printf("Number of frames:\t%i\n", numFrames);
	printf("Total memory accesses:\t%i\n", numRefs);
	printf("Total page faults:\t%i\n", numFaults);
	printf("Total writes to disk:\t%i\n\n", numWrites);
	
	//clean up memory
	free(frames);
	free(future);
} 

//run the clock simulation
void simulateClock(bit32 numFrames, const char *fileName) {
	struct clockFrame *frames, *currFrame;						//pointers to the frame list and the current frame
	bit32 *pageTable;																	//page table
	struct memRef *memList, *currRef;									//pointer to current memory reference
	bit32 numFaults = 0, numWrites = 0, numRefs = 0;	//stat counters
	bit32 usedFrames = 0, pageNo, i;									//number of used frames, loop counter
	
	//set up page table and ensure all bits are 0
	pageTable = (bit32 *) malloc(NUM_PAGES * sizeof(bit32));
	memset(pageTable, 0, sizeof(bit32) * NUM_PAGES);	
	
	//set up the frame clock
	for(i = 0; i < numFrames; i++) {
		struct clockFrame *temp = (struct clockFrame *) malloc(sizeof(struct clockFrame));
		temp->frameNo = i;
		temp->pageNo = ZERO;
		temp->next = NULL;
		
		if(i == 0) {
			frames = temp;					//if its the first time through, we've created the first frame
		}
		else {
			currFrame->next = temp;	//if it's not the first, temp frame should be pointed to by previous frame
		}
		currFrame = temp;					//update curr	
	}
	currFrame->next = frames;
	currFrame = frames;
	
	//read the file in and store the references and future uses of the references
	readFile(&memList, fileName);
	
	currRef = memList;
	while(currRef != NULL) {
		pageNo = getPageNumber(currRef->reference);
		
		if(isValid(pageTable, pageNo)) {
			//page already valid/in a frame - hit
			pageHitSetBits(pageTable, pageNo, currRef->mode);			//set appropriate bits
			
			printf("%i\thit\n", numRefs + 1);
		}
		else {
			//page is not in a frame yet - miss
			numFaults++;
			
			if(usedFrames < numFrames) {
				//compulsory miss - add the page to the next frame
				currFrame->pageNo = pageNo;
				currFrame = currFrame->next;
				
				placePageInFrame(pageTable, pageNo, currFrame->frameNo, currRef->mode);	//map current page to frame
				usedFrames++;
				
				printf("%i\tpage fault - no eviction\n", numRefs + 1);
			}
			else {
				//capacity miss - frames are full and page is not in a frame - will need to evict
				//find an unreferenced page, clearing referenced pages along the way
				while(isRef(pageTable, currFrame->pageNo)) {
					pageTable[currFrame->pageNo] = pageTable[currFrame->pageNo] & CLEAR_REF;
					currFrame = currFrame->next;
				}
				
				//currFrame now points to an unreferenced page - evict the frame
				if(isDirty(pageTable, currFrame->pageNo)) {
					numWrites++;
					printf("%i\tpage fault - evict dirty\n", numRefs + 1);
				}
				else {
					printf("%i\tpage fault - evict clean\n", numRefs + 1);
				}
				evictPage(pageTable, currFrame->pageNo);

				currFrame->pageNo = pageNo;																							//store the page number in the frame
				placePageInFrame(pageTable, pageNo, currFrame->frameNo, currRef->mode);	//map current page to frame

				//move curr to the next frame
				currFrame = currFrame->next;
			}
		}
		
		//increment number of references and move to next reference
		numRefs++;
		currRef = currRef->next;
	}	
	
	//print stats
	printf("\nClock\n");
	printf("Number of frames:\t%i\n", numFrames);
	printf("Total memory accesses:\t%i\n", numRefs);
	printf("Total page faults:\t%i\n", numFaults);
	printf("Total writes to disk:\t%i\n\n", numWrites);
	
	//clean up memory
	currFrame = frames;
	for(i = 0; i < numFrames; i++) {
		frames = currFrame->next;
		free(currFrame);
		currFrame = frames;
	}
} //ends clock simulation

//run the NRU algorithm simulation
void simulateNRU(bit32 numFrames, const char *fileName, bit32 refresh) {
	bit32 *frames;																			//list of frames
	bit32 *pageTable;																		//page table
	bit32 numFaults = 0, numWrites = 0, numRefs = 0;		//stat counters
	bit32 usedFrames = 0, pageNo, i;										//number of frames used and the page number, loop counter
	struct memRef *memList, *curr;											//pointer to current memory reference
	
	//set up page table and ensure all bits are 0
	pageTable = (bit32 *) malloc(NUM_PAGES * sizeof(bit32));
	memset(pageTable, 0, sizeof(bit32) * NUM_PAGES);	
	
	//set up the memory frames
	frames = (bit32 *) malloc(numFrames * sizeof(bit32));
	
	//seed the random number generator
	srand(time(NULL));
	
	//read the file in and store the references and future uses of the references
	readFile(&memList, fileName);
	
	curr = memList;
	while(curr != NULL) {
		//if we've hit the refresh mark, mark all pages unreferenced
		if((numRefs % refresh) == 0) {
			for(i = 0; i < usedFrames; i++) {
				pageTable[frames[i]] = pageTable[frames[i]] & CLEAR_REF;
			}
		}
		
		pageNo = getPageNumber(curr->reference);
				
		if(isValid(pageTable, pageNo)) {
			//page already already valid/in a frame - hit
			pageHitSetBits(pageTable, pageNo, curr->mode);			//set appropriate bits
			
			printf("%i\thit\n", numRefs + 1);
		}
		else {
			//page is not in a frame yet - miss
			numFaults++;
			
			//compulsory miss - will only happen numFrames times
			if(usedFrames < numFrames) {
				frames[usedFrames] = pageNo;		//store the page number in the frame
				
				placePageInFrame(pageTable, pageNo, usedFrames, curr->mode);	//map current page to frame
				usedFrames++;		
				
				printf("%i\tpage fault - no eviction\n", numRefs + 1);
			}
			else {
				//capacity miss - frames are full and page is not in a frame - will need to evict
				bit32 frameToEvict = -1;
				bit32 *cleanUnref, *dirtyUnref, *cleanRef;										//stores the indicies of each class of eviction candidate
				bit32 numCleanUnref = 0, numDirtyUnref = 0, numCleanRef = 0;	//stores the number of candidates in each class
				
				//set up eviction candidate arrays
				cleanUnref = (bit32 *) malloc(sizeof(bit32) * numFrames);
				dirtyUnref = (bit32 *) malloc(sizeof(bit32) * numFrames);
				cleanRef = (bit32 *) malloc(sizeof(bit32) * numFrames);
				
				//find the number and place of each eviction candidate
				for(i = 0; i < numFrames; i++) {
					if(!isRef(pageTable, frames[i]) && !isDirty(pageTable, frames[i])) {
						//page in current frame is not referenced and not dirty
						cleanUnref[numCleanUnref] = i;
						numCleanUnref++;
					}
					else if(!isRef(pageTable, frames[i]) && isDirty(pageTable, frames[i])) {
						//page in current frame is not referenced, but is dirty - second best solution
						dirtyUnref[numDirtyUnref] = i;
						numDirtyUnref++;
					}
					else if(isRef(pageTable, frames[i]) && !isDirty(pageTable, frames[i])) {
						//page is referenced, but not dirty - third best solution
						cleanRef[numCleanRef] = i;
						numCleanRef++;
					}
				}
				
				if(numCleanUnref > 0) {
					//choose random number
					i = rand() % numCleanUnref;
					frameToEvict = cleanUnref[i];
				}
				else if(numDirtyUnref > 0) {
					//choose random number
					i = rand() % numDirtyUnref;
					frameToEvict = dirtyUnref[i];
				}
				else if(numCleanRef > 0) {
					//choose random number
					i = rand() % numCleanRef;
					frameToEvict = cleanRef[i];
				}
				else {
					//all frames were referenced and dirty - choose any random frame
					frameToEvict = rand() % numFrames;
				}
				
				//free memory of arrays - frame to evict has been found
				free(cleanUnref);
				free(dirtyUnref);
				free(cleanRef);
				
				//evict the frame
				if(isDirty(pageTable, frames[frameToEvict])) {
					numWrites++;
					printf("%i\tpage fault - evict dirty\n", numRefs + 1);
				}
				else {
					printf("%i\tpage fault - evict clean\n", numRefs + 1);
				}
				evictPage(pageTable, frames[frameToEvict]);
				
				frames[frameToEvict] = pageNo;																	//store the page number in the frame
				placePageInFrame(pageTable, pageNo, frameToEvict, curr->mode);	//map current page to frame
			}
		}		
		
		//increment number of references and move to next reference
		numRefs++;
		curr = curr->next;
	} //ends while
		
	//print stats
	printf("\nNRU\n");
	printf("Number of frames:\t%i\n", numFrames);
	printf("Total memory accesses:\t%i\n", numRefs);
	printf("Total page faults:\t%i\n", numFaults);
	printf("Total writes to disk:\t%i\n\n", numWrites);
	
	//clean up memory
	free(frames);
} //ends NRU simulation

//run the aging simulation
void simulateAging(bit32 numFrames, const char *fileName, bit32 refresh) {
	struct agingFrame *frames;												//memory frames
	struct memRef *memList, *curr;										//pointer to the current memory reference
	bit32 *pageTable;																	//page table
	bit32 numRefs = 0, numFaults = 0, numWrites = 0;	//stat counters
	bit32 i, pageNo;																	//current page number and loop control
	bit32 usedFrames = 0;															//used frames - for compulsory misses
	
	//set up page table and ensure all bits are 0
	pageTable = (bit32 *) malloc(NUM_PAGES * sizeof(bit32));
	memset(pageTable, 0, sizeof(bit32) * NUM_PAGES);	
	
	//set up the memory frames
	frames = (struct agingFrame *) malloc(numFrames * sizeof(struct agingFrame));
	for(i = 0; i < numFrames; i++) {
		frames[i].pageNo = ZERO;
		frames[i].age = 0;
	}
	
	//read the file in and store the references and future uses of the references
	readFile(&memList, fileName);
	
	curr = memList;
	while(curr != NULL) {
		//if we have hit the end of the refresh period, shift the ref bit in to the age of each frame
		//also unreference each frame
		if(numRefs % refresh == 0) {
			for(i = 0; i < numFrames; i++) {
				frames[i].age = frames[i].age >> 1;
				if(isRef(pageTable, frames[i].pageNo)) {
					frames[i].age = frames[i].age | AGING_REF;
				}
				pageTable[frames[i].pageNo] = pageTable[frames[i].pageNo] & CLEAR_REF;
			}
		}
		
		pageNo = getPageNumber(curr->reference);
		
		if(isValid(pageTable, pageNo)) {
			//page already already valid/in a frame - hit
			pageHitSetBits(pageTable, pageNo, curr->mode);			//set appropriate bits
			
			printf("%i\thit\n", numRefs + 1);
		}
		else {
			//page is not in frame yet - miss
			numFaults++;
			
			//compulsory miss - will only happen numFrames times
			if(usedFrames < numFrames) {
				frames[usedFrames].pageNo = pageNo;										//store the page number in the frame
				
				//update the page table entry
				placePageInFrame(pageTable, pageNo, usedFrames, curr->mode);	//map current page to frame
				usedFrames++;		
				
				printf("%i\tpage fault - no eviction\n", numRefs + 1);
			}
			else {
				//capacity miss - frames are full and page is not in a frame - will need to evict
				bit32 oldestFrame = 0;
				unsigned short int min = NINE_BITS;  			
				for(i = 0; i < numFrames; i++) {
					//find the oldest frame 
					unsigned short int age = frames[i].age;
					if(isRef(pageTable, frames[i].pageNo)) {
						//if page is referenced, shift on the 9th bit
						age = age | AGING_REF;
					}
					if(age < min) {
						oldestFrame = i;
						min = age;
					}
				} //ends for
				
				//evict the oldest frame
				if(isDirty(pageTable, frames[oldestFrame].pageNo)) {
					numWrites++;
					printf("%i\tpage fault - evict dirty\n", numRefs + 1);
				}
				else {
					printf("%i\tpage fault - evict clean\n", numRefs + 1);
				}
				evictPage(pageTable, frames[oldestFrame].pageNo);
				frames[oldestFrame].pageNo = pageNo;									//store the page number in the frame
				
				//update the page table entry
				placePageInFrame(pageTable, pageNo, oldestFrame, curr->mode);	//map current page to frame
			}
		}

		//increment number of references and move to next reference
		numRefs++;
		curr = curr->next;
	}
		
	//print stats
	printf("\nAging\n");
	printf("Number of frames:\t%i\n", numFrames);
	printf("Total memory accesses:\t%i\n", numRefs);
	printf("Total page faults:\t%i\n", numFaults);
	printf("Total writes to disk:\t%i\n\n", numWrites);
	
	//clean up memory
	free(frames);
}

int main(int argc, char *argv[]) {
	char *cmdLine = "Command line format: ./vmsim –n <numframes> -a <opt|clock|nru|aging> [-r <refresh>] <tracefile>\0";
	char *algName, *traceFile;				//algorithm and files names
	bit32 numFrames, refresh;					//number of frames and refresh rate	
	
	//parse command line arguments
	if((argc < 6) || (argc > 8)) {
			printf("Incorrect usage.\n%s\n", cmdLine);
			return 1;
	}
	if(argc == 8) {
		if(strcmp("-r", argv[5])) {
			printf("Incorrect usage.\n%s\n", cmdLine);
			return 1;
		}
		else {
			refresh = (int) strtol(argv[6], NULL, 10);
			traceFile = argv[7];
		}		
	}
	else if(argc == 6) {
		traceFile = argv[5];
	}
	if(strcmp("-n", argv[1]) || strcmp("-a", argv[3])) {
			printf("Incorrect usage.\n%s\n", cmdLine);
			return 1;
	} 
	else {
		numFrames = (int) strtol(argv[2], NULL, 10);
		algName = argv[4];
	}
	
	//call the correct algortihm and run simulation
	if(strcmp(algName, "opt") == 0) {
		simulateOpt(numFrames, traceFile);
	}
	else if(strcmp(algName, "clock") == 0) {
		simulateClock(numFrames, traceFile);
	}
	else if(strcmp(algName, "nru") == 0) {
		//make sure that user provided a refresh rate
		if(refresh > 0) {
			simulateNRU(numFrames, traceFile, refresh);
		}
		else {
			printf("Invalid refresh rate provided. Exiting program.\n");
		}
	}
	else if(strcmp(algName, "aging") == 0) {
		//make sure that user provided a refresh rate
		if(refresh > 0) {
			simulateAging(numFrames, traceFile, refresh);
		}
		else {
			printf("Invalid refresh rate provided. Exiting program.\n");
		}
	}
	else {
		printf("Something went wrong...\n");
	}
	
	return 0;
}