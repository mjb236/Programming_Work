/* Michael Bowen
   CIT 145
   Chapter 2, Assignment 2
   Dev-C++ 4.9.9.2 */
   
#include <stdio.h>
#include <stdlib.h>

int main(void) {
    /* declare variable used */
    int num1, num2, num3, smallest, largest, sum = 0;
    double average;
    
    /* get and store the first number */
    printf("Enter the first integer.\n");
    scanf("%d", &num1);
    /* initalize the smallest and largest variables to be the first number */
    smallest = num1;
    largest = num1;
    /* add first number to the sum */
    sum = sum + num1;
    
    /* get and store the second number */
    printf("Enter the second integer.\n");
    scanf("%d", &num2);
    /* test num2 against smallest and largest, reassigning if necessary */
    if (num2 > largest)
       largest = num2;
    if (num2 < smallest)
       smallest = num2;
    /* add second number to the sum */
    sum = sum + num2;
       
    /* get and store the third number */
    printf("Enter the third integer.\n");
    scanf("%d", &num3);
    /* test num3 against smallest and largest, reassigning if necessary */
    if (num3 > largest)
       largest = num3;
    if (num3 < smallest)
       smallest = num3;
    /* add third number to the sum */
    sum = sum + num3;
    
    /* calculate average */
    average = sum / 3.0;
    
    /* display output */
    printf("\nThe smallest number is %d\n", smallest);
    printf("The largest number is %d\n", largest);    
    printf("The sum of the numbers is %d\n", sum);
    printf("The average of the numbers is %g\n", average);
    
    /* keep dos window open */
    printf("\n");
    system("pause");
    return 0;
} /* end main */
