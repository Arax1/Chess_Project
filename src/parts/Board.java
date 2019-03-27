package parts;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

public class Board {

	public Square[][] board;
	public ArrayList<Piece> black_pieces;
	public ArrayList<Piece> white_pieces;

	public Pawn en_passant = null;
	public King black_king;
	public King white_king;

	public Board() {

		board = new Square[8][8];
		black_pieces = new ArrayList<Piece>();
		white_pieces = new ArrayList<Piece>();

		for(int column = 0; column < 8; column++)
			for(int row = 0; row < 8; row++) {
				board[column][row] = new Square(column,row);

				if(row < 2 || row > 5) {
					// there should be pieces in this square
					// [0,0] is bottom left, [7,7] is top right

					if(row == 1 || row == 6) {
						//we have a pawn!
						Pawn p = new Pawn(column, row);
						addPiecePlay(p.getColumn(), p.getRow(), p);

					} else {
						//we have some other piece
						switch(column) {

						case 0: case 7:
							//rook
							Rook r = new Rook(column, row);
							addPiecePlay(r.getColumn(), r.getRow(), r);
							break;

						case 1: case 6:
							//knight
							Knight n = new Knight(column, row);
							addPiecePlay(n.getColumn(), n.getRow(), n);
							break;

						case 2: case 5:
							//bishop
							Bishop b = new Bishop(column, row);
							addPiecePlay(b.getColumn(), b.getRow(), b);
							break;

						case 3:
							//YASSS QUEEN
							Queen q = new Queen(column, row);
							addPiecePlay(q.getColumn(), q.getRow(), q);
							break;

						case 4:
							//THE KING
							King k = new King(column, row);
							addPiecePlay(k.getColumn(), k.getRow(), k);
							break;
						}
					}
				}
			}
	}

	
	//makes a blank board for testing purposes
	public static Board blankBoard() {
		Board b = new Board();
		b.board = new Square[8][8];
		b.black_pieces = new ArrayList<Piece>();
		b.white_pieces = new ArrayList<Piece>();
		b.black_king = null;
		b.white_king = null;
		
		for(int c = 0; c < 8; c++)
			for(int r = 0; r < 8; r++)
				b.board[c][r] = new Square(c,r);
		
		return b;
	}
	public static String brak(int a, int b) {
		return "[" + a + "," + b + "]";
	}
	
	//adds a new piece to the board and all that that entails.
	public void addPiecePlay(int c, int r, Piece p) {

		board[c][r].putPiece(p);

		if(p.getColor() == 'w') {

			if(p instanceof King)
				white_king = (King)p;

			white_pieces.add(p);

		}

		else if(p.getColor() == 'b') {

			if(p instanceof King)
				black_king = (King)p;

			black_pieces.add(p);

		}

	}

	//get the specified tile
	public Square getTileAt(int c, int r) {

		return board[c][r];

	}
	public Square getTileAt(Square s) {
		return getTileAt(s.column, s.row);
	}
	public Square getTileAt(String s) {

		int row = s.charAt(0) - 'a';
		int col = Character.getNumericValue(s.charAt(1)) - 1;

		if(onBoard(row,col))
			return board[row][col];
		
		/*maybe we should just throw an error?*/
		int boundrow = 0, boundcol = 0;
		if(row > 7)
			boundrow = 7;
		if(col > 7)
			boundcol = 7;
		
		return board[boundrow][boundcol];

	}

	//move a piece from one spot to another
	public void movePiece(Square oldspot, Square newspot, String promote) {

		Piece piece = oldspot.removePiece();

		if(newspot.filled) {

			Piece captured = newspot.removePiece();

			if(captured.getColor() == 'w')
				white_pieces.remove(captured);

			else
				black_pieces.remove(captured);
		}
		
		int prom = (piece.getColor() == 'w') ? 7 : 0;
		
		if(piece instanceof Pawn && newspot.row == prom) {
			
			Promotion(promote, newspot.column, newspot.row);
		}
		
		else {
			
			newspot.putPiece(piece);
		}
			
	}
	public void movePiece(int oc, int or, int nc, int nr) {
		Piece piece = board[oc][or].removePiece();
		//System.out.println("Moving Piece: " + piece + " to: " + board[nc][nr]);
		
		if(piece != null) {
			
			if(board[nc][nr].filled) {
	
				Piece captured = board[nc][nr].removePiece();
	
				if(captured.getColor() == 'w')
					white_pieces.remove(captured);
	
				else
					black_pieces.remove(captured);
			}
	
			board[nc][nr].putPiece(piece);
		
		}
	}
	
