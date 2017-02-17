
public class MatrixMultiplication {

	public static void main(String argv[]){
		
		
		System.out.println(waysToMultiplyMatrix( "ABcd"));
		int dimensiosn [] = new int [] {1,2,3 };
		System.out.println(minMatrixMultiplication(dimensiosn));
	}
	
	
	
	/*
	 * calculates the way to multiply a given matrix
	 * its nothing but a binary tree 
	 */
	public static int waysToMultiplyMatrix( String remaning){
		
		
		if(remaning.length() == 1 )
			return 1;
		
		int sum = 0;
		for(int i = 1; i < remaning.length();i++){
			String left =  remaning.substring(0,i) ;
			String right = remaning.substring(i) ;
			sum += waysToMultiplyMatrix(left) * waysToMultiplyMatrix(right);
		}
		return sum;
	}
	

	/*
	 * to find minimum wasys to multiply matrix
	 * M[1.n] =  min[1..k] + min[k+1 .. n] + d[1]d[k]d[n] for all k = 1 to n- 1
	 * with min[i..i] = 0;
	 * return min[n-1][n-1] this is build up in botton up fashion
	 */
	public static int minMatrixMultiplication(int [] dimensions){
		
		int [][] m = new int[dimensions.length-1][dimensions.length-1];
		
		
		for(int i = 0; i < dimensions.length-1;i++){
			m[i][i] = 0;
		}
		
		int lenght = dimensions.length-1;//n==4
		//steps 3 to 1
		for(int steps = lenght-1; steps >=1;steps--){
			// i = o to 
			for(int i = 0; i < steps;i++){
				int j = i + lenght - steps;
				int min = Integer.MAX_VALUE;
				for(int k = i; k < j;k++){
					int sum = m[i][k] + m[k+1][j] + dimensions[i] * dimensions[j+1] * dimensions[k+1];
					min = Math.min(min, sum);
				}
				m[i][j] = min;
			}
		}
		
		//top right most element
		return m[0][m.length-1];
		
		
	}
	
}
