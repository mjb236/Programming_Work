//Michael Bowen
//CS0449 Tues/Thurs 4:00-5:15pm Rec Fri 1:00pm
//
//Lab9 - function pointers

#include <stdio.h>
#include <string.h>

struct record
{
	char name[100];
	int id;
};

int isIntEqual(void *first, void *second);
int isFloatEqual(void *first, void *second);
int isStringEqual(void *first, void *second);
int isStructRecordEqual(void *first, void *second);
int getFrequencyOf(void *item, void *array, int numEl, int size, int (*isEqual)(void *, void *));

// Return 1 if both integers are equal. Otherwise, return 0.
int isIntEqual(void *first, void *second)
{
	//cast the variables	
	int *firstInt, *secondInt;
	firstInt = (int *) first;
	secondInt = (int *) second;

	//compare values, return 1 if equal, 0 if unequal
	if(*firstInt == *secondInt)
	{
		return 1;
	}
	else
	{
		return 0;
	}
}

// Return 1 if both floats are equal. Otherwise, return 0.
int isFloatEqual(void *first, void *second)
{
	//cast the variables	
	float *firstFloat, *secondFloat;
	firstFloat = (float *) first;
	secondFloat = (float *) second;

	//compare values, return 1 if equal, 0 if unequal
	if(*firstFloat == *secondFloat)
	{
		return 1;
	}
	else
	{
		return 0;
	}
}

// Return 1 if both string are equal. Otherwise, return 0.
int isStringEqual(void *first, void *second)
{
	//cast the variables
	char **firstStr, **secondStr;
	firstStr = (char **) first;
	secondStr = (char **) second;

	//compare values, return 1 if equal, 0 if unequal
	if(strcmp(*firstStr, *secondStr))
	{
		return 0;
	}
	else
	{
		return 1;
	}	
}

// Return 1 if both struct record are equal (first's name
// is equal to second's name and first'id is equal to second's id.
// Otherwise, return 0.
int isStructRecordEqual(void *first, void *second)
{
	//cast the variables
	struct record *firstRec, *secondRec;
	firstRec = (struct record *) first;
	secondRec = (struct record *) second;

	if(isIntEqual(&(firstRec->id), &(secondRec->id)))
	{
		//get the names from the records		
		char *firstName = firstRec->name;
		char *secondName = secondRec->name;
		
		if(isStringEqual(&firstName, &secondName))
		{
			return 1;
		}
	}

	return 0;
}

// Return the frequency of an given item in a given array.
int getFrequencyOf(void *item, void *array, int numEl, int size, int (*isEqual)(void *, void *))
{
	//variables to store the frequency and the loop counter	
	int freq = 0;
	int counter = 0;

	//loop through the array looking for items equal to the item supplied
	for(counter = 0; counter < numEl; counter++)
	{
		if(isEqual(item, array + (size * counter)))
		{
			freq++;
		}
	}

	return freq;
}

int main(void)
{
	int intArray[] = {1,3,4,3,4,7,9,3,4,6,6,3,5,5,8,4,2,4,5,6,2,3,4,5,1}; // 25 elements
	int intItem = 4;
	float floatArray[] = {3.5, 2.6, 3.2, 7.1, 4.8, 3.3, 2.6, 2.2, 6.9, 2.5}; // 10 elements
	float floatItem = 2.6;
	char *strArray[] = {"abc", "bca", "cba", "abc", "bbc", "acb", "abc", "cbb", "aba", "abc"}; // 10 elements
	char str[] = "abc";
	char *strItem = str;
	struct record recordArray[] = {{"John", 1234}, {"Jack", 2345}, {"Jim", 3345}, {"Jack", 2345}, {"Julia", 4321}, {"Jack", 3333}}; // 6 elements
	struct record recordItem = {"Jack", 2345};

	printf("The frequency of 4 in intArray is %i\n", getFrequencyOf(&intItem, intArray, 25, sizeof(int), isIntEqual));
	printf("The frequency of 2.6 in floatArray is %i\n", getFrequencyOf(&floatItem, floatArray, 10, sizeof(float), isFloatEqual));
	printf("The frequency of abc in strArray is %i\n", getFrequencyOf(&strItem, strArray, 10, sizeof(char *), isStringEqual));
	printf("The frequency of Jack(2345) in recordArray is %i\n", getFrequencyOf(&recordItem, recordArray, 6, sizeof(struct record), isStructRecordEqual));

	return 0;
}

