import java.util.*;

public class Translator {

	/**
	 * Private constructor - static class not meant to be instantiated
	 */
	private Translator() {}
	
	/**
	 * Ensures that the characters in the input conform to standard poker notation. Cards should
	 * be capitalized, suit choices should be lower case.
	 * @param input String of poker hands only with valid characters
	 * @return Case-corrected String of poker hands.
	 */
	private static String correctCase(String input) {
		//replace letters with their correct capitalizations
		input = input.replaceAll("a", "A");
    	input = input.replaceAll("k", "K");
    	input = input.replaceAll("q", "Q");
    	input = input.replaceAll("j", "J");
    	input = input.replaceAll("t", "T");
    	input = input.replaceAll("S", "s");
    	input = input.replaceAll("D", "d");
    	input = input.replaceAll("C", "c");
    	input = input.replaceAll("H", "h");
    	input = input.replaceAll("O", "o");
    	
    	return input;
	} //ends correctCase
	
	/**
	 * Checks that input consists of valid characters.
	 * @param input String of user defined poker hands.
	 * @return Validated String of poker hands.
	 */
	private static String validateInput(String input) {
		//return null if input contains characters that are not valid for card hands
		if(input.isEmpty())
			return null;
		for(int i = 0; i < input.length(); i++) {
			char ch = input.charAt(i);
			if (!(ch >= '0' && ch <= '9')) {
				String ltr = ch + "";
			    String validLetters = "AaKkQqJjTtSsDdHhCcOo, ";
			    			    
			    if (!validLetters.contains(ltr)) {
			    	return null;	    		
			    } //ends inner if
			} //ends outer if
		} //ends for
		
		input = correctCase(input);
 		
		return input;
	} //ends validateInput
	
	/**
	 * Translates a String of poker hands, separated by commas, into an ArrayList of Strings
	 * representing each individual hand.
	 * @param input String of poker hands defined by user.
	 * @return ArrayList<String> containing each individual hand listing.
	 */
	public static ArrayList<String> translate(String input) {
		//declare the list of strings
		ArrayList<String> tokens = new ArrayList<String>();
		
		//trim the input
		input = input.trim();
		
		//validate the input, return null if input invalid
		input = validateInput(input);
		if(input == null) {
			return null;
		}
			
		int i = 0;
		//separate the hands into their own individual strings
		while(input.length() != 0) {
			String curr = "";
			if(input.charAt(i) == ',') {
				curr = input.substring(0, i);
				curr = curr.trim();
				tokens.add(curr);
				input = input.substring(i+1);
				i = 0;
			} else if (i == (input.length()-1)) {
				curr = input.substring(0);
				curr = curr.trim();
				tokens.add(curr);
				input = "";
			} else {
				i++;
			} //ends if-else
		} //ends while
		
		return tokens;
	} //ends translate
} //ends Translator