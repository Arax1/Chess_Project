package parts;

public class Queen implements Piece {

	public int column, row;
	public char color;
	
	public Queen(int c, int r) {
		column = c;
		row = r;
		
		color = (r == 7) ? 'b' : 'w';
	}
	
	@Override
	public boolean threatens(int c, int r, Board b) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean moveTo(int c, int r, Board b) {
		// TODO Auto-generated method stub
		return false;
	}

	public String toString() {
		return color + "Q";
	}
}
