/**
 * The Deck class represents a deck of standard playing cards. 
 * @author pokerCalc team
 * Updated 7/3/2015
 */

import java.util.*;

public class Deck {
    private ArrayList<Card> deck = new ArrayList<Card>();		//actual arrayList used to store card objects
    private boolean[] avail;									//whether or not a card is available in the Deck
    private int currentCard, numCards;							//used for drawing the top card of the deck
   
    /**
     * Constructor that creates a Deck with all 52 playing cards.
     */
    public Deck() {
    	createDeck();
    	currentCard = 0;
    	numCards = CardValue.NUM_SUITS * CardValue.NUM_VALUES;
    }
    
    /**
     * Helper method that initializes the Deck of cards using the CardValue constants.
     */
	private void createDeck(){									//initialize deck
		//create the avail array
		avail = new boolean[CardValue.NUM_SUITS * CardValue.NUM_VALUES];
		
		//initialize the deck and avail array
		for(int i = 0; i < CardValue.NUM_SUITS; i++) {
			for(int j = 0; j < CardValue.NUM_VALUES; j++) {
				deck.add(new Card(j + (CardValue.NUM_VALUES * i)));
				avail[j + (CardValue.NUM_VALUES * i)] = true;
			}
		}// ends initialization loops    	
	}
	
	/**
	 * Function that will randomize the order of the Cards in the Deck.
	 */
	public void shuffle() {
		Collections.shuffle(deck);
	}
    
    /**
     * Draw the first card off of the top of the Deck.
     * @return The top Card in the Deck.
     */
    public Card draw(){
        //takes the top card, returns it to the user, removes the card from the available cards
        Card nextCard = deck.get(currentCard++);
        
        //make the card unavailable and decrease the number of cards. 
        avail[nextCard.getFullNumericValue()] = false;
        numCards--;

        return nextCard;
    }

    /**
     * Remove a specific Card from the Deck and return it to the user.
     * @param input String representation of the Card.
     * @return The Card object to be removed from the Deck.
     */
    public Card draw(String input){
    	//get numeric Card value of the string input
    	int val = CardValue.getCardValue(input);
    	if (val < 0) {
    		return null;			//return null if val is less that 0 i.e. not in the 52 playing cards.
    	} else if (avail[val]) {
    		//find the position of the Card in the Deck
    		int position = find(input);
    		Card drawnCard = deck.get(position);
    		
    		//make the card unavailable and decrease the number of cards in the Deck
	        avail[drawnCard.getFullNumericValue()] = false;
	        numCards--;
	        
	        //if the specific card was at the top of the deck, move currentCard to the next Card.
	        if(currentCard == position) {
	        	currentCard++;
	        }
		        
	        //return the Card
	        return drawnCard;
    	} else {
    		//if the Card was not available i.e. not in the Deck, return null
    		return null;
    	}
    }

    /**
     * Finds the index of the Card in the Deck.
     * @param input String representation of the Card.
     * @return The index of the Card in the Deck.
     */
    public int find(String input){
    	int val = CardValue.getCardValue(input);
    	//return -1 if the Card is invalid
    	if (val < 0)
    		return -1;
    	
    	//check availability of the Card
    	if(avail[val]){
    		int i = 0;

    		//search the deck for the Card
    		while(true){
				Card nextCard = deck.get(i);
	            
				//if the card value of the input is equal to the value of the currentCard, return its index
	            if(val == nextCard.getFullNumericValue())
	                return i;
	            i++;
    		} //ends while
    	} else
    		System.out.print("ERROR IN FIND");

    	//return -1 if the card is unavailable
        return -1;
    } //ends find

    //add card to deck
    public void addToDeck(String input){
    	ArrayList<String> str = Translator.translate(input);
    	if (str == null) {
    		System.out.println("Invalid Card");
    	} else {
    		//get the first string from the translated input - should only be one
    		String cardStr = str.get(0);
    		Card card = new Card(cardStr);
    		
    		//check validity and card availability
    		int val = card.getFullNumericValue();
    		if (val < 0) {
    			return;
    		}

    		if(avail[val]) {
    			System.out.println("Card already in Deck");
    			return;
    		} else {
    			avail[val] = true;
    			numCards++;
    		}
    	}
    }
   
    /**
     * Get the number of cards remaining in the deck.
     * @return The number of cards currently in the deck.
     */
    public int getLength(){
      return numCards;
    }

    /**
     * Returns a string representation of the Deck.
     */
    public String toString(){
    	StringBuilder sb = new StringBuilder();
    	for(Card c : deck) {
    		sb.append(c + " ");
    	}
    	
    	return sb.toString();
    } //ends toString 
    
    /**
     * Function to determine whether or not a card is available in the deck.
     * @param n The numeric value of the card.
     * @return True if the card is available, false otherwise.
     */
    public boolean isAvailable(int n) {
    	return avail[n];
    }
}