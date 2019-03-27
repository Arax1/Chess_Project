package parts;

import java.util.*;

public class King implements Piece {

	private int column, row;
	private char color;

	public boolean hasmoved;
	
	public King(int c, int r) {
		column = c;
		row = r;
		
		color = (r == 7) ? 'b' : 'w';
		hasmoved = false;
	}
	public King(int c, int r, char co) {
		column = c;
		row = r;
		
		color = co;
		hasmoved = false;
	}
	
	public boolean equals(Object obj) {
		
	    if (obj == null) 
	    	return false;
	    
	    if (obj == this) 
	    	return true;
	    
	    if (!(obj instanceof King)) 
	    	return false;
	    
	    King o = (King) obj;
	    return this.row == o.row && this.column == o.column;
	}
	
	@Override
	public boolean threatens(int c, int r, Board b) {
		
		if(c == column && r == row)
			return false;
		
		if(b.onBoard(c,r))
			if(Math.abs(c-column) < 2 && Math.abs(r-row) < 2)
				return true;
		
		return false;
		
	}
	public boolean moveTo(int c, int r, Board b) {
		
		if(!threatens(c,r,b))
			return false;
		
		if(b.board[c][r].filled) {
			
			if(b.board[c][r].p.getColor() == color)
				return false;
		}
			
			
		else {
			
			List<Piece> list = (this.getColor() == 'w') ? b.black_pieces : b.white_pieces;
			List<Piece> threatens = b.threatens_spot(list, c, r);
			
			if(!(threatens.isEmpty())) {
				return false;
			}
			
		}
		
		hasmoved = true;
		b.en_passant = null;
		return true;
	}
	public boolean canBlockPiece(Piece threat, Piece victim, Board b) {
		
		int o_row = row;
		int o_col = column;
		Piece o_p = b.en_passant;
		
		ArrayList<Square> threat_spots = threat.getAllMoves(b);
		
		for(Square s: threat_spots) {
			
			if(moveTo(s.column, s.row, b)) {
				
				b.movePiece(row, column, s.column, s.row);
				
				if(!threat.threatens(victim.getColumn(), victim.getRow(), b)) {
					return true;
				}
					
				else {
					
					row = o_row;
					column = o_col;
					b.en_passant = o_p;
				}
			}
				
		}
		
		return false;
	}
	
	public String toString() {
		return getColor() + "K";
	}

	@Override
	public ArrayList<Square> getAllMoves(Board b) {
		// TODO Auto-generated method stub
		ArrayList<Square> moves = new ArrayList<Square>();
		Piece o_pas = b.en_passant;
		
		for(int col = 0; col < 8; col++) {
			
			for(int ro = 0; ro < 8; ro++) {
				
				if(moveTo(col, ro, b)) {
					b.en_passant = o_pas;
					moves.add(b.board[col][ro]);
				}		
			}
		}
		
		return moves;
	}

	public char getColor() {
		return color;
	}
	public int getRow() {
		// TODO Auto-generated method stub
		return row;
	}
	public int getColumn() {
		return column;
	}
	@Override
	public void setRow(int r) {
		row = r;
	}
	@Override
	public void setColumn(int c) {
		column = c;
	}
}