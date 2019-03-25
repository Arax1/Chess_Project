package parts;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

public class Board {
	
	public Square[][] board;
	public ArrayList<Piece> black_pieces;
	public ArrayList<Piece> white_pieces;
	
	public Piece en_passant = null;
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
	
	public void printBoard() {
		
		for(int row = board[0].length; row > 0; row--) {
			
			for(int col = 0; col < board.length; col++)
				System.out.print(board[col][row-1] + " ");
			
			System.out.println(row);
		}
		
		for(int col = 0; col < board.length; col++) {
			if(col != 0)
				System.out.print(" ");
			
			System.out.print(" " + (char)('a'+col));
			
		}
		
		System.out.println("\n");
	}
	
	public void addPiecePlay(int c, int r, Piece p) {
		
		board[c][r].putPiece(p);
		
		if(p.getColor() == 'w') {
			
			if(p instanceof King)
				white_king = (King)p;
			
			else
				white_pieces.add(p);
			
		}
			
		else if(p.getColor() == 'b') {
			
			if(p instanceof King)
				black_king = (King)p;
			
			else
				black_pieces.add(p);
			
		}
		
	}
	
	public Square getTileAt(int c, int r) {
		
		return board[c][r];
		
	}
	
	public Square getTileAt(Square s) {
		return getTileAt(s.column, s.row);
	}
	
	public Square getTileAt(String s) {
		
		int row = s.charAt(0) - 'a' + 1;
		int col = Character.getNumericValue(s.charAt(1));
		
		return board[row - 1][col - 1];
		
	}
	
	public void movePiece(Square oldspot, Square newspot) {
		
		Piece piece = oldspot.removePiece();
		
		if(newspot.filled) {
			
			Piece captured = newspot.removePiece();
			
			if(captured.getColor() == 'w')
				white_pieces.remove(captured);
			
			else
				black_pieces.remove(captured);
		}
		
		newspot.putPiece(piece);
		
	}
	
	public void movePiece(int oc, int or, int nc, int nr) {
		Piece piece = board[oc][or].removePiece();
		
		if(board[nc][nr].filled) {
			
			Piece captured = board[nc][nr].removePiece();
			
			if(captured.getColor() == 'w')
				white_pieces.remove(captured);
			
			else
				black_pieces.remove(captured);
		}
		
		board[nc][nr].putPiece(piece);
	}
	
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
	
	public boolean onBoard(int c, int r) {
		if(c < 0 || c > 7 || r < 0 || r > 7)
			return false;
		return true;
	}
	
	//checks if a color is in check (more precisely, if that king is being threatened
	public boolean inCheck(char c) {
		if(c == 'w') {
			for(Piece p: black_pieces)
				if(p.threatens(white_king.getColumn(), white_king.getRow(), this))
					return true;
		}
		
		else if(c == 'b') {
			for(Piece p: white_pieces)
				if(p.threatens(black_king.getColumn(), black_king.getRow(), this))
					return true;
		}
		
		return false;
	}

	//same thing as printBoard, just returning the string instead of printing
	public String toString() {
		String ret = "";
		
		for(int row = board[0].length; row > 0; row--) {
			
			for(int col = 0; col < board.length; col++)
				ret += board[col][row-1] + " ";
			
			ret += row + "\n";
		}
		
		for(int col = 0; col < board.length; col++) {
			if(col != 0)
				ret += " ";
			
			ret += " " + (char)('a'+col);
			
		}
		
		return ret;
	}
}