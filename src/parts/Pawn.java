package parts;

public class Pawn implements Piece {
	public int row, column;
	public char color;
	
	public String toString() {
		return color + "P";
	}

	@Override
	public boolean threatens(int r, int c, Square[][] board) {
		// TODO Auto-generated method stub
		return false;
	}
}
