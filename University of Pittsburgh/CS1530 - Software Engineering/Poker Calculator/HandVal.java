import java.util.ArrayList;
import java.util.Collections;

public class HandVal {

    private static final int ROYAL_FLUSH = 1, STRAIGHT_FLUSH = 2, FOUR_OF_A_KIND = 3, FULL_HOUSE = 4, FLUSH = 5, STRAIGHT = 6, THREE_OF_A_KIND = 7, TWO_PAIR = 8, PAIR = 9, HIGH_CARD = 10;
    public Card[] hand = new Card[5];
    public ArrayList<Integer> kickers = new ArrayList<>();

    private int handType;
    private int highVal = -1;
    private int lowVal = -1;
    private int numKickers;
    private int kickerIndex = 0;

    public String getHandType() {
        switch (handType) {
            case HIGH_CARD:
                return "High Card";
            case PAIR:
                return "One Pair";
            case TWO_PAIR:
                return "Two Pair";
            case THREE_OF_A_KIND:
                return "Three of a Kind";
            case STRAIGHT:
                return "Straight";
            case FLUSH:
                return "Flush";
            case FULL_HOUSE:
                return "Full House";
            case FOUR_OF_A_KIND:
                return "Four of a Kind";
            case STRAIGHT_FLUSH:
                return "Straight Flush";
            case ROYAL_FLUSH:
                return "Royal Flush";
            default:
                return "ERROR";
        }
    }

    public int getNumKickers() {
        return numKickers;
    }

    public int getKickerIndex() {
        return kickerIndex;
    }

    public void setKickerIndex(int num) {
        kickerIndex = num;
    }

    public void setNumKickers(int num) {
        numKickers = num;
    }

    public int getHighVal() {
        return highVal;
    }

    public int getLowVal() {
        return lowVal;
    }
    
    public int getHandValue() {
    	return handType;
    }

    private void setHighVal(int val) {
        highVal = val;
    }

    private void setLowVal(int val) {
        lowVal = val;
    }

    private void setHandType(int type) {
        handType = type;
    }

    public HandVal(CompleteHand ch) {
    	for(int i = 0; i < 5; i++) {
    		hand[i] = ch.getCard(i);
    	}

        // Determines the strength of the current hand and sets the handtype, high, and low values.
    	getHandVal();

        // Based on the hand type, determine our kicker cards used in tie-breaks.
        getKickers();
    }

    // Returns the next "kicker" card used in tiebreaks
    // When hand type is pair: we return the next highest value card in the hand.
    // When hand type is twopair: we return the only non-paired card.
    // When hand type is flush: we return the next highest value card in the hand.
    public int getNextKicker() {
        // The kickers arrayList is pre-sorted in ascending numeric value. Since our card values are higher the lower their numeric value, this works perfectly.
        // We use kickerIndex to keep track of which kicker we're retrieving
        int kickerValue;

        // If kickerIndex is > our number of kickers, that means we've exhausted them all, and we return a -1 to indicate we're out of kickers.
        if (kickerIndex >= getNumKickers()) {
            kickerValue = -1;
        } else {
            kickerValue = kickers.get(kickerIndex);
            kickerIndex++;
        }

        return kickerValue;
    }

    // Returns the value of a kicker at a specific index; used for debugging.
    public int getSpecificKicker(int val) {
        return kickers.get(val);
    }

    // Based on handtype, determines the kickers, adds them to the kickers arraylist, and sorts them in ascending order.
    private void getKickers() {

        // If we have handtype pair, we need to have 3 kickers (the nonpaired cards)
        if (handType == PAIR) {
            setNumKickers(3);

            // We need to find the three cards that aren't in our pair and add them to the kickers arraylist
            findPairKickers();
        }

        // If we have handtype twopair, we find the 5th, non-paired card and add it to the kickers arraylist
        else if (handType == TWO_PAIR) {
            setNumKickers(1);
            findTwoPairKicker();
        }

        // If we have handtype flush, we add the 3 middle valued cards to the kickers arraylist
        else if (handType == FLUSH) {
            setNumKickers(3);
            findFlushKickers();
        }

        // If we have any other handtype, tiebreakers are broken throug highVal and lowVal, and thus kickers aren't needed.
        else {
            setNumKickers(0);
        }

        // Sorts the kickers in ascending order
        if (!kickers.isEmpty()) {
            Collections.sort(kickers);
        }
    }


    // Finds the 3 middle-valued cards in our flush and sets them as kickers
    private void findFlushKickers() {
        int curVal;

        // Loop through all 5 cards
        for (int i = 0; i < 5; i++) {
            curVal = hand[i].getNumericValue();

            // If we found a card that isn't the lowest or highest value flush card, we add it to kickers.
            if (curVal != getHighVal() && curVal != getLowVal()) {
                kickers.add(curVal);
            }
        }
    }

