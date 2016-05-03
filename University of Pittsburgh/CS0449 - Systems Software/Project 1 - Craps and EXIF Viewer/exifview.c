//Michael Bowen
//CS449 - Tues/Thurs 4:00-5:15  Rec: Fri 1:00-1:50
//
//Program to view EXIF data for JPEG files

#include <stdio.h>
#include <stdlib.h>

//structure for storing a two byte header.
struct header
{
	unsigned char first;
	unsigned char second;
};

//structure for storing the information in the app1 segment before the tiff header.
struct app1Segment
{
	struct header head;
	unsigned short int length;
	char exifString[4];
	struct header nul;	
};

//structure storing the tiff header in the jpeg file
struct tiffHeader
{
	unsigned char endian[2];
	unsigned short int version;
	int offset;
};

//structure for storing a tiff tag
struct tiffTag
{
	unsigned short int identifier;
	unsigned short int dataType;
	int numItems;
	int offset;
};

//structure for storing exif data discovered
struct exif
{
	char *manufacturer;
	char *model;
	unsigned int xResNumer;
	unsigned int xResDenom;
	unsigned int yResNumer;
	unsigned int yResDenom;
	char *resUnit;
	unsigned int exposureNumer;
	unsigned int exposureDenom;
	unsigned int fstopNumer;
	unsigned int fstopDenom;
	unsigned short iso;
	char *date;
	unsigned int focalNumer;
	unsigned int focalDenom;
	unsigned int width;
	unsigned int height;
};

//function that verifies that the file is valid format
//returns 1 if file is valid, 0 if not
int verifyFile(FILE *fp, int *app1Location)
{
	struct header head;
	fread(&head, sizeof(struct header), 1, fp);
	(*app1Location) += sizeof(struct header);

	if((head.first != 0xFF) || (head.second != 0xD8))
	{
		printf("File not supported.\n");
		return 0;
	}
	
	return 1;
} //ends verifyFile

//cycle through the file looking for the app1Location
//app1Location will be updated via pointer
void findApp1(FILE *fp, int *app1Location)
{
	struct header head;	
	int found = 0;
	
	while(!found)
	{
		//read two bytes from the current app1Location
		fseek(fp, *app1Location, SEEK_SET);
		fread(&head, sizeof(struct header), 1, fp);

		if((head.first == 0xFF) && (head.second == 0xE1))
		{
			found = 1;
		}
		else
		{
			(*app1Location)++;
		}
	} //ends while
} //ends findApp1

//function that reads the number of tiff tags and returns as unsigned short int
unsigned short int getNumTags(FILE *fp, int app1Location, int startOffset)
{
	unsigned short int numTags;
	fseek(fp, app1Location + 10 + startOffset, SEEK_SET);
	fread(&numTags, sizeof(unsigned short int), 1, fp);

	return numTags;
} //ends getNumTags

//function that processes the tiffTags read in from the file
//assigns data to the exif structure as appropriate
void processTiffTag(FILE *fp, struct tiffTag *tag, struct exif *exifInfo, int app1Location)
{
	char *c;

	switch(tag->identifier)
	{
		case 0x010F: //manufacturer string
			fseek(fp, app1Location + 10 + tag->offset, SEEK_SET);
			c = (char *) malloc(sizeof(char) * tag->numItems);
			fread(c, sizeof(char), tag->numItems, fp);
			exifInfo->manufacturer = c;			
			break;
		case 0x0110: //camera model string
			fseek(fp, app1Location + 10 + tag->offset, SEEK_SET);
			c = (char *) malloc(sizeof(char) * tag->numItems);
			fread(c, sizeof(char), tag->numItems, fp);
			exifInfo->model = c;
			break;
		case 0x011A: //x res - width
			fseek(fp, app1Location + 10 + tag->offset, SEEK_SET);
			fread(&(exifInfo->xResNumer), sizeof(unsigned int), 1, fp);
			fread(&(exifInfo->xResDenom), sizeof(unsigned int), 1, fp);
			break;
		case 0x011B: //y res - height
			fseek(fp, app1Location + 10 + tag->offset, SEEK_SET);
			fread(&(exifInfo->yResNumer), sizeof(int), 1, fp);
			fread(&(exifInfo->yResDenom), sizeof(int), 1, fp);
			break;
		case 0x0128: //unit
			if(tag->offset == 2)
			{
				exifInfo->resUnit = "Inch";
			}
			else if(tag->offset == 3)
			{
				exifInfo->resUnit = "Centimeter";
			}
			else
			{
				exifInfo->resUnit = "Unit unknown";
			}
			break;
		case 0xA002: //width in pixels
			exifInfo->width = tag->offset;
			break;
		case 0xA003: //height in pixels
			exifInfo->height = tag->offset;
			break;
		case 0x8827: //ISO value
			exifInfo->iso = tag->offset;
			break;
		case 0x829A: //exposure speed
			fseek(fp, app1Location + 10 + tag->offset, SEEK_SET);
			fread(&(exifInfo->exposureNumer), sizeof(unsigned int), 1, fp);
			fread(&(exifInfo->exposureDenom), sizeof(unsigned int), 1, fp);
			break;
		case 0x829D: //fstop
			fseek(fp, app1Location + 10 + tag->offset, SEEK_SET);
			fread(&(exifInfo->fstopNumer), sizeof(unsigned int), 1, fp);
			fread(&(exifInfo->fstopDenom), sizeof(unsigned int), 1, fp);
			break;
		case 0x920A: //focal length
			fseek(fp, app1Location + 10 + tag->offset, SEEK_SET);
			fread(&(exifInfo->focalNumer), sizeof(unsigned int), 1, fp);
			fread(&(exifInfo->focalDenom), sizeof(unsigned int), 1, fp);
			break;
		case 0x9003: //date string
			fseek(fp, app1Location + 10 + tag->offset, SEEK_SET);
			c = (char *) malloc(sizeof(char) * tag->numItems);
			fread(c, sizeof(char), tag->numItems, fp);
			exifInfo->date = c;
			break;
	} //ends switch
} //ends processTiffTag

