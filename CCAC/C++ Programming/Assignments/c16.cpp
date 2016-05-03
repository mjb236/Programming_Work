/* Michael Bowen
   CIT 245
   Chapter 16 Assignment
   Dev-C++ 4.9.9.2 */
   
#include <string>
#include <iostream>
using namespace std;

template<class T>
void search(const T array[], int first, int last, T key, bool& found, int& location);

template<class T>
void search(const T array[], int first, int last, T key, bool& found, int& location) {
    int mid;
    
    if(first > last) {
        found = false;
    } //end if
    else {
        mid = (first + last) / 2;
        if(key == array[mid]) {
            found = true;
            location = mid;
        } //end if 
        else if (key < array[mid]) {
            search(array, first, mid - 1, key, found, location);
        } //ends else if
        else if (key > array[mid]) {
            search(array, mid + 1, last, key, found, location);
        } //ends else if
    } //ends else
} //ends search

int main() {
    //constants for the number of items in the arrays
    const int NUM_INTS = 11;
    const int NUM_STRINGS = 4;
    //arrays to test the template binary search on
    int numArray[NUM_INTS] = {0, 1, 1, 2, 3, 5, 6, 13, 21, 32, 55};
    string stringArray[NUM_STRINGS] = {"head", "knees", "shoulders", "toes"};
    //variables to use while searching
    bool found = false;
    int location = -1;
    
    //print int test array
    cout << "Integer test array contains:\n";
    for(int i = 0; i < NUM_INTS; i++)
        cout << numArray[i] << " ";
    cout << endl;
    
    for(int i = -1; i < 8; i++) {
        search(numArray, 0, NUM_INTS - 1, i, found, location);
        if(found)
            cout << i << " is at index " << location << endl;
        else
            cout << i << " is not in the array.\n";
    } //end for loop
    
    //print string test array
    cout << "String test array contains:\n";
    for(int i = 0; i < NUM_STRINGS; i++)
        cout << stringArray[i] << " ";
    cout << endl;
    
    string key = "elbows";
    search(stringArray, 0, NUM_STRINGS - 1, key, found, location);
    if(found)
        cout << key << " is at index " << location << endl;
    else
        cout << key << " is not in the array.\n";
        
    key = "knees";
    search(stringArray, 0, NUM_STRINGS - 1, key, found, location);
    if(found)
        cout << key << " is at index " << location << endl;
    else
        cout << key << " is not in the array.\n";
    
    //put in some new lines, pause the system and end the program.
    cout << endl;
    system("pause");
    return 0;
} //ends main
