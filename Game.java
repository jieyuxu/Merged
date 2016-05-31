import java.util.ArrayList;

public class Game {
    
    private GameBoard _board;
    private int _score;
    
    public Game(){
	_board = new GameBoard();    
        _score = 0;
    }

    public Tile genOneTile(ArrayList<Integer> valOptions){  //no neighbor
        int choice = (int) (Math.random() * valOptions.size());
	int tileVal = valOptions.get(choice);
        Tile ans = new Tile(tileVal, null);
    }

    public Tile genTwoTiles(ArrayList<Integer> valOptions){
	Tile t1 = genOneTile(valOptions, null);
	// t1.getVal() returns Integer Object
	// uses remove(Object o)
	valOptions.remove(t1.getVal());
	Tile t2 = genOneTile(valOptions, t1);
	t1.setNeighbor(t2);
	return t1;
    }
    
    public Tile getNextPiece(){
	ArrayList<Integer> possible = new ArrayList<Integer>();
	for (int i = 1; i <= 7; i++)
	    possible.add(i);
	Tile piece = genOneTile(possible, null);
        if (_board.canFit2())
	    if ((int) (Math.random() * 2) == 0){
		piece = genTwoTiles(possible);
	    }
	return piece;
    }

    /*
    public void rotatePiece(Tile t){
	if (t.getNeighbor() == null) return;
	t.setOrientation( (t.getOrientationX() - 1) % 2, (t.getOrientationY() - 1) % 2);
	Tile n = t.getNeighbor();
	n.setOrientation( (n.getOrientationY() - 1) % 2, (n.getOrientationY() - 1) % 2);
    }
    */
    
    //-------------
    public Tile[][] getGameBoard(){
	return _board.getBoard();
    }

    public GameBoard getBoardOnly(){
	return _board;
    }
    
    public boolean placeOne(Tile t, int r, int c){
	if (! (getGameBoard()[r][c] == null)) return false;
	getGameBoard()[r][c] = t;
	return true;
    }

    public boolean placeTwo(Tile t, int r, int c){
	GameBoard bd = getBoardOnly();
	Tile[][] b = getGameBoard();
	if (! bd.canFit2()) return false;
	int[] orientation = new int[2];
	orientation[0] = t.getOrientationR();
	orientation[1] = t.getOrientationC();
	if (!(b[r][c] == null &&
	      b[r + orientation[0]][c + orientation[1]] == null)) return false;
	b[r][c] = t;
	b[r + orientation[0]][c + orientation[1]] = t.getNeighbor();
	t.setNeighbor(null);
	return true;
    }

    
    public void play(){
        System.out.println(_board);
    }

     public static void main(String [] args){
	 /*
	Game a = new Game();
	Tile b = a.getNextPiece();
	System.out.println(b);
	System.out.println(b.getNeighbor());
	 */	 
	 Game test = new Game();
	 int len = test.getGameBoard().length;
	 for (int i = 0; i < len; i++)
	     for (int j = 0; j < len; j++)
		 test.getBoardOnly().add(i,j,new Tile(i+1, null));
	 test.getGameBoard()[2][0] = null;
	 test.getGameBoard()[2][1] = null;
	 Tile a = new Tile(2, null);
	 Tile b = new Tile(3, null);	 
	 a.setNeighbor(b);
	 a.setOrientation(0,1); //right
	 Tile c = new Tile(4, null);
	 System.out.println(test.getBoardOnly());
	 System.out.println("placing tile:");
	 System.out.println(test.placeOne(c, 2, 0));
	 System.out.println(test.getBoardOnly());	 
    }
}
