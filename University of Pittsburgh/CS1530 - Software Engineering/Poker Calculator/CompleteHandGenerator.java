import java.util.*;

public class CompleteHandGenerator {
	//combos holds all 21 different combinations to choose a 5 card hand from the hole hand and the 
	//community cards on the poker table. Not the most elegant solution, but will work
	private static int[][] combos = {{0,1,2,3,4}, {0,1,2,3,5}, {0,1,2,3,6},
									 {0,1,2,4,5}, {0,1,2,4,6}, {0,1,2,5,6},
									 {0,1,3,4,5}, {0,1,3,4,6}, {0,1,3,5,6},
									 {0,1,4,5,6}, {0,2,3,4,5}, {0,2,3,4,6},
									 {0,2,3,5,6}, {0,2,4,5,6}, {0,3,4,5,6}, 
									 {1,2,3,4,5}, {1,2,3,4,6}, {2,3,4,5,6}, 
									 {1,3,4,5,6}, {1,2,3,5,6}, {1,2,4,5,6}};
	private static final int CARDS_IN_HAND = 5, TOTAL_CARDS = 7;
	
	/**
	 * Generates a list of CompleteHands given a list of HoleHands and a PokerTable.
	 * @param holeHands The HoleHands to consider.
	 * @param table The community cards to consider.
	 * @return A list of complete Hands that will need to be evaluated.
	 */
	public static ArrayList<CompleteHand> generateHands(ArrayList<HoleHand> holeHands, PokerTable table) {
		ArrayList<CompleteHand> completeHands = new ArrayList<CompleteHand>();
		Deck deck = new Deck();
		
		for(HoleHand hh : holeHands) {
			//set up the Card array with all seven possible cards
			Card[] allCards = getCardArray(hh, table);
			//make sure there aren't duplicate cards in the the array. if there are, continue to next hole hand combo
			if(duplicateCard(allCards)) {
				continue;
			}
			
			//draw concrete cards from the deck
			drawCards(deck, allCards);
			
			//set the high hand to null
			CompleteHand highHand = null;
			
			//cycle through the combos array to try all 21 combinations of the hole hand and table
			for(int i = 0; i < combos.length; i++) {
				Card[] currentHand = new Card[CARDS_IN_HAND];
				for(int j = 0; j < combos[i].length; j++) {
					currentHand[j] = allCards[combos[i][j]];
				}
				
				//currentHand now holds one of the 21 combinations. need to check for null cards.
				//if there is a null card in the combination, we need to make every variation available of this currentHand
				ArrayList<Card[]> handsWithNull = new ArrayList<Card[]>();
				if(handContainsNull(currentHand)) {
					handsWithNull = generateHandsWithNull(0,deck, currentHand, handsWithNull);
					
					for(Card[] c : handsWithNull) {
						CompleteHand compHand = new CompleteHand(c);
						if(highHand == null) {
							highHand = compHand;
						} else {
							HandVal high = new HandVal(highHand);
							HandVal curr = new HandVal(compHand);
							if(high.getHandValue() > curr.getHandValue()){
								highHand = compHand;
							}
						}
					}
				} else {
					CompleteHand compHand = new CompleteHand(currentHand);
					if(highHand == null) {
						highHand = compHand;
					} else {
						HandVal high = new HandVal(highHand);
						HandVal curr = new HandVal(compHand);
						if(high.getHandValue() > curr.getHandValue()) {
							highHand = compHand;
						}
					}
				}

			}
			completeHands.add(highHand);
		}		
		return completeHands;		
	}
	
	/**
	 * Makes a copy of the Card array
	 * @param hand The array to copy.
	 * @return A copy of the array.
	 */
	private static Card[] copyHand(Card[] hand) {
		Card[] newHand = new Card[hand.length];
		for(int i = 0; i < hand.length; i++) {
			newHand[i] = hand[i];
		}
		
		return newHand;
	}
	
