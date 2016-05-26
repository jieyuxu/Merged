public class GameBoard{
  private int _openSpots;
  private Tile[][] _board;
  private int _maxVal;

  
  public GameBoard(){
    _board = new Tile[5][5]; //always a square matrix
    _openSpots = _board.length * _board.length;
    _maxVal = 0;
  }
  
  public int numOpenSpots(){
    return _openSpots;
  }
  
  public void add(int x, int y, Tile addin){
  	//if (_board[x][y] != null) return;
  	_board[x][y] = addin;
    if (addin.getVal() > _maxVal)
      _maxVal = addin.getVal();
  }

    public boolean canFit2(){
    	if (numOpenSpots() < 2) return false;
            int maxIndex = _board.length - 1;
      	for (int i = 0; i < maxIndex; i++){
    	    for (int j = 0; j < maxIndex; j++){
    		    if (_board[i][j] == null)
    		    //check against right neighbor and downstairs neighbor
    		    if (_board[i][j+1] == null || _board[i+1][j] == null) return true;
    	    }
      	}
    	for (int i = 0; i < maxIndex; i++){
    	    //check in last row against right neighbor
    	    if (_board[maxIndex][i] == null && _board[maxIndex][i+1] == null) return true;
    	    //check in last column against downstairs neighbor
    	    if (_board[i][maxIndex] == null && _board[i+1][maxIndex] == null) return true;
    	}
      	return false;

    //public static boolean canFit2(String[][] _board){
    public boolean canFit2(){
	if (numOpenSpots() < 2) return false;
        int maxIndex = _board.length - 1;
  	for (int i = 0; i < maxIndex; i++){
	    for (int j = 0; j < maxIndex; j++){
		if (_board[i][j] == null)
		    //check against right neighbor and downstairs neighbor
		    if (_board[i][j+1] == null || _board[i+1][j] == null) return true;
	    }
  	}
	for (int i = 0; i < maxIndex; i++){
	    //check in last row against right neighbor
	    if (_board[maxIndex][i] == null && _board[maxIndex][i+1] == null) return true;
	    //check in last column against downstairs neighbor
	    if (_board[i][maxIndex] == null && _board[i+1][maxIndex] == null) return true;
	}
  	return false;

    }

  public static boolean canFitHere(int x, int y, Tile addin, Tile[][] board){
       //if (! canFit2()) return false;
       if (board[x][y] != null) return false;
       int newX, newY;
       newX = x + addin.getOrientationX();
       newY = y + addin.getOrientationY();
       if (newX >= board.length || newY >= board.length)
          return false;
       return board[newX][newY] == null;
  }

  public String toString(){
    String ans = "";
    System.out.println("    0 1 2 3 4");
    System.out.println("===============");
    for (int i = 0; i < _board.length; i++){
      ans += i + " | ";
      for (int j = 0; j < _board[i].length; j++){
        ans += _board[i][j] + " ";
        if (j == 4) ans += "\n";
      }
    }
    return ans;
  }

  public static void main(String[] args){
    GameBoard test = new GameBoard();
    Tile a = new Tile(2, null);
    int len = test._board.length;
    test.add(0,0,a);
    for (int i = 0; i < len; i++)
    	for (int j = 0; j < len; j++)
    		test.add(i,j,new Tile(i+1, null));
    
    //test for canFit2()
    test._board[1][0] = null;
    test._board[0][0] = null;
    // test._board[0][1] = null;
    // test._board[2][0] = null;
    System.out.println(test);
    System.out.println(canFitHere(0,0, a, test._board));
   // System.out.println(test.canFit2());
  }


}
