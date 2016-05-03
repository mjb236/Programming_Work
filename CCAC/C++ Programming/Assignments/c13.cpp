/* Michael Bowen
   CIT 245
   Chapter 13 Assignment
   Dev-C++ 4.9.9.2 */
   
#include <iostream>
#include <cmath>
using namespace std;

//recursive function to solve the tower of hanoi puzzle. Accepts 5 int
//arguments. number of disks remaining, number of the start peg, number
//of the end peg, number of the extra peg and a call by reference int
//to count the total number of moves performed to solve.
void solveTowers(int disks, int start, int end, int spare, int &count);


int main() {
    //int variables for solving the towers of hanoi puzzle and number of moves
    int disks, start, end, spare, count, tryAgain = 1;

    //header
    cout << "This program will describe how to solve the Towers of Hanoi puzzle" 
         << endl << endl;
    
    do {
        //initialize variables
        start = 1;
        end = 2;
        spare = 3;
        count = 0;

        //get user input for number of disks
        cout << "Enter number of disks: ";
        cin >> disks;

        //call the solveTowers function with the starting information
        cout << "Source " << start << " Target " << end << " Temporary " << spare 
             << endl;
        //call solveTowers     
        solveTowers(disks, start, end, spare, count);

         //output the number of moves required
         cout << "2 to the " << disks << " power = " << (pow(2.0, disks * 1.0)) <<
         endl << "Number of moves " << count << endl << endl;

         //ask user to repeat process
         cout << "Continue? (1 = yes, 0 = no): ";
         cin >> tryAgain;
    }while(tryAgain == 1);
    
    //put in some new lines, pause the system and end the program.
    cout << endl;
    system("pause");
    return 0;
} //ends main


void solveTowers(int disks, int start, int end, int spare, int &count) {
    if(disks == 1) {
        //base  case - only 1 disk to move
        cout << "from " << start << " to " << end << endl;
        count++;
    } //ends if 
    else {
        //recursive case - more than one disk remain to move
        //call solveTowers with the spare and end pegs flipped
        solveTowers(disks - 1, start, spare, end, count);
 
        //move the current disk
        cout << "from " << start << " to " << end << endl;
        count++;

        //call solveTowers with start and spare pegs flipped
        solveTowers(disks - 1, spare, end, start, count);
    } //end else
} //ends solveTowers
