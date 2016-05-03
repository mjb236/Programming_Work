/* Michael Bowen
   CIT 145
   Chapter 4 Assignment 
   Dev-C++ 4.9.9.2 */
   
#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int main(void) {
    /* variables for principal, amount after interest, and interst rate */
    double principal, amount, rate;
    /* loops control variables for the number of years to compute interest and
       the number of different rates to compute interest for */
    int year, numCalcs;
    /* constants for the number of years to compute for and the number of interest
       rates to perform calculations for */
    const int MAX_YEARS = 10, MAX_CALCS = 6;
    
    /* initialize the interest rate and principal */
    rate = 0.05;
    principal = 1000.0;
    
    for(numCalcs = 1; numCalcs <= MAX_CALCS; numCalcs++) {
        /* output header */
        printf("Calculations for %.2f percent interest.\n", (rate * 100));
        printf("Year\tAmount\n");
        
        for(year = 1; year <= MAX_YEARS; year++) {
            /* calculate new amount for each year based on interest rate */
            amount = principal * pow(1.0 + rate, year); 
            
            /* display output */
            printf("%d\t%.2f\n", year, amount);
        } /* ends inner for */
        
        /* increment interest rate by 0.01 for next round of calculations */
        rate += 0.01;
        /* newline for legibility */
        printf("\n");
    } /* ends outer for */
    
    /* keep dos window open */
    printf("\n");
    system("pause");
    return 0;    
} /* ends main */