	/**
	 * Recursive function that will generate all combinations of hands condsidering null cards.
	 * @param start Starting index.
	 * @param deck The deck of cards.
	 * @param hand Array of Cards in the hand.
	 * @param handList List of hands.
	 * @return
	 */
	public static ArrayList<Card[]> generateHandsWithNull(int start, Deck deck, Card[] hand, ArrayList<Card[]> handList) {
		//base case - return null if at end of hand
		if(start >= hand.length) {
			return null;
		}
				
		ArrayList<Card[]> masterList = new ArrayList<Card[]>();
		
		for(int i = start; i < hand.length; i++) {
			if(hand[i] == null) {
				//if this card is null, loop through the deck and make hands using all available cards
				for(int j = 0; j < CardValue.NUM_SUITS; j++) {
					for(int k = 0; k < CardValue.NUM_VALUES; k++) {
						int cardNum = k + (CardValue.NUM_VALUES * j);						
						if(deck.isAvailable(cardNum)) {
							Card[] newHand = copyHand(hand);
							newHand[i] = new Card(cardNum);
							deck.draw(newHand[i].toString());
							ArrayList<Card[]> tempList = generateHandsWithNull(i+1, deck, newHand, handList);
							if(tempList != null) {
								masterList.addAll(tempList);
							} else {
								masterList.add(newHand);
								deck.addToDeck(newHand[i].toString());
							}						
						}
					}
				}								
			}
		}
		
		return masterList;
	}
	
	
	
	
	
	
	public static ArrayList<Card[]> generateHandsWithNull(Deck deck, Card[] hand) {
		ArrayList<Card[]> handList = new ArrayList<Card[]>();
		
		//loop through the hand
		for(int i = 0; i < hand.length; i++) {
			if(hand[i] == null) {
				//if this card is null, loop through the deck and make hands using all available cards
				for(int j = 0; j < CardValue.NUM_SUITS; j++) {
					for(int k = 0; k < CardValue.NUM_VALUES; k++) {
						int cardNum = k + (CardValue.NUM_VALUES * j);
						
						if(deck.isAvailable(cardNum)) {
							Card[] newHand = copyHand(hand);
							newHand[i] = new Card(cardNum);
							handList.add(newHand);
						}
					}
				}
			}
		}
		
		return handList;
	}
	
	/**
	 * Determine if the hand contains a null card
	 * @param hand The hand to search.
	 * @return True if a null is found, false otherwise.
	 */
	private static boolean handContainsNull(Card[] hand) {
		//search hand for a null card
		for(int i = 0; i < hand.length; i++){
			if(hand[i] == null)
				//null found, return true
				return true;
		}
		
		//no null found, return false
		return false;
	}
	
	/**
	 * Draws cards from the deck.
	 * @param deck The deck from which to draw cards.
	 * @param allCards The cards being considered.
	 */
	private static void drawCards(Deck deck, Card[] allCards) {
		for(int i = 0; i < allCards.length; i++) {
			if(allCards[i] != null) {
				deck.draw(allCards[i].toString());
			}
		}	
	}
	
	/**
	 * Check the current card array for duplicate cards
	 * @param cardArray Array of cards to be checked.
	 * @return True if there is a duplicate card, false otherwise.
	 */
	private static boolean duplicateCard(Card[] cardArray) {
		for(int i = 0; i < cardArray.length; i++) {
			Card temp = cardArray[i];
			
			//continue to next card if this card is null. cannot check null cards for 
			if(temp == null) {
				continue;
			}
			for(int j = i+1; j < cardArray.length; j++) {
				Card temp2 = cardArray[j];
				//continue to next card if this card is null. cannot check null cards for 
				if(temp2 == null) {
					continue;
				}
				
				if(temp.getFullNumericValue() == cardArray[j].getFullNumericValue())
					//there is a duplicate card, return true
					return true;					
			} //ends inner for
		} //ends outer for
			
		//no duplicate card, return false
		return false;
	}
	
	
	/**
	 * Method to get the array of seven possible cards for this Hole Hand.
	 * @param hh The hole hand to consider.
	 * @param table The poker table to consider.
	 * @return Array of possible cards.
	 */
	private static Card[] getCardArray(HoleHand hh, PokerTable table) {
		Card[] allCards = new Card[TOTAL_CARDS];
		allCards[0] = hh.getCard1();
		allCards[1] = hh.getCard2();
		allCards[2] = table.getFlop(0);
		allCards[3] = table.getFlop(1);
		allCards[4] = table.getFlop(2);
		allCards[5] = table.getTurn();
		allCards[6] = table.getRiver();
		
		return allCards;
	}
}
