public class Tile {

    private Integer _val;
    private String _colorCode;
    private Tile _neighbor;
    private int[] _orientation; //a 2 elem array indicating delta x delta y of neighbor


    /*
      Tile Orientations
      right = [0,1]
      left = [0,-1]
      up = [-1,0]
      down = [1,0]
    */

    public static final String ANSI_RESET = "\u001B[0m";  //default text
    public static final String ANSI_PURPLE = "\u001B[37;45m";  //1    
    public static final String ANSI_CYAN = "\u001B[37;46m";  //2
    public static final String ANSI_RED = "\u001B[37;41m";  //3
    public static final String ANSI_BLUE = "\u001B[37;44m";  //4
    public static final String ANSI_YELLOW = "\u001B[37;43m";  //5
    public static final String ANSI_GREEN = "\u001B[37;42m";  //6
    public static final String ANSI_BLACK = "\u001B[37;40m";  //7
    private String[] _codes = {ANSI_PURPLE, ANSI_CYAN, ANSI_RED, ANSI_BLUE, ANSI_GREEN, ANSI_YELLOW, ANSI_BLACK};
  

    public Tile(Integer val, Tile neighbor){
	_val = val;
	_colorCode = _codes[val-1];
	_neighbor = neighbor;
	_orientation = new int[2];
	_orientation[0] = 1;
	_orientation[1] = 0;
    }

    public void setOrientation(int r, int c){
	_orientation[0] = r;
	_orientation[1] = c;
    }

    public void setNeighbor(Tile t){
	_neighbor = t;
    }
  
    public Integer getVal(){
	return _val;
    }
  
    public int getOrientationR(){
	return _orientation[0];
    }

    public int getOrientationC(){
	return _orientation[1];
    }

    public Tile getNeighbor(){
	return _neighbor;
    }
  
    public String toString(){
	return _colorCode + getVal() + ANSI_RESET;
    }
      
    public static void main(String [] args){
	//	Tile x = new Tile(3, null);
	//	Tile y = new Tile(4, x);
	//	System.out.println(x);
	//	System.out.println(y);
	for (int i = 1; i <= 7; i++) {
	    Tile b = new Tile(i, null);
	    System.out.println(b);
	}
	System.out.println(1 % -2);
	System.out.println(0 % -2);
	System.out.println(-1 % -2);
	System.out.println(-2 % -2);
    }
  
}
