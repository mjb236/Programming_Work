//*******************************************************************************
//Name: Michael Bowen
//Class: CS 1501  Mon/Wed 6:00pm  Recitation: Mon 7:30
//Assignment 3 - Graphs
//
//Prim's minimum spanning tree algorthim, adapted to handle queries by either distance
//or price. Mostly borrowed from the book, adapted to handle minimum spanning forests as well.
//*******************************************************************************

public class PrimMST
{
    private Edge[] edgeTo;
    private double[] distTo;
    private boolean[] marked;
    private IndexMinPQ pq;
    private boolean byDist;

    public PrimMST(Graph g, boolean byDist)
    {
	this.byDist = byDist;
	edgeTo = new Edge[g.V()];
        distTo = new double[g.V()];
        marked = new boolean[g.V()];
        for(int v = 0; v < g.V(); v++)
	    distTo[v] = Double.POSITIVE_INFINITY;
	pq = new IndexMinPQ(g.V());

        for(int v = 0; v < g.V(); v++)
	    if(!marked[v])
		prim(g, v);
    } //ends constructor
    
    public void prim(Graph g, int s) 
    {
	distTo[s] = 0.0;
	pq.insert(s, distTo[s]);
        while(!pq.isEmpty()) {
	    int v = pq.delMin();
	    scan(g, v);
        }
    }

    private void scan(Graph g, int v)
    {
	marked[v] = true;
        for(Edge e: g.adj(v)) {        
            int w = e.to();
	    if(marked[w])
                continue;
            if(byDist) {                             //compare edges by distance
                if(e.distance() < distTo[w]) {
		    edgeTo[w] = e;
                    distTo[w] = e.distance();
                    if(pq.contains(w))
			pq.changeKey(w, distTo[w]);
		    else
                        pq.insert(w, distTo[w]);
		}
	    } else {                                 //compare edges by price
		if(e.price() < distTo[w]) {
		    edgeTo[w] = e;
                    distTo[w] = e.price();
                    if(pq.contains(w))
			pq.changeKey(w, distTo[w]);
		    else
                        pq.insert(w, distTo[w]);
		}
            }
	} //ends for
    } //ends visit

    public Iterable<Edge> edges()
    {
        Queue<Edge> mst = new Queue<Edge>();
	for(int v = 1; v < edgeTo.length; v++) {
	    Edge e = edgeTo[v];
	    if(e != null)
		mst.enqueue(e);
        }
                
        return mst;
    } //ends edges
} //ends PrimMST