    // TODO: Finding kickers for twopair and flush are basically identical right now; left in only for readability, but needs refactor.
    // Finds the single non-paired card in our hand and sets that as the kicker
    private void findTwoPairKicker() {
        int curVal;
        int i = 0;
        boolean found = false;

        // while we haven't found anything and we're in-bounds for our 5-card hand
        while (!found && i < 5) {
            curVal = hand[i].getNumericValue();

            // If this card doesn't match either pair, make it the kicker and exit the loop.
            if (curVal != highVal && curVal != lowVal) {
                kickers.add(curVal);
                found = true;
            }
            i++;
        }
    }

    // Adds the 3 non-paired cards to the kicker arraylist
    private void findPairKickers() {
        int curVal;
        // Loop through our 5 cards, ignore the two that match highVal (the paired cards)
        for (int i = 0; i < 5; i++) {
            curVal = hand[i].getNumericValue();
            // If this card isn't in our pair, add it to kickers
            if (curVal != getHighVal()) {
                kickers.add(curVal);
            }
        }
    }

    private void getHandVal() {

        int foundPair, foundTwoPair = -1, foundThreeKind = -1, foundStraight, foundFlush, foundFullHouse, foundFourKind;
        // Step through all possible hand combinations
        findHighCard();                         // Sets highval to the highest valued card.
        foundPair = findPair();             // Sets highval to the highest paired card.

        // If we didn't find a pair, we're not going to find two pair.
        if (foundPair != -1) {
            foundTwoPair = findTwoPair();   // Sets highval to the higher valued, and lowval to the lower valued.
            foundThreeKind = findThreeKind(); // Sets high val to the triple paired card.
        }

        foundStraight = findStraight();     // Sets highval to the end of the straight, and low to the start.
        foundFlush = findFlush();           // Sets highval to the highest value in the flush, and low to the lowest.

        // Can't find a full house unless we found three of a kind & two pair.
        if (foundThreeKind != -1 && foundTwoPair != -1) {
            foundFullHouse = findFullHouse(foundThreeKind); // Sets high val to three of a kind, and low val to the single pair.
        }

        // Can't find four of a kind if we didn't find a pair originally..
        if (foundPair != -1) {
            foundFourKind = findFourKind(); // Sets highval to the paired value if we found four of a kind.
        }

        // Can't find a straight flush if we didn't have both a straight, and a flush.
        if (foundStraight != -1 && foundFlush != -1) {

            // If our high value is an ace, and the low value is a ten, we have a royal flush
            if (highVal == 0 && lowVal == 4) {
                setHandType(ROYAL_FLUSH);
            } else {
                // Fix ace high/low val in the case of an ace->five straight
                setHandType(STRAIGHT_FLUSH);
                if (highVal == 0 && lowVal == 12) {
                    highVal = 9;    // Set the high value to the five
                    lowVal = 0;     // And the low to the ace
                }
            }
        }
    }

    // Set highVal to the highest value card in the hand
    private int findHighCard() {
        int high = 99, cur;

        // Loop through each card and compare to the current high card. Note that lower numeric values are better.
        for (int i = 0; i < 5; i++) {
            cur = hand[i].getNumericValue();

            if (cur < high) {
                high = cur;
            }
        }

        // Set the hands "high" value to the strongest card in the hand.
        setHighVal(high);
        setHandType(HIGH_CARD);
        return high;
    }

    // Search for any pairs, and if any are found, set the paired card as highVal.
    // High holds the paired card.
    private int findPair() {
        int in, out;

        // Loop through each card and check if any cards to the right are paired with it.
        for (int i = 0; i < 5; i++) {

            out = hand[i].getNumericValue();
            // Inner loop for comparison to cards to the right
            for (int j = i+1; j < 5; j++) {
                in = hand[j].getNumericValue();

                // If we found a match...
                if (out == in) {
                    setHighVal(out);    // Sets the high value to the pair we found
                    setHandType(PAIR);  // Update the hand type
                    return out;         // Return the value of our pair.
                }
            }
        }

        // Didn't find a pair, return failure.
        return -1;
    }

    // Search for two pair, and if found, set the high and low values appropriately.
    // High holds the higher ranked pair, and low holds the lower ranked pair.
    private int findTwoPair() {
        int pair1 = -1;
        int pair2, out, in;

        // Start at the left and loop through all cards to the right for comparison.
        for (int i = 0; i < 5; i++) {
            out = hand[i].getNumericValue();

            // Inner loop
            for (int j = i+1; j < 5; j++) {
                in = hand[j].getNumericValue();

                if (out == in && in != pair1) { // Ensure we don't bother comparing card if equals an already found pair (in cases of third of a kind or more)

                    if (pair1 == -1) {  // Have match and no first pair yet.
                        pair1 = in;
                    }
                    else {              // Have match and already have first pair.
                        pair2 = in;

                        // Determine the high and low value, and return success.
                        setHandType(TWO_PAIR);
                        if (pair1 < pair2) {
                            setHighVal(pair1);
                            setLowVal(pair2);
                            return pair1;
                        } else {
                            setHighVal(pair2);
                            setLowVal(pair1);
                            return pair2;
                        }
                    }
                }
            }
        }

        // Didn't find two pair, return failure.
        return -1;
    }

