/* Michael Bowen
   CIT 145
   Chapter 14 Assignment
   Dev-C++ 4.9.9.2 */
   
#include <stdio.h>
#include <stdlib.h>
#include <stdarg.h>
#include <time.h>

/* variable arg list function to return the product of the integer arguments */
int product(int num, ...);

int main(void) {
    /* variables to store ints to multiply */
    int a, b, c, d, e;
    
    /* seed the random number generator */
    srand(time(NULL));
    
    /* initialize the varaibles to random numbers between 1 and 10 inclusive */
    a = rand() % 10 + 1;
    b = rand() % 10 + 1;
    c = rand() % 10 + 1;
    d = rand() % 10 + 1;
    e = rand() % 10 + 1;
    
    /* display output */
    printf("A = %d, B = %d, C = %d, D = %d, E = %d\n\n", a, b, c, d, e);
    printf("Product of A, B, C, D and E is %d\n", product(5, a, b, c, d, e));
    printf("Product of A, B, C and D is %d\n", product(4, a, b, c, d));
    printf("Product of A, B and C is %d\n", product(3, a, b, c));
    printf("Product of A and B is %d\n", product(2, a, b));
    printf("Product of C, D and E is %d\n", product(3, c, d, e));
    
    /* keep dos window open */
    printf("\n");
    system("pause");
    return 0;    
} /* ends main */

int product(int num, ...) {
    int result = 1;       /* product, initialized to 1 */
    int i;                /* loop counter */
    
    va_list ap;
    va_start(ap, num);
    
    //get the product of all the non-num parameters
    for(i = 1; i <= num; i++)
        result *= va_arg(ap, int);
    
    va_end(ap);
    
    return result;
} /* ends product */
