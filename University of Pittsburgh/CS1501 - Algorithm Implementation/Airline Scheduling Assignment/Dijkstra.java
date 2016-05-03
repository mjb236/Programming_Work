//*******************************************************************************
//Name: Michael Bowen
//Class: CS 1501  Mon/Wed 6:00pm  Recitation: Mon 7:30
//Assignment 3 - Graphs
//
//Dijkstra's algortihm, adapted from the book, to accomodate queries via cost or distance.
//*******************************************************************************

public class Dijkstra 
{
    private Edge[] edgeTo;
    private double[] distTo;
    private IndexMinPQ pq;
    private boolean byDist;

    public Dijkstra(Graph g, int s, boolean byDist)
    {
	this.byDist = byDist;
        edgeTo = new Edge[g.V()];
	distTo = new double[g.V()];
	pq = new IndexMinPQ(g.V());

        for(int v =0; v < g.V(); v++)
	    distTo[v] = Double.POSITIVE_INFINITY;
	distTo[s] = 0.0;

        pq.insert(s, 0.0);
	while(!pq.isEmpty()) {
	    relax(g, pq.delMin());
        }
    }

    private void relax(Graph g, int v)
    {
	for(Edge e : g.adj(v))
	{
	    int w = e.to();
            if(byDist) {
		if(distTo[w] > distTo[v] + e.distance())
		{
		    distTo[w] = distTo[v] + e.distance();
		    edgeTo[w] = e;
		    if(pq.contains(w))
			pq.changeKey(w, distTo[w]);
		    else
			pq.insert(w, distTo[w]);
		}
	    } else {
		if(distTo[w] > distTo[v] + e.price())
		{
		    distTo[w] = distTo[v] + e.price();
		    edgeTo[w] = e;
		    if(pq.contains(w))
			pq.changeKey(w, distTo[w]);
		    else
			pq.insert(w, distTo[w]);
		}
	    }
	}
    }

    //return distance from s to v
    public double distTo(int v)
    {
	return distTo[v];
    }

    //is there a path from s to v
    public boolean hasPathTo(int v)
    {
	return distTo[v] < Double.POSITIVE_INFINITY;
    }

    //get the path of edges from s to v
    public Iterable<Edge> pathTo(int v) 
    {
        if(!hasPathTo(v)) return null;
	Stack<Edge> path = new Stack<Edge>();
        for(Edge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
            path.push(e);
        return path;
    }
} //ends Dijkstra