package parts;

import java.util.ArrayList;

public class Rook extends Piece {

	private int column, row;
	private char color;

	public boolean hasmoved;

	public Rook(int c, int r) {
		column = c;
		row = r;

		color = (r == 7) ? 'b' : 'w';
		hasmoved = false;
	}
	public Rook(int c, int r, char co) {
		column = c;
		row = r;

		color = co;
		hasmoved = false;
	}

	@Override
	public boolean equals(Object obj) {

	    if (obj == null)
	    	return false;

	    if (obj == this)
	    	return true;

	    if (!(obj instanceof Rook))
	    	return false;

	    Rook o = (Rook) obj;
	    return this.row == o.row && this.column == o.column;
	}

	@Override
	public boolean threatens(int c, int r, Board b) {
		if(c != column && r != row)
			return false;

		if(c == column && c == row)
			return false;

		if(c == column) {
			int direction = (r > row) ? 1 : -1;
			for(int x = row + direction; x != r; x += direction)
				if(b.filled(c,x))
					return false;

			return true;
		}

		if(r == row) {
			int direction = (c > column) ? 1 : -1;
			for(int x = column + direction; x != c; x+= direction)
				if(b.filled(x,r))
					return false;

			return true;
		}

		return false;
	}
	@Override
	public boolean moveTo(int c, int r, Board b) {
		boolean canmove = super.moveTo(c, r, b);
		
		if(!hasmoved && canmove)
			hasmoved = true;
			
		return canmove;
	}
	
	public boolean canBlockPiece(Piece threat, Piece victim, Board b) {

		int o_row = row;
		int o_col = column;
		boolean old_moved = hasmoved;
		Pawn o_p = b.en_passant;

		ArrayList<Square> threat_spots = threat.getAllMoves(b);

		for(Square s: threat_spots) {

			if(moveTo(s.column, s.row, b)) {

				b.movePiece(column, row, s.column, s.row);

				if(!threat.threatens(victim.getColumn(), victim.getRow(), b)) {
					b.movePiece(s.column, s.row, o_col, o_row);
					b.en_passant = o_p;
					hasmoved = old_moved;
					return true;
				}

				else {
					
					b.movePiece(s.column, s.row, o_col, o_row);
					b.en_passant = o_p;
					hasmoved = old_moved;
				}
			}

		}
		

		return false;
	}

	public String toString() {
		return getColor() + "R";
	}

	public char getColor() {
		return color;
	}
	@Override
	public int getRow() {
		// TODO Auto-generated method stub
		return row;
	}
	@Override
	public int getColumn() {
		// TODO Auto-generated method stub
		return column;
	}
	@Override
	public void setRow(int r) {
		// TODO Auto-generated method stub
		row = r;
	}
	@Override
	public void setColumn(int c) {
		// TODO Auto-generated method stub
		column = c;
	}
}
