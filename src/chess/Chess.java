package chess;
import parts.*;
import java.util.Scanner;


public class Chess {

	public static void main(String args[]){
		
		int turns = 0;
		
		Board chess_board = new Board();
		Scanner scan = new Scanner(System.in);
		String player = "White";
		String str;
		
		while (true) {
			
			chess_board.printBoard();
			
			player = turns % 2 == 0 ? "White" : "Black";
			
			System.out.print(player + "'s move: ");
			str = scan.nextLine();
			
			if(!str.equals("resign"))
			{
					String[] arr = str.split(" ");
					Square s1 = chess_board.getTileAt(arr[0]);
					Square s2 = chess_board.getTileAt(arr[1]);
					System.out.println(player + " moved from" + s1 + " to " + s2);
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
	
}
