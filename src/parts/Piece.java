package parts;


public interface Piece {

	public int column = 0, row = 0;
	public char color = 'w';
	
	public boolean threatens(int c, int r, Board board);
	
	public boolean moveTo(int c, int r, Board board);
	
	public char getColor();
	
	public int getRow();
	
	public int getColumn();
	
}