//*******************************************************************************
//Name: Michael Bowen
//Class: CS 1501  Mon/Wed 6:00pm  Recitation: Mon 7:30
//Assignment 3 - Graphs
//
//Bag data structure used by the Graph class to store the edges in adjacency lists.
//Borrowed from the book, adapted to accomodate removal of edges
//*******************************************************************************

import java.util.Iterator;

public class Bag<Item> implements Iterable<Item>
{
    private Node first;

    private class Node
    {
        Item item;
        Node next;
    }

    //add an item to the bag
    public void add(Item item)
    {
        Node old = first;
        first = new Node();
        first.item = item;
        first.next = old;
    }
    
    //remove the specified edge from the bag
    public void remove(Item item) {
        Node curr = first;
        Node prev = null;
        while(curr != null) {
            if(curr.item.equals(item)) {
                //if the item is at the beginning of the  list
                if(prev == null && curr.next == null) {
                    first = null;
                } else if(prev == null && curr.next != null) {
                    first = first.next;
                } 
                
                //if the item is in the end of the list
                if(prev != null && curr.next == null) {
                    prev.next = null;
                //if item is in the middle of the list
                }else if(prev != null && curr.next != null) {
                    prev.next = curr.next;
                }
                
                curr = null;
            } else {
                prev = curr;
                curr = curr.next;                      
            } //ends if/else
        } //ends while
    }

    //return an iterator to iterate through the items in the bag
    public Iterator<Item> iterator() 
    {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>
    {
        private Node current = first;
    
        public boolean hasNext() { return current != null; }

        public void remove() {

        }

        public Item next()
        {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}