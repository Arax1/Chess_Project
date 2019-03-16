package parts;

public class Bishop implements Piece {

	public int column, row;
	public char color;
	
	public Bishop(int c, int r) {
		column = c;
		row = r;
		
		color = (r == 7) ? 'b' : 'w';
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
		return color + "B";
	}
}
