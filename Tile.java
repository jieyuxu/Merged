public class Tile {

  private int _val;
  private String _colorCode;
  private Tile _neighbor;
  private int _orientation; //can only be 0 (up), 1 (left), 2 (down), 3 (right)
  private String[] _codes = {"\u001B[35m", "\u001B[36m", "\u001B[31m", "\u001B[34m", "\u001B[32m", "\u001B[33m"};
  public static final String RESET =  "\u001B[0m";
  

  public Tile(int val, Tile neighbor){
    _val = val;
    _colorCode = _codes[val-1]
    _neighbor = neighbor;
  }
  
  public int getVal(){
    return _val;
  }
  
  public Tile getNeighbor(){
    return _neighbor;
  }
  
  public String toString(){
    return _colorCode + getVal() + RESET;
  }
      
  public static void main(String [] args){
	  Tile x = new Tile(3, "\u001B[34m", null);
	  Tile y = new Tile(4, "\u001B[31m", x);
  	System.out.println(x);
	  System.out.println(y);
    }
  
}
