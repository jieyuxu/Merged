import java.util.ArrayList;

public class Game {
    
    private GameBoard _board;
    private int _score;
    
    public Game(){
	//   _board = new GameBoard();    
        _score = 0;
    }

    public Tile genOneTile(ArrayList<Integer> valOptions, Tile neighbor){
        int choice = (int) (Math.random() * valOptions.size());
	System.out.println(choice + " first");			   
	int tileVal = valOptions.get(choice);
	System.out.println(tileVal);
        return new Tile(tileVal, neighbor);
    }

    public Tile getTwoTiles(ArrayList<Integer> valOptions){
	Tile t1 = genOneTile(valOptions, null);
	// t1.getVal returns Integer Object
	// uses remove(Object o)
	valOptions.remove(t1.getVal());
	Tile t2 = genOneTile(valOptions, t1);
	return t1;
    }
    
    /*   public Tile getNextTile(){
        int howManyTiles = 1;
        if (Board.fitTwo())
            howManyTiles = (int) (Math.random() * 2 + 1);   
            
    
    }
    */
    public void play(){
        System.out.println(_board);
    }

    public static void main(String [] args){
        Game game = new Game();
	//  game.play();
	ArrayList<Integer> a = new ArrayList<Integer>();
	for (int i = 1; i < 8; i++)
	    a.add(i);
	Tile y = game.getTwoTiles(a);
	System.out.println(a);
	System.out.println(y.getNeighbor());
    }
