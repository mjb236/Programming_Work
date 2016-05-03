//*******************************************************************************
//Name: Michael Bowen
//Class: CS 1501  Mon/Wed 6:00pm  Recitation: Mon 7:30
//Assignment 3 - Graphs
//
//Minium Indexed priority queue structure used in Prim's and Dijkstra's algorthims.
//Mostly borrowed from the book and book's website.
//*******************************************************************************

import java.util.*;

public class IndexMinPQ<Key extends Comparable<Key>>
{
    private Key[] keys;
    private int NMAX;
    private int N;
    private int[] pq;
    private int[] qp;
    
    public IndexMinPQ(int NMAX)
    {
	this.NMAX = NMAX;
	keys = (Key[]) new Comparable[NMAX + 1];
	pq = new int[NMAX + 1];
	qp = new int[NMAX + 1];
	for(int i = 0; i <= NMAX; i++)
	    qp[i] = -1;
	N = 0;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public boolean contains(int i) {
        if (i < 0 || i >= NMAX) throw new IndexOutOfBoundsException();
        return qp[i] != -1;
    }

    public int size() {
        return N;
    }

    public void insert(int i, Key key) {
        if (i < 0 || i >= NMAX) throw new IndexOutOfBoundsException();
        if (contains(i)) throw new IllegalArgumentException("index is already in the priority queue");
        N++;
        qp[i] = N;
        pq[N] = i;
        keys[i] = key;
        swim(N);
    }

    private void swim(int k)  {
        while (k > 1 && greater(k/2, k)) {
            exch(k, k/2);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2*k <= N) {
            int j = 2*k;
            if (j < N && greater(j, j+1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private boolean greater(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }

    private void exch(int i, int j) {
        int swap = pq[i]; 
	pq[i] = pq[j]; 
	pq[j] = swap;
        qp[pq[i]] = i; 
	qp[pq[j]] = j;
    }

    public void changeKey(int i, Key key) {
        if (i < 0 || i >= NMAX) throw new IndexOutOfBoundsException();
        if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
        keys[i] = key;
        swim(qp[i]);
        sink(qp[i]);
    }

    public int delMin() { 
        if (N == 0) throw new NoSuchElementException("Priority queue underflow");
        int min = pq[1];        
        exch(1, N--); 
        sink(1);
        qp[min] = -1;            // delete
        keys[pq[N+1]] = null;    // to help with garbage collection
        pq[N+1] = -1;            // not needed
        return min; 
    }

}