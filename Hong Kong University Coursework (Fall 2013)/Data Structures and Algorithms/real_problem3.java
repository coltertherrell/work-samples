
public class real_problem3 {
	
	public static int[] stocks = new int[20];
	
	public static int max = 0;
	public static int max_ind;
	public static int min_ind;
	
	public static int[] brute(int[] stocks){
		int[] max_dates = new int[3];
		int min_i = 0;
		int max_j = 0;
		max_dates[0] = 0;
		
		for (int i = 0; i < stocks.length;i++){
			for (int j= i+1; j < stocks.length; j++) {
				if (stocks[j] - stocks[i] > max_dates[0]){
					min_i = i;
					max_j = j;
					max_dates[0] = (stocks[j]-stocks[i]);
				}
			}
		}
		max_dates[1] = min_i;
		max_dates[2] = max_j;
		return max_dates;
	}
	
	public static int[] recur(int[] stocks, int start, int end){
		//NEED TO STORE ABSOLUTE MIN OF LEFT AND ABSOLUTE MAX OF RIGHT? ON THE RIGHT TRACK
		int[] to_ret = new int[5];
		
		if (start == end){
			to_ret[0]=start;
			to_ret[1]=end;
			to_ret[2]=(start-end);
			to_ret[3]=start;
			to_ret[4]=end;
			return to_ret;
		}
		
		int middle = ((end+1)/2);
		
		int[] left = recur(stocks, 1, middle);
		int[] right = recur(stocks, middle+1, end);
		
		if ((right[4] - left[3]) > right[2] && (right[4] - left[3]) > left[2]){
			to_ret[0]=left[3];
			to_ret[1]=right[4];
			to_ret[2]=(right[4] - left[3]);
			to_ret[3]=left[3];
			to_ret[4]=right[4];
			return to_ret;
			
		}
		else if((right[4] - left[3]) > right[2]){
			to_ret[0]=left[0];
			to_ret[1]=left[1];
			to_ret[2]=left[2];
			if (right[3] < left[3])
				to_ret[3]=right[3];
			else
				to_ret[3]=left[3];
			if (right[4] > left[4])
				to_ret[4]=right[4];
			else
				to_ret[4]=left[4];
			return to_ret;
				}
		
	return to_ret;	
	}
	
	public static void pop_stocks(){
		for (int i = 1; i < 21; i++)
			stocks[i-1] = (int) Math.abs((Math.sin(i)*2300));
	}
	
	
	
	public static void main(String[] args){
		pop_stocks();
		//for (int i=0; i<stocks.length;i++){
		//	System.out.println("Day " + i+1 + " Price: " + stocks[i]);
		//}
		int[] temp = brute(stocks);
		System.out.println("Maximum profit " + temp[0]);
		System.out.println("Day to buy " + temp[1]);
		System.out.println("Day to sell " + temp[2]);
	}

}
