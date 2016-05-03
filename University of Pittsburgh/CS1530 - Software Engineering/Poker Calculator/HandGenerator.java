/**
 * The HandGenerator class is used to generate Hand combinations for calculating equity. 
 * @author pokerCalc team
 * Updated 7/5/2015
 */

import java.util.*;

public class HandGenerator {
	private final static String VALID_FACES = "AKQJT98765432";
	private final static String VALID_SUITS = "schd";
	private final static String OFFSUIT = "o";
	private final static int MIN_HAND_STRING_LENGTH = 2;
	private static ArrayList<HoleHand> holeHands;
	
	/**
	 * Generate the HoleHands given a list of Strings representing those hands.
	 * @param input The list of strings representing the hole hands to be generated.
	 * @return The list of the generated HoleHand objects.
	 */
	public static ArrayList<HoleHand> generateHoleHands(ArrayList<String> input) {
		holeHands = new ArrayList<HoleHand>();
		
		for(String s : input) {
			if(!isHandValid(s))
				return null;
		}
		
		return holeHands;
	}
	
	/**
	 * Validate that the hands are of the correct form and send on to the appropriate generate method if so.
	 * @param hand The string of the current hand.
	 * @return true if hand was valid and combinations were generated, false otherwise.
	 */
	private static boolean isHandValid(String hand) {
		//if hand was null, return false
		if(hand == null)
			return false;
		//if hand length is less than 2, return false - hand must be at least 2 cards long
		if(hand.length() < MIN_HAND_STRING_LENGTH)
			return false;
		
		int currentChar = 0;
		String firstChar = Character.toString(hand.charAt(currentChar++));
		System.out.println("FirstChar: " + firstChar);
		
		//return false if first character is not a valid Face
		if(!VALID_FACES.contains(firstChar)) {
			return false;
		} else {
			String secondChar = Character.toString(hand.charAt(currentChar++));
			System.out.println("Second Char: " + secondChar);
			//continue processing if the second character is a valid face or suit, return false otherwise
			if(VALID_FACES.contains(secondChar)) {
				//two Face values in a row - check for offsuit
				//if only two characters AND they are the same face value - get offsuit combos of that face. Two of the
				//same face value must be offsuit
				if(currentChar == hand.length() && firstChar.equals(secondChar)) {
					getOffsuitHandCombosSameCard(firstChar + secondChar);
					return true;
				} else if(currentChar == hand.length() && !firstChar.equals(secondChar)) {
					//if only two characters AND they are not the same face value - get suited combos. 
					getSuitedHandCombos(firstChar + secondChar);
					return true;
				} else if(currentChar < hand.length()) {
					//check for offsuit flag - return false if third char does not signal a suit or offsuit. if encountering two
					//face values in a row, hand must either be offsuit or specify one card's suit.
					String thirdChar = Character.toString(hand.charAt(currentChar++));
					System.out.println("Third Char: " + thirdChar);
					//return false if hand is greater than 3 characters and we have seen 2 faces already
					if(hand.length() > currentChar)
						return false;
					
					if(thirdChar.equals(OFFSUIT)) {
						//send first char and second char to getOffsuitHandCombos
						getOffsuitHandCombos(firstChar + secondChar);
						return true;
					} else if(VALID_SUITS.contains(thirdChar)){
						//send hand to getOffsuitHandCombosWithSpecified
						getOffsuitHandCombosWithSpecified(hand);
						return true;
					} else
						//return false if third character is not a valid suit or offsuit
						return false;
				}
			} else if (VALID_SUITS.contains(secondChar)) {
				//Face value followed by a suit
				//if only one face value and a suit in the string, return false - cannot have a one card hand
				if(currentChar == hand.length()) {
					return false;
				} else {
					//check third character - must be a valid face value. return false otherwise.
					String thirdChar = Character.toString(hand.charAt(currentChar++));
					System.out.println("Third Char: " + thirdChar);
					if(!VALID_FACES.contains(thirdChar)) {
						return false;
					} else {
						//hand has form of FsF - if this is the entire hand, reorganize hand and send to getOffsuitHandCombosWithSpecified
						if(hand.length() == currentChar) {
							getOffsuitHandCombosWithSpecified(firstChar + thirdChar + secondChar);
							return true;
						} else {
							//hand is 4 characters long and of the form FsF. Next character must be a valid Suit, else return false.
							String fourthChar = Character.toString(hand.charAt(currentChar++));
							System.out.println("Fourth Char: " + fourthChar);
							if(VALID_SUITS.contains(fourthChar)) {
								getSingleHand(hand);
								return true;
							} else {
								return false;
							}
						}
					}
				}
			} else {
				return false;
			}
			//if this point is reached there was a problem
		    return false;	
		}
	}
	
