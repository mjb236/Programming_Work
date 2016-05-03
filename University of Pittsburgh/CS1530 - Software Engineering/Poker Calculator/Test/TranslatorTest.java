import junit.framework.TestCase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;


public class TranslatorTest extends TestCase {

	public TranslatorTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	/**
	 * Test to make sure the translate method returns the proper individual hand Strings.
	 */
	@Test
	public void testTranslate() {
		ArrayList<String> tokens = Translator.translate("67o, 54,AsAc");
		assertNotNull(tokens);
		Iterator it = tokens.iterator();
		assertTrue(it.next().equals("67o"));
		assertTrue(it.next().equals("54"));
		assertTrue(it.next().equals("AsAc"));
	}
	
	/**
	 * Indirectly tests the validateInput method with an escape character.
	 * translate should return a null value.
	 */
	@Test
	public void testValidateInputEscapeCharacter() {
		String input = "67o, \n54,AsAc";
		assertNull(Translator.translate(input));
	}
	
	/**
	 * Indirectly tests the validateInput method - translate should return a non-null value.
	 */
	@Test
	public void testValidateInputTrue() {
		String input = "67o, 54,AsAc";
		assertNotNull(Translator.translate(input));
	}
	
	/**
	 * Indirectly tests the validateInput method - translation should return null value
	 */
	@Test
	public void testValidateInputFalse() {
		String input = "r";
		assertNull(Translator.translate(input));
	}
	
	/**
	 * Indirectly tests the validateInput method - translation should return null value
	 */
	@Test
	public void testValidateInputFalse2() {
		String input = "23o, rK, hahdhd";
		assertNull(Translator.translate(input));
	}
	
	/**
	 * Indirectly test the private correctCase method - should return case-corrected value.
	 */
	@Test
	public void testValidateInputCapitalizations() {
		String input = "67O, 54,aSaC";
		ArrayList<String> tokens = Translator.translate(input);
		assertNotNull(tokens);
		//System.out.println("Input after validate call: " + input);
		Iterator it = tokens.iterator();
		String output = "";
		while(it.hasNext()) {
			output += it.next();			
		}
		
		assertTrue(output.equals("67o54AsAc"));
	}
}
