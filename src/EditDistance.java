
public class EditDistance {

	
	public static void main(String [] argv){
		
		editDistance("ABC", "ABC");
	}
	
	
	public static void editDistance(String left, String right){
		
		
		int [][]edit = new int [left.length()+1][right.length()+1];
		
		
		for(int i = 0 ; i <= left.length();i++){
			edit[i][0] = i;
		}
		for(int j = 0 ; j <= right.length();j++){
			edit[0][j] = j;
		}
		
		
		for(int i =1;i < left.length()+1;i++){
			for(int j=1; j < right.length()+1;j++){
				if(left.substring(i-1,i).equals(right.substring(j-1,j)) ){
					edit[i][j]= edit[i-1][j-1];
				}else{
					int min = Integer.MAX_VALUE;
					int del = edit[i-1][j]+1;
					min = Math.min(min, del);

					int add = edit[i][j-1] + 1;
					min = Math.min(min, add);
					
					int sub = edit[i-1][j-1] +1;
					min = Math.min(min, sub);
					edit[i][j]= min;
	
				}
			}
		}
		
		System.out.println(edit[left.length()][right.length()]);
	}
}
