/* Michael Bowen
   CIT 245
   Chapter 8 Assignment
   Dev-C++ 4.9.9.2 */
   
#include <cstdlib>
#include <iostream>
#include <cmath>
using namespace std;

//class to represent an amount of money in US dollars
class Money {
public:
    //constructors for Money class - no arg sets money to 0, double arg sets money
    //to the amount passed in, 2 int arg sets dollars and cents to the values passed in,
    //1 int arg sets money to having dollars = to amount passed in and 0 cents
    Money();
    Money(double amount);
    Money(int numDollars, int numCents);
    Money(int numDollars);
    
    //functions to get the amount of money as double, the total dollars and total cents
    double getAmount() const {return (dollars + (cents * 0.01));}
    int getDollars() const {return dollars;}
    int getCents() const {return cents;}
    
    //overloaded operators for +, -, %, ==, < and >
    friend const Money operator +(const Money& amount1, const Money& amount2);
    friend const Money operator -(const Money& amount1, const Money& amount2);
    friend const Money operator %(const Money& amount, int percent);
    friend bool operator ==(const Money& amount1, const Money& amount2);
    friend bool operator <(const Money& amount1, const Money& amount2);
    friend bool operator >(const Money& amount1, const Money& amount2);
    //overloaded operators for << and >>
    friend ostream& operator <<(ostream& outputStream, const Money& amount);
    friend istream& operator >>(istream& inputStream, Money& amount);    
private:
    //class variables for dollars and cents
    int dollars;
    int cents;
    
    //functions to get the number of dollars and cents given a double, and round to
    //round cents to a whole number.
    int dollarsPart(double amount) const;
    int centsPart(double amount) const;
    int round(double number) const;
}; //ends Money


int main() {
    //variables for my and your money
    Money myAmount(10, 9), yourAmount;

    //get your amount of money - uses overloaded << and >>
    cout << "Enter an amount of money: ";
    cin >> yourAmount;
    cout << "Your amount is " << yourAmount << endl;
    cout << "My amount is " << myAmount << endl;
    
    //output if one person is richer or not
    if (myAmount == yourAmount)
        cout << "We have the same amounts.\n";
    else
        cout << "One of us is richer.\n";
    
    //output who has more money - uses overloaded operators < and >
    if (myAmount > yourAmount)
        cout << "I have more money than you.\n";
    else if (myAmount < yourAmount)
        cout << "You have more money than me.\n";
    
    //output 10% of yourAmount - uses overloaded %    
    cout << "10% of your money is: " << (yourAmount % 10) << endl;
    
    //output added and subtracted amounts - uses overloaded <<, + and -
    cout << yourAmount << " + " << myAmount << " equals " << (yourAmount + myAmount) << endl;
    cout << yourAmount << " - " << myAmount << " equals " << (yourAmount - myAmount) << endl;
    
    //put in some new lines, pause the system and end the program.
    cout << endl;
    system("pause");
    return 0;
} //ends main

Money::Money(): dollars(0), cents(0) { //body empty
} //ends no arg construtor

Money::Money(double amount): dollars(dollarsPart(amount)), cents(centsPart(amount)) { //body empty
} //ends 1 double arg constructor

Money::Money(int numDollars, int numCents): dollars(numDollars), cents(numCents) { //body empty
} //ends 2 int arg constructor

Money::Money(int numDollars): dollars(numDollars), cents(0) { //body empty
} //ends 1 int arg constructor

const Money operator +(const Money& amount1, const Money& amount2) {
    //get total cents for each amount
    int totalCents1 = amount1.cents + (amount1.dollars * 100);
    int totalCents2 = amount2.cents + (amount2.dollars * 100);
    
    //get the cents after addition and account for negative money
    int sumAllCents = totalCents1 + totalCents2;
    int absAllCents = abs(sumAllCents);
    
    //get dollars and cents based on the subtracted amount
    int finalDollars = absAllCents / 100;
    int finalCents = absAllCents % 100;
    
    //make finalDollars and finalCents negative if needed
    if (sumAllCents < 0) {
        finalDollars = -finalDollars;
        finalCents = -finalCents;
    } //ends if
    
    //return a Money object with the amount after amount1 + amount2
    return Money(finalDollars, finalCents);
} //ends +

