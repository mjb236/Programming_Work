//*******************************************************************************
//Name: Michael Bowen
//Class: CS 1501  Mon/Wed 6:00pm  Recitation: Mon 7:30
//Assignment 3 - Graphs
//
//Simple first in, first out Queue data structure. Borrowed from the book.
//*******************************************************************************

import java.util.Iterator;

public class Queue<Item> implements Iterable<Item>
{
    private Node first;
    private Node last;
    private int N;

    private class Node
    {
	Item item;
	Node next;
    }

    //is the queue empty?
    public boolean isEmpty()
    {
	return first == null;
    }

    //returns the number of items in the queue
    public int size()
    {
        return N;
    }

    //add an item to the queue
    public void enqueue(Item item)
    {
	Node oldLast = last;
	last = new Node();
	last.item = item;
        last.next = null;
	if(isEmpty())
	    first = last;
        else
	    oldLast.next = last;

        N++;
    }

    //remove the first item from the queue
    public Item dequeue() 
    {
	Item item = first.item;
	first = first.next;
	N--;
	if(isEmpty())
	    last = null;

	return item;
    }

    //returns an iterator to iterate through items in the queue without removing them.
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