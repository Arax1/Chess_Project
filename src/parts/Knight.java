package parts;

public class Knight implements Piece {
	
	private int column, row;
	private char color;
	
	public Knight(int c, int r) {
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
	    
	    if (!(obj instanceof Knight)) 
	    	return false;
	    
	    Knight o = (Knight) obj;
	    return this.row == o.row && this.column == o.column;
	}
	
	@Override
	public boolean threatens(int c, int r, Board b) {
		// TODO Auto-generated method stub
		
		if(c == column || r == row) {
			return false;
		}
		
		
		int r_mod_1 = (r > row) ? 2 : -2;
		int r_mod_2 = (r_mod_1 > 0) ? -1 : 1;
		
		int c_mod_1 = (c > column) ? 1 : -1;
		int c_mod_2 = c_mod_1;
		
		
		int rindex_1 = row + r_mod_1;
		int cindex_1 = column + c_mod_1;
		
		int rindex_2 = rindex_1 + r_mod_2;
		int cindex_2 = cindex_1 + c_mod_2;
		
		if((rindex_1 >= 0 && rindex_1 < 8) && (cindex_1 >= 0 && cindex_1 < 8)){
			
			if(r == rindex_1 && c == cindex_1)
				return true;
		}
		
		else if(rindex_2 >= 0 && rindex_2 < 8 && cindex_2 >= 0 && cindex_2 < 8){
			
			if(r == rindex_2 && c == cindex_2)
				return true;
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
		
		if(b.board[c][r].filled)
			if(b.board[c][r].p.getColor() == color)
				return false;
		
		column = c;
		row = r;
		b.en_passant = null;
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
		return getColor() + "N";
	}

	@Override
	public Square[] getAllMoves() {
		// TODO Auto-generated method stub
		return null;
	}
}
