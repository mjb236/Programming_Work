/* Michael Bowen
   CIT 245
   Chapter 12 Assignment
   Dev-C++ 4.9.9.2 */

#include <fstream>
#include <string>
#include <iostream>
#include <cstdlib>
using namespace std;

//function to get user advice for writing to the file.
string getAdvice();

int main() {
    //instream for input
    ifstream inStream;
    //open the file
    inStream.open("advice.txt");
    
    if(!inStream.fail()) {
        //file found
        cout << "Found Advice File." << endl << "Old Advice:" << endl;
                
        //write prior advice to the screen until end of file
        char next;
        inStream.get(next);
        while(! inStream.eof()) {
            cout << next;
            inStream.get(next);
        } //ends while
        
        //close inStream
        inStream.close();
        cout << endl;
        
        //open outStream for appending
        ofstream outStream;
        outStream.open("advice.txt", ios::app);
        //append new advice to file
        outStream << endl << getAdvice();
        //close outStream
        outStream.close();
    } //ends if file already exists
    else {
        //close prior stream and open a new file
        inStream.close();
        fstream inOutStream;
        //create new file
        inOutStream.open("advice.txt", ios::in | ios::out | ios::trunc); //create new file
        cout << "Could not open Advice File." << endl << "Assumption: first run"
                " - creating a new file..." << endl;
        //write output to new file
        inOutStream << getAdvice();
                
        //close file
        inOutStream.close();                          
    } //ends if file does not exist   
    
    //put in some new lines, pause the system and end the program.
    cout << endl;
    system("pause");
    return 0;    
} // ends main

string getAdvice() {
    string s;
    //get user input and return
    cout << "Enter your phrase for the next user:" << endl;
    getline(cin, s);
    
    return s;
} //ends getAdvice
