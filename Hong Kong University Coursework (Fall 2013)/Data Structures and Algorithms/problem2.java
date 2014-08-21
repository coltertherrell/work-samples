import java.util.ArrayList;


public class problem2 {
	
	public static ArrayList<Integer> to_chk = new ArrayList<Integer>();
	public static ArrayList<Integer> more_chk = new ArrayList<Integer>();
	
	
	public static void tru_array() {
		to_chk.clear();
		for (int i=0; i<10; i++)
		{
			to_chk.add(i);
		}
	}
	
	public static void false_array() {
		to_chk.clear();
		for (int i=0; i<10; i++)
		{
			to_chk.add(i+1);
		}
	}
	
	public static boolean chk_array(ArrayList<Integer> list, int index){
		//System.out.println(index);
		if(index == (list.size()))
			return false;
		else if (list.get(index) == index)
			return true;
		if (chk_array(list, ++index))
			return true;
		else
			return false;
	}
	
	public static boolean chk_array2(ArrayList<Integer> list, int start, int stop){
		if (stop == list.get(stop)){
			return true;
		}
		int middle = (stop+1)/2;
		
		
	}
	
	public static void main(String[] args)
	{
		more_chk.add(3);
		more_chk.add(4);
		more_chk.add(5);
		
		
		tru_array();
		System.out.println(chk_array(to_chk,0));
		false_array();
		System.out.println(chk_array(to_chk,0));
		System.out.println(chk_array(more_chk,0));
	}

}
