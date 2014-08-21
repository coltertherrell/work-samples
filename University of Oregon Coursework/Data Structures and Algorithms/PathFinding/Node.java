import java.util.LinkedList;

/*Author: Sam Vitello
 * Node Object used in Find_Paths.java to store adjacency list, shortest path, longest path
 * and the total number of paths. Also contains methods for visiting neighbors to
 * exchange path information. Run Find_Paths.java to initialize application.
 */

public class Node {
	
	public LinkedList<Node> neighbors = new LinkedList<Node>();
	public int shortest = -1;
	public int longest = -1;
	public int total = 0;
	public int num;
	public int in_num = 0;
	
	public Node(int number){
		num = number;
	}
	
	public boolean visit(Node x){
		x.in_num -= 1;
		if (x.shortest == -1)
			x.shortest = shortest+1; //passes along shortest path
		else if (x.shortest > shortest)
			x.shortest = shortest+1;
		if (x.longest == -1)
			x.longest = longest+1; //passes along longest path
		else if (x.longest <= longest)
			x.longest = longest+1;
		x.total += total; //increments total path length
		if (in_num == 0) //returns true if all in-paths have visited the node
			return true;
		else
			return false;
	}
	
	
	public void addNeighbor(Node neigh){
		neighbors.add(neigh);
	}
	
	public Node getNeighbor(int index){
		return neighbors.get(index);
	}
	
	public int getNum(){
		return num;
	}
}
