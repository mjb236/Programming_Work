/* Michael Bowen
   CIT 145
   Chapter 5 Assignment 
   Dev-C++ 4.9.9.2 */
   
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int tossCoin();
//function to randomly choose 1 or 0, 1 representing heads, 0 representing tails

int main(void) {
    /* variables for number of heads, tails, trials performed, max number of trials
       number of simulations to run and loop control variable for retrying simulation */
    int heads, tails, numTrial, maxTrials, numSim, retry = 1;
    /* variable to the percent of each heads or tails */
    double percent;
    /* constant for the number of simulations to run on each retry */
    const int SIMULATIONS = 6;
       
    /* seed the random number function with the system time */
    srand(time(NULL));
    
    do {
       /* initialize maxTrials */
       maxTrials = 10; 
       
       for(numSim = 1; numSim <= SIMULATIONS; numSim++) {
          /* number of coin flips being performed */
          printf("Trials: %d\n", maxTrials);
          /* set heads and tails to 0 */
          heads = 0;
          tails = 0;
          
          for(numTrial = 1; numTrial <= maxTrials; numTrial++) {
             /* add 1 to heads if tossCoin() returns 0, otherwise add 1 to tails */
             if(tossCoin() == 1)
                heads++;
             else
                tails++;
          } /* ends inner for */      
          
          /* calculate the percent of heads and display output */
          percent = (heads * 100.0) / maxTrials;
          printf("Head count:%8d  Percent Heads %.2f\n", heads, percent);
          /* calculate the percent of tails and display output */
          percent = 100.0 - percent;
          printf("Tail count:%8d  Percent Tails %.2f\n", tails, percent);
                   
          /* increase maxTrials ten fold and insert a new line */
          maxTrials *= 10;
          printf("\n");          
       } /* ends outer for */    
        
       /* determine if user would like to try again */
       printf("Try Again (1)  Exit (0): ");
       scanf("%d", &retry); 
    }while(retry != 0); /* end do-while */  
    
    /* keep dos window open */
    printf("\n");
    system("pause");
    return 0;
} /* ends main */

int tossCoin() {
    /* use rand() to get a random 0 or 1 */
    int roll = (rand() % 2);
    
    return roll;
} /* end tossCoin */
