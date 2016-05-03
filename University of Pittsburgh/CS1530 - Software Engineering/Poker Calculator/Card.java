/**
 *
 * @author pokerCalc team
 */
public class Card {
    private char num;
    private char suit;
    
    /**
     * Card constructor that builds a Card object using a String input.
     * @param details Poker card in standard poker notation.
     */
    public Card(String details) {
    	if (details == null) {
    		num = 'X';
    		suit = 'x';
    	} else {
    		num = details.charAt(0);
            suit = details.charAt(1);
    	}    	
    }
    
    /**
     * Empty Constructor
     */
    public Card(){}
    
    /**
     * Card constructor that builds a Card object using an integer input. Based on values from the CardValue class.
     * @param value
     */
    public Card(int value) {
    	String details = CardValue.getString(value);
    	num = details.charAt(0);
        suit = details.charAt(1);
    }
    
    /**
     * Return the integer value of this card's FACE VALUE using the CardValue class.
     * @return The numeric value of this card.
     */
    public int getNumericValue(){
        return CardValue.getFaceValue(this);
    }
    
    /**
     * Return the full numeric value of this Card, adjusted for suit.
     * @return The integer value of this card.
     */
    public int getFullNumericValue() {
    	return CardValue.getCardValue(this);
    }
    
    
    /**
     * Return the String representation of the Card's face value.
     * @return The face value of this Card.
     */
    public String getFaceValue(){
        return Character.toString(this.num);
    }
    
    /**
     * Return the String representation of the Card's suit.
     * @return The suit of this Card.
     */
    public String getSuit(){
        return Character.toString(this.suit);
    }
    
    /**
     * Return a String representation of this card in standard poker notation.
     */
    public String toString(){
        return CardValue.getString(this);
    }
}