	public void Promotion(String promote, int column, int row) {
		
		switch(promote.toLowerCase()) {

			case "rook":
				//rook
				Rook rook = new Rook(column, row);
				addPiecePlay(rook.getColumn(), rook.getRow(), rook);
				break;
	
			case "knight":
				//knight
				Knight n = new Knight(column, row);
				addPiecePlay(n.getColumn(), n.getRow(), n);
				break;
	
			case "bishop":
				//bishop
				Bishop b = new Bishop(column, row);
				addPiecePlay(b.getColumn(), b.getRow(), b);
				break;
	
			default:
				//YASSS QUEEN
				Queen q = new Queen(column, row);
				addPiecePlay(q.getColumn(), q.getRow(), q);
				break;
	
		}
		
	}
	
	// where you determine if there's checkmate or not;
	public boolean resolve_check(List<Piece> checks, King k) {

			ArrayList<Square> king_spots = k.getAllMoves(this);

			if(king_spots.size() > 0)
				return false;

			//with double check, the only possible way to escape is for the king move to a safe spot.
			if(checks.size() < 2) {
				
				List<Piece> ally = new ArrayList<Piece>();
				List<Piece> enemy = new ArrayList<Piece>();
				
				if(k.getColor() == 'w') {
					
					ally = white_pieces;
					ally.remove(white_king);
					enemy = black_pieces;
					enemy.remove(black_king);
				}
				
				else {
					
					ally = black_pieces;
					ally.remove(black_king);
					enemy = white_pieces;
					enemy.remove(white_king);
				}
				
				Piece threat = checks.get(0);
				List<Piece> eliminate_check = threatens_spot(ally, checks.get(0).getColumn(), checks.get(0).getRow());
				List<Piece> blocks_check = filter(ally, p -> p.canBlockPiece(threat, (Piece)k, this));

				if(!eliminate_check.isEmpty() || !blocks_check.isEmpty())
					return false;
			}

			return true;
		}

	
	//methods to find what pieces threaten a certain spot
	public static <T> List<T> filter(List<T> list, Predicate<T> p){

		List<T> result = new ArrayList<T>();

		for(T t: list) {

			if(p.test(t))
				result.add(t);
		}

		return result;
	}
	public List<Piece> threatens_spot(List<Piece> list, int c, int r) {
		// TODO Auto-generated method stub
		return filter(list, p -> p.threatens(c, r, this));
	}
	
	
	/** Minor methods that can be reused often to save lines of code **/
	
	//checks if a color is in check, or if a general piece of a color in a square would be threatened
	public boolean inCheck(char c) {
		King k = (c == 'w') ? white_king : black_king;
		
		return threatened(k.getColumn(), k.getRow(), c);
	}
	
	public boolean threatened(int c, int r, char color) {

		if(color == 'w') {
			for(Piece p: black_pieces)
				if(p.threatens(c, r, this))
					return true;
		}

		if(color == 'b') {
			for(Piece p: white_pieces)
				if(p.threatens(c, r, this))
					return true;
		}
		
		return false;
	}
	
	//to check if there's a piece at a given spot on the board
	public boolean filled(int c, int r) {
		return board[c][r].filled;
	}
	public boolean filled(Square s) {
		return board[s.column][s.row].filled;
	}

	//checks if a given row/column is on the board
	public boolean onBoard(int c, int r) {
		return (c >= 0) && (c < 8) && (r >= 0) && (r < 8);
	}
	public boolean onBoard(Square s) {
		return onBoard(s.column, s.row);
	}

	//checks the color of a given square
	public char colorAt(int c, int r) {
		if(!filled(c,r))
			return 'n';
		return board[c][r].p.getColor();
	}
	
	//gets you the piece at a location
	public Piece pieceAt(int c, int r) {
		return board[c][r].p;
	}
	
	//returns the string of the board
	public String toString() {
		String ret = "";

		for(int r = 7; r >= 0; r--) {
			for(int c = 0; c < 8; c++) {
				ret += board[c][r] + " ";
			}
			ret += (r+1) + "\n";
		}
		
		for(char c = 'a'; c <= 'h'; c++) {
			ret += " " + c;
			
			if(c != 'h')
				ret += " ";
		}
		
		return ret;
	}
	public void printBoard() {
		System.out.println(toString() + "\n");
	}

	public static void main(String[] args) {
		Board b = blankBoard();
		b.addPiecePlay(1, 7, new King(1, 7, 'b'));
		b.addPiecePlay(0, 0, new Queen(0, 0, 'w'));
		
		b.printBoard();
		if(b.pieceAt(1, 7).moveTo(0, 7, b))
			b.movePiece(1, 7, 0, 7);
		b.printBoard();
	}
}
