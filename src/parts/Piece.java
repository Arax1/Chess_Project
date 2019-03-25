package parts;


public interface Piece {
	
	public boolean threatens(int c, int r, Board board);
	
	public boolean moveTo(int c, int r, Board board);
	
	public char getColor();
	
	public int getRow();
	
	public int getColumn();
	
	public Square[] getAllMoves();
	
}