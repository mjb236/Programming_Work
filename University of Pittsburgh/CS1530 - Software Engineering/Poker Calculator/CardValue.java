/**
 * The CardValue class stores integer values to represent each card value and each suit.
 * It also will return poker notation given an integer or vice versa.
 * @author pokerCalc team
 * Updated 6/30/15
 */

public class CardValue {

	//public static variables to represent each card value, each suit and the number of values and suits
	public static final int ACE = 0, KING = 1, QUEEN = 2, JACK = 3, TEN = 4, NINE = 5,
							 EIGHT = 6, SEVEN = 7, SIX = 8, FIVE = 9, FOUR = 10, THREE = 11, TWO = 12;
	public static final int SPADE = 0, CLUB = 1, HEART = 2, DIAMOND = 3;
	public static final int NUM_VALUES = 13, NUM_SUITS = 4;
	
	/**
	 * Converts standard poker notation of form VALUEsuit (e.g. Ah, Ts, 6d, etc) into an integer.
	 * @param card String representing the poker card.
	 * @return The constant integer value of that card.
	 */
	public static int getCardValue(String card) {
		//account for null value
		if (card == null)
			return -1;
		
		//get the value and suit of the card
		char val = card.charAt(0);
		char suit = card.charAt(1);
		
		int value = -1;
		
		//assign base value
		switch (val) {
			case 'A':
				value = ACE;
				break;
			case 'K':
				value = KING;
				break;
			case 'Q':
				value = QUEEN;
				break;
			case 'J':
				value = JACK;
				break;
			case 'T':
				value = TEN;
				break;
			case '9':
				value = NINE;
				break;
			case '8':
				value = EIGHT;
				break;
			case '7':
				value = SEVEN;
				break;
			case '6':
				value = SIX;
				break;
			case '5':
				value = FIVE;
				break;
			case '4':
				value = FOUR;
				break;
			case '3':
				value = THREE;
				break;
			case '2':
				value = TWO;
				break;
			default:
				return value;
		} //ends switch for value
		
		//shift the value to the suit
		switch(suit) {
			case 's':
				value += (SPADE * NUM_VALUES);
				break;
			case 'c':
				value += (CLUB * NUM_VALUES);
				break;
			case 'd':
				value += (DIAMOND * NUM_VALUES);
				break;
			case 'h':
				value += (HEART * NUM_VALUES);
				break;
			default:
				value *= -1;
		} //ends switch for suit
		
		return value;
	} //ends getCardValue
	
	/**
	 * Returns the value of the Card object as an integer.
	 * @param c The Card object representing the poker card.
	 * @return The integer value of the poker Card.
	 */
	public static int getCardValue(Card c) {
		String[] str = new String[2];
		str[0] = c.getFaceValue();
		str[1] = c.getSuit();
		String card = str[0]+str[1];
		
		return getCardValue(card);
	} //ends getCardValue
	
	/**
	 * Gets a user-friendly String representation of a poker card.
	 * @param card The poker card in standard poker notation.
	 * @return The String representation of that poker card.
	 */
	public static String toString(String card) {
		if (card == null)
			return null;
		else {
			int value = getCardValue(card);
			return toString(value);
		}		
	} //ends toString
	
	/**
	 * Gets a user-friendly String representation of a poker card.
	 * @param card The Card object to be represented.
	 * @return The String representation of that poker card.
	 */
	public static String toString(Card card) {
		if (card == null)
			return null;
		else {
			int value = getCardValue(card);
			return toString(value);
		}		
	}
	
