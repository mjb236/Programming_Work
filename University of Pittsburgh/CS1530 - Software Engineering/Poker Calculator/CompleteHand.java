/**
 * The CompleteHand class represents a player's complete 5 card hand in a game of Texas Hold 'Em poker. 
 * @author pokerCalc team
 * Updated 7/5/2015
 */

public class CompleteHand {
	private Card[] cards;
	private final int MAX_CARDS = 5; //5 is the maximum number of cards in a Texas Hole 'Em poker hand.
	
	/**
	 * Constructor that creates a CompleteHand given all 5 cards of the hand.
	 * @param card1 The first card.
	 * @param card2 The second card.
	 * @param card3 The third card.
	 * @param card4 The fourth card.
	 * @param card5 The fifth card.
	 */
	public CompleteHand(Card card1, Card card2, Card card3, Card card4, Card card5) {
		cards = new Card[MAX_CARDS];
		cards[0] = card1;
		cards[1] = card2;
		cards[2] = card3;
		cards[3] = card4;
		cards[4] = card5;
	}
	
	/**
	 * Constructor that creates a CompleteHand given an array of all cards in the hand.
	 * @param cards The array of cards of which the CompleteHand will be comprised.
	 */
	public CompleteHand(Card[] cards) {
		if(cards.length == MAX_CARDS) {
			this.cards = new Card[MAX_CARDS];
			for(int i = 0; i < cards.length; i++) {
				this.cards[i] = cards[i];
			}
		} else {
			this.cards = null;
		}
	}
	
	/**
	 * Generic constructor that creates a CompleteHand with all null cards
	 */
	public CompleteHand() {
		cards = new Card[MAX_CARDS];
		for(int i = 0; i < cards.length; i++) {
			cards[i] = null;
		}
	}
	
	/**
	 * Get the nth card of the hand.
	 * @param n The index of the card.
	 * @return The nth card of the hand.
	 */
	public Card getCard(int n) {
		if(n < 0 || n >= MAX_CARDS) {
			return null;
		} else {
			return cards[n];
		}
	}
	
	/**
	 * Get the highest face value card in the hand.
	 * @return The card with the highest face value.
	 */
	public Card getHighCard() {
		//start high card at first card of the hand
		Card high = cards[0];
		
		//traverse hand looking for the highest face value
		for(int i = 1; i < cards.length; i++) {
			if(high.getNumericValue() > cards[i].getNumericValue()) {
				high = cards[i];
			}
		}
		
		return high;
	}
	
	/**
	 * Get the lowest face value card in the hand.
	 * @return The card with the lowest face value.
	 */
	public Card getLowCard() {
		//start high card at first card of the hand
		Card low = cards[0];
		
		//traverse hand looking for the highest face value
		for(int i = 1; i < cards.length; i++) {
			if(low.getNumericValue() < cards[i].getNumericValue()) {
				low = cards[i];
			}
		}
		
		return low;
	}
	
	/**
	 * Get the total number of cards in the Hand. A card counts if it is null or not.
	 * @return The total number of cards in the hand.
	 */
	public int getNumCards() {
		return MAX_CARDS;
	}
	
	/**
	 * Method to return a string representation of the CompleteHand.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		for(int i = 0; i < cards.length; i++) {
			sb.append(cards[i].toString());
			sb.append(" ");
		}
		
		return sb.toString().trim();
	}
}
