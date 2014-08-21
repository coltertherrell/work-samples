import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*Author: Sam Vitello
 * Run Find_Paths.java in command line and pass through a .txt file as an argument.
 * Find_Paths creates nodes which communitivly store an adjacency list by saving a 
 * linked list of directed paths at each node. Find_Paths then starts with the first
 * node and visits neighbors until the last node is reached. Along the way path lengths
 * and the number of paths are passed from node to node. Finally Find_Paths.java checks
 * the last node to determine the longest path, the shortest path and the total number of
 * paths
 */


public class Find_Paths {
	
	public static LinkedList<Node> graph = new LinkedList<Node>();
	public static int[] visited;
	
	public static void main(String[] args) throws IOException{
		 if (args.length < 1) {
			 System.out.println("Error: Enter graph.txt");
			 System.exit(1);
		 }
		 
		 Scanner reader = new Scanner(new FileInputStream(args[0]));
	
		 
		int graph_number = 0;
		int nodes = 0;
		int edges = 0;
		graph_number = reader.nextInt();
		int graph_count = 1;
		 
		 while(graph_number > 0){ 
			 
			nodes = reader.nextInt();
			edges = reader.nextInt();
			graph = new LinkedList<Node>();
			visited = new int[nodes];
			
		 
			 for (int i = 0; i<nodes; i++){
				 graph.add(new Node(i)); //create Node objects of graph
			 }
		 
			 for (int i = 0; i<edges; i++){
				 int tail = reader.nextInt();
				 int head = reader.nextInt();
				 graph.get(tail-1).addNeighbor(graph.get(head-1)); //Construct Adjacency List
				 graph.get(head-1).in_num += 1; //Increments incoming paths counter
			 }
			 
			 
			 Node start = graph.get(0);
			 start.total += 1;
			 start.shortest = 0;
			 start.longest = 0;
			 Queue<Node> visitQueue = new LinkedList<Node>();
			 visitQueue.add(start);
			 while (visitQueue.size() > 0){
				 Node working = visitQueue.poll();
				 for (int i = 0; i<working.neighbors.size();i++){
					 Node next = working.getNeighbor(i);
					 boolean add = working.visit(next); //Visit method found in Node.java
					 if (add && visited[next.getNum()] == 0){
						visitQueue.add(next);
					 	//System.out.println("Node "+next.getNum()+" added");
					 	visited[next.getNum()] = 1;
					 }
				 }
			 }
			 
			 Node last = graph.get(nodes-1); //get end node to access path information
			 System.out.println("Graph Number: " + graph_count);
			 graph_count++;
			 System.out.println("Shortest Path: " + last.shortest);
			 System.out.println("Longest Path: " + last.longest);
			 System.out.println("Total Paths: " + last.total);
			 System.out.println();
			 graph_number -= 1;
		 }
	}

}
