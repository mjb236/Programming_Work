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
 * @author PokerCalc
 */
public class PokerCalcTest {
    
    public PokerCalcTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    public void testString() {
        String str = "Hello, World!";
        String hello = "Hello, World!";
        assertEquals(str,hello);
    }
    /**
     * Test of main method, of class PokerCalc.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        PokerCalc.main(args);
        // TODO review the generated test code and remove the default call to fail.
        testString();
    }
    
}
