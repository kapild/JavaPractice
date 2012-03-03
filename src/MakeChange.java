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
		System.out.println(getMinChangeRecursiveDP(array, 16));
		System.out.println(minChange);
		
	}
	
	
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
	
	public static int getMinChangeIterativeDP(int array[], int C){
		minChange.clear();
		for(int i : array){
			minChange.put(i, 1);
		}
		
		for (int i = 2; i <=C ;i++){
			if(minChange.containsKey(i)){
				continue;
			}
			int min = Integer.MAX_VALUE;
			for(int options = 0; options < array.length && array[options] < i ;options++){
				int x  = 1 + minChange.get(i - array[options]);
				min = Math.min(min, x);
			}
			minChange.put(i, min);
		}
		return minChange.get(C);
	}
}
