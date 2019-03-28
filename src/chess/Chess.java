/**
 * @author Anand Raju
 * @author Sammy Berger
 * 
 * <h1>Main Chess Program</h1>
 * 
 *
 * This program simulates a real chess game, where each player takes turns to control the board 
 * and put the opponent's king into checkmate! Each player's moves are constrained by the rules of the game, and are
 * able to use special moves such as castling, en passant and promotion of pieces. 
 * 
 * To move your piece, simply type in the command line [rankFile1] [rankfile2], where rankfile1 is the piece you want to move,
 * and rankfile2 is spot you want to move the piece to.
 * 
 * To quit, simply type in "resign" and to draw, type in [rankFile1] [rankFile2] "draw?"
 *
 * 
 * */

package chess;

import java.util.Scanner;

import parts.Board;
import parts.Piece;
import parts.Square;


public class Chess {

	public static void main(String args[]){

		int turns = 0;
		boolean checkmate = false;
		boolean draw = false;
		boolean invalidmove = false;

		Board board = new Board();
		Scanner scan = new Scanner(System.in);
		String player = "White";
		char pc;
		//Piece king;

		boolean cantMove, inCheck;
		
		String str;

		while (true) {
			
			if(checkmate) {
				draw = false;
				break;
			}
			
			if(!invalidmove)
				board.printBoard();

			pc = turns % 2 == 0 ? 'w' : 'b';
			player = turns % 2 == 0 ? "White" : "Black";
			//king = (pc == 'w')? board.black_king: board.white_king;
				

			System.out.print(player + "'s move: ");
			str = scan.nextLine();

			if(!str.equals("resign")) {

					String[] arr = str.split(" ");
					Square s1 = board.getTileAt(arr[0]);
					Square s2 = board.getTileAt(arr[1]);
					String third = "";
					
					if(arr.length > 2)
						third = arr[2];
					
					if(third.equals("draw?")) {
						
						draw = true;
						break;
						
					}
					
					Piece piece = (s1.filled) ? s1.p : null;

					//System.out.println(s1 + " getting to: " + s2);

					if(piece != null && piece.getColor() == pc && board.tryMove(s1, s2, third)) {

						invalidmove = false;
						
						//System.out.println(s1 + " has moved to: " + s2);
						turns++;

					}

					else {
						System.out.println("Illegal move, try again");
						invalidmove = true;
						
						//System.out.println("Cannot move from " + s1.pos() + " to " + s2.pos());
					}
					
					/*System.out.print("\n");

					List<Piece> list = (king.getColor() == 'w') ? board.black_pieces : board.white_pieces;
					List<Piece> checks = board.threatens_spot(list, king.getColumn(), king.getRow());

					if(!checks.isEmpty()) {

						//check for checkmate on OPPOSING king
						checkmate = board.resolve_check(checks, (King) king);
					} */

					inCheck = board.inCheck(turns % 2 == 1 ? 'b' : 'w');
					cantMove = !board.canMove(turns % 2 == 1 ? 'b' : 'w');
					
					
					//System.out.println("checking special conditions:");
					if(cantMove && inCheck) {
						System.out.println("Checkmate");
						checkmate = true;
						break;
					}
					else if(cantMove && !inCheck) {
						System.out.println("Stalemate");
						draw = true;
						break;
					}
					else if(!cantMove && inCheck) {
						System.out.println("Check");
					}/**/
					
					System.out.println();

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
		
		if(draw)
			System.out.println("draw");
		
		else if(checkmate)
			System.out.println(player + " wins!");
		
		else
			System.out.println(player + " wins!");

	}
	
	public static String brak(int a, int b) {
		
		hideOldCode(); //does nothing, just is there so that there isn't a warning for never using the method
		
		return "[" + a + "," + b + "]";
	}

	
	//literally does nothing, just exists so we can collapse the old code that's commented out.
	private static void hideOldCode() {
	/* For quick testing of checkmate, use the following move order:
	  e2 e4
	  f7 f6
	  f1 e2
	  g7 g5
	  e2 h5
	 */


	//this code has been deprecated
	/*	public static boolean resolve_check(Board b, King k) {

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
}
