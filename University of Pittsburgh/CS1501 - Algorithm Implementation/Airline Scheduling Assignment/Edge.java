//*******************************************************************************
//Name: Michael Bowen
//Class: CS 1501  Mon/Wed 6:00pm  Recitation: Mon 7:30
//Assignment 3 - Graphs
//
//Edge structure. Implemented as directional edges
//*******************************************************************************

public class Edge 
{
    //class variables for the two vertices and two weights
    private final int v;
    private final int w;
    private final int distance;
    private final double price;

    public Edge(int v, int w, int distance, double price) {
        this.v = v;
        this.w = w;
        this.distance = distance; 
        this.price = price;
    }

    //returns the distance of the flight
    public int distance() {
        return distance;
    }

    //returns the cost of the flight
    public double price() {
        return price;
    }

    //returns the index of the origin city
    public int to() {
        return w;
    }
    
    //returns the index of the destination city
    public int from() {
        return v;
    }

    //simple string output representing the flight
    public String toString() {
        return String.format("%d-%d Distance: %d miles Price: %.2f", v, w, distance, price);
    }
} //ends Edge