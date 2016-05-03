#include <stdio.h>

int main(void)
{
	//print the size of each variable type to the console.
	printf("int\t\t%d bytes\n", sizeof(int));
	printf("short\t\t%d bytes\n", sizeof(short));
	printf("long\t\t%d bytes\n", sizeof(long));
	printf("long long\t%d bytes\n", sizeof(long long));
	printf("unsigned int\t%d bytes\n", sizeof(unsigned int));
	printf("char\t\t%d bytes\n", sizeof(char));
	printf("float\t\t%d bytes\n", sizeof(float));
	printf("double\t\t%d bytes\n", sizeof(double));
	printf("long double\t%d bytes\n", sizeof(long double));

	return 0;
}
