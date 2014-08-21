import java.lang.Comparable;

public class Compare_Char implements Comparable<Compare_Char> 
{
	private Object key;
	private int count;
	
	public Compare_Char(Object ch, Integer value)
	{
		this.key = ch;
		this.count = value;
	}
	
	@Override
	public int compareTo(Compare_Char other)
	{
		//System.out.println( Integer.valueOf(count).compareTo(other.count));
		return (-1*Integer.valueOf(count).compareTo(other.count));
	}
	
	public static void main(String[] args)
	{
		//Compare_Char first = new Compare_Char('r',8);
		//Compare_Char second = new Compare_Char('t', 8);
		//System.out.println(first.compareTo(second));
		Integer a = 1;
		System.out.println(a);
	}
	
	@Override
	public String toString()
	{
		return (count + " Instances of: " + key);
	}
}
