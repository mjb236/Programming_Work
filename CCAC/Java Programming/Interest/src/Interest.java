//Calculates the interest of a loan using dialog boxes
//Created by Michael Bowen
//Created on 02/13/2013
//JDK Version 7

import javax.swing.JOptionPane; //for creating input/output boxes
import java.text.NumberFormat; //for formatting currency

public class Interest {

    public static void main(String[] args) {
        //get the loan amount from user and store in variable
        String dollarInput = JOptionPane.showInputDialog(null, "How many dollars do you wish " +
                "to borrow?");
        double dollars = Double.parseDouble(dollarInput);
        
        //get the interest rate from user and store in variable
        String rateInput = JOptionPane.showInputDialog(null, "What is the interest rate?");
        double rate = Double.parseDouble(rateInput);
        
        //get the length of the loan from user and store in variable
        String yearInput = JOptionPane.showInputDialog(null, "How many years will you take the " + 
                "loan?(whole number)");
        int years = Integer.parseInt(yearInput);
        
        //declare variable to store interest and calculate total interest
        double totalInterest = dollars * years * rate / 100.0;
        
        //create a number formatter to format numbers as currency
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        
        //print output in dialog box
        JOptionPane.showMessageDialog(null, "If you borrow " + currencyFormat.format(dollars) +
                " at an interest rate of " + rate + "\nfor " + years +
                " years, you will pay " + currencyFormat.format(totalInterest) + " in interest.");
        
    }//ends main
}//ends class
