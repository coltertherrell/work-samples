
public class problem3 {
	
	public static int g(int n){
		//NON-RECURSIVE
		int g_0 = 0;
		int g_1 = 1;
		int g_2 = 1;
		int count = 2;
		
		if (n == 0)
			return 0;
		else if (n == 1 || n==2)
			return 1;
		else
		{
			while (count < n){
				int temp = (g_0 + g_1 + g_2);
				g_0 = g_1;
				g_1 = g_2;
				g_2 = temp;
				count++;
			}
			return g_2;
		}
	}
	
	public static int g_rec(int n){
		//RECURSIVE
		if (n==0)
			return 0;
		else if (n==1 || n==2)
			return 1;
		else
			return (g_rec(n-1)+g_rec(n-2)+g_rec(n-3));
	}
	
	public static void main(String[] args){
		System.out.println(g(10));
		System.out.println(g_rec(10));
	}
		
}
