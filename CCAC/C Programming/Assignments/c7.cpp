/* Michael Bowen
   CIT 145
   Chapter 7 Assignment 
   Dev-C++ 4.9.9.2 */
   
#include <stdio.h>
#include <stdlib.h>
#define MAX 50

/* mystery function to implement and test */
int mystery(const char *s1, const char *s2);

int main(void) {
    /* pointer variables for char arrays */
    char *s1, *s2;
    /* character arrays for further mystery function testing */
    char *s3, *s4, *s5;
    
    /* create the char arrays */
    s1 = new char[MAX];
    s2 = new char[MAX];
    s3 = "Pointers are fun.";
    s4 = "Pointers are confusing.";
    s5 = "Pointers are fun.";
    
    /* program header explaining what mystery function should do */
    printf("This program uses a mystery function that should test whether or not "
           "\ntwo strings are equal. The user will enter two strings which will be "
           "\nstored in char arrays. The mystery function will accept pointers "
           "\nto the arrays and use pointer arithmetic to step through each and "
           "\ndetermine whether or not the two strings are equal. The function  "
           "\nreturns a 1 if they are the same or a 0 otherwise.\n\n");
           
    /* get user input */
    printf("Enter two strings of upto %d characters(no white space)\n", MAX);
    printf("Enter the first string: ");
    scanf("%s", s1);
    printf("Enter the second string: ");
    scanf("%s", s2);
    
    /* use the call the mystery function to deterimine output */
    if(mystery(s1, s2) == 1)
        printf("\nThe two strings you entered are the same.\n");
    else
        printf("\nThe two strings you entered are not the same.\n");   
    
    /* test s3, s4 and s5 */    
    printf("\nHere are three strings to be tested.\nString 1: \"%s\"\n"
           "String 2: \"%s\"\nString 3: \"%s\"\n\n", s3, s4, s5);
    
    /* print output based on the equality of the three strings */
    if(mystery(s3, s4) == 1)
        printf("Strings 1 and 2 are equal.\n");
    else
        printf("Strings 1 and 2 are not equal.\n");
        
    if(mystery(s3, s5) == 1)
        printf("Strings 1 and 3 are equal.\n");
    else
        printf("Strings 1 and 3 are not equal.\n");
        
    if(mystery(s4, s5) == 1)
        printf("Strings 2 and 3 are equal.\n");
    else
        printf("Strings 2 and 3 are not equal.\n");
           
    /* keep dos window open */
    printf("\n");
    system("pause");
    return 0;    
} /* ends main */

int mystery(const char *s1, const char *s2) {
    for( ; *s1 != '\0' && *s2 != '\0'; s1++, s2++) {
        if(*s1 != *s2) {
            return 0;
        } /* ends if */
    } /* ends for */
    
    return 1;
} /* ends mystery */
