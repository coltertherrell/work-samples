import javax.swing.JOptionPane;
import java.util.HashMap;
import java.util.Set;
import java.util.PriorityQueue;


public class Character_Count {
	
	public static void main(String[] args)
	{
		String input = JOptionPane.showInputDialog("Enter something: ");
		char[] char_array = input.toCharArray();
		
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
			}
		}
		
		Set<Character> keys = tracker.keySet();
		Object[] key_array = new Object[keys.size()];
		key_array = keys.toArray();
		
		//Using Priority Queue
		PriorityQueue<Compare_Char> sorter = new PriorityQueue<Compare_Char>();
		
		//Extra Credit (Custom_Queue.java)
		//Custom_Queue sorter = new Custom_Queue();
		
		for (int i = 0; i < key_array.length; i++)
		{
			Compare_Char temp = new Compare_Char(key_array[i],tracker.get(key_array[i]));
			sorter.offer(temp);
		}
		
		while (sorter.size() > 0)
		{
			Compare_Char temp = sorter.poll();
			System.out.println(temp);
		}
		
	}
}
