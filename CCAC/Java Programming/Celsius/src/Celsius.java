//Converts farenheit temps to celsius temps
//Michael Bowen
//Created February 6, 2013
//JDK Version 7

import javax.swing.JOptionPane;
import java.text.DecimalFormat;

public class Celsius {

    public static void main(String[] args) {
        /* 1. get farenheit temp
         * 2. convert farentheit to celsius - 5/9 * (farenheit - 32)
         * 3. display both temps */
        
        String input = JOptionPane.showInputDialog(null, "Please enter a temp in farenheit.");
        double farenheit = Double.parseDouble(input);
        double celsius = 5.0 / 9 * (farenheit - 32);
        
        DecimalFormat numFormat = new DecimalFormat("#0.00");
        JOptionPane.showMessageDialog(null, "The farenheit temperature is " + farenheit
                                      + "\nThe Celsius temperature is " + numFormat.format(celsius));  
        
        //command line print statments
        //System.out.println("The Farenheit temperature is " + farenheit);
        //System.out.printf("The Celsius temperature is %6.2f\n", celsius);
        //System.out.println("The Celsius temperature is " + celsius);
        
    }//ends main
}//ends Celsius
