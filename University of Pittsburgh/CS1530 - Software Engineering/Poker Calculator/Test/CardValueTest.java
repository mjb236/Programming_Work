import junit.framework.TestCase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class CardValueTest extends TestCase {

	public CardValueTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	@Test
	//Test to ensure that the getCardValue works for a card in the middle of the Deck
	public void testGetValueAceHearts() {
		int value = CardValue.getCardValue("Ah");
		assertEquals(value, 26);
	}
	
	@Test
	//Test that the getCardValue works for the first card in the Deck
	public void testGetValueAceSpades() {
		int value = CardValue.getCardValue("As");
		assertEquals(value, 0);
	}
	
	@Test
	//Test to ensure that getCardValue works for a card in the middle of the deck
	public void testGetValue8Clubs() {
		int value = CardValue.getCardValue("8c");
		assertEquals(value, 19);
	}
	
	@Test
	//Test that the getCardValue works for the last card in the Deck
	public void testGetValue2Diamonds() {
		int value = CardValue.getCardValue("2d");
		assertEquals(value, 51);
	}
	
	@Test
	//Test to ensure that the getCardValue function returns -1 when given a null value.
	public void testGetValueNull() {
		String n = null;
		int value = CardValue.getCardValue(n);
		assertEquals(value, -1);
	}
	
	@Test
	//Test to ensure that the getCardValue for an invalid card returns -1
	public void testGetValueInvalidValue() {
		int value = CardValue.getCardValue("Yd");
		assertEquals(value, -1);
	}
	
	@Test
	//Test to ensure that getCardValue function works for a valid card
	public void testGetValueCard() {
		Card c = new Card("8c");
		int value = CardValue.getCardValue(c);
		assertEquals(value, 19);
	}
	
	
	//test to ensure that the toString for Ah returns Ace of Hearts
	@Test
	public void testToStringAceHearts() {
		String result = CardValue.toString("Ah");
		assertTrue(result.equals("Ace of Hearts"));
	}
	
	@Test
	//test to ensure that the toString fro 8c returns Eight of Clubs
	public void testToStringCard() {
		Card c = new Card("8c");
		String result = CardValue.toString(c);
		assertTrue(result.equals("Eight of Clubs"));
	}
	
	@Test
	//test that the toString function for 8d returns Eight of Diamonds
	public void testToStringEightDiamonds() {
		String result = CardValue.toString("8d");
		assertTrue(result.equals("Eight of Diamonds"));
	}
	
	@Test
	//test that the toString function that accepts integer values returns the correct string
	public void testToStringAceHearts2() {
		String result = CardValue.toString(26);
		assertTrue(result.equals("Ace of Hearts"));
	}
	
	@Test
	//test that the toString function that accepts integer values returns the correct string
	public void testToStringEightDiamonds2() {
		String result = CardValue.toString(45);
		assertTrue(result.equals("Eight of Diamonds"));
	}
	
	@Test
	//test what happens when toString is given a null card.
	public void testToStringNull() {
		Card c = null;
		String result = CardValue.toString(c);
		assertNull(result);
	}
	
	@Test
	//test to ensure that a null value is returns when a negative number is given to toString
	public void testToStringNegative() {
		String result = CardValue.toString(-1);
		assertNull(result);
	}

	@Test
	//test that the getString function returns proper poker notation
	public void testGetStringAceHearts() {
		String result = CardValue.getString(26);
		assertTrue(result.equals("Ah"));
	}
	
	@Test
	//test that the getString function returns proper poker notation
	public void testGetStringEightDiamonds() {
		String result = CardValue.getString(45);
		assertTrue(result.equals("8d"));
	}
	
	@Test
	//test that getString returns null given a negative number
	public void testGetStringNegative() {
		String result = CardValue.getString(-1);
		assertNull(result);
	}
	
	@Test
	//test that getString returns null if given a number bigger than 51
	public void testGetStringHighNumber() {
		String result = CardValue.getString(52);
		assertNull(result);
	}
}
