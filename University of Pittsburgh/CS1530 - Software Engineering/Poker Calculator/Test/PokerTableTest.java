import static org.junit.Assert.*;

import org.junit.Test;

public class PokerTableTest {

	/**
	 * Ensure that a poker table given no information returns null cards.
	 */
	@Test
	public void testNullTable() {
		PokerTable pt = new PokerTable();
		for(int i = 0; i < 3; i++) {
			assertNull(pt.getFlop(i));
		}
		assertNull(pt.getTurn());
		assertNull(pt.getRiver());
	}
	
	/**
	 * Test that a poker table given specific cards will return those cards appropriately.
	 */
	@Test
	public void testGoodTable() {
		Card flop1 = new Card("Ah");
		Card flop2 = new Card("8c");
		Card flop3 = new Card("3d");
		Card turn = new Card("Jc");
		Card river = new Card("9s");
		
		PokerTable pt = new PokerTable(flop1, flop2, flop3, turn, river);
		
		assertEquals(flop1.getFullNumericValue(), pt.getFlop(0).getFullNumericValue());
		assertEquals(flop2.getFullNumericValue(), pt.getFlop(1).getFullNumericValue());
		assertEquals(flop3.getFullNumericValue(), pt.getFlop(2).getFullNumericValue());
		assertEquals(turn.getFullNumericValue(), pt.getTurn().getFullNumericValue());
		assertEquals(river.getFullNumericValue(), pt.getRiver().getFullNumericValue());
	}
	
	/**
	 * Test that a poker table given a comma-separated string of cards will return those cards appropriately.
	 */
	@Test
	public void testTableStringSetup() {
		String cards = "Ah, 8c, 3d, Jc, 9s";
		PokerTable pt = new PokerTable(cards);
		
		assertTrue(pt.getFlop(0).toString().equals("Ah"));
		assertTrue(pt.getFlop(1).toString().equals("8c"));
		assertTrue(pt.getFlop(2).toString().equals("3d"));
		assertTrue(pt.getTurn().toString().equals("Jc"));
		assertTrue(pt.getRiver().toString().equals("9s"));
	}
	
	/**
	 * Test that a poker table given a null turn card returns those cards appropriately.
	 */
	@Test
	public void testTableNullTurn() {
		Card flop1 = new Card("Ah");
		Card flop2 = new Card("8c");
		Card flop3 = new Card("3d");
		Card turn = null;
		Card river = new Card("9s");
		
		PokerTable pt = new PokerTable(flop1, flop2, flop3, turn, river);
		
		assertNotNull(pt.getFlop(0));
		assertNotNull(pt.getFlop(1));
		assertNotNull(pt.getFlop(2));
		assertNull(pt.getTurn());
		assertNotNull(pt.getRiver());
	}
	
	/**
	 * Test that an out of bounds call to getFlop returns null
	 */
	@Test
	public void testOOBFlopCall() {
		PokerTable pt = new PokerTable("Ah, 8c, 3d, Jc, 9s");
		assertNull(pt.getFlop(-1));
		assertNull(pt.getFlop(3));
		assertNull(pt.getFlop(10000));
		assertNull(pt.getFlop(Integer.MAX_VALUE));
		assertNull(pt.getFlop(Integer.MIN_VALUE));
	}
}
