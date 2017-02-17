
public class MaximizeStockProfitLoss {
	
	
	//brute force solution
	// DP solution of maintaining the max. 
	// convert it to a max sequence problem by taking the diff. 
	//good starting point 
//	http://stackoverflow.com/questions/7086464/interview-question-maximum-single-sell-profit
		

	public static class ThreeVal {
		public int max; 
		public int min; 
		public int profitMax; 
		
		public ThreeVal(){
			
		}
		
		public ThreeVal(int min, int max, int profitMax){
			this.max = max;
			this.min = min;
			this.profitMax = profitMax;
			
		}
		
		@Override
		public String toString(){
			return "profit:" + profitMax + ", buy:"+ min + ", sell:" + max;
		}
	}
	
	public static void main(String argv[]){
		
		int [] input = new int[]{10,2,3,10,12,1000,10,10000,100000};
		bruteForce(input);
		dyanamicApproach(input);
		convertToMaxContigousSumDP(input);
		System.out.println(maxProfitRecursion(input,0, input.length-1));
		
	}
	/*
	 * This is an O(n^2) approach to finding the solution. It just tries to sell at a 
	 * position and check what all previous position it can buy. 
	 */
	
	public static void bruteForce(int [] input){
		
		int lenght = input.length;
		
		int maxProfit = Integer.MIN_VALUE;
		int buyIndex=0, sellIndex =0;
		for(int i = 1; i < lenght;i++){
			for(int j = 0; j <i;j++){
				int profit = input[i] - input[j];
				if(profit > maxProfit){
					maxProfit = profit;
					buyIndex = j;
					sellIndex = i;
				}
			}
		}
		
		System.out.println("profit:" + maxProfit + ", buy:"+ input[buyIndex] + ", sell:" + input[sellIndex]);
		
	}
	
	/*
	 * This is the DP solution, which says that suppose at position k, I know what is the minimum value so far, 
	 * if I sell it at the position k, if the profit made is more than the earlier profit, then profit at k is the
	 * max profit I can make. 
	 */
	public static void dyanamicApproach(int [] input){
		
		int minBuySoFar = input[0];
		int maxProfit  = Integer.MIN_VALUE;
		
		int buyIndex = 0, sellIndex = 0;
		for(int i = 1; i < input.length;i++){
			int profit = input[i] - minBuySoFar;
			if(profit > maxProfit){
				maxProfit = profit;
				sellIndex = i;
			}
			if(input[i] < minBuySoFar){
				minBuySoFar = input[i];
				buyIndex = i;
			}
		}
		System.out.println("profit:" + maxProfit + ", buy:"+ input[buyIndex] + ", sell:" + input[sellIndex]);
	}
	
	public static void convertToMaxContigousSumDP(int [] input){
		
		int [] diffInput = new int[input.length -1];
		
		//calculate the diff. of two consecutive aaray
		for(int i = 1; i < input.length;i++){
			diffInput[i-1] = input[i] - input[i-1];
		}
		
		//now the solution is to find the max. contigous sequence
		
		int maxSumOverall  = Integer.MIN_VALUE;
		int maxPrevSum = diffInput[0];
		int buyIndex=0, sellIndex =0;
		for(int i = 1; i < diffInput.length;i++){
			
			int sum = diffInput[i] + maxPrevSum;
			if(sum > diffInput[i]){
				sellIndex=i;
				maxPrevSum = sum;
			}else{
				maxPrevSum = diffInput[i];
				buyIndex = i;
			}
			
			if(maxPrevSum > maxSumOverall){
				maxSumOverall = maxPrevSum;
				sellIndex = i+1;
			}
			
		}
		
		System.out.println("profit:" + maxSumOverall + ", buy:"+ input[buyIndex] + ", sell:" + input[sellIndex]);
	}
	
	
	public static ThreeVal maxProfitRecursion(int [] input, int low, int high){
		
		if(low == high){
			return new ThreeVal(input[low], input[high], 0);
		}
		
		int mid = low + (high - low)/2;
		
		ThreeVal left = maxProfitRecursion(input, low, mid);
		ThreeVal right = maxProfitRecursion(input, mid+1, high);
		
		int maxProfit = Math.max(right.max - left.min, Math.max(right.profitMax, left.profitMax));
		
		return new ThreeVal(Math.min(left.min, right.min), Math.max(left.max, right.max), maxProfit);
	}
}
