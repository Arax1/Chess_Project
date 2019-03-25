package chess;

import java.util.ArrayList;
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
		char pc, oc; // 'player color' and 'other color'
		Piece king;

		String str;
		
		while (true) {
			
			board.printBoard();
			
			pc = turns % 2 == 0 ? 'w' : 'b';
			oc = turns % 2 == 1 ? 'w' : 'b';
			player = turns % 2 == 0 ? "White" : "Black";
			king = (pc == 'w')? board.white_king: board.black_king;
			
			System.out.print(player + "'s move: ");
			str = scan.nextLine();
			
			if(!str.equals("resign") || !checkmate) {
				
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

					if(board.inCheck(oc)) {
						
						System.out.println("In check! Will check for checkmate.");
						
						//check for checkmate on OPPOSING king
						checkmate = resolve_check(board, (pc == 'b')? board.white_king : board.black_king);
					
						if(checkmate) {
							System.out.println("Checkmate!");
							break;
						}
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

	
	
	// where you determine if there's checkmate or not;
	/** For quick testing of checkmate, use the following move order:
	 * e2 e4
	 * f7 f6
	 * f1 e2
	 * g7 g5
	 * e2 h5
	 */
	public static boolean resolve_check(Board b, King k) {
		
		//basically, just brute force check every possible location
		Board newboard = b;
		ArrayList<Piece> enemies = (k.getColor() == 'w') ? newboard.black_pieces : newboard.white_pieces;
		
		Piece temp = null;
		Square old = null;
		boolean foundmove = false;
		
		for(Piece p: enemies) {
			//save the piece's original location
			old = newboard.board[p.getColumn()][p.getRow()];
			
			for(Square s: p.getAllMoves(newboard)) {
				
				//if filled, save the piece for later
				if(b.getTileAt(s).filled) {
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
					System.out.println("Can move " + p + " to + " + s);
					return false;
				}
			}
		}
		
		return true;
	}
	
}
