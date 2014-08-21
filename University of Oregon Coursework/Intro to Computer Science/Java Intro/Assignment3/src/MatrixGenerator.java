// Author: Sam Vitello
//
// After running the Matrix Generator for Matrices with dimension from 1x1 to 5000x5000 and with
// Iteration counts from 1 to 50 I found that the Sparse Matrix data structure always takes longer
// to complete than the dense matrix data structure. This was surprising to me as I thought before that
// the dense matrix would take longer as the CPU would have to iterate through more values, especially as
// the matrix dimensions got larger and larger. This must mean that the ArrayList method is more costly
// than the Array method. I am not really sure why this is however. For the average entries in the
// sparse matrixes I found that it was often very close to the expected number which I obtained by
// multiplying the total spaces by the density.
//
// Note: I have some commented out test code in the main method and 2 test methods displayDenMatrix and displaySparMatrix

import java.util.ArrayList;
import java.util.Scanner;



public class MatrixGenerator {
	private int dimension;
	private double density;
	private int count;
	
	public MatrixGenerator(int dim, double den, int cnt)
	{
		dimension = dim;
		density = den;
		count = cnt;
	}
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter Matrix Dimension: ");
		String input1 = input.nextLine();
		int userEntry1 = Integer.parseInt(input1);
		System.out.println("Enter Matrix Density: ");
		String input2 = input.nextLine();
		double userEntry2 = Double.parseDouble(input2);
		System.out.println("Enter Iteration Count: ");
		String input3 = input.nextLine();
		int userEntry3 = Integer.parseInt(input3);
		
		MatrixGenerator matrix = new MatrixGenerator(userEntry1, userEntry2, userEntry3);
		double denseTime = 0;
		double sparseTime = 0;	
		int totalEntries = 0;
		//System.out.println(matrix.dimension);
		for (int cnt = 0; cnt < matrix.count; cnt++)
		{
			double[][] denseMatrix = getDenseMatrix(matrix);
			ArrayList<ArrayList< Double >> sparseMatrix = new ArrayList<ArrayList< Double >>();
			int sparseEntries = populateMatrix(matrix,denseMatrix,sparseMatrix);
			//System.out.println(sparseEntries);
			totalEntries += sparseEntries;
			//displayDenMatrix(denseMatrix);
			//displaySparMatrix(sparseMatrix);
			//int denseRow = sumDense(matrix,denseMatrix);
			//int sparseRow = sumSparse(sparseMatrix);
			//System.out.println("Greatest Dense Row: " + denseRow);
			//System.out.println("Greatest Sparse Row: " + sparseRow);
			double denseRunTime = sumDense(matrix,denseMatrix);
			double sparseRunTime = sumSparse(sparseMatrix);
			denseTime += denseRunTime;
			sparseTime += sparseRunTime;
		}
		
		System.out.println("Total time for dense matrix: " + denseTime);
		System.out.println("Total time for sparse matrix: " + sparseTime);
		double avgEntry = totalEntries/matrix.count;
		System.out.println("Average Entries: " + avgEntry);
	}
	
	private static double[][] getDenseMatrix(MatrixGenerator matrix)
	{
		double [][] denseMatrix = new double[matrix.dimension][matrix.dimension];
		return denseMatrix;
	}
	
	private static int populateMatrix( MatrixGenerator master, double[][] Den, ArrayList< ArrayList< Double > > Spar)
	{
		int filled = 0;
		for (int rowCnt = 0; rowCnt < master.dimension; rowCnt++ )
		{
			ArrayList< Double > newRow = new ArrayList< Double >();
			
			for (int colCnt = 0; colCnt < master.dimension; colCnt++ )
			{
				double testNum;
				testNum = Math.random();
				if (testNum <= master.density)
				{
					Den[rowCnt][colCnt] = testNum + Double.MIN_VALUE;
					newRow.add(testNum + Double.MIN_VALUE);
					filled++;
				}
				else
				{
					Den[rowCnt][colCnt] = 0.0;
				}
			}
			Spar.add(newRow);
		}
		return filled;
	}
	
	private static void displayDenMatrix(double[][] matrix)
	{
		for (int rowCnt = 0; rowCnt < matrix.length; rowCnt++ )
		{
			System.out.println();
			for (int colCnt = 0; colCnt < matrix.length; colCnt++ )
			{
				System.out.print( matrix[rowCnt][colCnt] + ",");
			}
		}
	}
	
	private static void displaySparMatrix(ArrayList< ArrayList< Double > > Spar)
	{
		for (ArrayList< Double > list : Spar )
		{
			System.out.println();
			System.out.print("row");
			System.out.println();
			for (double item : list )
			{
				System.out.print( item + ",");
			}
		}
	}
	
	private static double sumDense(MatrixGenerator master, double[][] matrix)
	{
		double start = System.currentTimeMillis();
		double greatestSum = 0;
		int greatestRowIndex = 0;
		for (int rowCnt = 0; rowCnt < master.dimension; rowCnt++ )
		{
			double sum = 0;
			for (int colCnt = 0; colCnt < master.dimension; colCnt++ )
			{
				sum += matrix[rowCnt][colCnt];
			}
			if(sum >= greatestSum)
			{
				greatestSum = sum;
				greatestRowIndex = rowCnt;
			}
		}
		double end = System.currentTimeMillis() - start;
		//return greatestRowIndex;
		return end;
	}
	
	private static double sumSparse(ArrayList< ArrayList< Double >> matrix)
	{
		double start = System.currentTimeMillis();
		double greatestSum = 0;
		int greatestRowIndex = 0;
		int rowCount = 0;
		for (ArrayList< Double > list : matrix )
		{
			double sum = 0;
			for (double item : list )
			{
				sum += item;
			}
			if(sum >= greatestSum)
			{
				greatestSum = sum;
				greatestRowIndex = rowCount;
			}
			++rowCount;
		}
		double end = System.currentTimeMillis() - start;
		//return greatestRowIndex;
		return end;
	}
}
