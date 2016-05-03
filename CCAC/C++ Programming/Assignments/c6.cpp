/* Michael Bowen
   CIT 245
   Chapter 6 Assignment
   Dev-C++ 4.9.9.2 */
   
#include <cstdlib>
#include <iostream>
#include <string>
using namespace std;

//structure to represent a position as an (x,y) coordinate
struct Position {
    int x;
    int y;
}; // ends Position

//class to represent a player
class Player {
public: 
    //functions to get and set player name
    void setName(string n) {name = n;}
    string getName() {return name;}
    //functions to get and set player password
    void setPassword(string p) {password = p;}
    string getPassword() {return password;}
    //functions to get and set player experience
    void setExp(int exp) {experience = exp;}
    int getExp() {return experience;}
    //functions to get and set player's (x,y) position
    void setPosition(Position p) {position = p;}
    Position getPosition() {return position;}
    //set the players inventory
    void setInventory(string* inv, const int size);
    //get the players inventory
    string *getInventory();
    //display player information to the user
    void display();
private:
    //class variables for name, password, experience, position and inventory
    string name;
    string password;
    int experience;
    Position position;
    string* inventory;
}; //ends Player

int main() {
    //objects to hold the players
    Player player1, player2, player3;
    //position structure to hold the player's position
    Position position;
    //inventory size
    const int INV_SIZE = 4;
    //string to sore player's inventory
    string* inventory = new string[INV_SIZE];
    
    //program header
    cout << "This program generates three player objects and displays them.\n\n";
        
    //create and display the first player
    player1.setName("Nemotacyst");
    player1.setPassword("obfuscator");
    player1.setExp(1098);
    position.x = 55689;
    position.y = 76453;
    player1.setPosition(position);
    inventory[0] = "sword";
    inventory[1] = "shield";
    inventory[2] = "food";
    inventory[3] = "potion";
    player1.setInventory(inventory, INV_SIZE);
    player1.display();
    
    //create and display the second player
    player2.setName("Omphaloskeps");
    player2.setPassword("obstreperous");
    player2.setExp(11456);
    position.x = 12;
    position.y = 5108;
    player2.setPosition(position);
    inventory[0] = "sword of magic";
    inventory[1] = "shield of power";
    inventory[2] = "mana potion";
    inventory[3] = "anti-fire potion";
    player2.setInventory(inventory, INV_SIZE);
    player2.display();
    
    //create and display the third player
    player3.setName("Tokamakfusion");
    player3.setPassword("toroid");
    player3.setExp(15678);
    position.x = 99;
    position.y = 108;
    player3.setPosition(position);
    inventory[0] = "axe of damage";
    inventory[1] = "shield of warding";
    inventory[2] = "strength potion";
    inventory[3] = "healing potion";
    player3.setInventory(inventory, INV_SIZE);
    player3.display();
    
    //put in some new lines, pause the system and end the program.
    cout << endl;
    system("pause");
    return 0;    
} //ends main

void Player::display() {
    //display player information
    cout << "Player Info -\n"
         << "Name:\t\t" << getName() << endl
         << "Password:\t" << getPassword() << endl
         << "Experience:\t" << getExp() << endl
         << "Position:\t" << getPosition().x << ", " << getPosition().y << endl
         << "Inventory:\n";
    
    for(int i = 0; i < 4; i++) {
        cout << " " << getInventory()[i] << endl;
    } //end for
    cout << endl;
} //end display

void Player::setInventory(string* inv, const int size) {
     //create the class inventory array.
     inventory = new string[size];
     //copy each element of the inventory to the class's inventory array
     for(int i = 0; i < size; i++) {
        inventory[i] = inv[i];
     } //end for
} //end setInventory

string* Player::getInventory() {
    return inventory;
} //end getInventory     
    