    // Search for a straight, and if found, set high and low values appropriately.
    // High holds the highest value card, and low holds the beginning card.
    private int findStraight() {
        ArrayList<Integer> handSort = new ArrayList<>();
        int left, right;
        int aceLow = 0;

        // Insert all our cards numeric value into the list
        for (int i = 0; i < 5; i++) {
            handSort.add(hand[i].getNumericValue());
        }

        Collections.sort(handSort);     // Sort the list

        // Loop through first four and compare to the next to ensure they're a straight
        for (int i = 0; i < 4; i++) {
            left = handSort.get(i);
            right = handSort.get(i+1);

            // Check for ace to two progression
            if (left == 0 && right == 9) {
                aceLow = 1;
                continue;
            }

            if (left != right-1) { return -1; }
        }

        // Reaching this point means we have an ordered straight
        setHandType(STRAIGHT);

        // Hard check if we're an ace to 5 straight.
        if (aceLow == 1) {
            setLowVal(0);
            setHighVal(9);
        } else {
            setHighVal(handSort.get(0));
            setLowVal(handSort.get(4));
        }
        return highVal;
    }

    // Search for a flush, and set how and low values appropriately if found.
    // High is the value of the highest card, and low is the lowest card.
    private int findFlush() {
        String outSuit, inSuit;

        // REFACTOR: Redundancies with other methods that want a value-sorted arraylist, make method to accomplish this later
        ArrayList<Integer> handSort = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            handSort.add(hand[i].getNumericValue());
        }
        Collections.sort(handSort);

        // Get suit of the first card and compare to all other cards.
        outSuit = hand[0].getSuit();
        for (int i = 1; i < 5; i++) {
            inSuit = hand[i].getSuit();
            if (!outSuit.equals(inSuit)) {
                return -1;
            }
        }

        // We have a flush, set proper high/low values and return success (high value).
        setHandType(FLUSH);
        setHighVal(handSort.get(0));
        setLowVal(handSort.get(4));
        return highVal;
    }

    // Search for a full house, and if found, set high and low values appropriately.
    // Three holds the value of the three of a kind card we found earlier.
    // High holds the value of the 3 of a kind, and low holds the value of the pair.
    private int findFullHouse(int three) {
        int in, out;

        for (int i = 0; i < 5; i++) {
            out = hand[i].getNumericValue();

            // If this card is in our three of a kind already, skip it
            if (out == three) continue;

            for (int j = i+1; j < 5; j++) {
                in = hand[j].getNumericValue();

                // Likewise, skip if we match in our three of a kind again
                if (in == three) continue;

                // We found our match
                if (in == out) {
                    setHandType(FULL_HOUSE);
                    setHighVal(three);
                    setLowVal(out);
                    return out;
                }
            }
        }

        // Didn't find full house, return failure.
        return -1;
    }
    // Search for three of a kind, and if found, set the high value appropriately.
    private int findThreeKind() {
        // Lot of overlap between this and four of a kind; refactor later.
        int in, out, count = 0;

        // Start at left, only check two cards
        for (int i = 0; i < 2; i++) {
            out = hand[i].getNumericValue();

            // Inner loop to compare cards to the right
            for (int j = i+1; j < 5; j++) {
                in = hand[j].getNumericValue();

                // Do we have a match?
                if (out == in) {
                    count++;
                }
            }

            if (count == 2) {
                setHandType(THREE_OF_A_KIND);
                setHighVal(out);
                return out;
            } else {
                count = 0;
            }
        }

        // Didn't find three of a kind, return failure.
        return -1;
    }

    // Search for four of a kind, and if found, set the high values appropriately.
    private int findFourKind() {

        int in, out, count = 0;

        // Start at the leftmost card, only need to check twice
        for (int i = 0; i < 2; i++) {
            out = hand[i].getNumericValue();

            // Compare cards to the right
            for (int j = i+1; j < 5; j++) {
                in = hand[j].getNumericValue();

                // Have a match
                if (out == in) {
                    count++;
                }
            }

            // Did we find four?
            if (count == 3) {
                setHandType(FOUR_OF_A_KIND);
                setHighVal(out);
                return out;
            } else {
                count = 0;
            }
        }

        // Didn't find four of a kind, return failure.
        return -1;
    }

}