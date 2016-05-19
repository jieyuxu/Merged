public class Tile {

  private int _val;
  private String _colorCode;
  private Tile _neighbor;
  public static final String ANSI_RESET =  "\u001B[0m";

  public Tile(int val, String colorCode, Tile neighbor){
    _val = val;
    _colorCode = colorCode;
    _neighbor = neighbor;
  }
  
  public int getVal(){
    return _val;
  }
  
  public Tile getNeighbor(){
    return _neighbor;
  }
  
  public String toString(){
    return _colorCode + " " + getVal() + ANSI_RESET;
  }
  
}
