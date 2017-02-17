
public class GenerateSets {

	
	
	public static void main(String argv[]){
		
//		generateSetsRecursive("", "ABC");
//		generateSetsBitWise("ABC");
//		generateRecurviseSets(0, "", "ABC");
		generateRecurviseSetsofSize(0, 3, "", "ABCDE");
	}
	
	
	/*
	 * The typical exclusion inclusion way of subsets
	 * At each call either you add the first element or you don't to the final string
	 */
	public static void generateSetsRecursive(String left, String remaining){
		if(remaining.length() == 0){
			System.out.println(left);
			return;
		}
		
		generateSetsRecursive(left + "", remaining.substring(1));
		generateSetsRecursive(left + remaining.substring(0, 1), remaining.substring(1));
	}
	
	/*
	 * The way to generate all subsets is to iterate over all binary numbers for that length of that string
	 * and use the and property to find exclusion/inclusion.
	 */
	public static void generateSetsBitWise(String input){
		
		
		int lengh = input.length();
		
		for(int i = 0 ; i < Math.pow(2, lengh);i++){
			String answer = "";
			
			for(int k = 0 ; k < lengh;k++){
				if(((i >> k) & 1) ==1  ){
					answer +=input.substring(k,k+1);
				}
			}
			System.out.println(answer);
		}
		
	}
	
	/*
	 * Again, the same inclusion exclusion way but in this case we are using index.
	 * start at zero, include or exclude it, then move to the next index of string and then do the 
	 * same logic.
	 */
	public static void generateRecurviseSets(int start, String soFar, String org){
		
		if(start == org.length()){
			System.out.println(soFar);
			return;
		}
			
		generateRecurviseSets(start+1, soFar + org.substring(start,start+1), org);
		generateRecurviseSets(start+1, soFar , org);
	}
	
	/*
	 * This generates teh subsets of size left.
	 * At every step we have k options out of org.length() - star left.
	 */
	public static void generateRecurviseSetsofSize(int start, int left , String soFar, String org){
		
		if(left == 0){
			System.out.println(soFar);
			return;
		}
			
		for(int i = start; i < org.length();i++){
			String newsoFar = soFar + org.substring(i,i+1);
			generateRecurviseSetsofSize(i+1, left-1, newsoFar, org);
		}
	}
	
}
