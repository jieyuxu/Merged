public class Rotate {

    private static final int[][] ROT_MATRIX_270 = { {0, -1}, {1, 0} };
    
    public static void rotate(Tile t){
        int newOrientationR = t.getOrientationR() * ROT_MATRIX_270[0][0] + 
	    t.getOrientationC() * ROT_MATRIX_270[1][0] ;
	int newOrientationC =  t.getOrientationR() * ROT_MATRIX_270[0][1] +
	    t.getOrientationC() * ROT_MATRIX_270[1][1];
	t.setOrientation(newOrientationR, newOrientationC);
    }

    public static void main(String [] args){
	Tile t = new Tile(4, null);
	System.out.println(t.getOrientationR() + " " + t.getOrientationC());
	rotate(t);
	System.out.println(t.getOrientationR() + " " + t.getOrientationC());
	rotate(t);
	System.out.println(t.getOrientationR() + " " + t.getOrientationC());
	rotate(t);
	System.out.println(t.getOrientationR() + " " + t.getOrientationC());
	rotate(t);
	System.out.println(t.getOrientationR() + " " + t.getOrientationC());
    }


}
