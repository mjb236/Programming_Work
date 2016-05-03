import org.junit.Test;
import static org.junit.Assert.*;

public class PokerCalcGUITest {

    // Extends the PokerCalcGUI class and overrides popup errors so they don't annoy us during tests.
    public class TestGUI extends PokerCalcGUI {
        @Override
        public void displayTableCardError() {
            System.out.println("Please enter valid data for the community cards.");
        }
    }

    PokerCalcGUI gt = new TestGUI();

    /**
     * Test that three valid flop cards and null turn/river are considered valid input.
     */
    @Test
    public void testGUIFieldLength1() {
        gt.flopTextField1.setText("Ah");
        gt.flopTextField2.setText("Ad");
        gt.flopTextField3.setText("As");
        assertEquals("Should be valid flop values.", 1, gt.checkFieldLengths());
    }

    /**
     * Test that random input is invalid.
     */
    @Test
    public void testGUIFieldLength2() {
        gt.flopTextField1.setText("Words!");
        gt.flopTextField2.setText("Words!");
        gt.flopTextField3.setText("Words!");
        assertEquals("Should be invalid input", 0, gt.checkFieldLengths());
    }

    /**
     * Test that we can enter values for the flop, turn, and river.
     */
    @Test
    public void testGUIFieldLength3() {
        gt.flopTextField1.setText("Ah");
        gt.flopTextField2.setText("Ak");
        gt.flopTextField3.setText("Ad");
        gt.turnTextField.setText("Kh");
        gt.riverTextField.setText("Kd");
        assertEquals("Should be valid input length.", 1, gt.checkFieldLengths());
    }

    /**
     * Test that we can't use invalid format for cards
     */
    @Test
    public void testGUIFieldLength4() {
        gt.flopTextField1.setText("AA");
        gt.flopTextField1.setText("AB");
        gt.flopTextField1.setText("AC");
        assertEquals("Should be invalid input", 0, gt.checkFieldLengths());
    }

    /**
     * Ensure that resetFields is clearing form values.
     */
    @Test
    public void testGUIReset1() {
        gt.flopTextField1.setText("Ah");
        gt.flopTextField2.setText("Ak");
        gt.flopTextField3.setText("Ad");
        gt.turnTextField.setText("Kh");
        gt.riverTextField.setText("Kd");
        assertEquals("Should be valid input length.", 1, gt.checkFieldLengths());
        gt.resetFields();
        assertEquals("Flop 1 should be empty", "", gt.flopTextField1.getText());
        assertEquals("Flop 2 should be empty", "", gt.flopTextField2.getText());
        assertEquals("Flop 3 should be empty", "", gt.flopTextField3.getText());
        assertEquals("Turn should be empty", "", gt.turnTextField.getText());
        assertEquals("River should be empty", "", gt.riverTextField.getText());
    }

    /**
     * Test hard-coded entries for the player and enemy hands, as well as flop.
     */
    @Test
    public void testGUIDisplayCalculations1() {
        gt.playerTextField.setText("AA");
        gt.enemyTextField.setText("KK");
        gt.flopTextField1.setText("6h");
        gt.flopTextField2.setText("7h");
        gt.flopTextField3.setText("2d");
        assertEquals("Should have valid community cards", 1, gt.checkFieldLengths());
        gt.displayCalculations();
        assertEquals("Player should have AA", "AA", gt.playerTextField.getText());
        assertEquals("Enemy should have KK", "KK", gt.enemyTextField.getText());
        assertEquals("Flop 1 should be 6h", "6h", gt.flopTextField1.getText());
        assertEquals("Flop 2 should be 7h", "7h", gt.flopTextField2.getText());
        assertEquals("Flop 3 should be 2d", "2d", gt.flopTextField3.getText());
    }

    /**
     * Test hard coded hand entry win percent
     */
    @Test
    public void testGUIDisplayCalculation2() {
        gt.playerTextField.setText("AhAs");
        gt.enemyTextField.setText("2h2d");
        gt.flopTextField1.setText("Ad");
        gt.flopTextField2.setText("Ac");
        gt.flopTextField3.setText("2c");
        gt.turnTextField.setText("2s");
        gt.riverTextField.setText("5s");
        assertEquals("Should have valid community cards", 1, gt.checkFieldLengths());
        gt.displayCalculations();
        assertEquals("Player should have AhAs", "AhAs", gt.playerTextField.getText());
        assertEquals("Enemy should have 2h2d", "2h2d", gt.enemyTextField.getText());
        assertEquals("Flop 1 should be Ad", "Ad", gt.flopTextField1.getText());
        assertEquals("Flop 2 should be Ac", "Ac", gt.flopTextField2.getText());
        assertEquals("Flop 3 should be 2c", "2c", gt.flopTextField3.getText());
        assertEquals("Turn should be 2s", "2s", gt.turnTextField.getText());
        assertEquals("River should be 5s", "5s", gt.riverTextField.getText());
        assertEquals("Players should have 100% win chance", 1.0D, gt.playerWinPct, 0.001);
        assertEquals("Enemy should have 0% win chance", 0.0D, gt.enemyWinPct, 0.001);
        assertEquals("Tie chance should be 0%", 0.0D, gt.tiePct, 0.001);
    }

}
