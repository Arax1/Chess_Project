package parts;

public class Knight implements Piece {
	
	public int column, row;
	public char color;
	
	public Knight(int c, int r) {
		column = c;
		row = r;
		
		color = (r == 7) ? 'b' : 'w';
	}
	
	@Override
	public boolean threatens(int c, int r, Square[][] board) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean moveTo(int c, int r, Square[][] board) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public String toString() {
		return color + "N";
	}
}
