import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Scanner;


public class Client {
	private static final int PORT = 1337;
	private Socket client;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private LinkedList<Integer> user_input;
	private int sum;
	
	public Client() {
		
		//User Input From Console
		
		user_input = new LinkedList<Integer>();
		
		Scanner user_in = new Scanner(System.in);
		String number = "new";
		
		while (number.length() != 0)
		{
			System.out.println("Enter a number, or press enter to exit");
			number = user_in.nextLine();
			if (number.length() != 0)
				user_input.add(Integer.parseInt(number));
			else {
				System.out.println("Done Entering");
			}
		}
	}
		
	public void runClient()
	{
		try
		{
			connectToServer();
			getStreams();
			processConnection();
		}
		catch (EOFException eofException) { System.out.println("Client Terminated Connection"); }
		catch (IOException ioException) { ioException.printStackTrace(); }
		finally { closeConnection(); }
	}
	private void connectToServer() throws IOException
	{
		System.out.println("Connecting...");		
		
		client = new Socket(InetAddress.getLocalHost(), PORT );
		
		System.out.println("Connected!");
	}
	
	private void getStreams() throws IOException
	{
		output = new ObjectOutputStream( client.getOutputStream());
		input = new ObjectInputStream( client.getInputStream());
	}
	
	private void processConnection() throws IOException
	{
		boolean proceed = true;
		sendData(user_input);
		
		do
		{
			try
			{
				sum = (Integer) input.readObject();
				System.out.println("Sum received");
				proceed = false;
			}
			catch ( ClassNotFoundException classNotFound) {System.out.println("Unknown object type");}
			
		} while (proceed);
	}
	
	private void closeConnection()
	{
		System.out.println("Closing connection");
		
		try
		{
			output.close();
			input.close();
			client.close();
		}
		catch(IOException io) { io.printStackTrace(); }
		
		System.out.println("The sum is: " + sum);
	}
	
	private void sendData( LinkedList<Integer> list)
	{
		try
		{
			//Talked to Professor Wills and he said it was ok to use
			//writeObject() instead of writeInt()
			
			output.writeObject(list);
			output.flush();
			System.out.println("List Sent");
		}
		catch(IOException io) { System.out.println("Error Writing Object"); }
	}
	
	public static void main(String[] args) {
		Client client = new Client();
		client.runClient();
		
	}
	
}


