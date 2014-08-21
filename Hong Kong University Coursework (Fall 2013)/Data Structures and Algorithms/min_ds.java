import java.util.ArrayList;

public class min_ds {
	
	public static ArrayList<Integer> main_stack = new ArrayList<Integer>();
	public static ArrayList<Integer> min_stack = new ArrayList<Integer>();
	
	public static void push(int x){
		if (min_stack.size() == 0 || min_stack.get(min_stack.size()-1) > x)
			min_stack.add(x);
		main_stack.add(x);
	}
	
	public static int pop(){
		if(min_stack.get(min_stack.size()-1) == main_stack.get(main_stack.size()-1))
			min_stack.remove(min_stack.size()-1);
		int temp = main_stack.get(main_stack.size()-1);
		main_stack.remove(main_stack.size()-1);
		return temp;
	}
	
	public static int top(){
		return main_stack.get(main_stack.size()-1);
	}
	
	public static int min_value(){
		return min_stack.get(min_stack.size()-1);
	}

}
