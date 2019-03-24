package parts;

public class King implements Piece {

	public int column, row;
	private char color;
	
	public King(int c, int r) {
		column = c;
		row = r;
		
		color = (r == 7) ? 'b' : 'w';
	}
	
	@Override
	public boolean threatens(int c, int r, Board b) {
		// TODO Auto-generated method stub
		
		if(r >= 0 && r < 8 && c >= 0 && c < 8) {
			
			if(r <= (row + 1) && r >= (row - 1) && c <= (column + 1) && c >= (column - 1)) {
				return true;
			}
			
		}
		
		return false;
		
		
	}

	@Override
	public boolean moveTo(int c, int r, Board b) {
		// TODO Auto-generated method stub
		if(!threatens(c,r,b))
			return false;
		
		if(b.board[c][r].filled)
			if(b.board[c][r].p.getColor() == color)
				return false;
		
		/* logic for checking if other pieces threaten king goes here as function.
		 * 
		 * if(is_threatened()) 
		 * 		return false;
		 * 
		 * */
		
		column = c;
		row = r;
		b.en_passant = false;
		return true;
	}

	public char getColor() {
		return color;
	}
	
	public String toString() {
		return getColor() + "B";
	}
}
