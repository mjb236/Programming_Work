//*******************************************************************************
//Name: Michael Bowen
//Class: CS 1501  Mon/Wed 6:00pm  Recitation: Mon 7:30
//Assignment 3 - Graphs
//
//Graph structure.Implemented as a directional graph.
//*******************************************************************************

import java.util.Iterator;

public class Graph 
{
    private final int V;
    private int E;
    private Bag<Edge>[] adj;
    private String[] cities;	//keep track of the vertices added to the graph
    
    public Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Edge>[]) new Bag[V];
	cities = new String[V];
        for(int v = 0; v < V; v++) {
            adj[v] = new Bag<Edge>();
	    cities[v] = null;
	}
    }

    //return the number of vertices
    public int V() {
        return V;
    }

    //return the number of edges
    public int E() {
        return E;
    }

    //add the specified edge
    public void addEdge(Edge e) {
        adj[e.from()].add(e);
        E++;
    }
    
    //add the vertex's name to the array
    public void addCity(String c, int v) {
        cities[v] = new String(c);
    }
    
    //return the specified vertex's name
    public String getCityName(int v) {
        return cities[v];
    }

    //remove the specified edge from the array
    public void removeEdge(int v, int w) {
        //find the edge for these two verticies
        Edge e = null;
        //remove edge from v
        Iterator<Edge> iter = adj[v].iterator();
        while(iter.hasNext() && e == null) {
            Edge testEdge = iter.next();
            
            int num = testEdge.to();
            if(num == w) {
                e = testEdge;
            } else {
                e = null;
            }
        }
        if(e != null) {
            adj[v].remove(e);            
        }
        
        //remove edge from w        
        iter = adj[w].iterator();
        e = null;
        while(iter.hasNext() && e == null) {
            Edge testEdge = iter.next();
            int num = testEdge.to();
            if(num == v)
                e = testEdge;
            else
                e = null;
        }
        if(e != null) {
            adj[w].remove(e);
        }
    }

    //returns the edges for the specified vertex
    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    public Iterable<Edge> edges() {
        Bag<Edge> b = new Bag<Edge>();
        for(int v = 0; v < V; v++)
            for(Edge e : adj[v]) {
                if(e.to() > v)
                    b.add(e);                
            }
        return b;
    } //ends edges
} //ends Graph