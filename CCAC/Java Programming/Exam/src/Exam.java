/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.*;
import java.text.*;

public class Exam {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int computerNumber = (int) (Math.random() * 100 + 1);
        System.out.println("The correct guess would be " + computerNumber);
        int userAnswer;
        
        String response = JOptionPane.showInputDialog(null, "Enter a guess between 1 and 100", "Guessing Game", 3);
        userAnswer = Integer.parseInt(response);
        
        if (userAnswer == computerNumber)
        {
            JOptionPane.showMessageDialog(null, "Your guess of " + userAnswer + " was correct. Great job!! \nGame over.");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Your guess of " + userAnswer + " was incorrect. The computer number was " + computerNumber + ".   \nGame over ");
        }
        
        System.exit(0);
        }
        
    
}