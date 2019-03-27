package parts;

import java.util.ArrayList;

public class Bishop implements Piece {

	private int column, row;
	private char color;
	
	public Bishop(int c, int r) {
		column = c;
		row = r;
		
		color = (r == 7) ? 'b' : 'w';
	}
	public Bishop(int c, int r, char co) {
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
	    
	    if (!(obj instanceof Bishop)) 
	    	return false;
	    
	    Bishop o = (Bishop) obj;
	    return this.row == o.row && this.column == o.column;
	}
	
	@Override
	public boolean threatens(int c, int r, Board b) {
		// TODO Auto-generated method stub
		
		if(c == column || r == row) {
			return false;
		}
		
		int c_mod = (c > column) ? 1 : -1;
		int r_mod = (r > row) ? 1 : -1;
		
		int rindex = row + r_mod;
		int cindex = column + c_mod;
		
		while((rindex > 0) && (rindex < 7) || (cindex > 0) && (cindex < 7)){
			
			if(rindex == r && cindex == c)
				return true;
			
			else if(rindex == r && cindex != c)
				return false;
			
			else if(rindex != r && cindex == c)
				return false;
			
			else {
				if(b.filled(cindex,rindex))
					return false;
				
				rindex += r_mod;
				cindex += c_mod;
			}
		}
		
		return false;
		
	}

	@Override
	public boolean moveTo(int c, int r, Board b) {
		// TODO Auto-generated method stub
		
		//System.out.println("Current Row and Collumn: " + row + " " + column);
		//System.out.println("Destination Row and Collumn: " + r + " " + c);
		
		if(!threatens(c,r,b))
			return false;
		
		if(b.filled(c,r)) {
			char pcolor = b.board[c][r].p.getColor();
			//System.out.println("Piece: " + b.board[c][r].p + " Color: " + pcolor );
			if(b.board[c][r].p.getColor() == color)
				return false;
		}
			
		
		column = c;
		row = r;
		
		return true;
	}

	public char getColor() {
		return color;
	}
	public int getRow() {
		// TODO Auto-generated method stub
		return row;
	}
	public int getColumn() {
		// TODO Auto-generated method stub
		return column;
	}
	
	public String toString() {
		return getColor() + "B";
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
