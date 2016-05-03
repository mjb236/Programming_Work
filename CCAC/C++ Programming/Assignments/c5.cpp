/* Michael Bowen
   CIT 245
   Chapter 5 Assignment
   Dev-C++ 4.9.9.2 */
   
#include <cstdlib>
#include <iostream>
#include <iomanip>
#include <ctime>
#include <algorithm>
using namespace std;

void fillArray(int a[], int size);
//fills an array (a) of size (size) with random numbers using the random() function

int random();
//returns a random integer from 1-10 inclusive

void initializeResults(int results[], int size);
//intializes the result array (results) to have 0 at each subscript. Needs to be called
//prior to the getResults() function to ensure results are accurate for each trial

void getResults(int results[], int a[], int aSize);
//iterates through the array (a) and adds 1 to the appropriate subscript of results
//for each occurance of each number 1-10. results will store the number of each occurance
//after this function is called, with the number of ones being stored in results[0],
//the number of twos being stored in results[1], etc.

void printArray(int a[], int size);
//prints output for the array of random numbers

void printResults(int results[], int size);
//prints output for the array of results


int main() {
    //constants for the size of the array of numbers and the array of summed results
    const int ARRAY_SIZE = 20, RESULT_SIZE = 10;
    //arrays to store the random numbers and how many of each number is produced    
    int a[ARRAY_SIZE], results[RESULT_SIZE];
    //loop control variable to determine whether the user would try again or not
    int tryAgain = 1;
    
    //seed rand() with the system time
    srand(time(NULL));
    
    //output header
    cout << "This program generates random numbers and tabulates the results.\n\n";
    cout << "Orignal List Sorted:\n\n";
    
    do {
        //populate the array with random numbers and use STL sort to sort
        fillArray(a, ARRAY_SIZE);
        sort(a, a + ARRAY_SIZE);
        //print the sorted array
        printArray(a, ARRAY_SIZE);
        
        //initialize the result array then get the number of each number 1-10 
        //in the random number array and store in the result array
        initializeResults(results, RESULT_SIZE);
        getResults(results, a, ARRAY_SIZE);
        //print the results of the random number array
        printResults(results, RESULT_SIZE);
        
        //prompt user to try again or exit
        cout << "Try Again? (1 = yes, 0 = exit)\n";
        cin >> tryAgain;
        cout << endl;
    }while(tryAgain != 0);
    
    //put in some new lines, pause the system and end the program.
    cout << endl;
    system("pause");
    return 0;    
} //ends main

void fillArray(int a[], int size) {
     //fill the array with random numbers
     for(int i = 0; i < size; i++) {
         a[i] = random();
     } //ends for
} //ends fillArray

int random() {
    //return a random integer from 1-10 inclusive
    return (rand() % 10 + 1);
} //ends random

void printArray(int a[], int size) {
    //print the array
    for(int i = 0; i < size; i++) {
        cout << "a[" << setw(2) << i << "]" << setw(4) << a[i] << endl;
    } //ends for 
} //ends printArray

void printResults(int results[], int size) {
    //results header
    cout << "\n N Count" << endl;
    
    //print the results
    for(int i = 0; i < size; i++) {
        cout << setw(2) << (i + 1) << ":" << setw(3) << results[i] << endl;
    } //ends for
} //ends printResults

void initializeResults(int results[], int size) {
     for(int i = 0; i < size; i++) {
         results[i] = 0;
     } //ends for    
} //ends initializeResults

void getResults(int results[], int a[], int aSize) {
     //fill the results array with the number of occurances of each number
     for(int i = 0; i < aSize; i++) {
         results[a[i] - 1]++;
     } //ends for        
} //ends getResults
