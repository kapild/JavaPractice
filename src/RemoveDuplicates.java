import java.util.ArrayList;
import java.util.List;


public class RemoveDuplicates {

	
	public static void main(String argv[]){
		
		
		List<String> str = new ArrayList<String>();
		str.add("a");
		str.add("a");
		str.add("b");
		str.add("c");
		str.add("a");
		str.add("k");
		System.out.println(str);
		removeDuplicate(str);
	}
	
	
	private static void removeDuplicate(List<String> str){
		
		int tail = 1;
		for(int i  = 1; i < str.size(); i++){
			int j;
			for( j = 0 ; j < tail ; j ++){
				
				if(str.get(i).equals(str.get(j))){
					break;
				}
			}
			if(j == tail){
				str.set(tail, str.get(i));
				tail++;
			}
		}
		
		List<String> removed = str.subList(0, tail);
		System.out.println(removed);
		
	}
}
