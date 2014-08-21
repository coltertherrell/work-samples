import java.util.Scanner;

public class Adder {
	
	private double firstIn;
	private double secondIn;
	
	public Adder(double input1, double input2)
	{
		firstIn = input1;
		secondIn = input2;
	}
	
	private double evaluate()
	{
		return (firstIn + secondIn);
	}
	
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter a number: ");
		String input1 = input.nextLine();
		double userEntry1 = Double.parseDouble(input1);
		System.out.println("Enter a number: ");
		String input2 = input.nextLine();
		double userEntry2 = Double.parseDouble(input2);
		
		Adder adder1 = new Adder(userEntry1,userEntry2);
		double toPrint = adder1.evaluate();
		System.out.println(toPrint);
	}
	
}
