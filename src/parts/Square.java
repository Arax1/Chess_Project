package parts;

public class Square {
	public boolean filled;
	public int column, row;
	public Piece p;
	
	public Square(int c, int r) {
		row = r;
		column = c;
		filled = false;
	}
	public Square(int c, int r, Piece in) {
		row = r;
		column = c;
		p = in;
		filled = true;
	}
	
	public void putPiece(Piece in) {
		
		p = in;
		p.setRow(row);
		p.setColumn(column);
		
		filled = true;
		
	}
	public Piece removePiece() {
		Piece pout = p;
		p = null;
		filled = false;
		return pout;
	}
	
	public String toString() {
		if(!filled) {
			if((row + column) % 2 == 0)
				return "##";
			else
				return "  ";
		} else {
			return p.toString();
		}
	}
	public String pos() {
		return "[" + column + "," + row + "]";
	}
}