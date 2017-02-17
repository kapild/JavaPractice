
public class LongestIncreasingSubSequence {
	
	
	public static void main(String argv[]){
		
		int [] input = new int[] {11,12,-2,-3,3,7};
		System.out.println(longestIncreasingSubSequenceDP(input));
		
	}
	
	/*
	 * 
	 * M(j) = max (M(i)) for all i < j and A[i] < A[j]
	 * O(n^2) as we have to solve n sub problems, maximizing over n already solved problems
	 */
	
	public static int longestIncreasingSubSequenceDP(int [] input){
		
		int max = Integer.MIN_VALUE;
		
		int result [] = new int[input.length];
		
		result[0] =1;
		
		for (int i = 1; i < input.length;i++){
			int subMax = 1;
			int current  = input[i];
			for(int j = 0; j < i;j++){
				if( current > input[j]){
					int x = result[j] +1;
					subMax = Math.max(subMax, x);
				}
			}
			result[i] = subMax;
			max = Math.max(max, result[i]);
		}

		System.out.println();
		for(int i =0 ; i < input.length;i++){
			System.out.print(result[i] + ",");
		}
		System.out.println();
		return max;
	}

}
