/* Michael Bowen
   CIT 245
   Chapter 3 Assignment
   Dev-C++ 4.9.9.2 */
   
#include <cstdlib>
#include <ctime>
#include <cmath>
#include <iostream>
#include <iomanip>
using namespace std;

int diRoll();
//uses a random number generator to simulate the roll of one di.


int main() {
    //variables for the total rolls, the number or rolls already performed, result
    //whether to try again and variables to hold the number of each result
    int totalRolls, numRolls, twos, threes, fours, fives, sixes, sevens, eights, 
        nines, tens, elevens, twelves, rollResult, tryAgain = 1;
    //variables for the odds of each result and the amount of error
    double twoOdds, threeOdds, fourOdds, fiveOdds, sixOdds, sevenOdds, eightOdds, 
           nineOdds, tenOdds, elevenOdds, twelveOdds, error;
    
    //seed the rand() function with time()
    srand(time(NULL));
    
    //output program's function        
    cout << "This program rolls two dice and tabulates the results.\n\n";
    
    do {
       //reset numRolls and number of rolls of each number
       numRolls = 0;
       twos = 0;
       threes = 0;
       fours = 0;
       fives = 0;
       sixes = 0;
       sevens = 0;
       eights = 0;
       nines = 0;
       tens = 0;
       elevens = 0;
       twelves = 0;
       
       //prompt user for number of rolls and store input
       cout << "Please enter the number of rolls you want: ";
       cin >> totalRolls;
       cout << endl;
       
       //perform the number of rolls specified
       while (numRolls < totalRolls) {
          //roll two di and store the summed result in rollResult
          rollResult = diRoll() + diRoll();
          
          //add 1 to the correct result based on the di rolls
          switch(rollResult) {
              case 2:
                  twos++;
                  break;
              case 3:
                  threes++;
                  break;
              case 4:
                  fours++;
                  break;
              case 5:
                  fives++;
                  break;
              case 6:
                  sixes++;
                  break;
              case 7:
                  sevens++;
                  break;
              case 8:
                  eights++;
                  break;
              case 9:
                  nines++;
                  break;
              case 10:
                  tens++;
                  break;
              case 11:
                  elevens++;
                  break;
              case 12:
                  twelves++;
                  break;
          } //end switch
          
          //increment the number of rolls
          numRolls++;
       } //end while
       
       //initialize odds
       twoOdds = (1.0/36.0) * totalRolls;
       threeOdds = (2.0/36.0) * totalRolls;
       fourOdds = (3.0/36.0) * totalRolls;
       fiveOdds = (4.0/36.0) * totalRolls;
       sixOdds = (5.0/36.0) * totalRolls;
       sevenOdds = (6.0/36.0) * totalRolls;
       eightOdds = (5.0/36.0) * totalRolls;
       nineOdds = (4.0/36.0) * totalRolls;
       tenOdds = (3.0/36.0) * totalRolls;
       elevenOdds = (2.0/36.0) * totalRolls;
       twelveOdds = (1.0/36.0) * totalRolls;
       
       //format doubles to show 4 digits after decimal point
       cout.setf(ios::fixed);
       cout.setf(ios::showpoint);
       cout.precision(4);
             
       //calculate error for each roll and display results
       //casts the odds for each to an integer for ease of display purposes
       cout << endl << setw(5) << "Sum" << setw(10) << "#Rolled" << setw(10) 
            << "Odds" << setw(10) << "%Error" << endl;
       error = fabs(((twoOdds - twos) / twoOdds) * 100.0);
       cout << endl << setw(5) << "2:" << setw(10) << twos << setw(10) 
            << (int)twoOdds << setw(10) << error;
       error = fabs(((threeOdds - threes) / threeOdds) * 100.0);
       cout << endl << setw(5) << "3:" << setw(10) << threes << setw(10) 
            << (int)threeOdds << setw(10) << error;
       error = fabs(((fourOdds - fours) / fourOdds) * 100.0);
       cout << endl << setw(5) << "4:" << setw(10) << fours << setw(10) 
            << (int)fourOdds << setw(10) << error;
       error = fabs(((fiveOdds - fives) / fiveOdds) * 100.0);
       cout << endl << setw(5) << "5:" << setw(10) << fives << setw(10) 
            << (int)fiveOdds << setw(10) << error;
       error = fabs(((sixOdds - sixes) / sixOdds) * 100.0);
       cout << endl << setw(5) << "6:" << setw(10) << sixes << setw(10) 
            << (int)sixOdds << setw(10) << error;       
       error = fabs(((sevenOdds - sevens) / sevenOdds) * 100.0);
       cout << endl << setw(5) << "7:" << setw(10) << sevens << setw(10) 
            << (int)sevenOdds << setw(10) << error;
       error = fabs(((eightOdds - eights) / eightOdds) * 100.0);
       cout << endl << setw(5) << "8:" << setw(10) << eights << setw(10) 
            << (int)eightOdds << setw(10) << error;
       error = fabs(((nineOdds - nines) / nineOdds) * 100.0);
       cout << endl << setw(5) << "9:" << setw(10) << nines << setw(10) 
            << (int)nineOdds << setw(10) << error;
       error = fabs(((tenOdds - tens) / tenOdds) * 100.0);
       cout << endl << setw(5) << "10:" << setw(10) << tens << setw(10) 
            << (int)tenOdds << setw(10) << error;
       error = fabs(((elevenOdds - elevens) / elevenOdds) * 100.0);
       cout << endl << setw(5) << "11:" << setw(10) << elevens << setw(10) 
            << (int)elevenOdds << setw(10) << error;
       error = fabs(((twelveOdds - twelves) / twelveOdds) * 100.0);
       cout << endl << setw(5) << "12:" << setw(10) << twelves << setw(10) 
            << (int)twelveOdds << setw(10) << error;
           
       //ask user if s/he wants to try again and get response
       cout << "\nTry again? (1 = Yes, 0 = Exit)\n";
       cin >> tryAgain;
       cout << endl;
    } while(tryAgain != 0);

    //put in some new lines, pause the system and end the program.
    cout << endl;
    system("pause");
    return 0;
} //end main

int diRoll() {
    //get a random number between 1 and 6 inclusive    
    int roll = (rand() % 6) + 1;
    
    return roll;
} //end diRoll()
