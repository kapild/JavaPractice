
public class BinomialCoeff {

	
	public static void main(String argc[]){
		
		System.out.println(BC(5,0));
		System.out.println(BC_DP(5,2));
		
	}
	
	
	
	public static int BC(int n, int k){
		
		if(k==0 || k == n){
			return 1;
		}
		
		int x = BC(n-1, k-1) + BC(n-1,k);
		return x;
	}
	
	
	public static int BC_DP(int n, int k){
		
		int[][] DP = new int[n+1][k+1];
		
		
		for(int i = 0 ; i<= n;i++){
			for(int j =0; j <= k;j++ ){
				// nCn == 1 , nC0 == 1 and 0C0 == 1
				if(j==0 || j == i || i ==0){
					DP[i][j] = 1;
					continue;
				}
				DP[i][j] = DP[i-1][j-1] + DP[i-1][j];
			}
		}
		
		return DP[n][k];
	}
}
