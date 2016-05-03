import static org.junit.Assert.*;
import org.junit.Test;

public class HandValTest {

    // Hand Value translations
    private static final int ACE = 0, KING = 1, QUEEN = 2, JACK = 3, TEN = 4, NINE = 5,
            EIGHT = 6, SEVEN = 7, SIX = 8, FIVE = 9, FOUR = 10, THREE = 11, TWO = 12;

    // Card definitions
    Card twoH = new Card("2h");
    Card threeH = new Card("3h");
    Card fourH = new Card("4h");
    Card fiveH = new Card("5h");
    Card sixH = new Card("6h");
    Card sevenH = new Card("7h");
    Card eightH = new Card("8h");
    Card nineH = new Card("9h");
    Card tenH = new Card("Th");
    Card jackH = new Card("Jh");
    Card queenH = new Card("Qh");
    Card kingH = new Card("Kh");
    Card aceH = new Card("Ah");

    Card twoS = new Card("2s");
    Card threeS = new Card("3s");
    Card fourS = new Card("4s");
    Card fiveS = new Card("5s");
    Card sixS = new Card("6s");
    Card sevenS = new Card("7s");
    Card eightS = new Card("8s");
    Card nineS = new Card("9s");
    Card tenS = new Card("Ts");
    Card jackS = new Card("Js");
    Card queenS = new Card("Qs");
    Card kingS = new Card("Ks");
    Card aceS = new Card("As");

    Card twoD = new Card("2d");
    Card threeD = new Card("3d");
    Card fourD = new Card("4d");
    Card fiveD = new Card("5d");
    Card sixD = new Card("6d");
    Card sevenD = new Card("7d");
    Card eightD = new Card("8d");
    Card nineD = new Card("9d");
    Card tenD = new Card("Td");
    Card jackD = new Card("Jd");
    Card queenD = new Card("Qd");
    Card kingD = new Card("Kd");
    Card aceD = new Card("Ad");

    Card twoC = new Card("2c");
    Card threeC = new Card("3c");
    Card fourC = new Card("4c");
    Card fiveC = new Card("5c");
    Card sixC = new Card("6c");
    Card sevenC = new Card("7c");
    Card eightC = new Card("8c");
    Card nineC = new Card("9c");
    Card tenC = new Card("Tc");
    Card jackC = new Card("Jc");
    Card queenC = new Card("Qc");
    Card kingC = new Card("Kc");
    Card aceC = new Card("Ac");

    // Test hands
    HandVal testHand;
    CompleteHand comHand;

    // Start testing 5 card hands, first just by checking for a high val
    @Test
    public void testHighCard1() throws Exception {
        comHand = new CompleteHand(aceH, kingH, queenH, jackH, twoS);
        testHand = new HandVal(comHand);
        assertEquals("Should have HandType of High Card", "High Card", testHand.getHandType());
        assertEquals("High card should be Ace (val 0)", ACE, testHand.getHighVal());
        assertEquals("Low value shouldn't exist (-1)", -1, testHand.getLowVal());
    }

    // 5 card hand with a pair of jacks
    @Test
    public void testPair1() throws Exception {
        comHand = new CompleteHand(aceH, kingH, queenH, jackH, jackS);
        testHand = new HandVal(comHand);
        assertEquals("Should have handtype of one pair", "One Pair", testHand.getHandType());
        assertEquals("Should have a high/pair value of jack (val 3)", JACK, testHand.getHighVal());
        assertEquals("Low value shouldn't exist (-1)", -1, testHand.getLowVal());
    }

    // 5 card hand with pair of twos in different order
    @Test
    public void testTwoPair1() throws Exception {
        comHand = new CompleteHand(twoH, aceH, twoS, jackH, tenH);
        testHand = new HandVal(comHand);
        assertEquals("Should have handtype of one pair", "One Pair", testHand.getHandType());
        assertEquals("High/pair value should be a 2 (val 13)", TWO, testHand.getHighVal());
    }

    // 5 card hand with two pairs
    @Test
    public void testTwoPair2() {
        comHand = new CompleteHand(aceH, aceS, kingH, kingS, twoH);
        testHand = new HandVal(comHand);
        assertEquals("Should have handtype of two pair", "Two Pair", testHand.getHandType());
        assertEquals("Should have high val/pair of ace (val 0)", ACE, testHand.getHighVal());
        assertEquals("Should have low val/pair of king (val 1)", KING, testHand.getLowVal());
    }

