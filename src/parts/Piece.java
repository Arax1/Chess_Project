package parts;

import java.util.ArrayList;

/**
 * abstract class that all board pieces inherit from
 * 
 * @author Anand Raju
 * @author Sammy Berger
 *
 */
public abstract class Piece {

	protected int row;
	protected int column;

	protected char color;
	
	/**
	 * 
	 * @param c
	 * @param r
	 * @param board
	 * @return Returns true if this piece threatens a space with column c and row r on a board
	 */
	public boolean threatens(int c, int r, Board board) {
		return false;
	}
	
	/**
	 * 
	 * @param c
	 * @param r
	 * @param board
	 * @return Returs true if this piece can actually move to a space on the board
	 */
	public boolean moveTo(int c, int r, Board board) {

		int o_row = getRow();
		int o_col = getColumn();
		Pawn o_p = board.en_passant;

		if(!threatens(c,r,board))
			return false;

		if(c == getColumn() && r == getRow())
			return false;

		if(board.filled(c,r))
			if(board.colorAt(c,r) == getColor())
				return false;

		/*Piece king = (getColor() == 'w') ? board.white_king : board.black_king;

		board.movePiece(column, row, c, r);

		if(!board.threatened(king.getColumn(), king.getRow(), king.getColor())) {

			board.movePiece(c, r, o_col, o_row);
			board.en_passant = o_p;
			return true;
		}*/

		board.en_passant = null;
		return true;
	}
	
	/**
	 * 
	 * @param threat
	 * @param victim
	 * @param b
	 * @return Returns true if this piece can block Piece threat from capturing Piece victim
	 */
	public boolean canBlockPiece(Piece threat, Piece victim, Board b) {

		int o_row = getRow();
		int o_col = getColumn();
		Pawn o_p = b.en_passant;

		ArrayList<Square> threat_spots = threat.getAllMoves(b);

		for(Square s: threat_spots) {

			if(moveTo(s.column, s.row, b)) {

				b.movePiece(getColumn(), getRow(), s.column, s.row);

				if(!threat.threatens(victim.getColumn(), victim.getRow(), b)) {

					if(!b.threatened(victim.getColumn(), victim.getRow(), victim.getColor())) {

						b.movePiece(s.column, s.row, o_col, o_row);
						b.en_passant = o_p;
						return true;
					}


				}

				else {

					b.movePiece(s.column, s.row, o_col, o_row);
					b.en_passant = o_p;
				}
			}

		}


		return false;
	}
	
	/**
	 * 
	 * @return gets color of piece
	 */
	public char getColor() {
		return color;
	}

	/**
	 * 
	 * @return gets row of piece
	 */
	public int getRow() {
		return row;
	}
	
	
	/**
	 * 
	 * @return gets column of piece
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * 
	 * @return sets row of piece
	 */
	public void setRow(int r) {
		row = r;
	}

	/**
	 * 
	 * @return sets column of piece
	 */
	public void setColumn(int c) {

		column = c;
	}

	/**
	 * 
	 * @return Returns a list of all valid squares on a piece
	 */
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
