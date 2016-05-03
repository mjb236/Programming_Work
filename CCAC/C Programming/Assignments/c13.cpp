/* Michael Bowen
   CIT 145
   Chapter 13 Assignment
   Dev-C++ 4.9.9.2 */
   
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
using namespace std;

#define MAX_SIZE 10
//macro for SUMARRAY = uses and array and sum substitutions.
#define SUMARRAY(array, sum) sum=0; for(i=0; i<MAX_SIZE;i++){sum=sum+array[i];}

int main(void) {
    int intArray[MAX_SIZE];            /* array of ints */
    double doubleArray[MAX_SIZE];      /* array of doubles */
    int i = 0;                         /* loop counter */
    int sumInt = 0;                    /* sum of the int array */
    double sumDouble = 0.0;            /* sum of the double array */

    srand(time(NULL));       /* seed the random number generator */
    printf("This program uses a macro to sum arrays of integers and doubles.\n");

    /* load integer array with random numbers from 1-10 inclusive and print */
    printf("Integer array contains:\n");
    for(i = 0; i < MAX_SIZE; i++) {
        intArray[i] = ((rand() % 10) + 1);
        printf("%d ", intArray[i]);
    } /* ends for */

    printf("\n\n");

    /* use the SUMARRAY macro to find the sum of the int array */
    SUMARRAY(intArray, sumInt);
    printf("Sum of integer array is %d\n\n", sumInt);

    /* load double array with random numbers from 0.0 and 10.0 and print */
    printf("Double array contains:\n");
    for(i = 0; i < MAX_SIZE; i++) {
        doubleArray[i] = ((double)rand()/(double)(RAND_MAX)) * 10;
        printf("%.2f ", doubleArray[i]);
    } /* ends for */

    printf("\n\n");
    
    /* use the SUMARRAY macro to find the sum of the double array */
    SUMARRAY(doubleArray, sumDouble);
    printf("Sum of double array is %.2f\n", sumDouble);

    /* keep dos window open */
    printf("\n");
    system("pause");
    return 0;
} /* ends main */
