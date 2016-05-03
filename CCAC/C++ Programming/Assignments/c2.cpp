/* Michael Bowen
   CIT 245
   Chapter 2 Assignment
   Dev-C++ 4.9.9.2 */
   
#include <cstdlib>
#include <iostream>
using namespace std;

int main() {
    //variables needed for calculation and loop control
    double buoyForce, radius, weight, volume;
    int recalc = 1;
    //constants needed for calcuations
    const double PI = 3.141592, WATER_WEIGHT = 62.4;
    
    cout << "This program computes Buoyant Force i nwater given sphere radius."
         << "\nBased on the weight of the sphere, it determines whether the "
         << "\nsphere floats or sinks.\n\n";
    
    do {
        //get and store the radius of the sphere
        cout << "Enter the radius of the sphere.\n";
        cin >> radius;
        cout << "You entered " << radius;
        cout << endl << endl;
        
        //get and store the weight of the sphere
        cout << "Enter the weight of the sphere.\n";
        cin >> weight;
        cout << "You entered " << weight;
        cout << endl << endl;
        
        //perform necessary calculations
        volume = (4.0 / 3.0) * PI * (radius * radius * radius);
        buoyForce = volume * WATER_WEIGHT;
        
        cout << "Buoyant Force = " << buoyForce;
        cout << endl;
        
        //decide whether the sphere floats or sinks and output accordingly
        if (buoyForce >= weight)
            cout << "Egads, it floats!\n";
        else
            cout << "It sunk...\n";
            
        //prompt user to recalculate or end the program
        cout << "Recalculate? (1 = yes, 0 = exit)\n";
        cin >> recalc;
        cout << endl;
    }while(recalc == 1); //end do-while loop
    
    //put in some new lines, pause the system and end the program.
    cout << endl;
    system("pause");
    return 0;
} //end main
