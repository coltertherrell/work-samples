import java.util.LinkedList;
import java.util.Queue;


public class Custom_Queue extends LinkedList<Compare_Char> implements Queue<Compare_Char>
{
	
	public Custom_Queue()
	{
		super();
		
	}
	
	@Override
	public boolean offer(Compare_Char ch)
	{
		if (this.size() == 0){
			this.add(ch);
			return true;
		}
		else {
			for (int i = 0; i < this.size(); i++)
			{
				if (this.get(i).compareTo(ch) == 1)
				{
					//System.out.println("Replace True");
					this.add(i, ch);
					return true;
				}
				else if (this.get(i).compareTo(ch) == 0)
				{
					//System.out.println("Compare equal");
					this.add(i,ch);
					return true;
				}
			}
			//System.out.println("Replace False");
			this.add(ch);
			return true;
		}
	}
	

}
