/* Michael Bowen
   CIT 245
   Chapter 19 Assignment
   Dev-C++ 4.9.9.2 */
   
#include <string>
#include <map>
//#include <stdlib>
#include <iostream>
using namespace std;

int main() {
    map<string, int> socNumbers;

    //load the map with name keys and assign social security numbers
    cout << "Person 123-45-6790: Paul Brown" << endl;
    socNumbers["Paul Brown"] = 123456790;

    cout << "Person 123-45-6791: Mary Smith" << endl;
    socNumbers["Mary Smith"] = 123456791;

    cout << "Person 123-45-6789: John Smith" << endl;
    socNumbers["John Smith"] = 123456789;

    cout << "Person 123-45-6790: Lisa Brown" << endl << endl;
    socNumbers["Lisa Brown"] = 123456792;

    //iterator for moving through the map
    map<string, int>::const_iterator iter;

    //iterate through the map
    cout << "Iterating through list..." << endl;
    for(iter = socNumbers.begin(); iter != socNumbers.end(); iter++) {
        cout << iter->second << ": " << iter->first << endl;
    } //end for loop

    //search for specific names
    string name = "John Brown";
    iter = socNumbers.find(name);
    if(iter != socNumbers.end()) {
        cout << endl << iter-> first << " found " << iter->second << endl;
    } else {
        cout << endl << name << " not found" << endl;
    } // ends if-else

    name = "Paul Brown";
    iter = socNumbers.find(name);
    if(iter != socNumbers.end()) {
        cout << endl << iter-> first << " found " << iter->second << endl;
    } else {
        cout << endl << name << " not found" << endl;
    } // ends if-else

    cout << endl;
    system("pause");
    return 0;
} //ends main
