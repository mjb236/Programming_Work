/**
 * The PokerTable class represents the table of community cards for Texas Hold 'Em poker. 
 * @author pokerCalc team
 * Updated 7/5/2015
 */

import java.util.*;

public class PokerTable {
	private Card[] flop;
	private Card turn;
	private Card river;
	
	/**
	 * Basic constructor that sets all cards to null.
	 */
	public PokerTable() {
		flop = new Card[3];
		for(int i = 0; i < flop.length; i++)
			flop[i] = null;
		turn = null;
		river = null;
	}
	
	/**
	 * Constructor that builds the table given the community cards on the table.
	 * @param flop1 The first card of the flop.
	 * @param flop2 The second card of the flop.
	 * @param flop3 The third card of the flop.
	 * @param turn The turn card.
	 * @param river The river card.
	 */
	public PokerTable(Card flop1, Card flop2, Card flop3, Card turn, Card river) {
		flop = new Card[] {flop1, flop2, flop3};
		this.turn = turn;
		this.river = river;
	}
	
	/**
	 * Constructor that builds the table given a comma-separated list of cards in poker notation.
	 * @param cards Comma-separated list of cards on the table, in poker notation.
	 */
	public PokerTable(String cards) {
		this();
		ArrayList<String> table = Translator.translate(cards);
		
		//leave the cards null if the Translator returns a null list of strings.
		if(table == null) {
			return;
		}
		
		Iterator<String> it = table.iterator();
		for(int i = 0; i < flop.length; i++)
			flop[i] = new Card(it.next());
		turn = new Card(it.next());
		river = new Card(it.next());
	}
	
	/**
	 * Get the nth card of the flop.
	 * @param n The index of the card in the flop.
	 * @return The flop card at index n.
	 */
	public Card getFlop(int n) {
		if (n < 0 || n > (flop.length - 1)) {
			return null;
		} else {
			return flop[n];
		}
	}
	
	/**
	 * Get the turn card.
	 * @return The turn card.
	 */
	public Card getTurn() {
		return turn;
	}
	
	/**
	 * Get the river card.
	 * @return The river card.
	 */
	public Card getRiver() {
		return river;		
	}
}
