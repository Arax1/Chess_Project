package parts;

public class Rook implements Piece {

	public int column, row;
	public char color;
	
	public Rook(int c, int r) {
		column = c;
		row = r;
		
		color = (r == 7) ? 'b' : 'w';
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

	@SuppressWarnings("static-access")
	@Override
	public boolean moveTo(int c, int r, Board b) {
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
		return color + "R";
	}
}
