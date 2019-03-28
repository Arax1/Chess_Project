package parts;
/**
 * 
 * @author Anand Raju
 * @author Sammy Berger
 * 
 * Square class represents a square on the board, these squares each have a designated row and column and also 
 * can hold up to one piece
 */
public class Square {
	public boolean filled;
	public int column, row;
	public Piece p;
	
	/**
	 * 
	 * 
	 * @param c
	 * @param r
	 * 
	 * Square constructor for Column and Row
	 * 
	 */
	public Square(int c, int r) {
		row = r;
		column = c;
		filled = false;
	}
	
	/**
	 * 
	 * @param c
	 * @param r
	 * 
	 * Another Square constructor that has Piece
	 */
	public Square(int c, int r, Piece in) {
		row = r;
		column = c;
		p = in;
		filled = true;
	}
	
	/**
	 * 
	 * @param in
	 * 
	 * Places a piece on a square, then sets the piece's row and column to that of squares
	 */
	public void putPiece(Piece in) {
		if(in == null)
			return;
		
		
		p = in;
		p.setRow(row);
		p.setColumn(column);
		
		filled = true;
		
	}
	
	/**
	 * 
	 * @return Returns a piece after removing it from the square
	 */
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