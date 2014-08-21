import java.util.ArrayList;


public class multigraph_matrix {
	//OUTER ARRAY = ROW; INNER ARRAY = COL, 1 DENOTES DIRECTED PATH FROM ROW[n] -> COL[n]
	
	public ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();
	
	public int neighbors(ArrayList<ArrayList<Integer>> matrix, int vertex){
		int neighbors = 0;
		
		for (int i=0; i<matrix.size(); i++){
			if (matrix.get(vertex).get(i) == 1)
				neighbors++;
			if (matrix.get(i).get(vertex) == 1)
				neighbors++;
			}
		return neighbors;
	}
	
	public int largest_out(ArrayList<ArrayList<Integer>> matrix){
		int greatest = -1;
		for (int i=0; i<matrix.size(); i++) {
			int sum=0;
			for (int k=0; k < matrix.size(); k++){
				if (matrix.get(i).get(k) == 1);
				sum++;
			}
			if (sum > greatest)
				greatest = 1;
		}
		return greatest;
	}
	
	public boolean two_steps(ArrayList<ArrayList<Integer>> matrix, int start, int end){
		ArrayList<Integer> one_step = new ArrayList<Integer>();
		
		for (int i=0; i<matrix.size();i++){
			if (matrix.get(start).get(i) == 1)
				one_step.add(i);
		}
		for (int i = 0; i<one_step.size();i++){
			if (matrix.get(i).get(end)==1)
				return true;
		}
		return false;
	}
}



