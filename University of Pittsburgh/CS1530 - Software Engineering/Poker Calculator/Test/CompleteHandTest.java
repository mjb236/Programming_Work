import static org.junit.Assert.*;

import org.junit.Test;


public class CompleteHandTest {
	//a test array of cards
	private Card[] test = {new Card("Ah"), new Card("Ts"), new Card("8c"), new Card("Jd"), new Card("4s")};
	
	/**
	 * Test that the CompleteHand stores and returns the cards appropriately.
	 */
	@Test
	public void testCompleteHandCreation() {
		CompleteHand ch = new CompleteHand(new Card("Ah"), new Card("Ts"), new Card("8c"), new Card("Jd"), new Card("4s"));
		for(int i = 0; i < ch.getNumCards(); i++) {
			assertNotNull(ch.getCard(i));
		}		
	}
	
	/**
	 * Test that the CompleteHand stores and returns the cards appropriately given an array of cards.
	 */
	@Test
	public void testCompleteHandCreation2() {
	    CompleteHand ch = new CompleteHand(test);
		for(int i = 0; i < ch.getNumCards(); i++) {
			assertNotNull(ch.getCard(i));
		}		
	}
	
	/**
	 * Test that the blank CompleteHand stores and returns null cards.
	 */
	@Test
	public void testCompleteHandCreation3() {
	    CompleteHand ch = new CompleteHand();
		for(int i = 0; i < ch.getNumCards(); i++) {
			assertNull(ch.getCard(i));
		}		
	}
	
	/**
	 * Test the highCard function on the test array - should return "Ah"
	 */
	@Test
	public void testHighCard() {
		CompleteHand ch = new CompleteHand(test);
		assertEquals(ch.getHighCard().toString(), "Ah");
	}
	
	/**
	 * Test the lowCard function on the test array - should return "4s"
	 */
	@Test
	public void testLowCard() {
		CompleteHand ch = new CompleteHand(test);
		assertEquals(ch.getLowCard().toString(), "4s");
	}
}
