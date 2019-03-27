package parts;

import java.util.ArrayList;

public interface Piece {
	
	public boolean threatens(int c, int r, Board board);
	public boolean moveTo(int c, int r, Board board);
	public boolean canBlockPiece(Piece threat, Piece victim, Board b);
	
	public char getColor();
	
	public int getRow();
	public int getColumn();
	
	public void setRow(int row);
	public void setColumn(int col);
	
	public ArrayList<Square> getAllMoves(Board b);
	
}