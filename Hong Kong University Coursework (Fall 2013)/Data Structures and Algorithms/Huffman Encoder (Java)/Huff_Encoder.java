///AUTHOR: SAM VITELLO///
///CSIS 1119 ASSIGNMENT 3///
///November 21 2013///
///THIS PROGRAM TAKES USER INPUT, CREATES A BINARY TREE AND ASSIGNS///
///HUFFMAN CODE VALUES AND PRINTS THE VALUES, THE AVERAGE CHAR BIT ///
///LENGTH AND THE ENCODED MESSAGE                                  ///

import javax.swing.JOptionPane;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Huff_Encoder {
	
	private static Char_Store tree;
	private static String original;
	
	public static void main(String[] args)
	{
		String input = JOptionPane.showInputDialog("Enter something: ");
		original = input;
		char char_array[] = new char[input.length()];
		for (int i = 0; i < input.length(); i++){
			char_array[i] = input.charAt(i);
		}
		
		ArrayList<Character> key_array = new ArrayList<Character>();
		HashMap<Character,Integer> tracker = new HashMap<Character,Integer>();
		for (int i = 0; i < input.length(); i++)
		{
			if (tracker.containsKey(char_array[i]))
			{
				//System.out.println(char_array[i] + " ++");
				tracker.put(char_array[i], tracker.get(char_array[i]) + 1);
			}
			else
			{
				//System.out.println(char_array[i] + " new");
				tracker.put(char_array[i], 1);
				key_array.add(char_array[i]);
				
			}
		}
		
		PriorityQueue<Char_Store> sorter = new PriorityQueue<Char_Store>();
		
		
		int count = 0;
		for (int i = 0; i< key_array.size(); i++)
		{
			Char_Store temp = new Char_Store(key_array.get(i),tracker.get(key_array.get(i)), count);
			count++;
			sorter.offer(temp);
		}
		
		
		build_tree(sorter);
		int[] avg = new int[2];
		avg = assign_values(tree, avg);
		float avg_count = (float) avg[0]/avg[1];
		DecimalFormat df = new DecimalFormat("#.##");
		System.out.println(df.format(avg_count));
		String encoded = "";
		for (int i = 0; i < original.length(); i++){
			encoded += find_huff(original.charAt(i),tree);
		}
		System.out.println(encoded);
		

	}
	
	private static void build_tree(PriorityQueue<Char_Store> sorter){
		while (sorter.size() > 0){
			if (sorter.size() == 1) {
				tree = sorter.poll();
				//sorter.remove(0);
				tree.huff_code = "";
				continue;
			}
			Char_Store left = sorter.poll();
			Char_Store right = sorter.poll();
			if (left.get_priority() > right.get_priority()){
				Char_Store temp = right;
				right = left;
				left = temp;
			}
			Char_Store temp = new Char_Store(null, (left.get_value() + right.get_value()), left.get_priority());
			temp.set_left(left);
			temp.set_right(right);
			sorter.offer(temp);
		}	
			
	}
	
	private static int[] assign_values(Char_Store root, int[] avg){
		if (root.left != null){
			Char_Store left = root.left;
			left.huff_code += (root.huff_code + "0");
			if (left.key != null){
				System.out.println(left.key + " : " + left.huff_code);
				avg[0] = (avg[0] + (left.huff_code.length() * left.get_value()));
				avg[1] = (avg[1] + left.get_value());
			}
			assign_values(left, avg);
		}
		if (root.right != null){
			Char_Store right = root.right;
			right.huff_code += (root.huff_code + "1");
			if (right.key != null){
				System.out.println(right.key + " : " + right.huff_code);
				avg[0] = (avg[0] + (right.huff_code.length() * right.get_value()));
				avg[1] = (avg[1] + right.get_value());
			}
			assign_values(right, avg);
		}
		
		return avg;
		
	}
	
	public static String find_huff(char target, Char_Store root){
		if (root.key != null){
			if (root.key.toString().charAt(0) == target)
				return root.huff_code;
		}
		String code = "";
		if (root.left != null){
			String left = find_huff(target, root.left);
			if (left != "")
				code = left;
		}
		if (root.right != null){
			String right = find_huff(target, root.right);
			if (right != "")
				code = right;
		}
		return code;
	}


}
