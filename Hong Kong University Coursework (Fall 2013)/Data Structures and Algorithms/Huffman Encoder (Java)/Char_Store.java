
public class Char_Store implements Comparable<Char_Store> {
	public Object key;
	private int count;
	public Char_Store left = null;
	public Char_Store right = null;
	public String huff_code = "";
	private int priority;
	
	public Char_Store(Object ch, Integer value, int count)
	{
		this.key = ch;
		this.count = value;
		priority = count;
	}
	
	@Override
	public int compareTo(Char_Store other)
	{
		//System.out.println( Integer.valueOf(count).compareTo(other.count));
		if ((this.count == other.count)){
			if (this.priority < other.priority)
				return -1;
			else
				return 1;
		}
		else{
			return (Integer.valueOf(count).compareTo(other.count));
		}
	}
	
	public void set_left(Char_Store node){
		left = node;
	}
	
	public void set_right(Char_Store node){
		right = node;
	}
	
	public int get_value(){
		return count;
	}
	
	public void set_huff(String code){
		huff_code = code;
	}
	
	public int get_priority(){
		return priority;
	}
	
	@Override
	public String toString()
	{
		return (count + " Instances of: " + key + " priority: " + priority);
	}
	
	public static void main(String[] args)
	{
		//Char_Store first = new Char_Store('r',8);
		//Char_Store second = new Char_Store('t', 8);
		//System.out.println(first.compareTo(second));
		Integer a = 1;
		System.out.println(a);
	}
}
