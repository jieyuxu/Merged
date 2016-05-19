public class GameBoard{
  private int _spots;
  private Tile[][] _board;

  
  public GameBoard(){
    _board = new Tile[5][5]; //always a square matrix
    _spots = _board.length() * _board.length();
  }
  
  public int getSpots(){
    return _spots;
  }
  

}
