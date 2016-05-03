/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Michael
 */
public class DLBTrie implements DictionaryInterface {
    //root node of the trie
    private Node root;
    
    //private class used to set up the Trie
    private static class Node {
        char c;
        boolean isWord;
        Node child;
        Node next;        
    } //ends Node
    
    //generic add method that accepts a String and passes to the recursive add method
    public boolean add(String s) {
        root = add(root, s, 0);
        return true;
    } //ends add
    
    //Recursive method to traverse the trie to the correct place and add the characters necessary
    //to store the word.
    public Node add(Node x, String s, int depth) {
        char c = s.charAt(depth);
        //create new node if necessary
        if(x == null) {
            x = new Node();
            x.c = c;
        }
        
        //check current root to see if this is the correct subtrie, or if we need to move right in the
        //list to find the correct subtrie
        if(c != x.c) {
            x.next = add(x.next, s, depth);             //move to next subtrie if needed
        } else if (depth < s.length()-1) {
            x.child = add(x.child, s, depth+1);         //move down current subtrie
        } else {
            x.isWord = true;                            //end of string, set isWord to true
        }
        
        return x;            
    } //ends add
    
    //generic search method. returns whether or not the string is a word, prefix, both or neither
    public int search(StringBuilder s) {
        Node x = search(root, s, 0);
        
        //if x is null, the string was not found. otherwise, determine if string
        //was a word, prefix, or both and return the appropriate value.
        if(x == null)
            return 0;
        if(!x.isWord && x.child != null)                //search string is a prefix
            return 1;
        else if(x.isWord && x.child == null)            //search string is a word
            return 2;
        else
            return 3;                                   //search string is a word and prefix
    } //ends search
    
    //recursive search method. 
    public Node search(Node x, StringBuilder s, int depth) {
        if(x == null)
            return null;                               //end of trie, word not found
        
        char c = s.charAt(depth);
        //determine if we are in the correct subtrie, move down if so, move right if not.
        if(c != x.c) {
            return search(x.next, s, depth);            
        } else if(depth < s.length()-1) {
            return search(x.child, s, depth+1);
        } else
            return x;
    } //ends search
} //ends DLBTrie
