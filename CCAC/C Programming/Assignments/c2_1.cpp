/* Michael Bowen
   CIT 145
   Chapter 2, Assignment 1
   Dev-C++ 4.9.9.2 */
   
#include <stdio.h>
#include <stdlib.h>

int main(void) {
    /* declare variable used */
    int num1, num2;
    
    /* get and store the first number */
    printf("Enter the first integer.\n");
    scanf("%d", &num1);
    /* get and store the second number */
    printf("Enter the second integer.\n");
    scanf("%d", &num2);
    
    /* determine which number is larger and output accordingly */
    if (num1 > num2)
       printf("\n%d is the larger number.\n", num1);
    else if (num1 < num2)
       printf("\n%d is the larger number.\n", num2);
    else
       printf("\nThe numbers are the same.\n");
           
    /* keep dos window open */
    printf("\n");
    system("pause");
    return 0;
} /* end main */
