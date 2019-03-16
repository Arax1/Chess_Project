package parts;

import java.util.ArrayList;

public class Pawn implements Piece {
	public int column, row;
	public char color;
	
	public boolean hasmoved = false, justjumped = false;
	
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
	public boolean threatens(int c, int r, Board board) {
		if(c < 0 || c > 7 || r < 0 || r > 7)
			return false;
		
		int direction = (color == 'w') ? 1 : -1;
		
		if(r == row + direction) {
			if(c == column + 1 || c == column - 1) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public boolean moveTo(int newc, int newr, Board b) {
		if(color == 'w') {
			
			if(newr > 7)
				return false;
			
			//check for trying to move 2 forward
			if(newr == row + 2 && newc == column && !hasmoved 
					&& !b.board[newc][newr].filled && !b.board[newc][newr].filled) {
				//if all of these conditions are met, we can do a double-move forward
				
				hasmoved = true;
				justjumped = true;
				row = newr;
				column = newc;
				return true;
			}
			
			//check for trying to move 1 forward
			if(true) {
				
			}
			
			//check for taking diagonally, not en passant
			if(true) {
				
			}
			
			//check for en passant
		}
		
		//now do the same thing but for black. 
		//note that pawns actually probably have the most complicated moving rules.
	
		return false;
	}

	/*public static void main(String[] args) {
		Pawn wp = new Pawn(4,1);
		Pawn bp = new Pawn(4,6);
		
		Square[][] b = new Square[1][1];
		
		
		System.out.println(wp.threatens(3,2,b));
		System.out.println(wp.threatens(4,0,b));
		System.out.println(wp.threatens(5,0,b));
		System.out.println(bp.threatens(3,5,b));
		System.out.println(bp.threatens(4,7,b));
		System.out.println(bp.threatens(5,7,b));
		
	}*/

	
}
