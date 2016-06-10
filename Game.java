import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;


public class Game {
    
    private GameBoard _board;
    private int _score;
    private int _maxTileVal;
    private ArrayList<Integer> _valOptions;
    
    public Game(){
	_board = new GameBoard();    
        _score = 0;
	_maxTileVal = 2;
	_valOptions = new ArrayList<Integer>();
	_valOptions.add(1);
	_valOptions.add(2);
    }

    
    public GameBoard getGameBoard(){
	return _board;
    }

    public void printBoard(){
	System.out.println(getGameBoard());
    }

    public void printScore(){
	System.out.println("Score: " + _score);
    }

    public ArrayList<Integer> getValOptions(){
	return _valOptions;
    }

    //returns a new random single tile with a null neighbor
    public Tile genOneTile(){
        int choice = (int) (Math.random() * _valOptions.size());
	int tileVal = _valOptions.get(choice);
        return new Tile(tileVal, null);
    }

    // returns a new random tile whose neighbor is not null and
    // does not have the same value
    public Tile genTwoTiles(){
	Tile t1 = genOneTile();
	// t1.getVal() returns Integer Object
	// uses remove(Object o)
	_valOptions.remove(t1.getVal());
	Tile t2 = genOneTile(); //
	t1.setNeighbor(t2);
	_valOptions.add(t1.getVal());
	/*
        ============This bit of code gives the tile a random orientation
	but I'm not sure if that's what we want right now.===============
	int[][] orientations = {{0,1},{0,-1},{-1,0},{1,0}};
	int[] orArray = orientations[(int)(Math.random()*4)];
	t1.setOrientation(orArray[0], orArray[1]);
	*/
	return t1;
    }
    
    public Tile getNextPiece(){
	ArrayList<Integer> possible = new ArrayList<Integer>();
	for (int i = 1; i <= 7; i++)
	    possible.add(i);
	Tile piece = genOneTile();
        if (_board.canFit2())
	    if ((int) (Math.random() * 2) == 0){
		piece = genTwoTiles();
	    }
	return piece;
    }
        
    public void printPiece(Tile t){
	if (t.isSingleTile())
	    System.out.println(t + "");
	else {
	    String ans = "";
	    // up
	    if (t.getOrientationR() == -1)
		ans += "  " + t.getNeighbor() + "\n" + "* "+ t;
	    // down
	    else if (t.getOrientationR() == 1)
		ans += "* " + t + "\n  " + t.getNeighbor();
	    // right
	    else if (t.getOrientationC() == 1)
		ans += t + "" + t.getNeighbor() + "\n*";
	    // left
	    else
		ans += t.getNeighbor() + "" + t + "\n *";
	    System.out.println(ans);
	}
    }
    
    //recursive
    public void findAdjacent(int val, int row, int col, ArrayList<CoordinatePair> b, boolean[][] seen){
	if (row < 0 || row >= _board.boardLength() || col < 0 || col >= _board.boardLength()) return;
	if ( seen[row][col] || _board.getTileAt(row, col) == null) return;
	if (_board.getTileAt(row, col).getVal() == val){
	    seen[row][col] = true;
	    b.add(new CoordinatePair(row, col));
	    findAdjacent(val, row + 1, col, b, seen);
	    findAdjacent(val, row, col + 1, b, seen);
	    findAdjacent(val, row - 1, col, b, seen);
	    findAdjacent(val, row, col - 1, b, seen);
	}
	return;
    }

    //wrapper
    public void findAdjacent(int val, int row, int col, ArrayList<CoordinatePair> b){
	boolean[][] seen = new boolean[5][5];
	findAdjacent(val, row, col, b, seen);
    }

    public boolean tryToMergeAt(int r, int c){
	Tile t = _board.getTileAt(r, c);
	if (_board.getTileAt(r, c) == null) return false;
	int value = t.getVal();
	ArrayList<CoordinatePair> adjacentTiles = new ArrayList<CoordinatePair>();
	findAdjacent(value, r, c, adjacentTiles);
	if (adjacentTiles.size() < 3) return false;
	adjacentTiles.remove(0);
	for (CoordinatePair cp : adjacentTiles){
	    _board.clearTileAt(cp.getRow(), cp.getCol());
	    _board.setNumOpenSpots(_board.numOpenSpots() + 1);
	    _score += value; 
	}
	int newVal = value + 1;
	_board.getTileAt(r, c).setVal(newVal);
	if (newVal == 7) explode(r, c);
	else if (newVal > _maxTileVal) {
	    _maxTileVal = newVal;
	    _valOptions.add(newVal);
	}
	return true;
    }


    //round robin scheduling
    public void mergeAllPossible(int r, int c){
	Queue<CoordinatePair> lookAt = new LinkedList<CoordinatePair>();
	lookAt.add(new CoordinatePair(r, c));
	Tile t = _board.getTileAt(r, c);
	if (! t.isSingleTile())
	    lookAt.add(new CoordinatePair(r + t.getOrientationR(), c + t.getOrientationC())); 
	while (! lookAt.isEmpty()) {
	    //	    printBoard();
	    CoordinatePair location = lookAt.remove();
	    if (tryToMergeAt(location.getRow(), location.getCol())) {
		printBoard();
		lookAt.add(location);
	    }
	}
    }


    //pre: tile at _board[r][c] is a 7;
    public void explode(int r, int c){
	
    }

    public void play(){
	Scanner sc = new Scanner(System.in);
	System.out.println("Welcome to Merged!");
	System.out.println("For a brief introduction to this game, visit www.merged.it and watch the 30s trailer.");
	System.out.println();
	System.out.println("Starting new game...");
	System.out.println();
	printBoard();
	Tile nextPiece = getNextPiece();
	printPiece(nextPiece);
	System.out.println();
	System.out.println("Enter coordinates to place tile");
	if (! nextPiece.isSingleTile()) System.out.println("or type 'r' to rotate the piece");
	while (sc.hasNextLine()){
	    String userInput = sc.nextLine();
	    if (userInput.equals("r")) {
	    	nextPiece.rotate();
	    	printBoard();	
	    }
	    else if (userInput.length() != 3 || ! userInput.substring(1, 2).equals(" "))
		System.out.println("\nPlease enter a valid row and column coordinate pair separated by a space");
	    else {
		String rowCoor = userInput.substring(0, 1);
		String colCoor = userInput.substring(2, 3);
		try {
		    int r = Integer.parseInt(rowCoor);
		    int c = Integer.parseInt(colCoor);
		    boolean putIn = _board.placePiece(nextPiece, r, c);
		    printBoard();
		    if (putIn) {
			nextPiece = getNextPiece();
			mergeAllPossible(r, c);
		    }
		    else {
			System.out.println("\nPiece cannot fit there. Try again");
			System.out.println();
			}
		}
		catch(NumberFormatException e){
		    System.out.println("\nPlease enter a valid row and column coordinate pair separated by a space, or enter 'r' to rotate the piece");
		}
	    }
	    System.out.println();
	    if (_board.isFilled()) {
		System.out.println("Game over!");
		printScore();
		break;
	    }

	    printPiece(nextPiece);
	    System.out.println("Enter coordinates to place tile");
	    if (! nextPiece.isSingleTile()) System.out.println("or type 'r' to turn the piece");
	    
	}
    }    

    public static void main(String [] args){
      
	 Game test = new Game();
	 test.play();
    }

}
