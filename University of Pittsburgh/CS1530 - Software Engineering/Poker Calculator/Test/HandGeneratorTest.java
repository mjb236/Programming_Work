import static org.junit.Assert.*;
import java.util.*;

import org.junit.Test;


public class HandGeneratorTest {

	@Test
	/**
	 * Test that QQ enters the Offhand combo method
	 */
	public void testGenerateHoleHand() {
		ArrayList<String> input = new ArrayList<String>();
		input.add("QQ");
		System.out.println("***TESTING QQ***");
		ArrayList<HoleHand> holeHands = HandGenerator.generateHoleHands(input);
				
		assertNotNull(holeHands);
		for(HoleHand hh : holeHands) {
			System.out.println(hh.getCard1().toString() + hh.getCard2().toString());
		}
	}
	
	@Test
	/**
	 * Test that KQ enters the suited combo method
	 */
	public void testGenerateHoleHandKQ() {
		ArrayList<String> input = new ArrayList<String>();
		input.add("KQ");
		System.out.println("***TESTING KQ***");
		ArrayList<HoleHand> holeHands = HandGenerator.generateHoleHands(input);
				
		assertNotNull(holeHands);
		for(HoleHand hh : holeHands) {
			System.out.println(hh.getCard1().toString() + hh.getCard2().toString());
		}
		
	}
	
	@Test
	/**
	 * Test that KQo enters the Offhand combo method
	 */
	public void testGenerateHoleHandKQo() {
		ArrayList<String> input = new ArrayList<String>();
		input.add("KQo");
		System.out.println("***TESTING KQo***");
		ArrayList<HoleHand> holeHands = HandGenerator.generateHoleHands(input);
				
		assertNotNull(holeHands);
		for(HoleHand hh : holeHands) {
			System.out.println(hh.getCard1().toString() + hh.getCard2().toString());
		}
	}
	
	@Test
	/**
	 * Test that QhQ enters the Offhand combo with specified method
	 */
	public void testGenerateHoleHandQhQ() {
		ArrayList<String> input = new ArrayList<String>();
		input.add("QhQ");
		System.out.println("***TESTING QhQ***");
		ArrayList<HoleHand> holeHands = HandGenerator.generateHoleHands(input);
				
		assertNotNull(holeHands);
		
		for(HoleHand hh : holeHands) {
			System.out.println(hh.getCard1().toString() + hh.getCard2().toString());
		}
	}
	
	@Test
	/**
	 * Test that QQh enters the Offhand combo with specified method
	 */
	public void testGenerateHoleHandQQh() {
		ArrayList<String> input = new ArrayList<String>();
		input.add("QQh");
		System.out.println("***TESTING QQh***");
		ArrayList<HoleHand> holeHands = HandGenerator.generateHoleHands(input);
				
		assertNotNull(holeHands);
		for(HoleHand hh : holeHands) {
			System.out.println(hh.getCard1().toString() + hh.getCard2().toString());
		}
	}
	
	@Test
	/**
	 * Test that J5c enters the offsuit with specified method and returns the correct combos
	 */
	public void testGenerateHoleHandJ5c() {
		ArrayList<String> input = new ArrayList<String>();
		input.add("J5c");
		System.out.println("***TESTING J5c***");
		ArrayList<HoleHand> holeHands = HandGenerator.generateHoleHands(input);
				
		assertNotNull(holeHands);
		for(HoleHand hh : holeHands) {
			System.out.println(hh.getCard1().toString() + hh.getCard2().toString());
		}	
	}
	
	@Test
	/**
	 * Test that QdQh enters the single hand
	 */
	public void testGenerateHoleHandQdQh() {
		ArrayList<String> input = new ArrayList<String>();
		input.add("QdQh");
		System.out.println("***TESTING QdQh***");
		ArrayList<HoleHand> holeHands = HandGenerator.generateHoleHands(input);
				
		assertNotNull(holeHands);
		assertEquals(holeHands.size(), 1);
		Iterator it = holeHands.iterator();
		HoleHand h = (HoleHand) it.next();
		
		assertEquals(h.getCard1().toString(), "Qd");
		assertEquals(h.getCard2().toString(), "Qh");	
	}
	
	@Test
	/**
	 * Test that null returns null
	 */
	public void testGenerateHoleHandNull() {
		ArrayList<String> input = new ArrayList<String>();
		input.add(null);
		System.out.println("***TESTING NULL***");
		ArrayList<HoleHand> holeHands = HandGenerator.generateHoleHands(input);
				
		assertNull(holeHands);
	}
	
	@Test
	/**
	 * Test that a hand with too many faces returns null
	 */
	public void testGenerateHoleHandInvalidAllFaces() {
		ArrayList<String> input = new ArrayList<String>();
		input.add("AAA");
		System.out.println("***TESTING TOO MANY FACES***");
		ArrayList<HoleHand> holeHands = HandGenerator.generateHoleHands(input);
				
		assertNull(holeHands);
	}
	
	@Test
	/**
	 * Test that a hand with a suit first returns null
	 */
	public void testGenerateHoleHandInvalidSuitFirst() {
		ArrayList<String> input = new ArrayList<String>();
		input.add("hAA");
		System.out.println("***TESTING SUIT FIRST***");
		ArrayList<HoleHand> holeHands = HandGenerator.generateHoleHands(input);
				
		assertNull(holeHands);
	}
	
	@Test
	/**
	 * Test that a hand that is too long returns null
	 */
	public void testGenerateHoleHandInvalidTooLong() {
		ArrayList<String> input = new ArrayList<String>();
		input.add("hAAhA");
		System.out.println("***TESTING SUIT FIRST***");
		ArrayList<HoleHand> holeHands = HandGenerator.generateHoleHands(input);
				
		assertNull(holeHands);
	}
	
	@Test
	/**
	 * Test that a hand that has invalid characters returns null
	 */
	public void testGenerateHoleHandInvalidChars() {
		ArrayList<String> input = new ArrayList<String>();
		input.add("GlYe");
		System.out.println("***TESTING SUIT FIRST***");
		ArrayList<HoleHand> holeHands = HandGenerator.generateHoleHands(input);
				
		assertNull(holeHands);
	}

}
