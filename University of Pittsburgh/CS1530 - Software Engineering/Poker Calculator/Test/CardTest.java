/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Matt Landram
 */
public class CardTest {
    
    public CardTest() {
    }
    
 

    /**
     * Test of getFaceValue method, of class Card.
     */
    @Test
    public void testGetFaceValue() {
        System.out.println("getFaceValue");
        Card instance = new Card("Ah");
        String expResult = "A";
        String result = instance.getFaceValue();
        System.out.println(result);
        assertEquals(expResult, result);
    }

    /**
     * Test of getSuit method, of class Card.
     */
    @Test
    public void testGetSuit() {
        System.out.println("getSuit");
        Card instance = new Card("Ah");
        String expResult = "h";
        String result = instance.getSuit();
        System.out.println(result);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Card.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Card instance = new Card("Ah");
        String expResult = "Ah";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
    /**
     * Test creating a null card
     */
    @Test
    public void testNullCard() {
    	Card c = new Card(null);
    	assertEquals(c.getFaceValue(), "X");
    	assertEquals(c.getSuit(), "x");
    }
}