    // 5 card hand with two pairs in different order
    @Test
    public void testTwoPair3() {
        comHand = new CompleteHand(twoH, aceH, kingH, twoS, aceS);
        testHand = new HandVal(comHand);
        assertEquals("Should have handtype of two pair", "Two Pair", testHand.getHandType());
        assertEquals("Should have high val of ace (val 0)", ACE, testHand.getHighVal());
        assertEquals("Should have low val of two (val 13)", TWO, testHand.getLowVal());
    }

    // Testing three of a kind
    @Test
    public void testThreeKind1() {
        comHand = new CompleteHand(fiveH, sixC, fiveS, fiveD, aceD);
        testHand = new HandVal(comHand);
        assertEquals("Should have handtype of three of a kind", "Three of a Kind", testHand.getHandType());
        assertEquals("Should have high val of five", FIVE, testHand.getHighVal());
    }

    @Test
    public void testThreeKind2() {
        comHand = new CompleteHand(aceH, kingH, aceS, aceD, tenH);
        testHand = new HandVal(comHand);
        assertEquals("Should have handtype of three of a kind", "Three of a Kind", testHand.getHandType());
        assertEquals("Should have high val of ace", ACE, testHand.getHighVal());
    }

    // Straight from 4 to 8
    @Test
    public void testStraight1() {
        comHand = new CompleteHand(fourH, fiveS, sixH, sevenS, eightH);
        testHand = new HandVal(comHand);
        assertEquals("Should have HandType of straight", "Straight", testHand.getHandType());
        assertEquals("Should have highval of eight (7)", EIGHT, testHand.getHighVal());
        assertEquals("Should have lowval of four (11)", FOUR, testHand.getLowVal());
    }

    // Straight starting at ace
    @Test
    public void testStraight2() {
        comHand = new CompleteHand(aceH, twoS, threeS, fourS, fiveH);
        testHand = new HandVal(comHand);
        assertEquals("Should have HandType of straight", "Straight", testHand.getHandType());
        assertEquals("Should have high val of five (9)", FIVE, testHand.getHighVal());
        assertEquals("Should have low val of ace (0)", ACE, testHand.getLowVal());
    }

    // Find a flush
    @Test
    public void testFlush1() {
        comHand = new CompleteHand(aceH, kingH, queenH, tenH, twoH);
        testHand = new HandVal(comHand);
        assertEquals("Should have HandType of flush", "Flush", testHand.getHandType());
        assertEquals("Should have highval of ace (0)", ACE, testHand.getHighVal());
        assertEquals("Should have lowval of two (13)", TWO, testHand.getLowVal());
    }

    // More flush testing
    @Test
    public void testFlush2() {
        comHand = new CompleteHand(twoH, threeH, aceH, queenH, fiveH);
        testHand = new HandVal(comHand);
        assertEquals("Should have handtype of flush", "Flush", testHand.getHandType());
        assertEquals("Should have high val of ace", ACE, testHand.getHighVal());
        assertEquals("Should have low val of two", TWO, testHand.getLowVal());
    }

    // Test four of a kind
    @Test
    public void testFourKind1() {
        comHand = new CompleteHand(fiveH, twoH, fiveC, fiveD, fiveS);
        testHand = new HandVal(comHand);
        assertEquals("Should have HandType of four of a kind", "Four of a Kind", testHand.getHandType());
        assertEquals("Should have high val of five", FIVE, testHand.getHighVal());
    }

    @Test
    public void testFourKind2() {
        comHand = new CompleteHand(aceH, aceC, aceD, aceS, kingH);
        testHand = new HandVal(comHand);
        assertEquals("Should have handtype of four of a kind", "Four of a Kind", testHand.getHandType());
        assertEquals("Should have high value of ace", ACE, testHand.getHighVal());
    }

    @Test
    public void testFourKind3() {
        comHand = new CompleteHand(fiveH, threeH, threeS, threeC, threeD);
        testHand = new HandVal(comHand);
        assertEquals("Should have handtype of four of a kind", "Four of a Kind", testHand.getHandType());
        assertEquals("Should have high value of three", THREE, testHand.getHighVal());
    }

