import java.util.ArrayList;
import java.util.Queue;

public class Merge {

    private int[][] _nums;
    
    public Merge(){
	_nums = new int[5][5];
	_nums[0][0] = 1;

	_nums[2][1] = 1;
	_nums[1][1] = 1;
	_nums[3][1] = 1;
	_nums[3][2] = 1;
	_nums[2][2] = 1;
	_nums[2][3] = 1;
	_nums[2][4] = 1;
	_nums[1][4] = 1;
    }
    
    public void merge(int val, int row, int col, boolean[][] seen, ArrayList<int[]> b){
	if (row < 0 || row >= _nums.length || col < 0 || col >= _nums.length) return;
	if ( seen[row][col] ) return;
	if (_nums[row][col] == val) {
	    seen[row][col] = true;
	    int[] location = new int[2];
	    location[0] = row;
	    location[1] = col;
	    b.add(location);
	    merge(val, row + 1, col, seen, b);
	    merge(val, row, col + 1, seen, b);
	    merge(val, row - 1, col, seen, b);
	    merge(val, row, col - 1, seen, b);
	}
	return;
    }

    public static void main(String [] args){
	Merge a = new Merge();
	
	ArrayList<int[]> c = new ArrayList<int[]>();
	boolean [][] seen = new boolean[5][5];
	a.merge(1, 1, 1, seen, c);
	if (c.size() >= 3)
	    for (int[] location : c)
		a._nums[location[0]][location[1]] = 4;

	for (int i = 0; i < a._nums.length; i++) {
	    for (int j = 0; j < a._nums.length; j++)
		System.out.print(a._nums[i][j] + " ");	   
	    System.out.println();
	}

    }
}
