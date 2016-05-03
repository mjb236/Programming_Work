//*******************************************************************************
//Name: Michael Bowen
//Class: CS 1501  Mon/Wed 6:00pm  Recitation: Mon 7:30
//Assignment 3 - Graphs
//
//Driver program to simulate the air travel program.
//These implementations of the Graphs and edges are directional. Since travel
//is possible both ways between cities, each edge is added twice. Once as read from
//the input file, and once with the vertices reversed.
//*******************************************************************************

import java.io.*;
import java.util.*;

public class Airline {

    //display the user menu
    public static void displayMenu() {
        System.out.println("\nUSER MENU");
        System.out.println("---------");
        System.out.println("1. Display list of all routes.");
	System.out.println("2. Display minimum spanning tree/forest.");
	System.out.println("3. Find shortest paths.");
	System.out.println("4. Display trips under specified cost.");
	System.out.println("5. Add a new route.");
	System.out.println("6. Remove a route.");
	System.out.println("7. Quit.");
	System.out.print("\nPlease enter the number of your choice: ");
    } //ends displayMenu()
    
    //display all routes available for travel, their distances and prices
    public static void displayRoutes(Graph g) {
	//print out all routes
        System.out.println("\nCURRENT FLIGHT ROUTES");
        System.out.println("---------------------");
        for(Edge e : g.edges())
            System.out.println(g.getCityName(e.from()) + " to/from " + g.getCityName(e.to()) +
                               " - Distance: " + e.distance() + " Price: " + e.price());
    } //ends displayRoutes
    
    //display a minimum spanning tree or forest of the graph
    public static void displayMST(Graph g) {
	PrimMST mst = new PrimMST(g, true);
        Queue<Edge> edges = (Queue<Edge>) mst.edges();

       	System.out.println("\nMINIMUM SPANNING TREE/FOREST");
        System.out.println("----------------------------");
	while(!edges.isEmpty()) {
            Edge e = edges.dequeue();
    	    System.out.println(g.getCityName(e.from()) + " to " + g.getCityName(e.to()) +
                               " - Distance: " + e.distance());
        }
    }
    
    //display the shortest path between two cities, based on either price, distance, or stops
    public static void shortestPaths(Graph g, Scanner keyboard) {
        System.out.println("\nSHORTEST PATHS");
        System.out.println("--------------");
	System.out.print("Please enter the name of the starting city: ");
        String start = keyboard.next();
        System.out.print("Please enter the name of the destination city: ");
	String end = keyboard.next();
	System.out.print("\nHow would you like to measure your trip?\n" +
			 "\n1. Shortest total distance." +
			 "\n2. Lowest total cost." +
                         "\n3. Fewest stops." +
                         "\n\nPlease enter your choice: ");
        int choice = Integer.parseInt(keyboard.next());
        
        //find the vertices associated with the cities
        int v = -1, w = -1;
        for(int i = 0; i < g.V() && (v == -1 || w == -1); i++) {
            if(start.equalsIgnoreCase(g.getCityName(i)))
                v = i;
            else if(end.equalsIgnoreCase(g.getCityName(i)))
                w = i;
        }        
 
        boolean byDist = false;
	if(choice == 1)
	    byDist = true;
	else if(choice == 2)
	    byDist = false;
        else if(choice == 3) {
            //use BFS to find the shortest path in number of hops
            BFS bfs = new BFS(g, v);
            if(bfs.hasPathTo(w)) {
               Stack<Edge> path = (Stack<Edge>) bfs.pathTo(w);
                int hops = 0;
                Edge e = null;
                System.out.println("\nFewest hop path from " + g.getCityName(v) + " to " +
                                   g.getCityName(w) + ":");
                while(!path.isEmpty()) {
                    e = path.pop();
                    System.out.print(g.getCityName(e.from()) + " - ");
                    hops++;
                }            
                System.out.println(g.getCityName(e.to()) + "\nTotal number of hops: " + hops);
                return; 
            } else {
                System.out.println("\nNo path exists.");
                return;
            }            
        }
	else {
	    System.out.println("Invalid choice.");
	    return;
	}
        
        //make sure both cities exist
        if(v != -1 && w != -1) {
            //creat the dijkstra object with the starting city as its start point
            Dijkstra sp = new Dijkstra(g, v, byDist);
            if(sp.hasPathTo(w)) {
                Stack<Edge> path = (Stack<Edge>) sp.pathTo(w); 
                //display shortest path by distance
                if(byDist) {
                    int totalDistance = 0;
                    Edge e = null;
                    System.out.println("\nLowest distance path from " + g.getCityName(v) + " to " +
                                       g.getCityName(w) + ":");
                    while(!path.isEmpty()) {
                        e = path.pop();
                        totalDistance += e.distance();
                        System.out.print(g.getCityName(e.from()) + " " + e.distance() + " ");
                    }
                    System.out.println(g.getCityName(e.to()) + "\nTotal distance: " + totalDistance + " miles.");
                } else {
                    //display shortest path by cost
                    double totalPrice = 0;
                    Edge e = null   ;
                    System.out.println("\nLowest cost path from " + g.getCityName(v) + " to " +
                                       g.getCityName(w) + ":");
                    while(!path.isEmpty()) {
                        e = path.pop();
                        totalPrice += e.price();
                        System.out.print(g.getCityName(e.from()) + " " + e.price() + " ");
                    }
                    System.out.println(g.getCityName(e.to()) + "\nTotal price: $" + totalPrice);
                }
            } else {
                System.out.println("\nNo path exists.");
            }                                       
        } else {
            System.out.println("City not found.");
        }       
    }

