public class GameBoard{
    private int _openSpots;
    private Tile[][] _board;

  
    public GameBoard(){
	_board = new Tile[5][5]; //always a square matrix
	_openSpots = _board.length * _board.length;
    }
  
    public int numOpenSpots(){
	return _openSpots;
    }

    public void setNumOpenSpots(int val){
	_openSpots = val;
    }

    public Tile[][] getBoard(){
	return _board;
    }

    public int boardLength(){
	return _board.length;
    }


    //pre-condition: r and c are valid row/col coords
    public Tile getTileAt(int r, int c){
	return _board[r][c];
    }

    public void clearTileAt(int r, int c){
	_board[r][c] = null;
    }

    public boolean canFit2(){
	int open = numOpenSpots();
	if (open > 13) return true;
    	if (open < 2) return false;
	int maxIndex = boardLength() - 1;
      	for (int i = 0; i < maxIndex; i++){
    	    for (int j = 0; j < maxIndex; j++){
		if (_board[i][j] == null)
    		    //check against right neighbor and downstairs neighbor
    		    if (_board[i][j+1] == null || _board[i+1][j] == null)
			return true;
    	    }
      	}
    	for (int i = 0; i < maxIndex; i++){
    	    //check in last row against right neighbor
    	    if (_board[maxIndex][i] == null && _board[maxIndex][i+1] == null)
		return true;
    	    //check in last column against downstairs neighbor
    	    if (_board[i][maxIndex] == null && _board[i+1][maxIndex] == null)
		return true;
    	}
      	return false;
    }


    public boolean canFitHere(Tile t, int r, int c){
	if (r >= boardLength() || c >= boardLength() || r < 0 || c < 0)
	    return false;
	boolean firstFits = _board[r][c] == null;
	if (t.isSingleTile()) return firstFits;
	return firstFits && canFitHere(t.getNeighbor(), r + t.getOrientationR(), c + t.getOrientationC());
    }

    public boolean placeOne(Tile t, int r, int c){
	Tile[][] b = getBoard();
	if (! canFitHere(t, r, c)) return false;
        b[r][c] = t;
	_openSpots--;
	return true;
    }

    public boolean isFilled(){
	return _openSpots == 0;
    }

    public boolean placeTwo(Tile t, int r, int c){
	Tile[][] b = getBoard();
	int[] orientation = new int[2];
	orientation[0] = t.getOrientationR();
	orientation[1] = t.getOrientationC();
	//System.out.println(orientation[0] + orientation[1]);
	if (! canFitHere(t, r, c)) return false;
	b[r][c] = t;
	b[r + orientation[0]][c + orientation[1]] = t.getNeighbor();
	setNumOpenSpots(numOpenSpots() - 2);
	return true;
    }

    
    public boolean placePiece(Tile t, int r, int c){
	if (t.isSingleTile())
	    return placeOne(t, r, c);
	else
	    return placeTwo(t, r, c);
    }

    
    public String toString(){
	String ans = "";
	System.out.println("    0 1 2 3 4");
	System.out.println("===============");
	for (int i = 0; i < _board.length; i++){
	    ans += i + " | ";
	    for (int j = 0; j < _board[i].length; j++){
		if (_board[i][j] == null) ans += "X ";
		else ans += _board[i][j] + " ";
		if (j == 4) ans += "\n";
	    }
	}
	return ans;
    }


}
