
public class PartitionArray {

	
	public static void main(String argv[]){
		
	int [] array = {1,1,1,1,1,1,1,1};
	int [] array2 = {2,1,2,2,2,1,2,1,2,1,2,0};
		
	partionZeroOneTwo(array2);
	printArray(array2);
	}
	
	
	private static void partionZeroOne(int []array){
		
		int low = 0, i=0;
		
		int high = array.length-1;
		
		for(;low <=high; ){
			
			if(array[low]  == 0){
				low++;
			}else{
				int now  = array[high];
				array[high--] = array[low];
				array[low] = now;
			}
		}
	}
	
	private static void partionZeroOneTwo(int []array){
		
		int low = 0, i=0;
		
		int mid = 0;
		
		int high = array.length-1;
		
		for(;mid <=high; ){
			
			switch (array[mid])  {
				
				case 0:
					int now  = array[mid];
					array[mid] = array[low];
					array[low] = now;
					low++;
					mid++;
					break;
					
				case 1:
					mid++;
					break;
				case 2:
					now  = array[high];
					array[high--] = array[mid];
					array[mid] = now;
					
			}
			
		}
	}
	
		
	
	private static void printArray(int [] array){
		
		System.out.println("");
		for(int i = 0; i < array.length; i++){
			System.out.print(" " + array[i]);
		}
	}
	
}
