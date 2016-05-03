//Michael Bowen
//CS0449 Tue/Thur 4:00-5:15  Recitation Fri 1:00
//
//Decryption program to print out readable strings from a encrypted file

#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[])
{
	char *fileName;		//name of the file
	char *outString;	//output string
	char c;			//stores a character to test for readability
	int runLength = 0;	//number of consecutive readable characters
	int start = 0;		//start position of a run
	int i = 0;		//loop counter
	int eof;		//end of file marker
	FILE *fp;		//file pointer

	//get file name from the command line argument
	fileName = argv[1];

	//open file and set eof marker
	fp = fopen(fileName, "r");
	fseek(fp, 0, SEEK_END);
	eof = ftell(fp);
	fseek(fp, 0, SEEK_SET);

	do
	{
		//read character
		fread(&c, sizeof(char), 1, fp);
		if((c == 10) || ((c >= 31 && c <= 126)))
		{
			//find the length of the current run of printable characters
			while((c == 10) || ((c >= 32 && c <= 126)))
			{
				runLength++;
				fread(&c, sizeof(char), 1, fp);
			}

			//if the run was of length 4 or more, read those 
			//characters in as a string
			if(runLength >= 4)
			{
				//seek back to the start of current run, allocate memory, 
				//and read the string
				fseek(fp, start, SEEK_SET);
				outString = (char *) malloc(sizeof(char) * runLength + 1);
				fread(outString, sizeof(char), runLength, fp);
				
				//append null character to the end of the string just in case
				*(outString + runLength) = '\0';

				//print the string
				printf("%s", outString);
								
				//set start to the current position of the file pointer 
				//for the next run and reset run length
				start = ftell(fp);
				runLength = 0;

				//free the memory used by the outString
				free(outString);
			}
			else
			{
				//set start to the current position of the file pointer 
				//for the next run and reset run length
				start = ftell(fp);
				runLength = 0;
			}				
		}
		else
		{
			//increment start and reset run length
			start++;
			runLength = 0;
		}
	} while(ftell(fp) < eof); //while end of file not reached
	
	//close file
	fclose(fp);
	return 0;
}
