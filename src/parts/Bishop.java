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
				if(b.board[cindex][rindex].filled)
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
		
		if(b.board[c][r].filled) {
			char pcolor = b.board[c][r].p.getColor();
			System.out.println("Piece: " + b.board[c][r].p + " Color: " + pcolor );
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
	public ArrayList<Square> getAllMoves(Board b) {
		// TODO Auto-generated method stub
		return null;
	}
}
