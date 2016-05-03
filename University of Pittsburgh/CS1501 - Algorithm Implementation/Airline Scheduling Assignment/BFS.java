//*******************************************************************************
//Name: Michael Bowen
//Class: CS 1501  Mon/Wed 6:00pm  Recitation: Mon 7:30
//Assignment 3 - Graphs
//
//Breadth First Search class for finding the shortest path(in number of stops 
//from the start s.
//
//*******************************************************************************

public class BFS {
    private boolean[] marked;
    private Edge[] edgeTo;
    private final int s;
    
    public BFS(Graph g, int s) {
        marked = new boolean[g.V()];
        edgeTo = new Edge[g.V()];
        this.s = s;
        bfs(g, s);
    }
    
    //find the paths
    private void bfs(Graph g, int s) {
        Queue<Integer> q = new Queue<Integer>();
        marked[s] = true;
        q.enqueue(s);
        
        while(!q.isEmpty()) {
            int v = q.dequeue();
            for(Edge e : g.adj(v)) {
                int w = e.to();
                if(!marked[w]) {
                    edgeTo[w] = e;
                    marked[w] = true;
                    q.enqueue(w);
                }                
            }
        }
    }
    
    //is there a path from s to v
    public boolean hasPathTo(int v) {
        return marked[v];
    }
    
    //return the path from s to v
    public Iterable<Edge> pathTo(int v) {
        if(!hasPathTo(v)) return null;
        Stack<Edge> path = new Stack<Edge>();
        for(Edge e = edgeTo[v]; e != edgeTo[s]; e = edgeTo[e.from()])
            path.push(e);
        
        return path;
    }
}