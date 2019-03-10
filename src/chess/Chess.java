package chess;
import parts.*;
import java.util.Scanner;


public class Chess {

	public static void main(String args[]){
		
		int turns = 0;
		
		Square[][] board = makeBoard();
		
		Scanner scan = new Scanner(System.in);
		String player = "White";
		String str;
		
		while (true) {
			
			printBoard(board);
			
			player = turns % 2 == 0 ? "White" : "Black";
			
			System.out.print(player + "'s move: ");
			str = scan.nextLine();
			
			if(!str.equals("resign"))
			{
					String[] arr = str.split(" ");
					turns++;
			}
			
			else {
				turns++;
				player = turns % 2 == 0 ? "White" : "Black";
				break;
			}
				
		}
		
		scan.close();
		turns++;
		System.out.println(player + " wins!");
		
	}
	
	public static void printBoard(Square[][] board) {
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
	
	public static Square[][] makeBoard() {
		Square[][] ret = new Square[8][8];
		
		for(int column = 0; column < 8; column++)
			for(int row = 0; row < 8; row++) {
				ret[column][row] = new Square(column,row);
				
				if(row < 2 || row > 5) {
					// there should be pieces in this square
					// [0,0] is bottom left, [7,7] is top right
					
					if(row == 1 || row == 6) {
						//we have a pawn!
						ret[column][row].putPiece(new Pawn(column,row));
					
					} else {
						//we have some other piece
						switch(column) {
						
						case 0: case 7:
							//rook
							ret[column][row].putPiece(new Rook(column, row));
							break;
						
						case 1: case 6:
							//knight
							ret[column][row].putPiece(new Knight(column, row));
							break;
							
						case 2: case 5:
							//bishop
							ret[column][row].putPiece(new Bishop(column, row));
							break;
						
						case 3:
							//YASSS QUEEN
							ret[column][row].putPiece(new Queen(column, row));
							break;
							
						case 4:
							//THE KING
							ret[column][row].putPiece(new King(column, row));
							break;
						}	
					}
				}
			}
		
		return ret;
	}
	
}
