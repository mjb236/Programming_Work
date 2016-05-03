package my.bowlingleague;

/**
* Class to store bowlers for implementing a linked list
* JDK 8
* NetBeans 8.0
* @author Michael Bowen
*/
public class Node {
    private Bowler bowler;
    private Node next;
    
    /**
    * Two arg constructor for creating a node containing a bowler and the next node.
    * @param bowler The bowler data to store in the node.
    * @param next The next node in the list.
    */
    public Node(Bowler bowler, Node next) {
        this.bowler = bowler;
        this.next = next;
    } //ends constructor
    
    /**
     * One arg constructor for creating a node containing a bowler and no next node.
     * @param bowler The bowler data to store in the node.
    */
    public Node(Bowler bowler) {
        this.bowler = bowler;
        next = null;
    } //ends constructor
    
    /**
     * Get the Node after the current node.
     * @return The next node.
     */
    public Node getNext() {
        return next;
    } //ends getNext
    
    /**
     * Set the next node of the list.
     * @param n The next node.
     */
    public void setNext(Node n) {
        next = n;
    } //ends setNext
    
    /**
     * Get the bowler stored by the node
     * @return This node's bowler.
     */
    public Bowler getBowler() {
        return bowler;
    } //ends getBowler
    
    /**
     * Set this node's bowler data.
     * @param b The bowler to store in the node.
     */
    public void setBowler(Bowler b) {
        bowler = b;
    } //ends setBowler
} //ends Node