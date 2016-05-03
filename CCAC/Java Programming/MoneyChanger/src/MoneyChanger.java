//shows the number of coins and dollars to return
//Created by Michael Bowen
//Created 02/06/2013
//JDK Version 7

import javax.swing.JOptionPane;
import java.util.Scanner;

public class MoneyChanger {

    public static void main(String[] args) {
        //declare variables and retrieve user input
        String response = JOptionPane.showInputDialog(null, "Enter amount of change: ");
        //Double tempNumber = Double.parseDouble(response);
        //System.out.println(tempNumber);
        int cents = (int) (Double.parseDouble(response) * 100);
               
        //int cents = (int) (Double.parseDouble(response) * 100);
        System.out.println(cents);
        int centsLeft = 0;
        int dollars = 0;
        int quarters = 0;
        int dimes = 0;
        int nickels = 0;
        int pennies = 0;
        
        //calculate change denominations
        dollars = cents / 100;
        centsLeft = cents % 100;
        quarters = centsLeft / 25;
        centsLeft = centsLeft % 25;
        dimes = centsLeft / 10;
        centsLeft = centsLeft % 10;
        nickels = centsLeft / 5;
        centsLeft = centsLeft % 5;
        pennies = centsLeft;
        
        //display output
        JOptionPane.showMessageDialog(null, "The total cents: " + cents + 
                "\nThe dollars are: " + dollars + "\nThe quarters are: " + quarters +
                "\nThe dimes are: " + dimes + "\nThe nickels are: " + nickels +
                "\nThe pennies are: " + pennies);
        
        //command line output (old)
        /*System.out.println("The total cents of change is " + cents);
        System.out.println("The dollars are " + dollars);
        System.out.println("The quarters are " + quarters);
        System.out.println("The dimes are " + dimes);
        System.out.println("The nickels are " + nickels);
        System.out.println("The pennies are " + pennies);
        */ 
                
    }//ends main
}//ends class
