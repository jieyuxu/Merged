public Class Game {
    
    private GameBoard _board;
    private int _score;
    
    public Game(){
        _board = new GameBoard();    
        _score = 0;
    }

    public Tile genOneTile(){
        int maxVal = Board.maxVal;
        int tileVal = (int) (Math.random() * maxVal + 1);
        return new Tile(tileVal, null);
    
    }

    public Tile getNextTile(){
        int howManyTiles = 1;
        if (Board.fitTwo())
            howManyTiles = (int) (Math.random() * 2 + 1);   
            
    
    }

    public void play(){
        System.out.println(_board);
    }

    public static void main(String [] args){
        Game game = new Game();
        game.play();
    }

}
