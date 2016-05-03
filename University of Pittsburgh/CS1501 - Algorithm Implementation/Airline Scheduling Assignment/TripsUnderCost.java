//*******************************************************************************
//Name: Michael Bowen
//Class: CS 1501  Mon/Wed 6:00pm  Recitation: Mon 7:30
//Assignment 3 - Graphs
//
//This class uses a modified Depth First Search to find all paths under a given cost.
//All paths are stored in a Queue of Queues
//*******************************************************************************

import java.util.Iterator;

public class TripsUnderCost {
    private boolean[] marked;
    private final int s;
    private final double maxCost;
    private Queue<Queue<Edge>> paths;
    private Stack<Edge> p;
    
    public TripsUnderCost(Graph g, int s, double maxCost) {
        this.s = s;
        this.maxCost = maxCost;
        marked = new boolean[g.V()];
        paths = new Queue<Queue<Edge>>();
        p = new Stack<Edge>();
        findTrips(g, s, 0.0);
    }
    
    //recursive function to find all paths under the specified cost
    private void findTrips(Graph g, int v, double cost) {
        marked[v] = true;
              
        for(Edge e : g.adj(v)) {
            int w = e.to();
            if(!marked[w]) {
                if((e.price() + cost) > maxCost) {
                    continue;
                } else {
                    p.push(e);
                    Queue<Edge> path = new Queue<Edge>();
                    Iterator<Edge> iter = p.iterator();
                    while(iter.hasNext()) {
                        Edge edge = iter.next();
                        path.enqueue(edge);
                    }
                    paths.enqueue(path);
                    findTrips(g, w, cost + e.price());
                }
            }
        } //ends for
        
        marked[v] = false;
        if(!p.isEmpty())    //prevent null pointer exception
            p.pop();        
    }
    
    //return the Queue of path Queues
    public Queue<Queue<Edge>> getPaths() {
        return paths;
    }
}