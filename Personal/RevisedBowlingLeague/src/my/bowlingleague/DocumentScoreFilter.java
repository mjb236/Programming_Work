package my.bowlingleague;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.Toolkit;

/**
 * Document Filter to allow enter score fields to only contain up to 4 characters and
 * also only accept numeric characters or "A" or "R"
 * @author Mike Bowen
 */
public class DocumentScoreFilter extends DocumentFilter {
    int maxCharacters;
    String absent, rolloff;
    
    public DocumentScoreFilter() {
        maxCharacters = 4;
        absent = "A";
        rolloff = "R";
    } //ends constructor
    
    public void insertString(FilterBypass fb, int offs, String str, AttributeSet a) throws BadLocationException {
        try {
            int score = Integer.parseInt(str);
            //insert the string if it is a number
            if((fb.getDocument().getLength() + str.length()) <= maxCharacters) {
                super.insertString(fb, offs, str, a);
            } else {
                Toolkit.getDefaultToolkit().beep();
            } //ends if-else
        } catch(NumberFormatException e) {
            //insert string if it is under the max characters and either an A or R
            if( ((fb.getDocument().getLength() + str.length()) <= maxCharacters) && 
                 (str.equalsIgnoreCase(absent) || str.equalsIgnoreCase(rolloff) || str.equalsIgnoreCase(""))) {
                super.insertString(fb, offs, str, a);
            } else {
                Toolkit.getDefaultToolkit().beep();
            } //ends if-else
        } //ends catch
    } //ends insertString
    
    public void replace(FilterBypass fb, int offs, int length, String str, AttributeSet a) throws BadLocationException {
        try {
            int score = Integer.parseInt(str);
            //insert the string if it is a number
            if((fb.getDocument().getLength() + str.length() - length) <= maxCharacters) {
                super.insertString(fb, offs, str, a);
            } else {
                Toolkit.getDefaultToolkit().beep();
            } //ends if-else
        } catch(NumberFormatException e) {
            //insert string if it is under the max characters and either an A or R
            if( ((fb.getDocument().getLength() + str.length() - length) <= maxCharacters) && 
                 (str.equalsIgnoreCase(absent) || str.equalsIgnoreCase(rolloff) || str.equalsIgnoreCase(""))) {
                super.insertString(fb, offs, str, a);
            } else {
                Toolkit.getDefaultToolkit().beep();
            } //ends if-else
        } //ends catch        
    } //ends replace
    
} //ends DocumentScoreFilter