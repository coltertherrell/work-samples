import java.util.ArrayList;
import java.util.UUID;

public class StrGenerator {
	private static ArrayList<String> wordList = new ArrayList<String>();
	protected static int stopPoint = 50000;
	
	public static void main(String[] args)
			{
				
			}
	
	public ArrayList<String> getNewList()
	{
		wordList.clear();
		for (int i = 0; i < stopPoint; i++ )
		{
			UUID raw_uuid = UUID.randomUUID();
			String str_uuid = raw_uuid.toString();
			//System.out.println(str_uuid);
			wordList.add(str_uuid);
		}
		
		//System.out.println(wordList.size());
		return wordList;
	}
	
}
