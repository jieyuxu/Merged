public class GameBoard{
  private int _spots;
  private Tile[][] _board;

  
  public GameBoard(){
    _board = new Tile[5][5]; //always a square matrix
    _spots = _board.length * _board.length;
  }
  
  public int getSpots(){
    return _spots;
  }
  
  public void add(int x, int y, Tile addin){
  	//if (_board[x][y] != null) return;
  	_board[x][y] = addin;
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

 //  public boolean checkLeft(){
 //  	if (_board[i][j-1].equals(null))
	// 	return true;
	// return false;
 //  }

 //  public boolean checkRight(){
 //  	if (_board[i][j+1].equals(null))
	// 	return true;
 //  }

 //  public boolean checkUp(){
 //  	if(_board[i-1][j].equals(null))
	// 	return true;
 //  }

 //  public boolean checkDown(){
 //  	if (_board[i+1][j].equals(null))
	// 	return true;
 //  }
/*
	public boolean fitTwo(){
		if (_spots < 2) return false;
		for (int i = 0; i < _board.length; i++){
			for (int j = 0; j < _board.length; i++){
				if (_board[i][j].equals(null)){
					if (i == 0){
						if (_board[i+1][j].equals(null)) // down
							return true;
						if (j == 0)
							if (_board[i][j+1].equals(null))
								return true; //left
						else if (j == _board.length - 1) //right
							if (_board[i][j-1].equals(null))
								return true;
						else //not the corners
							if (_board[i][j-1].equals(null) || _board[i][j+1].equals(null))
								return true;
					}
					if (i == _board.length - 1){
						if (_board[i-1][j].equals(null))
							return true; //up

					}
				}
  	 		}
		}
		return false;
	}
*/
  public static void main(String[] args){
    GameBoard test = new GameBoard();
    Tile a = new Tile(2, null);
    int len = test._board.length;
    test.add(0,0,a);
    for (int i = 0; i < len; i++)
    	for (int j = 0; j < len; j++)
    		test.add(i,j,new Tile(i+1, null));
    test._board[0][0] = null;
    test._board[0][1] = null;
    System.out.println(test);
    System.out.println(test.fitTwo());
  }


}