	/**
	 * Gets a user-friendly String representation of a poker card.
	 * @param value The integer value of that poker card.
	 * @return The String representation of that poker card.
	 */
	public static String toString(int value) {
		String card = "";
		int val = value % NUM_VALUES;
		
		//face value of card
		switch(val) {
			case ACE:
				card += "Ace of ";
				break;
			case KING:
				card += "King of ";
				break;
			case QUEEN:
				card += "Queen of ";
				break;
			case JACK:
				card += "Jack of ";
				break;
			case TEN:
				card += "Ten of ";
				break;
			case NINE:
				card += "Nine of ";
				break;
			case EIGHT:
				card += "Eight of ";
				break;
			case SEVEN:
				card += "Seven of ";
				break;
			case SIX:
				card += "Six of ";
				break;
			case FIVE:
				card += "Five of ";
				break;
			case FOUR:
				card += "Four of ";
				break;
			case THREE:
				card += "Three of ";
				break;
			case TWO:
				card += "Two of ";
				break;
			default:
				return null;
		} //ends switch for values
		
		//shift to determine suit
		val = value / NUM_VALUES;
		
		//suit of card
		switch(val) {
			case SPADE:
				card += "Spades";
				break;
			case CLUB:
				card += "Clubs";
				break;
			case HEART:
				card += "Hearts";
				break;
			case DIAMOND:
				card += "Diamonds";
				break;
			default:
				return null;
		} //ends switch for suit
		
		return card;		
	} //ends toString
	
	/**
	 * Gets the standard poker notation form of the poker card.
	 * @param value The integer value of the poker card.
	 * @return The standard poker notation String representation of the poker card.
	 */
	public static String getString(int value) {
		String card = "";
		int val = value % NUM_VALUES;
		
		//face value of card
		switch(val) {
			case ACE:
				card += "A";
				break;
			case KING:
				card += "K";
				break;
			case QUEEN:
				card += "Q";
				break;
			case JACK:
				card += "J";
				break;
			case TEN:
				card += "T";
				break;
			case NINE:
				card += "9";
				break;
			case EIGHT:
				card += "8";
				break;
			case SEVEN:
				card += "7";
				break;
			case SIX:
				card += "6";
				break;
			case FIVE:
				card += "5";
				break;
			case FOUR:
				card += "4";
				break;
			case THREE:
				card += "3";
				break;
			case TWO:
				card += "2";
				break;
			default:
				return null;
		} //ends switch for values
		
		//shift to determine suit
		val = value / NUM_VALUES;
		
		//suit of card
		switch(val) {
			case SPADE:
				card += "s";
				break;
			case CLUB:
				card += "c";
				break;
			case HEART:
				card += "h";
				break;
			case DIAMOND:
				card += "d";
				break;
			default:
				return null;
		} //ends switch for suit
		
		return card;
	} //ends getString	
	
	/**
	 * Get the standard poker notation String representation of a Card.
	 * @param c The Card to be represented.
	 * @return The standard poker notation of the Card, null if Card is null. 
	 */
	public static String getString(Card c) {
		if(c == null)
			return null;
		else {
			int value = getCardValue(c);
			return getString(value);
		}		
	}
	
	/**
	 * Get the integer representatino of the face value of the card.
	 * @param c The Card object for which to get the integer face value.
	 * @return The integer value of the face
	 */
	public static int getFaceValue(Card c) {
		return getFaceValue(c.getFaceValue());
	}
	
	
	/**
	 * Get the integer representation of the face value of the card.
	 * @param face The String of the face value to be represented by an integer.
	 * @return The integer value of the face value of the card, -1 if invalid.
	 */
	public static int getFaceValue(String face) {
		if(face == null)
			return -1;
		
		switch (face.charAt(0)) {
			case 'A':
				return ACE;
			case 'K':
				return KING;
			case 'Q':
				return QUEEN;
			case 'J':
				return JACK;
			case 'T':
				return TEN;
			case '9':
				return NINE;
			case '8':
				return EIGHT;
			case '7':
				return SEVEN;
			case '6':
				return SIX;
			case '5':
				return FIVE;
			case '4':
				return FOUR;
			case '3':
				return THREE;
			case '2':
				return TWO;
			default:
				return -1;
		} //ends switch for value
	}
}
