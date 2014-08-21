import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;


public class Server {
	private static final int PORT = 1337;
	private ServerSocket server;
	private Socket socket;
	private ObjectOutputStream outputStream;
	private ObjectInputStream inputStream;
	private int sum = 0;
	
	public void runServer()
	{
		try {
			
			server = new ServerSocket(PORT);
			boolean proceed = true;
			

			while (proceed)
			{
				try
				{
					waitForConnection();
					getStreams();
					processConnection();
					proceed = false;
				}
				catch (EOFException eof)
				{
					System.out.println("Server Terminated Connection");
				}
				finally
				{
					closeConnection();
				}

			}

			
			socket.close();
			
			System.out.println("and... server done");
		}
		catch (IOException ex) { ex.printStackTrace(); }
	}
	
	private void waitForConnection() throws IOException
	{
		System.out.println("Waiting for connection...");
		socket = server.accept();
		System.out.println("Connection Recieved");
	}
	
	private void getStreams() throws IOException
	{
		outputStream = new ObjectOutputStream( socket.getOutputStream());
		outputStream.flush();
		
		inputStream = new ObjectInputStream( socket.getInputStream());
		
		System.out.println("Streams Established");
	}
	
	private void processConnection() throws IOException
	{
		System.out.println("Connection Successful");
		LinkedList toSum;
		boolean proceed = true;
		
		do
		{
			try
			{
				//Talked to Professor Wills and he said it was ok to use
				//readObject() instead of readInt()
				
				toSum = (LinkedList<Integer>) inputStream.readObject();
				System.out.println("Object Recieved");
				for (int i = 0; i < toSum.size(); i++)
				{
					int temp = (Integer) toSum.get(i);
					sum += temp;
				}
				proceed = false;
			}
			catch (ClassNotFoundException classNotFoundException)
			{
				System.out.println("Unknown object type recieved");
			}
		} while ( proceed );
		
		sendData(sum);
	}
	
	private void sendData(Integer data)
	{
		try
		{
			outputStream.writeObject(data);
			outputStream.flush();
			System.out.println("Data sent");
		}
		catch (IOException ioException) {
			System.out.println("Problem Writing Data");
		}
	}
	
	
	private void closeConnection()
	{
		System.out.println("Terminating Connection");
		
		try
		{
			outputStream.close();
			inputStream.close();
			socket.close();
		}
		catch (IOException ioException)
		{
			ioException.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Server server = new Server();
		server.runServer();
	}
}
