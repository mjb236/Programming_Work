import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;


public class DeckTester extends Deck{

	//Tests whether the deck initialized with the right number of cards(52)
	//Also tests deck length function since we know 52 is the number of cards in a deck
	@Test
    public void testDeckCreation(){
        System.out.println("Deck Test return length");
        Deck deck = new Deck();
        deck.shuffle();
        System.out.println("Number of cards after creation: " + deck.getLength());
        assertEquals(52, deck.getLength());
    }
	     
    //test random draw from deck. Since the deck draw is random, the test tests 
	//whether or not one card was drawn. Prints card removed.
 	@Test
	public void testDeckDraw(){
 		System.out.println("\nDeck Test check if one card was removed");
	 	Deck deck1 = new Deck();
	 	deck1.shuffle();
	 	Card drawnCard = deck1.draw();
	 	System.out.print("Random card drawn: " + drawnCard);
	 	assertEquals(51, deck1.getLength());
	 }
	 
 	//Tests draw specific card function
	@Test
	public void testDeckDrawSpecificCard(){
		System.out.println("\nDeck Test: check if specific card(2 of spades or 2s card was removed)");
		Deck deck2 = new Deck();
		deck2.shuffle();
		Card drawnCard = deck2.draw("2s");
		System.out.println("Card specified in test: 2s");
		System.out.println("Card taken from fdraw function: " + drawnCard.getFaceValue() + " " + drawnCard.getSuit());
		String test = new String();
		test = drawnCard.getFaceValue() + "" + drawnCard.getSuit();
		assertEquals(51, deck2.getLength());
		assertEquals("2s", test);
	}
	 	
	@Test
	//Test add card to deck
	public void testAddDeck(){
		//create deck and add joke
		System.out.println("\nDeck Test: check if a card can be drawn added:");
		Deck deck3 = new Deck();
		deck3.shuffle();
		
		Card drawnCard = deck3.draw("8h");
		String test = new String();
		test = drawnCard.getFaceValue() + "" + drawnCard.getSuit();
		assertTrue(test.equals("8h"));
		deck3.addToDeck("8h");
		System.out.println("\nCard drawn:" + drawnCard);
		assertEquals(52, deck3.getLength());
	}
	 	
	@Test
	//Test if an invalid card can be added to the deck
	public void testAddInvalidCard() {
		Deck d = new Deck();
		d.draw();
	 	assertEquals(d.getLength(), 51);
	 	d.addToDeck("JB");
	 	assertEquals(d.getLength(), 51);
	}
	 	
	@Test
	//tests the deck's toString function before and after shuffling
	public void testDeckToString() {
		Deck d = new Deck();
		System.out.println("******* Deck *******" + d);
		System.out.println("***** After Shuffle ******" + d);
		assertTrue(true);
	}
}
