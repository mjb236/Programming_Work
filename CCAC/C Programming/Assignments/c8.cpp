/* Michael Bowen
   CIT 145
   Chapter 8 Assignment 
   Dev-C++ 4.9.9.2 */
   
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <ctype.h>
#include <string.h>
#define MAX 35

int main(void) {
    /* string array declarations */
    const char *article[5] = {"the", "one", "a", "some", "any"};
    const char *noun[5] = {"boy", "girl", "dog", "town", "car"};
    const char *verb[5] = {"drove", "jumped", "walked", "ran", "skipped"};
    const char *preposition[5] = {"to", "from", "over", "under", "on"};
    /* output string */
    char sentence[MAX] = "";
    /* loop counter */
    int i;
    
    /* seed the random number generator */
    srand(time(NULL));
    
    /* create and output 20 random sentences */
    for(i = 0; i < 20; i++) {
        /* create sentence, with spaces and a period. change first letter to upper */
        strcat(sentence, article[(rand() % 5)]);
        strcat(sentence, " ");
        strcat(sentence, noun[(rand() % 5)]);
        strcat(sentence, " ");
        strcat(sentence, verb[(rand() % 5)]);
        strcat(sentence, " ");
        strcat(sentence, preposition[(rand() % 5)]);
        strcat(sentence, " ");
        strcat(sentence, article[(rand() % 5)]);
        strcat(sentence, " ");
        strcat(sentence, noun[(rand() % 5)]);
        strcat(sentence, ".");
        sentence[0] = toupper(sentence[0]);
        
        /* output sentence */
        printf("%s\n", sentence);
        
        /* clear sentence */
        memset(sentence, 0, MAX);
    } /* ends for */        
    
    /* keep dos window open */
    printf("\n");
    system("pause");
    return 0;    
} /* ends main */
