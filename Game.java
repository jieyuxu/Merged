public Class Game {
    
    Board _board;
    int _score;
    
    public Game(){
        _board = new Board();    
        _score = 0;
    }

    public Tile genNextTile(){
        int maxVal = Board.maxVal;
        int tileVal = (int) (Math.random() * maxVal + 1);
        return new Tile(tileVal);
    
    }

    public void play(){
        
    }

    public static void main(String [] args){
        Game game = new Game();
        game.play();
    }

}