	/**
	 * Function to add a single hand to the HoleHands using the specified string.
	 * @param hand String representing a hole hand in form XxYy
	 */
	private static void getSingleHand(String hand) {
		//if this method is called, the data in the hand string has been validated. Can manually create the cards and the holehand
		Card card1 = new Card(hand.substring(0, 2));
		Card card2 = new Card(hand.substring(2, 4));
		
		holeHands.add(new HoleHand(card1, card2));
		
		System.out.println("IN GET SINGLE HAND");		
	}
	
	/**
	 * Function to add the suited hand combos for a string of hole hands.
	 * @param hand
	 */
	private static void getSuitedHandCombos(String hand) {
		//if this method is called, the data in the hand string has been validated and is of the form XY
		for(int i = 0; i < CardValue.NUM_SUITS; i++) {
			int value1 = CardValue.getFaceValue(hand.substring(0,1));
			int value2 = CardValue.getFaceValue(hand.substring(1,2));
			Card card1 = new Card(value1 + (CardValue.NUM_VALUES * i));
			Card card2 = new Card(value2 + (CardValue.NUM_VALUES * i));
			holeHands.add(new HoleHand(card1, card2));
		}
		
		System.out.println("IN SUITED HAND COMBOS");

	}
	
	/**
	 * Function that will generate the 12 different combinations of off suit hole hand cards.
	 * @param hand String representing the hole hand cards.
	 */
	private static void getOffsuitHandCombos(String hand) {
		//if this method is called, the data in the hand has been validated and is of the form XY
		//generate the 12 different combos of off suit hole cards
		int value1 = CardValue.getFaceValue(hand.substring(0,1));
		int value2 = CardValue.getFaceValue(hand.substring(1,2));
		for(int i = 0; i < CardValue.NUM_SUITS; i++) {
			Card card1 = new Card(value1 + (CardValue.NUM_VALUES * i));
			for(int j = 0; j < CardValue.NUM_SUITS; j++) {
				if(i != j) {
					Card card2 = new Card(value2 + (CardValue.NUM_VALUES * j));
					holeHands.add(new HoleHand(card1, card2));
				}
			}
		}
		
		System.out.println("IN OFFSUIT COMBOS");

	}
	
	/**
	 * Function that will generate the 6 different offsuit combos of the same face value card.
	 * @param hand String of the cards in form XX.
	 */
	private static void getOffsuitHandCombosSameCard(String hand) {
		//if this method is called, the data in the hand has been validated and is of the form XX
		//generate the 6 different offsuit combos of the same face value
		int value = CardValue.getFaceValue(hand.substring(0, 1));
		for(int i = 0; i < CardValue.NUM_SUITS; i++) {
			Card card1 = new Card(value + (CardValue.NUM_VALUES * i));
			for(int j = (i+1); j < CardValue.NUM_SUITS; j++) {
				Card card2 = new Card(value + (CardValue.NUM_VALUES * j));
				holeHands.add(new HoleHand(card1, card2));
			}
		} 
		
		System.out.println("IN OFFSUIT SAME CARD");
	}
	
	/**
	 * Generates offsuit hole hands with one card having a specified suit
	 * @param hand
	 */
	private static void getOffsuitHandCombosWithSpecified(String hand) {
		//if this method is called, the data in the hand has been validated and is of the form XYy
		//generate the 3 different combos of hands
		int value = CardValue.getFaceValue(hand.substring(0,1));
		Card card2 = new Card(hand.substring(1,3));
		for(int i = 0; i < CardValue.NUM_SUITS; i++) {
			int adjustedVal = value + (CardValue.NUM_VALUES * i);
			if((adjustedVal / CardValue.NUM_VALUES) != (card2.getFullNumericValue() / CardValue.NUM_VALUES)) {
				Card card1 = new Card(adjustedVal);
				holeHands.add(new HoleHand(card1, card2));
			}
		}
		
		System.out.println("IN OFFSUIT COMBOS WITH SPECIFIED");
		
	}
}
