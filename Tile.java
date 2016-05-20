public class Tile {

    private Integer _val;
    private String _colorCode;
    private Tile _neighbor;
    private int _orientation; //can only be 0 (up), 1 (left), 2 (down), 3 (right)

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_BLACKBACKGROUND = "\u001B[40m";
    private String[] _codes = {ANSI_PURPLE, ANSI_CYAN, ANSI_RED, ANSI_BLUE, ANSI_GREEN, ANSI_YELLOW, ANSI_BLACKBACKGROUND};
    public static final String RESET =  "\u001B[0m";
  

    public Tile(Integer val, Tile neighbor){
	_val = val;
	_colorCode = _codes[val-1];
	_neighbor = neighbor;
	_orientation = 0;
    }
  
    public Integer getVal(){
	return _val;
    }
  
    public Tile getNeighbor(){
	return _neighbor;
    }
  
    public String toString(){
	return _colorCode + getVal() + RESET;
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
    }
  
}
