import java.util.ArrayList;


public class reverse {
	
	public static ArrayList<Integer> to_rev = new ArrayList<Integer>();
	
	public static void pop_array() {
		to_rev.clear();
		for (int i=0; i<10; i++)
		{
			to_rev.add(i);
		}
	}
	
	public static ArrayList<Integer> reverse_array(ArrayList<Integer> list) {
		//Recursively reverses the order of a list
		if (list.size()==1)
			return list;
		int temp = list.get(0);
		list.remove(0);
		reverse_array(list);
		list.add(temp);
		return list;
	}
	
	public static void main(String[] args){
		pop_array();
		for (int i=0; i<to_rev.size();i++)
			System.out.println(to_rev.get(i));
		reverse_array(to_rev);
		for (int i=0; i<to_rev.size();i++)
			System.out.println(to_rev.get(i));
	}

}
