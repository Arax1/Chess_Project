package parts;

import java.util.ArrayList;

public class Rook implements Piece {

	private int column, row;
	private char color;
	
	public boolean hasmoved = false;
	
	public Rook(int c, int r) {
		column = c;
		row = r;
		
		color = (r == 7) ? 'b' : 'w';
	}
	public Rook(int c, int r, char co) {
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
	    
	    if (!(obj instanceof Rook)) 
	    	return false;
	    
	    Rook o = (Rook) obj;
	    return this.row == o.row && this.column == o.column;
	}
	
	@Override
	public boolean threatens(int c, int r, Board b) {
		if(c != column && r != row)
			return false;
		
		if(c == column && c == row)
			return false;
		
		if(c == column) {
			int direction = (r > row) ? 1 : -1;
			for(int x = row + direction; x != r; x += direction)
				if(b.board[c][x].filled)
					return false;
			
			return true;
		}
		
		if(r == row) {
			int direction = (c > column) ? 1 : -1;
			for(int x = column + direction; x != c; x+= direction)
				if(b.board[x][r].filled)
					return false;
			
			return true;
		}
		
		return false;
	}

	@Override
	public boolean moveTo(int c, int r, Board b) {
		if(!threatens(c,r,b))
			return false;
		
		if(b.board[c][r].filled)
			if(b.board[c][r].p.getColor() == color)
				return false;
		
		column = c;
		row = r;
		b.en_passant = null;
		hasmoved = true;
		return true;
	}

	public char getColor() {
		return color;
	}
	@Override
	public int getRow() {
		// TODO Auto-generated method stub
		return row;
	}
	@Override
	public int getColumn() {
		// TODO Auto-generated method stub
		return column;
	}
	
	public String toString() {
		return getColor() + "R";
	}


	@Override
	public ArrayList<Square> getAllMoves(Board board) {
		// TODO Auto-generated method stub
		
		ArrayList<Square> moves = new ArrayList<Square>();
		
		for(int col = 0; col < 8; col++) {
			
			for(int ro = 0; ro < 8; ro++){
				
				if(threatens(col, ro, board))
					moves.add(board.board[col][ro]);
					
			}
		}
			
		
		return moves;
	}
}
