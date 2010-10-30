
/* A robot is situated at the top and it can go in left or right direction 
 *  find the number of paths.
 *  To find all the paths use back tracking
 * */

public class GridDPProblem {

	
	public static void main(String [] argv){
		
		int [][]grid = new int[][]{
				{1,1,0,0,0},
				{1,1,0,0,0},
				{1,1,1,1,0},
				{1,1,1,1,1},
				{1,1,1,1,1},
		};
	

		noOfPaths(grid);
		
		possiblePath(0,0,grid,"");
	}
	

	private static int noOfPaths(int [][] grid){

		int LENGTH = grid[0].length;
		
		int [][]path = new int[LENGTH+1][LENGTH+1];
		
		path[1][1] = 1;
		
		for(int i = 1 ; i <= LENGTH; i++){
			for(int j = 1 ; j <= LENGTH; j++){
				if(i == 1 && j == 1)
					continue;
				path[i][j] = (path[i-1][j] + path[i][j-1]) * grid[i-1][j-1]; 
			}
		}
		
		System.out.println(path[LENGTH][LENGTH]);
		return path[LENGTH][LENGTH];
		
	}
	
	private static void possiblePath(int row, int col, int [][]grid, String path){
		
		if(row == grid.length-1 && col == grid.length-1){
			System.out.println(path);
			return;
		}
		
		for(int i = 0;i < 2; i++){
			
			if(i == 0 && col < grid.length - 1 && grid[row][col+1] ==1 ){
				possiblePath(row, col+1, grid, path + " R->");
			}else if (i ==1 && row < grid.length - 1 && grid[row+1][col] ==1){
				possiblePath(row+1, col, grid, path + " D| ");
			}
		}
	}
}