    @Test
    public void testStraightFlush1() {
        comHand = new CompleteHand(aceH, twoH, threeH, fourH, fiveH);
        testHand = new HandVal(comHand);
        assertEquals("Should have handtype of straight flush", "Straight Flush", testHand.getHandType());
        assertEquals("Should have high val of five", FIVE, testHand.getHighVal());
        assertEquals("Should have low val of ace", ACE, testHand.getLowVal());
    }

    @Test
    public void testStraightFlush2() {
        comHand = new CompleteHand(eightH, tenH, nineH, jackH, queenH);
        testHand = new HandVal(comHand);
        assertEquals("Should have handtype of straight flush", "Straight Flush", testHand.getHandType());
        assertEquals("Should have high val of queen", QUEEN, testHand.getHighVal());
        assertEquals("Should have low val of eight", EIGHT, testHand.getLowVal());
    }

    @Test
    public void testRoyalFlush1() {
        comHand = new CompleteHand(aceH, tenH, queenH, jackH, kingH);
        testHand = new HandVal(comHand);
        assertEquals("Should have handtype Royal Flush", "Royal Flush", testHand.getHandType());
        assertEquals("Should have highval of ace", ACE, testHand.getHighVal());
        assertEquals("Should have lowval of ten", TEN, testHand.getLowVal());
    }

    @Test
    public void testRoyalFlush2() {
        comHand = new CompleteHand(tenC, kingC, aceC, jackC, queenC);
        testHand = new HandVal(comHand);
        assertEquals("Should have handtype Royal Flush", "Royal Flush", testHand.getHandType());
        assertEquals("Should have highval of ace", ACE, testHand.getHighVal());
        assertEquals("Should have lowval of ten", TEN, testHand.getLowVal());
    }

    @Test
    public void testFullHouse1() {
        comHand = new CompleteHand(twoC, aceC, twoH, aceH, twoD);
        testHand = new HandVal(comHand);
        assertEquals("Should have handtype Full House", "Full House", testHand.getHandType());
        assertEquals("Should have high val of two", TWO, testHand.getHighVal());
        assertEquals("Should have low val of ace", ACE, testHand.getLowVal());
    }

    @Test
    public void testFullHouse2() {
        comHand = new CompleteHand(fiveC, kingD, kingH, fiveS, fiveH);
        testHand = new HandVal(comHand);
        assertEquals("Should have handtype Full House", "Full House", testHand.getHandType());
        assertEquals("Should have high val of five", FIVE, testHand.getHighVal());
        assertEquals("Should have low val of king", KING, testHand.getLowVal());
    }

    // Tests for getnextkicker

    // Given [10h, 10s, 2d, Kd, Ad], we have a pair of 10's, and the kickers should be: ACE, KING, TWO
    @Test
    public void testKicker1() {
        comHand = new CompleteHand(tenH, tenS, twoD, kingD, aceD);
        testHand = new HandVal(comHand);
        assertEquals("Should have handtype One Pair", "One Pair", testHand.getHandType());
        assertEquals("Should have high val of ten", TEN, testHand.getHighVal());
        assertEquals("First kicker should be Ace", ACE, testHand.getNextKicker());
        assertEquals("Second kicker should be King", KING, testHand.getNextKicker());
        assertEquals("Third kicker should be Two", TWO, testHand.getNextKicker());
        assertEquals("Fourth kicker doesn't exist (-1)", -1, testHand.getNextKicker());
        assertEquals("Fifth kicker also shouldn't exist (-1)", -1, testHand.getNextKicker());
    }

    // Given [5h, 5s, Kh, Jd, 3s], have pair of 5's, kickers of: KING, JACK, 3
    @Test
    public void testKicker2() {
        comHand = new CompleteHand(fiveH, fiveS, kingH, jackD, threeS);
        testHand = new HandVal(comHand);
        assertEquals("Should have One Pair", "One Pair", testHand.getHandType());
        assertEquals("Should have highVal of Five", FIVE, testHand.getHighVal());
        assertEquals("First kicker should be King", KING, testHand.getNextKicker());
        assertEquals("Second kicker should be Jack", JACK, testHand.getNextKicker());
        assertEquals("Third kicker should be Three", THREE, testHand.getNextKicker());
        assertEquals("Fourth kicker doesn't exist (-1)", -1, testHand.getNextKicker());
    }

