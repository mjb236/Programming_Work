import static org.junit.Assert.*;

import org.junit.Test;


public class HoleHandTest {

	/**
	 * Test that HoleHand stores and returns cards appropriately
	 */
	@Test
	public void testHoleHandCreation() {
		HoleHand hh = new HoleHand(new Card("8c"), new Card("6s"));
		assertNotNull(hh.getCard1());
		assertNotNull(hh.getCard2());
	}
	
	/**
	 * Test that HoleHand stores and returns cards appropriately
	 */
	@Test
	public void testHoleHandCreation2() {
		HoleHand hh = new HoleHand(new Card("8c"), new Card("6s"));
		assertEquals(hh.getCard1().toString(), "8c");
		assertEquals(hh.getCard2().toString(), "6s");
	}
	
	/**
	 * Test that a blank HoleHand stores and returns null Cards.
	 */
	@Test
	public void testNullHoleHand() {
		HoleHand hh = new HoleHand();
		assertNull(hh.getCard1());
		assertNull(hh.getCard2());
	}
	
	/**
	 * Test the HoleHand getCard(n) method.
	 */
	@Test
	public void testGetCardMethod() {
		HoleHand hh = new HoleHand(new Card("8c"), new Card("6s"));
		assertEquals(hh.getCard(1).toString(), "8c");
		assertEquals(hh.getCard(2).toString(), "6s");
		assertNull(hh.getCard(0));
		assertNull(hh.getCard(3));
		assertNull(hh.getCard(Integer.MAX_VALUE));
		assertNull(hh.getCard(Integer.MIN_VALUE));
	}
	
}
