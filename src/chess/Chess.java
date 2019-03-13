package chess;
import parts.*;
import java.util.Scanner;


public class Chess {

	public static void main(String args[]){
		
		int turns = 0;
		
		Board board = new Board();
		
		Scanner scan = new Scanner(System.in);
		String player = "White";
		String str;
		
		while (true) {
			
			board.printBoard();
			
			player = turns % 2 == 0 ? "White" : "Black";
			
			System.out.print(player + "'s move: ");
			str = scan.nextLine();
			
			if(!str.equals("resign"))
			{
					//String[] arr = str.split(" ");
					System.out.print("\n");
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
