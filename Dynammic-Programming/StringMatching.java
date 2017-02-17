import java.awt.Label;




//find whether the string is a subtring of another
public class StringMatching {
	
	
	public static void main(String argvp[]){

//		bruteForce("an", "an");
//		KMP("kaplalwkapl", "kapl");
//		KMP("AAAAAAAAA", "AAA");
		
		RabinKarpWithMod("12345123", "123");
		
	}
	
	
	
	public static void bruteForce(String bigger, String smaller){
		
		for(int outer= 0; outer < bigger.length();outer++){
			boolean isMatch = true;
			for(int inner = 0; inner < smaller.length();inner++){
				if(smaller.charAt(inner)!= bigger.charAt(outer+ inner)){
					isMatch = false;
					break;
				}
			}
			if(isMatch == true){
				System.out.println("Pattern found at:" + outer);
			}
		}
		
		
	}
	
	

	private static int[] preprocess(String smaller){
		
		int [] lps = new int[smaller.length()];
		
		int len =0;
		lps[0]=0;
		
		int index =1;
		
		for(; index < smaller.length();){
			
			if(smaller.charAt(index) == smaller.charAt(len)){
				len++;
				lps[index]= len;
				index++;
			}
			else{
				
				if(len==0){
					lps[index]=0;
					index++;
				}else{
					len = lps[len-1];
				}
			}
		}
		return lps;
		
	}

	public static void KMP(String bigger, String smaller){
	
		int M = bigger.length();
		int N = smaller.length();

		int outer = 0;
		int inner = 0;
		
		int [] lps=preprocess(smaller);
		
		for(;outer < M - N +1;){
			boolean isMatch = true;
			for(;inner < N;inner++){
				if(smaller.charAt(inner)!=bigger.charAt(outer+inner)){
					isMatch = false;
					break;
				}
			}
			if(isMatch==true){
				System.out.println("Pattern found at:" + outer);
				outer++;
				inner = lps[N-1];
			}else{
				if(inner ==0){
					outer++;
				}else{
					outer++;
					inner = lps[inner-1];
				}
			}
		}
		
	}
	

	public static void RabinKarpWithMod(String bigger, String smaller){
		
		int d = 256;
		int q = 101;
		
		int pHash =0;
		int tAHash = 0;
		int h = 1;
		
		int M = bigger.length();
		int N = smaller.length();
		
		for(int i=0; i < N-1;i++){
			h = (h * d) % q;
		}
		
		for(int i=0; i < N;i++){
			pHash = (pHash * d + smaller.charAt(i))%q;
			tAHash = (tAHash * d + bigger.charAt(i))%q; 
			
		}
		System.out.println("pHash:" + pHash);
		System.out.println("tAHash:" + tAHash);
		System.out.println("h:" + h);
		
		int outer =0;
		for(; outer < M - N +1;outer++){
			
			if(pHash == tAHash){
				boolean isMatch = true;
				for(int i =0; i < N;i++){
					if(smaller.charAt(i) != bigger.charAt(i+outer)){
						isMatch = false;
						break;
					}
				}
				if(isMatch == true){
					System.out.println("Found at:" + outer);
				}
			}
			if( outer < M -N){
				tAHash = (d * (tAHash - bigger.charAt(outer) * h) + bigger.charAt(outer + N)) % q;
				if(tAHash < 0){
					tAHash+=q;
				}
				System.out.println("new Hash:" + tAHash);
			}
			
		}
	}



	public static void RabinKarp(String bigger, String smaller){
		
		int d = 10;
		int q = 101;
		int h =0;
		int pHash= 0;
		int tHash = 0;

		for(int i =0; i < smaller.length();i++){
			pHash = pHash * d + smaller.charAt(i);
			tHash = tHash * d + bigger.charAt(i);
		}
		
		h = (int) Math.pow(d, smaller.length() -1);
		
		
		System.out.println("pHash:" + pHash);
		System.out.println("tHash:" + tHash);
		
		System.out.println("h:" + h);
		
		
		for(int j = 0; j < bigger.length() - smaller.length() + 1;j++){
			
			if(pHash == tHash){
				boolean isMatch=true;
				for(int i = 0; i < smaller.length();i++){
					if(smaller.charAt(i) != bigger.charAt(i+j)){
						isMatch = false;
						break;
					}
				}
				if(isMatch == true){
					System.out.println("Pattern found at:" + j);
				}
			}
			if(j < bigger.length() - smaller.length() ){
				tHash = d* (tHash - bigger.charAt(j) * h) + bigger.charAt(j + smaller.length());
				System.out.println("new tHash:" + tHash);
			}
		}
	}
}
