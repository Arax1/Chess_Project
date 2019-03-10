package parts;


public interface Piece {

	public int column = 0, row = 0;
	public char color = 'w'; //w for white, b for black
	
	public boolean threatens(int r, int c, Square[][] board);
	
	public boolean moveTo(int r, int c, Square[][] board);
	
}
