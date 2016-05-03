/* Michael Bowen
   CIT 245
   Chapter 10 Assignment
   Dev-C++ 4.9.9.2 */
   
#include <cstdlib>
#include <cstdio>
#include <iostream>
using namespace std;

class WrapArrayDeep {
public:
    //default and copy constructors, and destructor for WrapArrayDeep
    WrapArrayDeep();
    WrapArrayDeep(const WrapArrayDeep& wad);
    ~WrapArrayDeep();
    //functions to print and alter the array
    void printArray();
    void alterArray();
    //return the capacity of the array
    int getCapacity() const {return capacity;}
    //overloaded = operator
    WrapArrayDeep& operator =(const WrapArrayDeep& wad);    
private:
    //class variables for the dynamic character array and capacity of the array
    char *pch;
    int capacity;
};

class WrapArrayShallow {
public:
    //default constructor and destructor for WrapArrayShallow
    WrapArrayShallow();
   ~WrapArrayShallow();
    //functions to print and alter the array
    void printArray();
    void alterArray();
    //return the capacity of the array
    int getCapacity() {return capacity;}    
private:
    //class variables for the dynamic character array and capacity of the array
    char *pca;
    int capacity;
};


void partOne();
void partTwo();

int main() {
    //part 1
    partOne();
         
    //part 2
    partTwo();    
         
    //put in some new lines, pause the system and end the program.
    cout << endl;
    system("pause");
    return 0;    
} // ends main

//part one of Chapter 10 assignment - pointers/dereference with simple data type
void partOne() {
    //part 1
    //int i and pointer to int pi
    int i = 7;
    int *pi;
    pi = &i;
    
    //output for i and pi
    cout << "This program section uses three variables" << endl
         << "i = " << i << ", pi a pointer to i and ppi a pointer to pi" << endl
         << "\npi = " << pi << endl << "dereference pi = " << *pi << endl
         << "address of pi = " << &pi << endl << "address of i = " << &i << endl;
    
    //create pointer to pointer ppi     
    int **ppi;
    ppi = &pi;
    
    //output including ppi
    cout << "\nppi = " << ppi << endl << "deference of ppi = " << *ppi << endl
         << "address of ppi = " << &ppi << endl << "double dereference of ppi = "
         << **ppi << endl << endl;
} //ends partOne

//part two of Chapter 10 assignment - array wrapping/copy constructor/destructors
void partTwo() {
    //part two header
    cout << "This section instantiates a wrapper class for a dynamic array of 5"
         << " elements" << endl;
        
    //declare and create wraparraydeep1 using default constructor and print
    WrapArrayDeep wad1, *wad2;
    cout << "WrapArrayDeep 1" << endl;
    wad1.printArray();
    
    //create wraparraydeep2 using copy constructor and print
    wad2 = new WrapArrayDeep(wad1);
    cout << "WrapArrayDeep 2 using the copy constructor on 1" << endl;
    wad2->printArray();
    
    //alter wad1 and print
    wad1.alterArray();
    cout << "after changing the contents of WrapArrayDeep1, 1 and 2 = " << endl;
    wad1.printArray();
    wad2->printArray();
    
    //declare and create WrapArrayShallow1 using default constructor and print
    WrapArrayShallow was1, *was2;
    cout << endl << "Now doing the same thing with WrapArrayShallow" << endl << endl
         << "WrapArrayShallow 1" << endl;
    was1.printArray();
    
    //create was2 using the unwritten copy constructor for WrapArrayShallow and print
    was2 = new WrapArrayShallow(was1);
    cout << "WrapArrayShallow 2 created using the copy constructor on 1" << endl;
    was2->printArray();
    
    //alter the array of was1 and print both arrays
    was1.alterArray();
    cout << "after changing the contents of WrapArrayShallow1, 1 and 2 = " << endl;
    was1.printArray();
    was2->printArray();
    cout << endl;
    
    //for some reason the destructor for the pointer versions of the classes is not being
    //called at the end of the partTwo function. So I am manually deleting the pointers
    //for wad2 and was2. wad1 and wad2 will call destructor automatically once 
    //the partTwo function finishes
    delete wad2;
    //delete was2;  - the delete call to was2 sometimes causes the program to crash
} //ends partTwo

//member functions for WrapArray Deep

WrapArrayDeep::WrapArrayDeep() {
    //create char array
    capacity = 5;
    pch = new char[capacity];

    //initialize char array using pointer arithmetic
    for(int i = 0; i < capacity; i++)
        *(pch + i) = (97 + i);
} //ends WrapArrayDeep constructor

WrapArrayDeep::WrapArrayDeep(const WrapArrayDeep& wad):capacity(wad.getCapacity()) {
    //allocate memory for the character array
    pch = new char[capacity];
    
    //copy char array using pointer arithmetic
    for(int i = 0; i < capacity; i++)
        *(pch + i) = wad.pch[i];                          
} //ends WrapArrayDeep copy constructor

WrapArrayDeep::~WrapArrayDeep() {
    cout << "calling destructor for WrapArrayDeep\n";
    //return memory used by the char array
    delete [] pch;                                    
} //ends WrapArrayDeep destructor

void WrapArrayDeep::printArray() {
    //print contents of array using array notation
    for(int i = 0; i < capacity; i++)
        cout << pch[i] << " ";
    
    cout << endl;
} //ends printArray

void WrapArrayDeep::alterArray() {
    //alter array contents
    for(int i = 0; i < capacity; i++) 
        *(pch + i) = (123 + i);
} //ends alterArray

WrapArrayDeep& WrapArrayDeep::operator =(const WrapArrayDeep& wad) {
    if(capacity != wad.capacity) {
        delete [] pch;
        pch = new char[wad.capacity];
    } //ends if
    
    capacity = wad.capacity;
    for(int i = 0; i < capacity; i++)
        pch[i] = wad.pch[i];
        
    return *this;
} //ends operator =

//member functions for WrapArrayShallow

WrapArrayShallow::WrapArrayShallow() {
    //create char array
    capacity = 5;
    pca = new char[capacity];

    //initialize char array using array notation
    for(int i = 0; i < capacity; i++)
        pca[i] = (97 + i);
} //ends WrapArrayShallow constructor

WrapArrayShallow::~WrapArrayShallow() {
    cout << "calling destructor for WrapArrayShallow\n";
    //return memory used by the char array
    delete [] pca;                                    
} //ends WrapArrayShallow destructor

void WrapArrayShallow::printArray() {
    //print contents of array using array notation
    for(int i = 0; i < capacity; i++)
        cout << *(pca + i) << " ";
    
    cout << endl;
} //ends printArray

void WrapArrayShallow::alterArray() {
    //alter array contents
    for(int i = 0; i < capacity; i++) 
        pca[i] = (123 + i);
} //ends alterArray
