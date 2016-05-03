//*******************************************************************************
//Name: Michael Bowen
//Class: CS 1501  Mon/Wed 6:00pm  Recitation: Mon 7:30
//Assignment 3 - Graphs
//
//Simple last in, first out data structure. Borrowed from the book.
//*******************************************************************************

import java.util.Iterator;

public class Stack<Item> implements Iterable<Item>
{
    private Node first;
    private int N;
    
    private class Node
    {
        Item item;
        Node next;
    }
    
    //is the stack empty
    public boolean isEmpty()
    {
        return first == null;
    }
    
    //returns number of items in the stack
    public int size() 
    {
        return N;
    }
    
    //add an item to the stack
    public void push(Item item)
    {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        N++;
    }
    
    //remove the top item of the stack
    public Item pop() 
    {
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }
    
    //return an iterator to iterate through items on the stack without removing them.
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