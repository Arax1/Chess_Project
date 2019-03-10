package parts;

import java.util.ArrayList;

public class Pawn implements Piece {
	public int row, column;
	public char color;
	
	public Pawn(Square s, char c) {
		row = s.row;
		column = s.column;
		color = c;
	}
	
	public String toString() {
		return color + "P";
	}

	@Override
	public boolean threatens(int r, int c, Square[][] board) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean moveTo(int r, int c, Square[][] board) {
		// TODO Auto-generated method stub
		return false;
	}

	

	
}
