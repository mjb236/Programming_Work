//Michael Bowen
//CS0449 Tues/Thurs 4:00-5:15  Rec: Fri 1:00pm
//
//lab7 with readInteger(), printString(), printInteger() and printFloat() implemented

#include <unistd.h>

int readInteger();

void printString(char *str);

void printInteger(int n);

void printFloat(float f);

//use the read() function to read a string version of an integer
//and return an actual integer value
int readInteger()
{
	char buffer[11];	//stores the string input
	int length;		//length of the string input
	int numberRead = 0;	//string converted to number
	int counter;		//loop counter
	int negative;		//flag for whether or not number is negative
	int power = 1;		//power of 10 to multiply each number by

	//read input
	length = read(STDIN_FILENO, buffer, 11);	//length 11 so that positive and negatives can be 9 digits long
	length--; 					//account for \n

	//check for negative
	if(buffer[0] == 45) 	//45 is ASCII for '-'
	{
		//if negative, set flag to true and set counter to account
		//for shorter actual number length
		negative = 1;
		counter = 2;
	}
	else
	{	
		//if not negative, set flag to false and start counter at 1
		negative = 0;
		counter = 1;
	}

	//set power of 10
	while(counter < length)
	{
		power *= 10;
		counter++;
	}
	
	//reset counter
	if(negative)
	{
		//start processing after the '-'
		counter = 1;
	}
	else
	{
		//start processing at start of string
		counter = 0;
	}	

	//read numbers, multiply by power, add to result
	while(counter < length)
	{
		int currNum = buffer[counter] - 48;	//account for ASCII
		numberRead += (currNum * power);
		power /= 10;
		counter++;
	}

	//return number
	if(negative)
	{
		return numberRead * -1;
	}
	else
	{
		return numberRead;
	}
}

//function that uses write() to print a string to the console
void printString(char *str)
{
	int length = 0;		//length of string
	int counter = 0;	//loop counter
	
	//find the length of the string
	while(*(str + length) != '\0')
	{
		length++;
	}

	//print string to console
	write(STDOUT_FILENO, str, length);
}

//function that uses write() to print an integer to the console
void printInteger(int n)
{
	char output[11];	//output array
	int numToPrint;		//number currently being processed
	int counter = 0;	//loop counter
	int length = 1;		//length of integer number/string
	int power = 1;		//power of 10

	//check for negative
	if(n < 0)
	{
		output[0] = 45;	//45 is ASCII for '-'

		//increment counter and length to account for '-'
		counter++;
		length++;
		
		//make n positive for further processing
		n = n * -1;
	}	

	//find power of 10
	while((n / power) >= 10)
	{
		power *= 10;
		length++;
	}			

	//find each number and add to string
	while(counter < length)
	{
		numToPrint = n / power;
		n -= (numToPrint * power);
		output[counter] = numToPrint + 48;
		power /= 10;
		counter++;
	}
	
	//print to console
	write(STDOUT_FILENO, output, length);	
}

//function that uses write() and printInteger() to print a floating point
//value to the console.
void printFloat(float f)
{
	int beforeDec;		//integer representation of number before decimal
	float remainder;	//float representation of number after decimal
	int afterDec;		//integer representation of number after decimal
	
	//get the integer before the decimal
	beforeDec = (int) f;
	
	//get the decimal part of the number
	//also adjust decimal to be positive - decimal part cannot be negative
	remainder = f - beforeDec;
	if(remainder < 0.0)
	{
		remainder *= -1;
	}

	//convert decimal into integer - 6 significant digits = multiply by 1,000,000
	afterDec = (int) (remainder * 1000000);

	//print first part of the float - the printInteger function will check for negative
	printInteger(beforeDec);
	write(STDOUT_FILENO, ".", 1);
	
	//print second part of the float - need special instructions to account for afterDec < 100,000
	if(afterDec >= 100000)
	{
		//if afterDec is >= 100000, we do not need leading zeroes
		printInteger(afterDec);
	}
	else
	{
		//if afterDec is < 10000, we need leading zeroes for precision
		char decimalOut[6] = {'0', '0', '0', '0', '0', '0'};
		int power = 1;
		int counter = 5;	//start at end of decimalOut array and work toward front

		//find power of 10
		while((afterDec / power) > 10)
		{
			power *= 10;
			counter--;
		}

		//copy numbers into array
		while(counter < 6)
		{
			int numToPrint = 0;
			numToPrint = afterDec / power;
			afterDec -= (numToPrint * power);
			decimalOut[counter] = numToPrint + 48;
			power /= 10;
			counter++;
		}

		//write the output string to the console including leading zeroes
		write(STDOUT_FILENO, decimalOut, 6);
	}
}

int main(void)
{
	int x, y;

	printString("Please enter an integer: ");
	x = readInteger();
	printString("Please enter another integer: ");
	y = readInteger();

	printInteger(x);
	printString(" + ");
	printInteger(y);
	printString(" = ");
	printInteger(x + y);
	printString(".\n");
	printInteger(x);
	printString(" - ");
	printInteger(y);
	printString(" = ");
	printInteger(x - y);
	printString(".\n");
	printInteger(x);
	printString(" * ");
	printInteger(y);
	printString(" = ");
	printInteger(x * y);
	printString(".\n");
	printInteger(x);
	printString(" / ");
	printInteger(y);
	printString(" = ");
	printFloat(((float) x) / y);
	printString(".\n");

	return 0;
}
