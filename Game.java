import java.util.ArrayList;

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
       
    public void play(){
        System.out.println(_board);
    }

 
    public String printPiece(Tile t){
	if (t.getNeighbor() == null) return t + "";
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
	return ans;
    }

    
     public static void main(String [] args){
	 
	 Game test = new Game();
	 GameBoard testB = test.getGameBoard();	 
	 Tile t1 = test.genOneTile();
	 testB.placeOne(t1, 0, 0);
	 Tile t2 = test.genTwoTiles();
	 t2.rotate();
	 testB.placeTwo(t2, 3, 2); //t
	 System.out.println(testB);
	 

	 Tile t3 = test.genTwoTiles();	 
	 System.out.println(t3 + "" + t3.getNeighbor());
	 for (int i = 0; i < 4; i++){
	     System.out.println("orientation: "+t3.getOrientationR() + ", "+
				t3.getOrientationC());
	     System.out.println(test.printPiece(t3));
	     t3.rotate();
	 }
	 
	 
			    
	 
    }
}

