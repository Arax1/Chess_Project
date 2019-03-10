package parts;

public class Square {
	public boolean filled;
	public int row, column;
	public Piece p;
	
	public Square(int r, int c) {
		row = r;
		column = c;
		filled = false;
	}
	
	public Square(int r, int c, Piece in) {
		row = r;
		column = c;
		p = in;
		filled = true;
	}
	
	public void putPiece(Piece in) {
		p = in;
		filled = true;
	}
	
	public String toString() {
		if(!filled) {
			if(row + column % 2 == 0)
				return "##";
			else
				return "  ";
		} else {
			return p.toString();
		}
	}
}
