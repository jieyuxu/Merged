import java.util.ArrayList;

public class Game {
    
    private GameBoard _board;
    private int _score;
    
    public Game(){
	//   _board = new GameBoard();    
        _score = 0;
    }

    public Tile genOneTile(ArrayList<Integer> options){
        int choice = (int) (Math.random() * options.size());
	int tileVal = options.get(choice);
        return new Tile(tileVal, null);
    }

    public Tile getTwoTiles(ArrayList<Integer> options){
	Tile t1 = genOneTile(options);
	// t1.getVal returns Integer Object
	// uses remove(Object o)
	options.remove(t1.getVal());
	Tile t2 = genOneTile(options);
	return t2;
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
	for (int i = 1; i < 10; i++)
	    a.add(i);
	game.getTwoTiles(a);
    }

}
