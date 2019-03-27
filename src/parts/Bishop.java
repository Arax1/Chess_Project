package parts;

import java.util.ArrayList;

public class Bishop implements Piece {

	private int column, row;
	private char color;

	public Bishop(int c, int r) {
		column = c;
		row = r;

		color = (r == 7) ? 'b' : 'w';
	}
	public Bishop(int c, int r, char co) {
		column = c;
		row = r;

		color = co;
	}

	@Override
	public boolean equals(Object obj) {

	    if (obj == null)
	    	return false;

	    if (obj == this)
	    	return true;

	    if (!(obj instanceof Bishop))
	    	return false;

	    Bishop o = (Bishop) obj;
	    return this.row == o.row && this.column == o.column;
	}

	@Override
	public boolean threatens(int c, int r, Board b) {
		// TODO Auto-generated method stub

		if(c == column || r == row) {
			return false;
		}

		int c_mod = (c > column) ? 1 : -1;
		int r_mod = (r > row) ? 1 : -1;

		int rindex = row + r_mod;
		int cindex = column + c_mod;

		while((rindex > 0) && (rindex < 7) || (cindex > 0) && (cindex < 7)){

			if(rindex == r && cindex == c)
				return true;

			else if(rindex == r && cindex != c)
				return false;

			else if(rindex != r && cindex == c)
				return false;

			else {
				if(b.board[cindex][rindex].filled)
					return false;

				rindex += r_mod;
				cindex += c_mod;
			}
		}

		return false;

	}

	@Override
	public boolean moveTo(int c, int r, Board b) {
		// TODO Auto-generated method stub

		//System.out.println("Current Row and Collumn: " + row + " " + column);
		//System.out.println("Destination Row and Collumn: " + r + " " + c);

		if(!threatens(c,r,b))
			return false;

		if(b.board[c][r].filled) {
			char pcolor = b.board[c][r].p.getColor();
			// System.out.println("Piece: " + b.board[c][r].p + " Color: " + pcolor );
			if(pcolor == color)
				return false;
		}

		b.en_passant = null;
		return true;
	}

	public boolean canBlockPiece(Piece threat, Piece victim, Board b) {

		int o_row = row;
		int o_col = column;
		Pawn o_p = b.en_passant;

		ArrayList<Square> threat_spots = threat.getAllMoves(b);

		for(Square s: threat_spots) {

			if(moveTo(s.column, s.row, b)) {

				b.movePiece(column, row, s.column, s.row);

				if(!threat.threatens(victim.getColumn(), victim.getRow(), b)) {
					return true;
				}

				else {

					b.movePiece(s.column, s.row, o_col, o_row);
					b.en_passant = o_p;
				}
			}

		}

		return false;
	}

	public String toString() {
		return getColor() + "B";
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
