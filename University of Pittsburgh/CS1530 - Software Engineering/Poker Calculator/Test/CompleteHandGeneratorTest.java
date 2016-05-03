import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;

public class CompleteHandGeneratorTest {
	private PokerTable table = new PokerTable(new Card("Jh"), new Card("2c"), new Card("4d"), new Card("7d"), new Card("Kc"));
	private PokerTable table2 = new PokerTable(new Card("Ks"), new Card("Js"), new Card("Ts"), new Card("Qs"), new Card("6d"));
	private ArrayList<HoleHand> holeHands = new ArrayList<HoleHand>();
	
	//Set up a list of HoleHands
	private void setupHoleHands() {
		holeHands.add(new HoleHand(new Card("Ah"), new Card("As")));
		holeHands.add(new HoleHand(new Card("Ah"), new Card("Ac")));
		holeHands.add(new HoleHand(new Card("Ah"), new Card("Ad")));
		holeHands.add(new HoleHand(new Card("As"), new Card("Ac")));
		holeHands.add(new HoleHand(new Card("As"), new Card("Ad")));
		holeHands.add(new HoleHand(new Card("Ac"), new Card("Ad")));		
	}
	
	/**
	 * Test that the generateHands method generates the hands as desired.
	 */
	@Test
	public void test() {
		setupHoleHands();
		ArrayList<CompleteHand> ch = null;
		ch = CompleteHandGenerator.generateHands(holeHands, table);
		
		assertNotNull(ch);
		for(CompleteHand c : ch) {
			HandVal hv = new HandVal(c);
			System.out.println(c + " - " + hv.getHandType());
		}
	}
	
	/**
	 * Test that the generateHands method generates the hands as desired.
	 */
	@Test
	public void test2() {
		setupHoleHands();
		ArrayList<CompleteHand> ch = null;
		ch = CompleteHandGenerator.generateHands(holeHands, table2);
		
		assertNotNull(ch);
		for(CompleteHand c : ch) {
			HandVal hv = new HandVal(c);
			System.out.println(c + " - " + hv.getHandType());
		}
	}
	
	/**
	 * Test the null hand generation method
	 */
/*	@Test
	public void testNull() {
		Deck deck = new Deck();
		Card[] hand = new Card[5];
		hand[0] = new Card("Ks");
		hand[1] = new Card("Qs");
		hand[2] = new Card("Js");
		hand[3] = new Card("Ts");
		hand[4] = null;
		for(int i = 0; i < hand.length; i++) {
			if(hand[i] != null) {
				deck.draw(hand[i].toString());
			}
		}
		
		ArrayList<Card[]> hands	= CompleteHandGenerator.generateHandsWithNull(deck, hand);
		for(Card[] c : hands) {
			for(int i = 0; i < c.length; i++) {
				if(c[i] == null) {
					System.out.print("null ");
				} else {
					System.out.print(c[i].toString() + " ");
				}				
			}
			System.out.println();
		}
		
		assertTrue(true);
	
	}
*/
	
	/**
	 * Test the null hand generation method
	 */
	@Test
	public void testNulls() {
		Deck deck = new Deck();
		Card[] hand = new Card[5];
		hand[0] = new Card("Ks");
		hand[1] = new Card("Qs");
		hand[2] = new Card("Js");
		hand[3] = null;
		hand[4] = null;
		for(int i = 0; i < hand.length; i++) {
			if(hand[i] != null) {
				deck.draw(hand[i].toString());
			}
		}
		
		ArrayList<Card[]> hands	= new ArrayList<Card[]>();
		hands = CompleteHandGenerator.generateHandsWithNull(0, deck, hand, hands);
		for(Card[] c : hands) {
			for(int i = 0; i < c.length; i++) {
				if(c[i] == null) {
					System.out.print("null ");
				} else {
					System.out.print(c[i].toString() + " ");
				}				
			}
			System.out.println();
		}
		
		assertTrue(true);
	
	}
}
