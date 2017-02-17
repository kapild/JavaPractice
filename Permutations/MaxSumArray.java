
public class MaxSumArray {
	
	
	public static void main(String argv[]){
		
		int[] inputArray = new int[] {1,1,-4,-1,-4};
		
		System.out.println("Max sum:" + getMaxSum(inputArray));
		System.out.println("Max sum2:" + getMax2(inputArray));
		
		for(int i = 0; i < inputArray.length;i++){
			inputArray[i] = inputArray[i] * -1;
		}
		
		System.out.println("Max sum:" + getMaxSum(inputArray));
		System.out.println("Max sum2:" + getMax2(inputArray));
		
	}

	
	private static int getMaxSum(int [] array){
		
		int sum = 0;
		int maxSumHere = 0;
		int maxSumTotal =0;
		for(int i = 0; i < array.length; i++){
			
			maxSumHere = Math.max(0, maxSumHere + array[i]);
			
			maxSumTotal = Math.max(maxSumTotal, maxSumHere);
			
		}
		return maxSumTotal;
		
	}
	
	private static int getMax2(int[] array){
		
		int max =0;
		for (int start = 0 ; start < array.length; start++){
			for(int window = array.length -1; window >= start; window--){
				int sum = 0;
				for(int i = start; i <= window  ; i++){
					sum+= array[i];
				}
				max = Math.max(sum,max);
			}
		}
		
		return max;
	}
	                                    
}
