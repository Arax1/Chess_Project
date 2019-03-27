package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import parts.Board;
import parts.King;
import parts.Piece;
import parts.Square;


public class Chess {

	public static void main(String args[]){

		int turns = 0;
		boolean checkmate = false;

		Board board = new Board();
		Scanner scan = new Scanner(System.in);
		String player;
		char pc;
		Piece king;

		String str;

		while (true) {

			board.printBoard();

			pc = turns % 2 == 0 ? 'w' : 'b';
			player = turns % 2 == 0 ? "White" : "Black";
			king = (pc == 'w')? board.black_king: board.white_king;

			if(checkmate)
				break;

			System.out.print(player + "'s move: ");
			str = scan.nextLine();

			if(!str.equals("resign")) {

					String[] arr = str.split(" ");
					Square s1 = board.getTileAt(arr[0]);
					Square s2 = board.getTileAt(arr[1]);

					Piece piece = (s1.filled) ? s1.p : null;

					int new_row = s2.row;
					int new_col = s2.column;
					System.out.println(s1 + " getting to: " + s2);

					if(piece != null && piece.getColor() == pc && piece.moveTo(new_col, new_row, board)) {

						System.out.println(s1 + " has moved to: " + s2);
						board.movePiece(s1, s2);
						turns++;

					}

					else
						System.out.println("Invalid Move");

					System.out.print("\n");

					List<Piece> list = (king.getColor() == 'w') ? board.black_pieces : board.white_pieces;
					List<Piece> checks = board.threatens_spot(list, king.getColumn(), king.getRow());
					System.out.println(checks.size());

					if(!checks.isEmpty()) {

						//check for checkmate on OPPOSING king
						checkmate = board.resolve_check(checks, (King) king);
					}

			}

			else {
				turns++;
				pc = turns % 2 == 0 ? 'w' : 'b';
				player = turns % 2 == 0 ? "White" : "Black";
				break;
			}

		}

		scan.close();
		turns++;
		System.out.println(player + " wins!");

	}


	/** For quick testing of checkmate, use the following move order:
	  e2 e4
	  f7 f6
	  f1 e2
	  g7 g5
	  e2 h5
	 */


	//this code has been deprecated
	/**	public static boolean resolve_check(Board b, King k) {

		//basically, just brute force check every possible location
		Board newboard = new Board();

		//throws a concurrent modification exception
		//ArrayList<Piece> enemies = (k.getColor() == 'w') ? b.black_pieces : b.white_pieces;

		ArrayList<Piece> enemies = new ArrayList<Piece>();

		for(Piece p: ((k.getColor() == 'w') ? newboard.white_pieces : newboard.black_pieces)) {
			enemies.add(copy(p));
		}

		Piece temp = null;
		Square old = null;
		boolean foundmove = false;

		for(Piece p: enemies) {
			//System.out.println("Checking " + p + " at [" + p.getColumn() + "," + p.getRow() + "]");

			//save the piece's original location
			old = newboard.board[p.getColumn()][p.getRow()];

			for(Square s: p.getAllMoves(newboard)) {

				//if filled, save the piece for later
				if(b.filled(s)) {
					temp = b.getTileAt(s).p;
				}

				//test the move
				newboard.movePiece(p.getColumn(), p.getRow(), s.column, s.row);

				if(!newboard.inCheck(k.getColor())) {
					//there is a move that results in leaving check - this isn't checkmate
					foundmove = true;
				}

				b.movePiece(s, old);
				if(temp != null)
					b.addPiecePlay(s.column, s.row, temp);
				temp = null;

				if(foundmove) {
					System.out.println("Can move " + p + " to [" + s.column + "," + s.row + "]");
					return false;
				}
			}
		}

		return true;
	}

	public static Piece copy(Piece p) {
		Piece ret;

		if(p instanceof Pawn) {
			ret = new Pawn(p.getColumn(), p.getRow(), p.getColor());
			((Pawn)ret).hasmoved = ((Pawn)p).hasmoved;

		} else if(p instanceof Bishop) {
			ret = new Bishop(p.getColumn(), p.getRow(), p.getColor());

		} else if(p instanceof Knight) {
			return new Knight(p.getColumn(), p.getRow(), p.getColor());

		} else if(p instanceof Rook) {
			ret = new Rook(p.getColumn(), p.getRow(), p.getColor());
			((Rook)ret).hasmoved = ((Rook)p).hasmoved;

		} else if(p instanceof Queen) {
			ret = new Queen(p.getColumn(), p.getRow(), p.getColor());

		} else { //p is an instance of King, presumably
			ret = new King(p.getColumn(), p.getRow(), p.getColor());
			((King)ret).hasmoved = ((King)p).hasmoved;
		}

		return ret;
	}
	 */
}
