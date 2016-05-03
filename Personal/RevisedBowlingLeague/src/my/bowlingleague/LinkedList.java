package my.bowlingleague;

/**
 * Linked list class for storing bowlers.
 * JDK 8
 * NetBeans 8.0
 * @author Michael Bowen
 */
public class LinkedList {
    private Node first;
    private Node last;
    private boolean alphabetic;
    
    /**
     * Constructor that creates an empty linked list.
     * @param alphabetic True if list should be alphabetized, false if the list should be sorted by average.
     */
    public LinkedList(boolean alphabetic) {
        first = null;
        last = null;
        this.alphabetic = alphabetic;
    } //ends constructor
    
    /**
     * Check to see if the linked list is empty.
     * @return True if the list empty, false otherwise.
     */
    public boolean isEmpty() {
        return first == null;
    } //ends isEmpty
   
    /**
     * The size method returns the length of the list.
     * @return The number of items in the list
     */
    public int size() {
        int count = 0;
        Node temp = first;
        while(temp != null) {
            count++;
            temp = temp.getNext();
        } //ends while loop
        
        return count;
    } //ends size
    
    /**
     * Method to add a bowler to the list in order.
     * @param b Bowler to add to the linked list.
     */
    public void add(Bowler b) {
        //check if list is empty. if so, bowler being added is the new head
        if(isEmpty()) {
            first = new Node(b);
            last = first;
        } else {
            Node curr = first;
            Node prev = null;
            if(alphabetic) {
                while(curr != null) {
                    //if current bowler's name is after new bowlers's name
                    if(curr.getBowler().getName().compareTo(b.getName()) > 0) {
                        if(prev == null) {
                            //add bowler to head of list
                            Node newHead = new Node(b, first);
                            first = newHead;
                            //set curr to null to exit the loop
                            curr = null;
                        } else {
                            //add bowler to middle of list
                            prev.setNext(new Node(b, curr));
                            //set curr to null to end the loop
                            curr = null;
                        } //ends else
                    } else {
                        //new bowler belongs 
                        prev = curr;
                        curr = curr.getNext();
                    } //ends if-else
                } //ends while
                //add bowler to the end of list
                if(prev == last) {
                    prev.setNext(new Node(b));
                    last = last.getNext();
                } //ends if                
            } //ends if alphabetic
            else {
                while(curr != null) {
                    //if current bowler's avgerage is larger than new bowlers's average
                    if(curr.getBowler().getAvg() < b.getAvg()) {
                        if(prev == null) {
                            //add bowler to head of list
                            Node newHead = new Node(b, first);
                            first = newHead;
                            //set curr to null to exit the loop
                            curr = null;
                        } else {
                            //add bowler to middle of list
                            prev.setNext(new Node(b, curr));
                            //set curr to null to end the loop
                            curr = null;
                        } //ends else
                    } else {
                        //new bowler belongs 
                        prev = curr;
                        curr = curr.getNext();
                    } //ends if-else
                } //ends while
                //add bowler to the end of list
                if(prev == last) {
                    prev.setNext(new Node(b));
                    last = last.getNext();
                } //ends if
            } //ends if not alphabetic
        } //ends else
    } //ends add
    
    /**
     * Get the first node of the list
     * @return The head node of the list.
     */
    public Node getHead() {
        return first;
    } //ends getHead
} //ends LinkedList
