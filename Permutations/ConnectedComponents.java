import java.util.LinkedList;
import java.util.Queue;


public class ConnectedComponents {

	static int input [][] = new int [][]{  {0,1,0,1},
										   {0,1,0,1},
										   {0,1,0,1},
										   {0,1,0,1}
										};
	static int bit[][] = new int[input[0].length][input[0].length];
	
	public static void main(String argv[]){
		
		System.out.println(findConnectedComponents());
		bit = new int[input[0].length][input[0].length];		
		System.out.println(findConnectedComponenteBFS());
	}
	
	public static class Point {
		int i, j;
		
		public Point(int i , int j){
			this.i= i;
			this.j=j;
		}
		public Point(){
			i = 0;
			j=0;
		}
	}

	public static int findConnectedComponenteBFS(){
		
		Queue<Point> queue = new LinkedList<Point>();
		
		int count=0;
		for(int i =0;i< input[0].length;i++){
			for(int j =0; j < input[0].length;j++){
				if(input[i][j] == 1 && bit[i][j] == 0){
					bit[i][j]=1;
					queue.offer(new Point(i,j));
					runBFS(queue);
					count++;
				}
			}
		}
		return count;
	}
	
	
	public static void runBFS(Queue<Point> queue){
		
		while(!queue.isEmpty()){
			Point point = queue.poll();
			
			int x=0, y=0;
			//check all four neighbors
			for(int k =0; k < 4;k++){
				switch (k){
					case 0:
						x = point.i+1;
						y=point.j;
						break;
					case 1:
						x = point.i;
						y=point.j+1;
						break;
					case 2:
						x = point.i-1;
						y=point.j;
						break;
					default :
						x = point.i;
						y=point.j-1;
						break;
				}
				if(isValidMove(x, y)){
					bit[x][y]=1;
					queue.offer(new Point(x,y));
				}
			}
		}
		
	}
	//find connected components by running a Depth first search
	public static int findConnectedComponents(){
		int count = 0;
		for(int i = 0; i < input[0].length;i++){
			for(int j = 0; j < input[0].length;j++){
				
				if(input[i][j]== 1 && bit[i][j] == 0){
					runDFS(i, j);
					count++;
				}
			}
		}
		return count;
	}
	
	
	public static void runDFS(int i, int j){
		bit[i][j] = 1;
		int x=0, y=0;
		for(int k =0; k < 4;k++){
			switch (k){
				case 0:
					x = i+1;
					y=j;
					break;
				case 1:
					x = i;
					y=j+1;
					break;
				case 2:
					x = i-1;
					y=j;
					break;
				default :
					x = i;
					y=j-1;
					break;
			}
			if(isValidMove(x, y)){
				runDFS(x, y);
			}
		}
	}
	
	public static boolean isValidMove(int x , int y){
		if( x < 0 ||y  < 0 || x >= input[0].length || y >= input[0].length){
			return false;
		}
		if( input[x][y] == 0 || bit[x][y] ==1){
			return false;
		}
		return true;
	}
	
}
