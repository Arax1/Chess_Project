package parts;

import java.util.*;

public class King implements Piece {

	private int column, row;
	private char color;

	public boolean hasmoved;

	public King(int c, int r) {
		column = c;
		row = r;

		color = (r == 7) ? 'b' : 'w';
		hasmoved = false;
	}
	public King(int c, int r, char co) {
		column = c;
		row = r;

		color = co;
		hasmoved = false;
	}

	public boolean equals(Object obj) {

	    if (obj == null)
	    	return false;

	    if (obj == this)
	    	return true;

	    if (!(obj instanceof King))
	    	return false;

	    King o = (King) obj;
	    return this.row == o.row && this.column == o.column;
	}

	@Override
	public boolean threatens(int c, int r, Board b) {

		if(c == column && r == row)
			return false;

		if(b.onBoard(c,r))
			if(Math.abs(c-column) < 2 && Math.abs(r-row) < 2)
				return true;

		return false;

	}
	public boolean moveTo(int c, int r, Board b) {

		if(!b.onBoard(c,r))
			return false;
		
		if(c == column && r == row)
			return false;

		if(b.filled(c,r)) {
			if(b.board[c][r].p.getColor() == color)
				return false;
		}


		else {
			
			/* Things to check!
			 * 
			 * 1 - New space threatened? If so, false.
			 * 2 - New space in distance 1? If so, true.
			 * 
			 * 3 - [castle] Have you ever moved before? If so, false.
			 * 4 - [castle] New space potential castle (distance 2 horizontally)? If not, false.
			 * 5 - [castle] Currently in check? If so, false.
			 * 6 - [castle] Intermediate space threatened? If so, false.
			 * 7 - [castle] Rook in place? If not, false.
			 * 8 - [castle] Rook has moved? If so, false.
			 * 9 - [castle] Anything in the way? If so, false.
			 * Now, we know neither piece has moved, our current, middle, and future spaces are safe, and the path is clear.
			 */
			
			//1 - New space threatened? If so, false.
			if(b.threatened(c, r, color))
				return false;
			
			//2 - New space in distance 1? If so, true.
			if(threatens(c,r,b) && b.colorAt(c, r) != color) {
				hasmoved = true;
				b.en_passant = null;
				return true;
			}
			
			//3 - [castle] Have you ever moved before? If so, false.
			if(hasmoved)
				return false;
			
			//4 - [castle] New space potential castle (distance 2 horizontally)? If not, false.
			int dir = (c > column) ? 1 : -1;
			if(!(r == row && c == column + 2*dir))
				return false;
			
			//5 - [castle] Currently in check? If so, false.
			if(b.inCheck(color))
				return false;
			
			//6 - [castle] Intermediate space threatened? If so, false.
			if(b.threatened((c + column)/2, r, color))
				return false;
			
			//7 - [castle] Rook in place? If not, false.
			int rookcol = (dir == 1) ? 7 : 0;
			if(!b.filled(rookcol, r) || !(b.pieceAt(rookcol, r) instanceof Rook))
				return false;
			
			// 8 - [castle] Rook has moved? If so, false.
			if(((Rook)b.pieceAt(rookcol, r)).hasmoved)
				return false;
			
			// 9 - [castle] Anything in the way? If so, false.
			for(int tempc = column + dir; tempc != rookcol; tempc += dir)
				if(b.filled(tempc, r))
					return false;
			
			//Apparently, we can now castle
			Rook partner = (Rook)(b.getTileAt(rookcol, r).removePiece());
			b.getTileAt(column + dir, r).putPiece(partner);
			partner.hasmoved = true;

			hasmoved = true;
			b.en_passant = null;
			return true;
		}
		
		return false;
	}
	public boolean canBlockPiece(Piece threat, Piece victim, Board b) {

		int o_row = row;
		int o_col = column;
		Pawn o_p = b.en_passant;

		ArrayList<Square> threat_spots = threat.getAllMoves(b);

		for(Square s: threat_spots) {

			if(moveTo(s.column, s.row, b)) {

				b.movePiece(row, column, s.column, s.row);

				if(!threat.threatens(victim.getColumn(), victim.getRow(), b)) {
					return true;
				}

				else {

					row = o_row;
					column = o_col;
					b.en_passant = o_p;
				}
			}

		}

		return false;
	}

	public String toString() {
		return getColor() + "K";
	}

	@Override
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

	public char getColor() {
		return color;
	}
	public int getRow() {
		// TODO Auto-generated method stub
		return row;
	}
	public int getColumn() {
		return column;
	}
	@Override
	public void setRow(int r) {
		row = r;
	}
	@Override
	public void setColumn(int c) {
		column = c;
	}
}
