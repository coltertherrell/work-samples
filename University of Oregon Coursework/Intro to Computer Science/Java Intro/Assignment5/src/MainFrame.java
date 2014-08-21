import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainFrame extends JFrame implements ActionListener
{
	private JButton mergeSort;
	private JButton selectSort;
	private static JLabel mergeTime;
	private static JLabel selectTime;
	
	public MainFrame()
	{
		super("Test Sorting Methods");
		
		mergeSort = new JButton("Merge Sort");
		mergeSort.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev) {
				MergeSort();
			}
		});
		
		selectSort = new JButton("Selection Sort");
		selectSort.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev) {
				SelectionSort();
			}
		});
		
		mergeTime = new JLabel("       " +"Merge Sort Time");
		selectTime = new JLabel("       " +"Selection Sort Time");
	
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(2,1));
		buttonPanel.add(mergeSort);
		buttonPanel.add(selectSort);
		
		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new GridLayout(2,1));
		labelPanel.add(mergeTime);
		labelPanel.add(selectTime);
		
		
		add(buttonPanel, BorderLayout.WEST);
		add(labelPanel, BorderLayout.CENTER);
		pack();
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	
	}
	
	public static void main(String[] args)
	{
		MainFrame application = new MainFrame();
		application.setSize(400,400);
		application.setVisible(true);
		checker_check();
		
		//ArrayList<Integer> list = new ArrayList<Integer>();
		//list.add(2,9);
		
	}
	
	private static ArrayList<String> getNewList()
	{
		StrGenerator newList = new StrGenerator();
		return newList.getNewList();
	}
	
	private static void SelectionSort()
	{
		ArrayList<String> base_list = getNewList();
		double start = System.currentTimeMillis()/1000.0;
		ArrayList<String> sorted_list = getSelectionSortedCopy(base_list);
		double end = System.currentTimeMillis()/1000.0;
		String time = Double.toString(end - start);
		selectTime.setText("       " + time + " seconds");
		System.out.println("Selection Sort Sorted: " + isSorted(sorted_list));
		
	}
	
	private static void MergeSort()
	{
		ArrayList<String> base_list = getNewList();
		//System.out.println(base_list.size());
		double start = (double) System.currentTimeMillis()/1000.0;
		ArrayList<String> sorted_list = getMergeSortedCopy(base_list);
		double end = (double) System.currentTimeMillis()/1000.0;
		//System.out.println(sorted_list.equals(base_list));
		String time = Double.toString((double) end - start);
		mergeTime.setText("       " + time + " seconds");
		System.out.println("Merge Sort Sorted: " + isSorted(sorted_list));
		
	}
	
	private static ArrayList<String> getSelectionSortedCopy(ArrayList<String> list)
	{
		int smallest;
		
		for (int i = 0; i < list.size(); i++)
		{
			smallest = i;
			
			for (int index = i + 1; index < list.size(); index++)
			{
				int check_index = 0;
				Character check_first = list.get(smallest).charAt(check_index);
				Character check_second = list.get(index).charAt(check_index);
				while (check_first == check_second)
				{
					check_index++;
					check_first = list.get(smallest).charAt(check_index);
					check_second = list.get(index).charAt(check_index);
					
				}
						
				if (list.get(index).charAt(check_index) < list.get(smallest).charAt(check_index))
					list = swap(list,smallest,index);
			}
		}
		return list;
	}
	
	private static ArrayList<String> swap(ArrayList<String> list, int move,int here )
	{
		String temporary = list.get(move);
		list.set(move, list.get(here));
		list.set(here, temporary);
		return list;
	}
	
	private static ArrayList<String> getMergeSortedCopy(ArrayList<String> list)
	{
		if (list.size() <= 1)
		{
			return list;
		}
		
		ArrayList<String> right = new ArrayList<String>();
		ArrayList<String> left = new ArrayList<String>();
		int half = (list.size() / 2);
		for (int i = 0; i < half; ++i)
		{
			left.add(list.get(i));
		}
		for (int i = half; i < list.size(); i++)
		{
			right.add(list.get(i));
		}
	
		left = getMergeSortedCopy(left);
		right = getMergeSortedCopy(right);
		
		return merge(left, right);
	}
	
	private static ArrayList<String> merge(ArrayList<String> left, ArrayList<String> right)
	{
		ArrayList<String> result = new ArrayList<String>();
		while (left.size() > 0 || right.size() > 0)
		{
			if (left.size() > 0 && right.size() > 0)
			{
				int count = 0;
				while (left.get(0).charAt(count) == right.get(0).charAt(count))
				{
					count += 1;
				}
				if (left.get(0).charAt(count) < right.get(0).charAt(count))
				{
					result.add(left.get(0));
					left.remove(0);
				}
				else
				{
					result.add(right.get(0));
					right.remove(0);
				}
			}
			else if (left.size() > 0)
			{
				result.add(left.get(0));
				left.remove(0);
			}
			else
			{
				result.add(right.get(0));
				right.remove(0);
			}
		}
		return result;
	}
	private static Boolean isSorted(ArrayList<String> list)
	{
		//System.out.println(list.size());

		for (int count = 1; count < list.size(); count++)
		{
			int check_index = 0;
			Character check_first = list.get(count -1).charAt(check_index);
			Character check_second = list.get(count).charAt(check_index);
			//System.out.println(check_first);
			//System.out.println(check_second);
			while (check_first == check_second)
			{
				check_index++;
				check_first = list.get(count -1).charAt(check_index);
				check_second = list.get(count).charAt(check_index);
			}
				
			if (greaterChar(check_first, check_second) == check_first)
			{
				//System.out.println(check_first +" "+ check_second + " " + count);
				return false;
			}
				
					
		}
		return true;
	}
	
	private static Character greaterChar(char a, char b)
		{
			if (a > b)
				return a;
			else
				return b;
			
		}
	
	private static void checker_check()
	{
		//Should Print true,false,false
		
		ArrayList<String> check1 = new ArrayList();
		ArrayList<String> check2 = new ArrayList();
		ArrayList<String> check3 = new ArrayList();
		check1.add("abcs");
		check1.add("ahds");
		check1.add("phdsh");
		check1.add("zhfdsh");
		check2.add("ggdr");
		check2.add("gagr");
		check2.add("thfdsh");
		check3.add("0ghreh");
		check3.add("0ar5hd");
		check3.add("pre43b");
		
		System.out.println(isSorted(check1));
		System.out.println(isSorted(check2));
		System.out.println(isSorted(check3));
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
