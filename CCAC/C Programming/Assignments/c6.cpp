/* Michael Bowen
   CIT 145
   Chapter 6 Assignment 
   Dev-C++ 4.9.9.2 */
   
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#define MAX 10000

int main(void) {
    /* variables for the limit, loop counters and number of primes */
    int limit, i, j, numPrimes = 0;
    /* array of prime numbers - initialize 0 and 1 to false */
    bool roots[MAX] = {0, 0};
    
    /* program header and input retrieval */
    printf("Uses the Sieve of Eratosthenes to find prime numbers.\n\n");
    printf("Enter the limit(up to %d): ", MAX);
    scanf("%d", &limit);
    printf("Primes up to %d\n\n", limit);
    
    /* load the prime array to be true on all counts but 0 and 1 */
    for(i = 2; i < limit; i++)
        roots[i] = true;
        
    /* use the Sieve algorithm to find the primes */
    for(i = 2; i < sqrt(limit); i++) {
        if(roots[i] == true) {
            for(j = (i*i); j < limit; j += i) {
                roots[j] = false;
            } /* ends inner for loop */
        } /* ends if */
    } /* ends outer for loop */
    
    /* print and tally totals */
    for(i = 2; i < limit; i++) {
        if(roots[i] == true) {
            printf("%5d", i);
            numPrimes++;
            if((numPrimes % 16) == 0)
                printf("\n");    
        } /* ends if */
    } /* ends for loop */
    
    printf("\n\nNumber of primes: %d", numPrimes);
    
    /* keep dos window open */
    printf("\n");
    system("pause");
    return 0;    
} /* ends main */
