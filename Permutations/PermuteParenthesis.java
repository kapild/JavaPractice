
/* This class permutes over all the possible valid options of left and right parenethesis.
 * ()(), (( )) is valid but )((), ())( is not valid */

public class PermuteParenthesis {

	
	public static void main(String argv[]){
		
		int noOfEachParenthesis = 3;
		PermuteValidParenthesis(0,0,"", noOfEachParenthesis);
	}
	
	
	
	private static void PermuteValidParenthesis(int leftUsed, int rightRight, String soFar, int totalParenth){
		
		
//		System.out.println("Called with:"  );
//		System.out.println("left:"  + leftUsed + " , right:" + rightRight + " , str:" + soFar);

		//base case for returning
		if(leftUsed > totalParenth || rightRight > totalParenth){
			return ;
		}
		
		//if we have consumed all print
		if(leftUsed + rightRight == totalParenth * 2){
			System.out.println(soFar);
			return;
		}
		
		for(int choice = 0 ; choice < 2; choice++){
			
			String parenthStr = "";
			boolean isValid = false;
			//left parenthesis is chosen
			if(choice == 0){
				parenthStr = " ( ";
				isValid = ifAllowed(leftUsed + 1, rightRight,  totalParenth );
			}else{
				//right parenthesis
				parenthStr = " ) ";
				isValid = ifAllowed(leftUsed , rightRight + 1,  totalParenth);
			}
			if(isValid == true){
					//if its a left choice, call by adding 1 to left
					if(choice == 0)
						PermuteValidParenthesis(leftUsed +1 ,rightRight, soFar + parenthStr, totalParenth);
					//if its a right choice, call by adding 1 to right
					else
						PermuteValidParenthesis(leftUsed,rightRight + 1, soFar + parenthStr, totalParenth);
						
			}
			
		}
	}
	
	private static boolean ifAllowed(int leftCount, int rightCount, int totalParenth){
		
		if((leftCount >=0 && rightCount >= 0 && rightCount > leftCount) ){
			return false;
		}
		return true;
		
		
	}
}