    // Given [5h, 5s, 6h, 6s, Kh], have two pair (6s, 5s) and a kicker of KING
    @Test
    public void testKicker3() {
        comHand = new CompleteHand(fiveH, fiveS, sixH, sixS, kingH);
        testHand = new HandVal(comHand);
        assertEquals("Should have Two Pair", "Two Pair", testHand.getHandType());
        assertEquals("highVal pair should be 6", SIX, testHand.getHighVal());
        assertEquals("lowVal pair should be 5", FIVE, testHand.getLowVal());
        assertEquals("Kicker should be King", KING, testHand.getNextKicker());
        assertEquals("Second kicker shouldn't exist", -1, testHand.getNextKicker());
    }

    // Given [Ah, 6s, Ad, 3s, 6h], have two pair (A,6), and kicker of 3
    @Test
    public void testKicker4() {
        comHand = new CompleteHand(aceH, sixS, aceD, threeS, sixH);
        testHand = new HandVal(comHand);
        assertEquals("Should have Two Pair", "Two Pair", testHand.getHandType());
        assertEquals("highVal pair should be Ace", ACE, testHand.getHighVal());
        assertEquals("lowVal pair should be six", SIX, testHand.getLowVal());
        assertEquals("First kicker should be three", THREE, testHand.getNextKicker());
        assertEquals("Second kicker shouldn't exist -1", -1, testHand.getNextKicker());
    }

    // Given [Ah, 5h, 6h, 7h, 9h], have ace high flush with low of 5, kickers of NINE, SEVEN, SIX
    @Test
    public void testKicker5() {
        comHand = new CompleteHand(aceH, fiveH, sixH, sevenH, nineH);
        testHand = new HandVal(comHand);
        assertEquals("Should have handtype flush", "Flush", testHand.getHandType());
        assertEquals("Should have highVal of Ace", ACE, testHand.getHighVal());
        assertEquals("Should have lowVal of Five", FIVE, testHand.getLowVal());
        assertEquals("First kicker should be Nine", NINE, testHand.getNextKicker());
        assertEquals("Second kicker should be Seven", SEVEN, testHand.getNextKicker());
        assertEquals("Third kicker should bee Six", SIX, testHand.getNextKicker());
        assertEquals("Fourth kicker shouldnt exist -1", -1, testHand.getNextKicker());
    }

    // Given [6s, 9s, Js, 2s, Ks], have King high flush, low of 2, kickers of Jack, Nine, Six
    @Test
    public void testKicker6() {
        comHand = new CompleteHand(sixS, nineS, jackS, twoS, kingS);
        testHand = new HandVal(comHand);
        assertEquals("Should have handtype flush", "Flush", testHand.getHandType());
        assertEquals("Should have highval of King", KING, testHand.getHighVal());
        assertEquals("Should have lowval of Two", TWO, testHand.getLowVal());
        assertEquals("First kicker should be Jack", JACK, testHand.getNextKicker());
        assertEquals("Second kicker should be Nine", NINE, testHand.getNextKicker());
        assertEquals("Third kicker should be Six", SIX, testHand.getNextKicker());
        assertEquals("Fourth kicker shouldnt exist -1", -1, testHand.getNextKicker());
    }

    // Given [Ah, 2h, 6h, 7h, 8h], have Ace high flush, low val of 2, kickers: 8, 7, 6
    @Test
    public void testKicker7() {
        comHand = new CompleteHand(aceH, twoH, sixH, sevenH, eightH);
        testHand = new HandVal(comHand);
        assertEquals("Should have handtype flush", "Flush", testHand.getHandType());
        assertEquals("Should have highval of Ace", ACE, testHand.getHighVal());
        assertEquals("Shoudl have lowval of Two", TWO, testHand.getLowVal());
        assertEquals("First kicker should be Eight", EIGHT, testHand.getNextKicker());
        assertEquals("Second kicker should be Seven", SEVEN, testHand.getNextKicker());
        assertEquals("Third kicker should be Six", SIX, testHand.getNextKicker());
        assertEquals("Fourth kicker shouldn't exist -1", -1, testHand.getNextKicker());
    }
}
