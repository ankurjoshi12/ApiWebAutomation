package ArrayProblem;

public class SubArrayWithMaxSum {
	
	private static void sumOfArray(int [] arr) {
		int len = arr.length ;
		
		int sum  = 0 ; 
		int start=0 ; 
		int end =0 ; 
		int lenth = 0 ; 
		
		for(int i = 0 ; i< len-1 ; i++) {
			for(int j =1 ; j<len-1 ; j++) {
				sum  = Math.max(sum,  arr[i]+arr[j]);
				lenth  = j-i ; 
				start = i ;
				end = j ;
			}
			for(int k = start ; k<=end ; k++) {
				System.out.println(arr[start]);
			}
		}
	}
	public static void main(String[] args) {
		int [] arr = {-1,2,3} ;
		SubArrayWithMaxSum.sumOfArray(arr);
	}

}
