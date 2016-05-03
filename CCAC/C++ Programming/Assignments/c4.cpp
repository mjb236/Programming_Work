/* Michael Bowen
   CIT 245
   Chapter 4 Assignment
   Dev-C++ 4.9.9.2 */
   
#include <cstdlib>
#include <iostream>
using namespace std;

int gcd(int num, int den);
//returns the greatest common denominator of a fraction given its numerator and denominator
//accepts a numerator and denominator as arguments by value

void reduce(int& num, int& den);
//reduces the original numerator and denominator based on the greatest common denominator
//accepts a numerator and denominator as arguments by reference, allowing reduce() to change
//the original values passed in.

int main() {
    //variables for the numerator and demoninators. also loop control flag
    int numerator, denominator, tryAgain = 1;
    
    //explain program
    cout << "This program takes a numerator and denominator and reduces to lowest terms.\n\n";
    
    do{
         //prompt user for input and store
         cout << "Enter the numerator\n";
         cin >> numerator;
         cout << "Enter the denominator\n";
         cin >> denominator;
         
         //output the gcd
         cout << "\n greatest common denominator is " << gcd(numerator, denominator);
         //reduce the fraction
         reduce(numerator, denominator);
         //output reduced fraction
         cout << "\n your fraction reduced is " << numerator << "/" << denominator;
         
         //ask user if s/he wants to try again
         cout << "\n\nTry Again? (1 = yes, 0 = exit)\n";
         cin >> tryAgain;
    }while(tryAgain != 0);
    
    //put in some new lines, pause the system and end the program.
    cout << endl;
    system("pause");
    return 0;
} //end main

int gcd(int num, int den) {
    int temp;
    
    //find the gcd
    while(den != 0) {
        temp = den;
        den = num % den;
        num = temp;
    }//end while
    
    return num;
}//end gcd

void reduce(int& num, int& dem) {
     //variable to store the gcd of the arguments
     int gcdenom = gcd(num, dem);
     
     //reduce the numerator and denominator
     num = num / gcdenom;
     dem = dem / gcdenom;
}//ends reduce
        
