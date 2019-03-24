package chess;

import java.util.Scanner;

import parts.*;


public class Chess {

	public static void main(String args[]){
		
		int turns = 0;
		
		Board board = new Board();
		
		Scanner scan = new Scanner(System.in);
		String player = "White";
		char pc = 'w';
		String str;
		
		while (true) {
			
			board.printBoard();
			
			pc = turns % 2 == 0 ? 'w' : 'b';
			player = turns % 2 == 0 ? "White" : "Black";
			
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
	
}
