import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ExecutorService;

public class UUIDComparer {
	private static LinkedBlockingQueue<String> main_queue;
	private static int compare_number = 2000000;
	
	public static void main(String[] args)
	{
		main_queue = new LinkedBlockingQueue<String>(100000);
		ExecutorService application = Executors.newCachedThreadPool();
		
		application.execute(new Producer(main_queue, compare_number));
		application.execute(new Consumer(main_queue, compare_number));
		application.shutdown();
	}
}
