/* Michael Bowen
   CIT 145
   Chapter 9 Assignment 
   Dev-C++ 4.9.9.2 */
   
#include <stdio.h>
#include <stdlib.h>

/* function to convert Farenheit to Celsius */
double toCelsius(int f);

int main(void) {
    /* farenheit variable/loop counter */
    int farenheit = 0;
    
    /* header */
    printf("%4c%9c\n", 'F', 'C');
    
    /* print out farenheit to celsius conversions */
    for(; farenheit <= 212; farenheit++) {
        printf("%4d%+9.3f\n", farenheit, toCelsius(farenheit));
    } /* ends for */
    
    /* keep dos window open */
    printf("\n");
    system("pause");
    return 0;    
} /* ends main */

double toCelsius(int f) {
    return (5.0 / 9.0 * (f - 32));
} /* ends toCelsius */
