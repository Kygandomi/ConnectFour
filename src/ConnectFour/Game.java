package ConnectFour;

import java.util.Scanner;

public class Game {
	boolean gameFinished = false;
	Board board;
	IContestant contestant1;
	IContestant contestant2;
	Scanner scan;
	
	Game(){
		this.board = new Board();
		this.scan = new Scanner(System.in);
	}
	
	
	/*
	 * establishes which type of game the player wishes to play
	 */
	public void setUp(){
		System.out.println("Enter 1 Player v. Player");
		System.out.println("Enter 2 Player v. Computer");
		System.out.println("Enter 3 Computer v. Computer");
		int choice = scan.nextInt();
		switch(choice){
			case 1: 
				initPvP();
				break;
			case 2:
				initPvC();
				break;
			case 3:
				initCvC();
				break;
		}
		
	}
	
	/*
	 * initializes a Player vs Player Game
	 */
	public void initPvP(){
		System.out.println("PvP game selected");
		System.out.println("Player 1 is + and Player 2 is x");
		this.contestant1 = new Player('+');
		this.contestant2 = new Player('x');
	}
	
	/*
	 * initializes a Player vs Computer Game
	 */
	public void initPvC(){
		System.out.println("PvC game selected");
		System.out.println("Player 1 is + and Computer is x");
		this.contestant1 = new Player('+');
		this.contestant2 = new Computer('x', '+');
	}
	
	/*
	 * initializes a Computer vs Computer Game
	 */
	public void initCvC(){
		System.out.println("CvC game selected");
		System.out.println("Computer 1 is + and Computer 2 is x");
		this.contestant1 = new Computer('+', 'x');
		this.contestant2 = new Computer('x', '+');
	}
	
	public void playGame(){
		this.setUp();
		board.setUpBoard();
		while(!gameFinished){
			System.out.print("Contestant 1: ");
			gameFinished = board.update(this.contestant1.makeMove(board));
			board.printBoard();
			if (gameFinished){
				System.out.println('+' + " is the WINNER!");
				break;
			}
			System.out.print("Contestant 2: ");
			gameFinished = board.update(this.contestant2.makeMove(board));
			board.printBoard();
			if (gameFinished){
				System.out.println('x' + " is the WINNER!");
				break;
			}
		}
	}

}
