import java.util.HashMap;
import java.util.Map;


public class Fibonnaci {

	
	public static Map<Integer, Integer> fibValue = new HashMap<Integer, Integer>();
	static int calls[] = new int[3];
	public static void main(String args[]){
		
		int n = 10;
		
		System.out.println(Fibonnaci_withoutDP_recursive(n) + ", Recurison no DP calls:" + calls[2]);
		System.out.println(Fibonnaci_withoutDP_iterative(n)+ ", Iterative calls:" + calls[0]);
		System.out.println(Fibonnaci_withDP_recursive(n) + ", Recurison with DP calls:" + calls[1]);;
	}
	
	
	/*
	 * Prints the nth Fibonacci number, this is basic recurvise method with no DP.
	 */
	public static int Fibonnaci_withoutDP_recursive(int n){
		
		calls[2]++;

		if(n == 0 || n == 1){
			return n;
		}
		
		int x = Fibonnaci_withoutDP_recursive(n-1)  +  Fibonnaci_withoutDP_recursive(n-2);
		return x;
	}

	/*
	 * Prints the nth Fibonacci number, this is DP version of Fibonacci.
	 * Its first check whetehr the value for fib(n) is already calculated, if yes tehn it is used, else a recursive call is made.
	 * This is the basic diff. b/w recursion and dp as in dp we use the solution to sub=problems.
	 */
	
	public static int Fibonnaci_withDP_recursive(int n){
		
		calls[1]++;
		
		if(n == 0 || n == 1){
			return n;
		}
		
		int x = -1;
		if(fibValue.containsKey(n-2)){
			x = fibValue.get(n-2);
		}else{
			x = Fibonnaci_withDP_recursive(n-2);
			fibValue.put(n-2, x);
		}
		
		int y = -1;
		if(fibValue.containsKey(n-1)){
			y = fibValue.get(n-1);
		}else{
			y = Fibonnaci_withDP_recursive(n-1);
			fibValue.put(n-1, y);
		}
		
		return x + y;
	}
	
	/*
	 * Prints the nth Fibonacci number, this is iterative version of DP.
	 */
	
	public static int Fibonnaci_withoutDP_iterative (int n){
		
		calls[0]++;
		if(n == 0 || n == 1){
			return n;
		}

		int last = 0;
		int now = 1;
		 
		int i = 2;
		int result=-1; 
		while( i++ <=n){
			result = last + now;
			last = now;
			now = result;
		}
		
		return result;
	}
}
