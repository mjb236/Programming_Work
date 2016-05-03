/* Michael Bowen
   CIT 245
   Chapter 1 Assignment
   Dev-C++ 4.9.9.2 */
   
#include <cstdlib>
#include <iostream>
using namespace std;

int main() {
    //variables needed for calculations
    double input, guess, root, difference = 1.0;
    
    //prompt user for input and read input into the input variable
    cout << "Enter a number and I will apply the Babylonian square root algorithm"
         << "\nuntil I am within 0.001 of the correct answer.\n";
    cin >> input;
    
    //intialize the guess
    guess = input / 2.0;
    
    //peform the algorithm until the answer is within 0.001 of the real square root
    do {
        //perform calculations according to Babylonian algorithm
        root = input / guess;
        guess = (guess + root) / 2.0;
        
        //this will get the difference between the current guess and next guess
        //to determine whether or not the current guess is close enough to the 
        //actual square root, and thus terminate the loop.
        difference = guess - ((guess + (input / guess)) / 2.0);
        
        //show user the current guess
        cout << "guessing " << guess << endl;
    }while (difference > 0.001); // end do-while loop
    
    //output the root determined by the algorithm and check its square
    cout << "The Babylonian algorithm gives " << guess
         << "\nChecking: " << guess << " * " << guess << " = " << (guess * guess);   
    
    //put in some new lines, pause the system and end the program.
    cout << "\n\n";
    system("pause");
    return 0;
} //end main
