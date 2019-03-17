package parts;

public class Queen implements Piece {

	public int column, row;
	public char color;
	
	public Queen(int c, int r) {
		column = c;
		row = r;
		
		color = (r == 7) ? 'b' : 'w';
	}
	
	
	// Reused code from Rook class
	private boolean threat_lateral(int c, int r, Board b) {
		
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
	
	
	// Reused code from Bishop class
	private boolean threat_diagonal(int c, int r, Board b) {
		
		if(c == column || r == row) {
			return false;
		}
		
		int c_mod = (c > column) ? 1 : -1;
		int r_mod = (r > row) ? 1 : -1;
		
		int rindex = row + r_mod;
		int cindex = column + c_mod;
		
		while((rindex > 0) && (rindex < 8) || (cindex > 0) && (cindex < 8)){
			
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
	// Uses threat_lateral and threat_diagonal
	public boolean threatens(int c, int r, Board b) {
		// TODO Auto-generated method stub
		return threat_lateral(c, r, b) || threat_diagonal(c, r, b);
	}

	@SuppressWarnings("static-access")
	@Override
	public boolean moveTo(int c, int r, Board b) {
		// TODO Auto-generated method stub
		if(!threatens(c,r,b))
			return false;
		
		if(b.board[c][r].filled)
			if(b.board[c][r].p.color == color)
				return false;
		
		column = c;
		row = r;
		return true;
	}

	public String toString() {
		return color + "Q";
	}
}
