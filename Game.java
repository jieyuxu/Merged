import java.util.ArrayList;
import java.util.Scanner;


public class Game {
    
    private GameBoard _board;
    private int _score;
    private ArrayList<Integer> _valOptions;
    
    public Game(){
	_board = new GameBoard();    
        _score = 0;
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
	if (t.getNeighbor() == null)  
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
	while (sc.hasNextLine()){
	    String userInput = sc.nextLine();
	    if (userInput.equals("rotate")) nextPiece.rotate();
	    else if (userInput.length() != 3 || ! userInput.substring(1, 2).equals(" "))
		System.out.println("\nPlease enter a valid row and column coordinate pair separated by a space");
	    else {
		String rowCoor = userInput.substring(0, 1);
		String colCoor = userInput.substring(2, 3);
		try {
		    int r = Integer.parseInt(rowCoor);
		    int c = Integer.parseInt(colCoor);
		    boolean putIn = _board.placePiece(nextPiece, r, c);
		    if (putIn)
			nextPiece = getNextPiece();
		    else
			System.out.println("\nPiece cannot fit there. Try again");
		}
		catch(NumberFormatException e){
		    System.out.println("\nPlease enter a valid row and column coordinate pair separated by a space");
		}
	    }
	    System.out.println();
	    printBoard();
	    printPiece(nextPiece);
	    
	    //System.out.println(userInput);
	}
	//     System.out.println(_board);
    }    

    public static void main(String [] args){
      
	 Game test = new Game();
	 test.play();
    }

}
