/**
 * The HoleHand class represents a player's hold hand in a game of Texas Hold 'Em poker. 
 * @author pokerCalc team
 * Updated 7/5/2015
 */
public class HoleHand {
    private Card card1, card2;
    
    /**
     * Constructor that accepts two hole cards.
     * @param card1 The first hole card.
     * @param card2 The second hole card.
     */
    public HoleHand(Card card1, Card card2) {
    	this.card1 = card1;
    	this.card2 = card2;
    }
    
    /**
     * Basic constructor that creates a hold hand with null cards.
     */
    public HoleHand() {
    	card1 = null;
    	card2 = null;
    }
    
    /**
     * Get the first hole card.
     * @return Card 1 of the hole hand.
     */
    public Card getCard1() {
    	return card1;
    }
    
    /**
     * Get the second hole card.
     * @return Card 2 of the hole hand.
     */
    public Card getCard2() {
    	return card2;
    }
    
    /**
     * Returns the nth card of the hole hand.
     * @param n The number of the card in the hole hand to get.
     * @return The nth card of the hole hand, null if out of bounds.
     */
    public Card getCard(int n) {
    	if(n == 1)
    		return card1;
    	else if (n == 2)
    		return card2;
    	else
    		return null;
    }
}
