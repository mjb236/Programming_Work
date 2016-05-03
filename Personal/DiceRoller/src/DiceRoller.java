import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DiceRoller extends JFrame implements ActionListener {
    private JLabel diceTypeLabel, numDiceLabel, rollResultLabel;
    private JTextField diceTypeText, numDiceText;
    private JButton rollButton, clearButton;
    private Container contentPane;
    
    public DiceRoller() {
        createGUI();
    }//ends constructor
    
    //this method sets up the graphical user interface
    public void createGUI() {
        contentPane = getContentPane();
        contentPane.setLayout(null);
        
        //ask user what kind of dice they want to roll
        diceTypeLabel = new JLabel();
        diceTypeLabel.setText("What kind of dice are you rolling?");
        diceTypeLabel.setLocation(10, 10);	
        diceTypeLabel.setSize(200, 25);	
        diceTypeLabel.setForeground(Color.BLACK);
        contentPane.add(diceTypeLabel);

        //create text box to hold user answer for dice type
        diceTypeText = new JTextField();	
        diceTypeText.setText("");	
        diceTypeText.setToolTipText("Enter number of sides or % for percentile.");
        diceTypeText.setLocation(210, 10);	//set location of JTextFfield
        diceTypeText.setSize(50, 25);   //set size of JTextField
        contentPane.add(diceTypeText);
        
        //ask user what how dice they want to roll
        numDiceLabel = new JLabel();
        numDiceLabel.setText("How many dice are you rolling?");
        numDiceLabel.setLocation(23, 50);	
        numDiceLabel.setSize(200, 25);	
        numDiceLabel.setForeground(Color.BLACK);
        contentPane.add(numDiceLabel);
        
        //create text box to hold user answer for number of dice
        numDiceText = new JTextField();	
        numDiceText.setText("");	
        numDiceText.setToolTipText("Enter number of dice you wish to roll.");
        numDiceText.setLocation(210, 50);	//set location of JTextFfield
        numDiceText.setSize(50, 25);   //set size of JTextField
        contentPane.add(numDiceText);
        
        // create the roll button
        rollButton = new JButton();
        rollButton.setText("Roll");
        rollButton.setToolTipText("Roll dem bones.");
        rollButton.setLocation(75, 90);
        rollButton.setSize(100, 30);
        contentPane.add(rollButton);
        rollButton.addActionListener(this);
        
        // create the clear button
        clearButton = new JButton();
        clearButton.setText("Clear");
        clearButton.setToolTipText("Get rid of them bones.");
        clearButton.setLocation(190, 90);
        clearButton.setSize(100, 30);
        contentPane.add(clearButton);
        clearButton.addActionListener(this);
 
        // create the output label
        rollResultLabel = new JLabel();
        rollResultLabel.setLocation(90, 150);
        rollResultLabel.setSize(190, 25);
        rollResultLabel.setForeground(Color.BLACK);
        rollResultLabel.setVisible(false);
        contentPane.add(rollResultLabel);
        
        
        //set the window's properties
        setTitle("Dice Roller");	//set window title
        setSize(400, 250); //set window size
        setVisible(true);
    }//ends createGUI
   
    //this version of the roll dice method accepts 2 strings for the type of die
    //and the number of dice to be rolled. returns the total roll summed as an int
    public int rollDice(String diType, String diNumber) {
        try{
            int totalRoll = 0;
            int numDice = Integer.parseInt(diNumber);
            if(numDice == 0){
                rollResultLabel.setText("Can't roll zero dice, dummy");
                rollResultLabel.setForeground(Color.RED);
                rollResultLabel.setVisible(true);
                return -1;
            }//ends if
            else {
                if(diType.equals("%")) {
                    int rollOne = ((int) (Math.random() * 10));
                    int rollTwo = ((int) (Math.random() * 10));
                    String tempRoll = "" + rollOne + rollTwo;
                    totalRoll = Integer.parseInt(tempRoll);
                    return totalRoll;
                }//ends if for percentile dice
                else {
                    int diceType = Integer.parseInt(diType);
                    for(int i = 0; i < numDice; i++) {
                        totalRoll += (((int) (Math.random() * diceType)) + 1);
                    }//ends for loop
                    return totalRoll;
                }//ends else for nonpercentile dice types
            }//ends outer else
        } catch(NumberFormatException nfe) {
            rollResultLabel.setText("Enter a valid type of dice to roll.");
            rollResultLabel.setForeground(Color.RED);
            rollResultLabel.setVisible(true);
            return -1;
        }//ends catch
    }//ends rollDice(string, string)
    
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Clear")) {
            numDiceText.setText("");
            diceTypeText.setText("");
            rollResultLabel.setVisible(false);
            rollResultLabel.setForeground(Color.BLACK);
        }//end if
        else {
            int rollResult = rollDice(diceTypeText.getText(), numDiceText.getText());
            if (rollResult != -1) {
                rollResultLabel.setText("The roll result is " + rollResult);
                rollResultLabel.setForeground(Color.BLACK);
                rollResultLabel.setVisible(true);
            }
        }//end else
    }//ends actionPerformed
    
    public static void main(String[] args) {
        DiceRoller diceApp = new DiceRoller();
        diceApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }//ends main
}//ends DiceRoller
