//Michael Bowen
//CS449 Mon/Wed 4-5:15   Recitation: Friday 1:00pm

#include <stdio.h>

//function that copys a string from src to dest
void strcpy(char *dest, const char *src)
{
	int counter = 0;
	while(*(src + counter) != '\0')
	{
		*(dest + counter) = *(src + counter);
		counter++;
	}

	*(dest + counter) = '\0';
} //ends strcpy

//function that concatenates src to the end of dest
void strcat(char *dest, const char *src)
{
	int endOfDest = 0;
	int counter = 0;
	
	//find the end of dest
	while(*(dest + endOfDest) != '\0')
	{
		endOfDest++;
	}

	while(*(src + counter) != '\0')
	{
		*(dest + endOfDest + counter) = *(src + counter);
		counter++;
	}

	*(dest + endOfDest + counter) = '\0';
} //ends strcat

//function that reverses str
void strrev(char *str)
{
	int start = 0;
	int end = 0;

	//find the end of str
	while(*(str + end) != '\0')
	{
		end++;
	}

	//adjust end back down one since it is now pointing at the null char
	end--;

	//reverse the characters in the string
	while(start < end)
	{
		char temp = *(str + start);
		*(str + start) = *(str + end);
		*(str + end) = temp;

		start++;
		end--;
	}
} //ends strrev

//function that copies the substring from src from start(inc) to end(exclusive)
void substring(char *dest, int start, int end, char *src)
{
	int length = 0;
	char *temp = dest;	

	//end less than start - return empty string
	if(end < start)
	{
		*dest = '\0';
		return;				//CAN YOU DO THIS IN C?
	}

	//set start to 0 if start is less than 0
	if(start < 0)	
	{
		start = 0;
	}

	//set end to 0 if end is less than 0
	//also should return a null string since end is exclusive
	if(end < 0)
	{
		end = 0;
		*dest = '\0';
		return;
	}

	//find the length src
	while(*(src + length) != '\0')
	{
		length++;
	}

	//return null string if start is greater than the length of the src
	if(start > length)
	{
		*dest = '\0';
		return;
	}

	//set end to src length if end is greater than length
	if(end > length)
	{
		end = length;
	}

	//copy the substring
	while(start < end)
	{
		*temp = *(src + start);
		start++;
		temp++;
	}
	
	*temp = '\0';	
}

//main method
int main(void)
{
	char str1[] = "Hello ";
	char str2[] = "World!!!";
	char buffer1[100];
	char buffer2[100];

	strcpy(buffer1, str1);
	printf("%s\n", buffer1);
	strcat(buffer1, str2);
	printf("%s\n", buffer1);
	strrev(str2);
	printf("%s\n", str2);
	substring(buffer2, 0, 5, buffer1);
	printf("%s\n", buffer2);
	substring(buffer2, 6, 12, buffer1);
	printf("%s\n", buffer2);

	return 0;
}
