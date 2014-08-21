import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;


public class Producer implements Runnable{
	
	private LinkedBlockingQueue<String> shared;
	private int stop_point;
	
	public Producer(LinkedBlockingQueue<String> link, int count)
	{
		shared = link;
		stop_point = count;
	}
	
	public void run()
	{
		
		for (int i = 1; i <= stop_point; i++ )
		{
			try
			{
				UUID raw_uuid = UUID.randomUUID();
				String str_uuid = raw_uuid.toString();
				shared.put(str_uuid);
				if ((i % 1000) == 0)
					System.out.println(i + " UUID's produced");
				Thread.yield();
			}
			
			catch (InterruptedException exception)
			{
				exception.printStackTrace();
			}
		}
	}

}
