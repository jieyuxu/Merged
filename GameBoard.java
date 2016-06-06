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

    public Tile[][] getBoard(){
	return _board;
    }

    public boolean canFit2(){
    	if (numOpenSpots() < 2) return false;
	int maxIndex = _board.length - 1;
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
	if (_board[r][c] != null) return false;
	int newR, newC;
	newR = r + t.getOrientationR();
	// System.out.println(newR);
	newC = c + t.getOrientationC();
	// System.out.println(newC);
	if (newR >= _board.length || newC >= _board.length ||
	    newR < 0 || newC < 0)
	    return false;
	return _board[newR][newC] == null;
    }

    public boolean placeOne(Tile t, int r, int c){
	Tile[][] b = getBoard();
	if (! (b[r][c] == null)) return false;
        b[r][c] = t;
	if (t.getVal() > _maxVal) _maxVal = t.getVal();
	return true;
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
	t.setNeighbor(null);
	if (t.getVal() > _maxVal) _maxVal = t.getVal();	
	return true;
    }

    
    public boolean placePiece(Tile t, int r, int c){
	if (t.getNeighbor() == null)
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

    public static void main(String[] args){
	GameBoard test = new GameBoard();
	int len = test.getBoard().length;
	Tile[][] testBoard = test.getBoard();
	for (int i = 0; i < len; i++)
	    for (int j = 0; j < len; j++)
		testBoard[i][j] = new Tile(i+1, null);
	//test.add(i,j,new Tile(i+1, null));
	System.out.println(test);


	Tile a = new Tile(2, null);
	a.setOrientation(0,1);
	a.setNeighbor(new Tile(3, null));
	test.getBoard()[2][0] = null;
	test.getBoard()[2][1] = null;
	System.out.println(test.placeTwo(a, 2, 0));
	System.out.println(test);
    

	//System.out.println(canFitHere(2,1, a, test._board));
	// System.out.println(test.canFit2());
    }


}
