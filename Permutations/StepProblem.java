
public class StepProblem {

	
	public static void main(String argv[]){
		
		
		int STEPS = 6;
		
		int [] a = new int[STEPS + 1];
		a[0] = 1;
		a[1] = 1;
		for(int i = 2; i <=STEPS; i++){
			
			a[i] = a[i-1] + a[i-2];
		}
		
		System.out.println(a[STEPS]);
	}
}
