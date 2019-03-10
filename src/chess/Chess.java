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
	
	public static Square[][] makeBoard() {
		Square[][] ret = new Square[8][8];
		
		for(int column = 0; column < 8; column++)
			for(int row = 0; row < 8; row++) {
				if(row > 1 && row < 6) {
					//we are in the middle columns, no pieces to start
					ret[column][row] = new Square(column,row);
				
				} else {
					// there should be pieces in this square
					// [0,0] is bottom left, [7,7] is top right
					
					if(row == 1 || row == 6) {
						//we have a pawn!
						Pawn p = new Pawn(column,row);
						ret[column][row] = new Square(column,row,p);
					
					} else {
						//we have some other piece
					}
				}
			}
		
		return ret;
	}
	
}
