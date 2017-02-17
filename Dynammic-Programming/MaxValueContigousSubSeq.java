
public class MaxValueContigousSubSeq {
	
	
	
	
	
	public static void main(String arg[]){
		int array[] = new int[] {1,6,3,-7,15,2,2,2};
		System.out.println(maxSumBrute(array));
		System.out.println(maxSumIncreasing(array));
		System.out.println(maxSumIncreasingDP(array));
	}
	
	
	/*
	 * This function extends the window of a and b and calculaltes the sum. O(n^3).
	 */
	public static int maxSumBrute(int [] array){
		
		int maxSum = Integer.MIN_VALUE;
		
		for( int a = 0 ; a < array.length;a++){
			for(int b = a ; b < array.length ; b++){
				int i = a;
				int sum = 0;
				while( i <= b){
					sum += array[i++];
				}
				maxSum = Math.max(maxSum, sum);
			}
		}
		return maxSum;
		
	}

	/*
	 * This function finds a monotonically increasing sum until it reaches zero.
	 */
	public static int maxSumIncreasing(int [] array){
		
		int maxSum = Integer.MIN_VALUE;
		
		int sum =0;
		
		for(int i = 0; i < array.length;i++){
			sum = sum + array[i];
			
			if(maxSum < sum){
				maxSum = sum;
			}else if (sum < 0){
				sum  = 0;
			}
		}
		return maxSum;
		
	}

	/*
	 * DP is the max sum over all the windows ending at i
	 * DP[i] = max( DP[i-1]  + A[i] , A[i]); 
	 * answer is the max of DP[i] ,  0<= i <= n-1
	 */
	public static int maxSumIncreasingDP(int [] array){
		
		int DP[] = new int [array.length];
		
		DP[0] = array[0];
		int max = Integer.MIN_VALUE;
		for(int i = 1; i < array.length;i++){
			int temp  = DP[i-1] + array[i];
			
			if(temp > array[i]){
				DP[i] = temp;
			}else{
				DP[i] = array[i];
			}
			max = Math.max(max, DP[i]);
		}
		return max;
	}
	
}
