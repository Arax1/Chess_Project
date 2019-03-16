package parts;


public interface Piece {

	public int column = 0, row = 0;
	public char color = 'w'; //w for white, b for black
	
	public boolean threatens(int c, int r, Board board);
	
	public boolean moveTo(int c, int r, Board board);
	
}
