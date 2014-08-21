import java.util.concurrent.LinkedBlockingQueue;

public class Consumer implements Runnable{

	private LinkedBlockingQueue<String> shared;
	private String greatest;
	private int stop_point;
	
	public Consumer(LinkedBlockingQueue<String> link, int count)
	{
		shared = link;
		stop_point = count;
	}
	
	public void run()
	{
		greatest = "";
		
		for (int i = 1; i <= stop_point; i++)
		{
			try
			{
				if (greatest.length() == 0)
					greatest = shared.take();
				else
				{
					String temp = shared.take();
					if (greatest.compareTo(temp) == -1)
						greatest = temp;
				Thread.sleep(1);
				//Thread.yield();
				}
				if ((i % 1000) == 0)
					System.out.println(i + " UUID's consumed");
				
			}
			
			catch (InterruptedException exception)
			{
				exception.printStackTrace();
			}
			
		}

		System.out.println("The greatest UUID is " + greatest);
	}
}
