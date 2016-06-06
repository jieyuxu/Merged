  public static void main(String [] args){
	 
	 Game test = new Game();
	 test.play();
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