//function that prints the exif information
void printExif(struct exif *exifInfo)
{
	printf("\nManufacturer\t: %s\n", exifInfo->manufacturer);
	printf("Model\t\t: %s\n", exifInfo->model);
	printf("X Res/Unit\t: %i/%i\n", exifInfo->xResNumer, exifInfo->xResDenom);
	printf("Y Res/Unit\t: %i/%i\n", exifInfo->yResNumer, exifInfo->yResDenom);
	printf("Res Unit\t: %s\n", exifInfo->resUnit);
	printf("Exposure Time\t: %i/%i second\n", exifInfo->exposureNumer, exifInfo->exposureDenom);
	printf("F-stop\t\t: %i/%i\n", exifInfo->fstopNumer, exifInfo->fstopDenom);
	printf("ISO\t\t: ISO %i\n", exifInfo->iso);
	printf("Date Taken\t: %s\n", exifInfo->date);
	printf("Focal Length\t: %i/%i mm\n", exifInfo->focalNumer, exifInfo->focalDenom);
	printf("Width\t\t: %i pixels\n", exifInfo->width);
	printf("Height\t\t: %i pixels\n\n", exifInfo->height);
}//ends printExif

int main(int argc, char *argv[])
{
	char *fileName;
	FILE *fp;
	struct tiffTag *tag;
	struct app1Segment *app1;
	struct tiffHeader *tiffHead;
	struct exif *exifInfo;
	int app1Location = 0;
	int startOffset = 0;
	int counter = 0;
	unsigned short int count = 0;

	//retrieve the file name and set up the file pointer
	fileName = argv[1];	
	fp = fopen(fileName, "r");

	//verify the file
	if(!verifyFile(fp, &app1Location))
	{
		return 0;
	}

	//find the APP1 marker and read the app1 segment
	findApp1(fp, &app1Location);
	app1 = (struct app1Segment *) malloc(sizeof(struct app1Segment));
	fseek(fp, app1Location, SEEK_SET);	
	fread(app1, sizeof(struct app1Segment), 1, fp);
	
	//verify Exif string
	if((app1->exifString[0] != 'E') || (app1->exifString[1] != 'x') || 
	   (app1->exifString[2] != 'i') || (app1->exifString[3] != 'f'))
	{
		//pring error message and exit program if Exif string incorrect
		printf("Tag not found.\n");
		return 0;
	}

	//read the tiff header and verify endianness
	tiffHead = (struct tiffHeader *) malloc(sizeof(struct tiffHeader));
	fread(tiffHead, sizeof(struct tiffHeader), 1, fp);
	if((tiffHead->endian[0] != 'I') && (tiffHead->endian[1] != 'I'))
	{
		//print error message and exit program if endianness incorrect
		printf("We do not support this endianness.\n");
		return 0;
	}

	//get startOffset
	startOffset = tiffHead->offset;

	//get number of tiff tags
	count = getNumTags(fp, app1Location, startOffset);

	//read and process the first set of tiff tags
	tag = (struct tiffTag *) malloc(sizeof(struct tiffTag));
	exifInfo = (struct exif *) malloc(sizeof(struct exif));
	tag->identifier = 0;
	while((counter < count) && (tag->identifier != 0x8769))
	{
		fseek(fp, app1Location + 10 + startOffset + 2 + (counter * sizeof(struct tiffTag)), SEEK_SET);		
		fread(tag, sizeof(struct tiffTag), 1, fp);
		processTiffTag(fp, tag, exifInfo, app1Location);
		counter++;
	}

	//tag should contain the exif sub block address. if it doesn't
	//print info and exit program	
	if(tag->identifier != 0x8769)
	{
		printExif(exifInfo);
		return 0;
	}

	//process the exif sub block
	count = getNumTags(fp, app1Location, tag->offset);
	startOffset = tag->offset;
	counter = 0;
	tag->identifier = 0;
	while(counter < count)
	{
		fseek(fp, app1Location + 10 + startOffset + 2 + (counter * sizeof(struct tiffTag)), SEEK_SET);		
		fread(tag, sizeof(struct tiffTag), 1, fp);
		processTiffTag(fp, tag, exifInfo, app1Location);
		counter++;
	}
	
	//print exif info
	printExif(exifInfo);

	//free memory
	free(app1);
	free(tiffHead);	
	free(tag);
	free(exifInfo);

	//close the file
	fclose(fp);

	return 0;
} //ends main
