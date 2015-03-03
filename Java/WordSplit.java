import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import javax.imageio.IIOException;
/*Author: Sam Vitello
 * Takes a list of strings as an input in the form #string "string1" "string2"... as a text file
 * and outputs whether the string can be split into words using a recursive and an iterative dynamic
 * programming algorithm and then prints out the corresponding split text.
 *
 *Build for JRE 7
 */

public class WordSplit {
	
	public static String test;
	public static int[] memoSolved;
	public static int[] iterSolved;
	public static int[] iterTrack;
	
	//checks to see if word is in dictionary
	public static boolean checkWord(String word) throws IOException{
		Scanner dict = new Scanner(new File("diction10k.txt"));
		while (dict.hasNext()){
			String temp = dict.next();
			if (temp.equals(word))
				return true;
		}
		return false;
	}
	
	//Iterative dynamic programming solution
	public static boolean dynamIter() throws IOException {
		for (int k = 0; k < test.length();k++){
			if (iterSolved[k]==0)
				continue;
			for (int b = k; b < test.length()+1; b++){
				if (checkWord(test.substring(k,b))){
					iterSolved[b-1] = 1;
					iterTrack[b-1] = k;
				}
				else if (iterSolved[k] == -1)
					iterSolved[k] = 0;
			}
			if (iterSolved[test.length()-1] == 1)
				return true;
		}
		return false;
	}
	
	//Recursive memoized dynamic programming solution
	public static boolean dynamRecur(int j) throws IOException{
		if (j >= test.length())
			return true;
		for (int x = j+1; x < test.length()+1; x++){
			//System.out.println(test.substring(j,x));
			if (memoSolved[x-1] == -1){
				if (checkWord(test.substring(j,x))){
					if (dynamRecur(x)){
						memoSolved[x-1]=1;
						return true;
					}
					else{
						memoSolved[x-1]=0;
					}
				}
			}
			else if (memoSolved[x-1] == 1){
				return true;
			}
		}
		return false;
	}
	
	//returns substring for iterative solution
	public static String splitStr(){
		String temp = "";
		int start = test.length();
		Stack<String> rev = new Stack<String>();
		while (start > 0){
			if (iterTrack[start-1] == -1)
				start -= 1;
			if (iterTrack[start-1] == 1){
				rev.push(test.substring(0,start));
				start = 0;
			}
			else{
				//System.out.println(start);
				rev.push(test.substring(iterTrack[start-1],start));
				//System.out.println(test.substring(iterTrack[start-1],start));
				start = iterTrack[start-1];
			}
		}
		while (!rev.isEmpty()){
			temp += (rev.pop() + " ");
		}
		return temp;
	}
	
	public static void main(String args[]) throws IOException{
		if (args.length < 1) {
			System.out.println("Error: Enter input.txt");
			System.exit(1);
		}
		
		int input_num = reader.nextInt();
		for (int i = 0; i < input_num; i++){
			test = reader.next();
			memoSolved = new int[test.length()];
			iterSolved = new int[test.length()];
			iterTrack = new int[test.length()];
			for (int k= 0; k < memoSolved.length; k++){
				memoSolved[k] = -1;
				iterSolved[k] = -1;
				iterTrack[k] = -1;
			}
			
			System.out.println("Phase Number: "+(i+1));
			System.out.println(test);
			System.out.println();
			
			
			boolean contIter = dynamIter();
			boolean contRecur = dynamRecur(0);
			
			
			System.out.println("Recursive:");
			if (contRecur){
				System.out.println("YES it can be split");
				for (int n = 0; n <memoSolved.length; n++){
					if (memoSolved[n] == 1)
						System.out.print(test.charAt(n)+" ");
					else
						System.out.print(test.charAt(n));
				}
			}
			else {
				System.out.println();
				System.out.println("NO it cannot be split");
			}
			
			System.out.println();
			System.out.println();
			System.out.println("Iterative:");
			if (contIter){
				System.out.println("YES it can be split");
				System.out.println(splitStr());
			}
			else {
				System.out.println();
				System.out.println("NO it cannot be split");
			}
			System.out.println();
			

			//System.out.println(checkWord(test.substring(0,6)));
		}
	}

}