const Money operator -(const Money& amount1, const Money& amount2) {
    //get total cents for each amount
    int totalCents1 = amount1.cents + (amount1.dollars * 100);
    int totalCents2 = amount2.cents + (amount2.dollars * 100);
    
    //get the cents after subtraction and account for negative money
    int subAllCents = totalCents1 - totalCents2;
    int absAllCents = abs(subAllCents);
    
    //get dollars and cents based on the subtracted amount
    int finalDollars = absAllCents / 100;
    int finalCents = absAllCents % 100;
    
    //make finalDollars and finalCents negative if needed
    if (subAllCents < 0) {
        finalDollars = -finalDollars;
        finalCents = -finalCents;
    } //ends if
    
    //return a Money object with the amount after amount1 - amount2
    return Money(finalDollars, finalCents);
} //ends -

const Money operator %(const Money& amount, int percent) {
    //total amount of cents in amount
    int totalCents = amount.cents + (amount.dollars * 100);
    //percentCents will hold the passed in percent of amount in cents
    double percentCents = totalCents / (percent * 1.0);
    //change percent cents back into a double number of dollars.cents
    percentCents /= 100;
    
    //return a Money object holding percent% of amount
    return Money(amount.dollarsPart(percentCents), amount.centsPart(percentCents));
} //ends %

bool operator ==(const Money& amount1, const Money& amount2) {
    //returns true if amount1 and amount2 have the same amount of money
    return((amount1.dollars == amount2.dollars) && (amount1.cents == amount2.cents));       
} //ends ==

bool operator >(const Money& amount1, const Money& amount2) {
     //returns true if amount1 is greater than amount2, false otherwise     
     if (amount1.dollars > amount2.dollars)
         return true;
     else if (amount1.dollars < amount2.dollars)
         return false;
     else {
          if(amount1.cents > amount2.cents)
              return true;
          else 
              return false;
     } //ends if-else
} //ends >

bool operator <(const Money& amount1, const Money& amount2) {
     //returns true if amount1 is less than amount2, false otherwise
     if (amount1.dollars < amount2.dollars)
         return true;
     else if (amount1.dollars > amount2.dollars)
         return false;
     else {
          if(amount1.cents < amount2.cents)
              return true;
          else 
              return false;
     } //ends if-else     
} //ends <

ostream& operator <<(ostream& outputStream, const Money& amount) {
    int absDollars = abs(amount.dollars);
    int absCents = abs(amount.cents);
    
    //account for negative money
    if (amount.dollars < 0 || amount.cents < 0)
        outputStream << "$-";
    else
        outputStream << '$';
    
    //output dollars
    outputStream << absDollars;
    
    //output cents
    if (absCents >= 10)
        outputStream << '.' << absCents;
    else
        outputStream << '.' << '0' << absCents;
        
    return outputStream;       
} //ends <<

istream& operator >>(istream& inputStream, Money& amount) {
    //read in the dollar sign, error otherwise
    char dollarSign;
    inputStream >> dollarSign;
    if (dollarSign != '$') {
        cout << "No dollar sign in Money input.\n";
        system("pause");
        exit(1);
    } //ends if
    
    //get the number part of the money input
    double moneyInput;
    inputStream >> moneyInput;
    //set amount's dollars and cents
    amount.dollars = amount.dollarsPart(moneyInput);
    amount.cents = amount.centsPart(moneyInput);
    
    return inputStream;     
} //ends >>

int Money::dollarsPart(double amount) const {
    return static_cast<int>(amount);
} //ends dollarsPart

int Money::centsPart(double amount) const {
    //holds the total number of cents in amount
    double totalCents = amount * 100;
    //gets the cents remaining after dollars are accounted for.
    int centsAfterDollars = (round(fabs(totalCents))) % 100;
    
    //adds a negative sign to centsAfterDollars if necessary
    if(amount < 0)
        centsAfterDollars = -centsAfterDollars;
        
    return centsAfterDollars;
} //ends centsPart

int Money::round(double number) const {
    return static_cast<int>(floor(number + 0.5));
} //ends round
