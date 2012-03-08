
public class GenerateSets {

	
	
	public static void main(String argv[]){
		
		generateSetsRecursive("", "ABC");
		generateSetsBitWise("ABC");
	}
	
	
	public static void generateSetsRecursive(String left, String remaining){
		if(remaining.length() == 0){
			System.out.println(left);
			return;
		}
		
		generateSetsRecursive(left + "", remaining.substring(1));
		generateSetsRecursive(left + remaining.substring(0, 1), remaining.substring(1));
		
	}
	
	
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
}
