package parts;

import java.util.ArrayList;

public abstract class Piece {
	
	protected int row;
	protected int column;
	
	protected char color;
	
	public boolean threatens(int c, int r, Board board) {
		return false;
	}
	public boolean moveTo(int c, int r, Board board) {
		return false;
	}
	public boolean canBlockPiece(Piece threat, Piece victim, Board b) {
		return false;
	}
	
	public char getColor() {
		return color;
	}
	
	public int getRow() {
		return row;
	}
	public int getColumn() {
		return column;
	}
	
	public void setRow(int r) {
		row = r;
	}
	
	public void setColumn(int c) {
		
		column = c;
	}
	
	public ArrayList<Square> getAllMoves(Board b) {
		// TODO Auto-generated method stub
		ArrayList<Square> moves = new ArrayList<Square>();
		Pawn o_pas = b.en_passant;

		for(int col = 0; col < 8; col++) {

			for(int ro = 0; ro < 8; ro++) {

				if(moveTo(col, ro, b)) {
					b.en_passant = o_pas;
					moves.add(b.board[col][ro]);
				}
			}
		}

		return moves;
	}
	
}