    //display all trips under specified cost
    public static void displayTrips(Graph g, Scanner keyboard) {
        System.out.println("\nTRIPS UNDER COST");
        System.out.println("----------------");
        System.out.print("Please enter the maximum cost of your trip: ");
        Double maxCost = Double.parseDouble(keyboard.next());
        
        //start at each city
        System.out.println("\nAll paths of cost " + maxCost + " or less:");
        System.out.println("Note that paths are duplicated, once from each city's point of view.");
        System.out.println("--------------------------------------------------------------------");
        for(int i = 0; i < g.V(); i++) {
            TripsUnderCost trips = new TripsUnderCost(g, i, maxCost);
            Queue<Queue<Edge>> allPaths = trips.getPaths();
            
            //iterate through all paths found
            while(!allPaths.isEmpty()) {
                double tripCost = 0.0;
                Edge e = null;
                Queue<Edge> path = allPaths.dequeue();
                //iterate through each edge on this path
                while(!path.isEmpty()) {
                    e = path.dequeue();
                    tripCost += e.price();
                    System.out.print(g.getCityName(e.to()) + " " + e.price() + " ");
                }
                System.out.println(g.getCityName(e.from()) + "  Total cost: $" + tripCost + "\n");                
            }
        }
    }

    //add the specified route to the graph
    public static void addRoute(Graph g, Scanner keyboard) {
        System.out.println("\nADD ROUTE");
        System.out.println("---------");
        //get user input
        System.out.print("Please enter name of first city: ");
        String start = keyboard.next();
	System.out.print("Please enter name of second city: ");
        String end = keyboard.next();
	System.out.print("Please enter the route's distance(in miles): ");
        int dist = Integer.parseInt(keyboard.next());
        System.out.print("Please enter the cost of the flight(in dollars): ");
        double cost = Double.parseDouble(keyboard.next());

        //find the vertices associated with the cities
        int v = -1, w = -1;
        for(int i = 0; i < g.V() && (v == -1 || w == -1); i++) {
            if(start.equalsIgnoreCase(g.getCityName(i)))
                v = i;
            else if(end.equalsIgnoreCase(g.getCityName(i)))
                w = i;
        }

        //add new route to the graph
        if(v != -1 && w != -1) {
            g.addEdge(new Edge(v, w, dist, cost));
            g.addEdge(new Edge(w, v, dist, cost));
            System.out.println("\nRoute has been added."); 
        } else {
            System.out.println("Invalid cities.");            
        }         
    } //ends addRoute

    //remove the route from the graph
    public static void removeRoute(Graph g, Scanner keyboard) {
try{
        System.out.println("\nREMOVE ROUTE");
        System.out.println("------------");
        //get user input
        System.out.print("Please enter name of first city: ");
        String start = keyboard.next();
	System.out.print("Please enter name of second city: ");
        String end = keyboard.next();

        //find the vertices associated with the cities
        int v = -1, w = -1;
        for(int i = 0; i < g.V() && (v == -1 || w == -1); i++) {
            if(start.equalsIgnoreCase(g.getCityName(i)))
                v = i;
            else if(end.equalsIgnoreCase(g.getCityName(i)))
                w = i;
        }
        
        g.removeEdge(v, w);
        
        System.out.println("\nRoute has been removed.");
}catch(Exception e) {
    System.out.println("\nInvalid city.");
}    
    } //ends removeRoute

    public static void main(String[] args) throws IOException {
        //get the file name from the user
        System.out.print("\nPlease enter the file name of the routes you wish to use: ");
        Scanner keyboard = new Scanner(System.in);
        String fileName = keyboard.next();
        
        //load information from file
        Scanner inFile = new Scanner(new FileInputStream(fileName));
        int v = inFile.nextInt();
        inFile.nextLine();

        //read verticies
        Graph g = new Graph(v);
        for(int i = 0; i < v; i++) {
            String city = inFile.nextLine();            
            g.addCity(city, i);
        } //ends for
        
        //read edges
        while(inFile.hasNext()) {
            int to = inFile.nextInt() - 1;
            int from = inFile.nextInt() - 1;
            int distance = inFile.nextInt();
            double price = inFile.nextDouble();
            //add edge and its reverse because travel can go both ways between cities
            g.addEdge(new Edge(to, from, distance, price));
            g.addEdge(new Edge(from, to, distance, price));
        } //ends while

        int choice = 0;
        do {
            displayMenu();
            choice = Integer.parseInt(keyboard.next());

            switch (choice) {
                case 1: displayRoutes(g);
                        break;
                case 2: displayMST(g);
                        break;
                case 3: shortestPaths(g, keyboard);
                        break;
                case 4: displayTrips(g, keyboard);
                        break;
                case 5: addRoute(g, keyboard);
                        break;
                case 6: removeRoute(g, keyboard);
                        break;
                case 7: break;
                default: System.out.println("Invalid choice.");
                         break;
	    }
        }while(choice != 7);
        
        //save the file
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        bw.write(g.V() + "");
        bw.newLine();
        for(int i = 0; i < g.V(); i++) {
            bw.write(g.getCityName(i));
            bw.newLine();
        }
        for(int i = 0; i < g.V(); i++) {
            for(Edge e: g.adj(i)) {
                if(e.to() > i) {
                    bw.write((e.from() + 1) + "");
                    bw.write(" ");
                    bw.write((e.to() + 1) + "");
                    bw.write(" ");
                    bw.write(e.distance() + "");
                    bw.write(" ");
                    bw.write(e.price() + "");
                    bw.write(" ");
                    bw.newLine();
                }                
            }
        }
        
        bw.close();        
    } //ends main
} //ends class