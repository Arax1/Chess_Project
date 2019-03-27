package parts;

public class Bishop extends Piece {

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

	public String toString() {
		return getColor() + "B";
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
