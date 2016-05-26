import java.util.ArrayList;

public class Game {
    
    private GameBoard _board;
    private int _score;
    
    public Game(){
	_board = new GameBoard();    
        _score = 0;
    }

    public Tile genOneTile(ArrayList<Integer> valOptions, Tile neighbor){
        int choice = (int) (Math.random() * valOptions.size());
	//	System.out.println(choice + " first");			   
	int tileVal = valOptions.get(choice);
	//System.out.println(tileVal);
        return new Tile(tileVal, neighbor);
    }

    public Tile genTwoTiles(ArrayList<Integer> valOptions){
	Tile t1 = genOneTile(valOptions, null);
	// t1.getVal returns Integer Object
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

    public void rotatePiece(Tile t){
	if (t.getNeighbor() == null) return;
	t.setOrientation( (t.getOrientationX() - 1) % 2, (t.getOrientationY() - 1) % 2);
	Tile n = t.getNeighbor();
	n.setOrientation( (n.getOrientationY() - 1) % 2, (n.getOrientationY() - 1) % 2);
    }


    public void play(){
        System.out.println(_board);
    }

    public static void main(String [] args){
	Game a = new Game();
	Tile b = a.getNextPiece();
	System.out.println(b);
	System.out.println(b.getNeighbor());
    }
}
