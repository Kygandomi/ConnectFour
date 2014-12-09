package ConnectFour;

import java.util.Scanner;

public class Player implements IContestant{
	Scanner scan;
	Chip chip;
	
	Player(char chipShape){
		
		this.chip = new Chip(chipShape);
		this.scan = new Scanner(System.in);
		
	}
	
	/*
	 * Asks a player which column he wishes to place a disk. Checks if valid
	 * and return the desired column
	 */
	public Chip makeMove(Board board){
			System.out.println("What column would you like?");
			int column = scan.nextInt();
			
			if (checkMove(column, board)){
				this.chip.set(column);
				return this.chip;
			} else {
				System.out.println("You fool! Invalid move.");
				return makeMove(board);
			}
	}
	
	/*
	 * Determine if the move is valid. Checks within range and if column is not full
	 */
	private boolean checkMove(int column, Board board){
		return column < 7 && column > -1 && board.checkColFull(column);
	}
}
