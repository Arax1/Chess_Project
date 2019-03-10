package parts;

import java.util.ArrayList;

public class Pawn implements Piece {
	public int column, row;
	public char color;
	
	//given a row, column and color
	public Pawn(int c, int r, char co) {
		row = r;
		column = c;
		color = co;
	}
	
	//assume basic setup, determine color yourself
	public Pawn(int c, int r) {
		row = r;
		column = c;
		
		color = (r == 6) ? 'b' : 'w';
	}
	
	//if you have a square already, use it to judge your position
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
