/* Michael Bowen
   CIT 245
   Chapter 7 Assignment
   Dev-C++ 4.9.9.2 */
   
#include <cstdlib>
#include <iostream>
using namespace std;

class Counter {
public:
    //default constructor to initialize count to 0
    Counter();
    //one arg constructor to intialize count to num
    Counter(int num);
    //display the information for the Counter
    void display();
    //functions to increment. no-arg increments by 1, one-arg increments by num
    void inc();
    void inc(int num);
    //functions to decrement. no-arg decrements by 1, one-arg decrements by num
    void dec();
    void dec(int num);
private:
    //function to add the number of increments or decrements to numChanges
    void addChanges(int num);
    //holds the current count
    int count;
    //holds the current number of increments/decrements over all instances of Counter
    static int numChanges;
}; //ends Counter

//initialize Counter's static variable
int Counter::numChanges = 0;

int main() {
    //create c1 with an initial count of 0 and c2 with initial count of 200
    Counter c1, c2(200);
    
    //display current states of each counter
    cout << "Current state of C1(created with the default constructor)\n";
    c1.display();
    cout << "Current state of C2(created as C2(200))\n";
    c2.display();
    
    //increment c1 and display
    c1.inc(85);
    cout << "Current state of C1 after 85 inc()\n";
    c1.display();
    
    //decrement c2 and display
    c2.dec(242);
    cout << "Current state of C3 after 242 dec()\n";
    c2.display();
      
    //put in some new lines, pause the system and end the program.
    cout << endl;
    system("pause");
    return 0;
} //ends main

Counter::Counter(): count(0) { //body empty
} //ends default constructor

Counter::Counter(int num): count(num) { //body empty
} //ends 1 arg constructor

void Counter::display() {
    cout << "Current count: " << count << endl
         << "total increments and decrements " << numChanges << endl;
} //ends display

void Counter::inc() {
    count++;
    addChanges(1);
} //ends no arg inc

void Counter::inc(int num) {
    for(int i = 0; i < num; i++)
        count++;
    addChanges(num);
} //ends 1 arg inc

void Counter::dec() {
    count--;
    addChanges(1);
} //ends no arg dec

void Counter::dec(int num) {
    for(int i = 0; i < num; i++)
        count--;
    addChanges(num);
} //ends 1 arg dec

void Counter::addChanges(int num) {
    numChanges += num;
} //ends addChanges
