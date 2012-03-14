import java.util.HashMap;
import java.util.Map;


public class MakeChange {

	
	static Map<Integer, Integer> minChange = new HashMap<Integer, Integer>();
	static int calls=0;
	public static void main(String argv[]){
		
		int array[] = new int [] {1,3,6,10,21};
		
		for(int i : array){
			minChange.put(i, 1);
		}
		System.out.println(getMinChangeRecursiveDP(array, 5));
		System.out.println(calls);
		calls = 0;
		System.out.println(getMinChangeRecursiveDP(array, 12));
		System.out.println(minChange);
		System.out.println(calls);
		
	}
	
	
	/*
	 * Consider this as a back tracking solution, where at at node you have an option of choose either of the coin, hence array.lenght choices.
	 * once that choice is made, the solution is nothing but recursion of that choice + C - choice.
	 * But, here we are using the already computed sub-tree solution and not calcuating it again as we do in recursion.
	 */
	public static int getMinChangeRecursiveDP(int array[], int C){
		calls++;
		if(minChange.containsKey(C)){
			return minChange.get(C);
		}
		
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < array.length && array[i] < C; i++){
			int x = getMinChangeRecursiveDP(array, array[i]) + getMinChangeRecursiveDP(array, C - array[i]);
			min = Math.min(min, x);
		}
		minChange.put(C, min);
		return min;
	}

	/*
	 * This uses the incremental DP approach. To calculate MakeChange(C), all value upto C- max(input) should be filled.
	 * consider minChange as a array which is filled from 2 to C.
	 * Add every stage of i, we have an option to choose any of the coins and then use the already computer minChange(i -array[option]) value
	 */
	public static int getMinChangeIterativeDP(int array[], int C){
		minChange.clear();
		for(int i : array){
			minChange.put(i, 1);
		}
		calls=0;
		for (int i = 2; i <=C ;i++){
			if(minChange.containsKey(i)){
				continue;
			}
			int min = Integer.MAX_VALUE;
			for(int options = 0; options < array.length && array[options] < i ;options++){
				calls++;
				int x  = 1 + minChange.get(i - array[options]);
				min = Math.min(min, x);
			}
			minChange.put(i, min);
		}
		return minChange.get(C);
	}
}
