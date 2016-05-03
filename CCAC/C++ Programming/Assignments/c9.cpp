/* Michael Bowen
   CIT 245
   Chapter 9 Assignment
   Dev-C++ 4.9.9.2 */
   
#include <cstdlib>
#include <cstdio>
#include <iostream>
#include <stack>
using namespace std;

int main() {
    //constant for string length, variable for the string of chars and loop control
    const int MAX_SIZE = 80;
    char* s = (char *)malloc(MAX_SIZE);
    int tryAgain = 1;
    //stack of type char to hold the characters of the string
    stack<char> charStack;
    
    //program header
    cout << "This program reverses a string using the STL stack\n";
            
    while(tryAgain == 1) {
        //prompt for input
        cout << "Enter your string of less than " << MAX_SIZE << " characters "
             << "followed by an Enter\n";
        cin.getline(s, MAX_SIZE + 1, '\n');
        
        //add the characters to the stack
        int i = 0;
        while((s[i] != '\0') && (i < MAX_SIZE)) {
            charStack.push(s[i]);
            i++;        
        } //end while
        
        //remove the items from the stack and put them back in the char array in reverse order
        i = 0;        
        while((s[i] != '\0') && (i < MAX_SIZE)) {
            s[i] = charStack.top();
            charStack.pop();
            i++;        
        } //end while
        
        //output the reversed string
        cout << s << endl;        
        
        //try again?
        tryAgain = 0;
        cout << "Enter another? 1 = continue. Anything else to stop.\n";
        cin >> tryAgain;
        getchar();                   
    } //ends while
    
    //free memory allocated for the character string
    free(s);
    
    //put in some new lines, pause the system and end the program.
    cout << endl;
    system("pause");
    return 0;    
} //ends main
