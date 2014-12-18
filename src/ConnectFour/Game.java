package ConnectFour;

import gnu.io.SerialPortEvent;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Game {
	boolean useSerial = false;
	boolean gameFinished = false;
	Board board;
	IContestant contestant1;
	IContestant contestant2;
	Scanner scan;
	Serial mySerial = new Serial();
	//SerialPortEvent oEvent = new SerialPortEvent(mySerial, DATA_AVAILABLE, getOldValue(), getnewValue());
	
	Game(){
		this.board = new Board();
		this.scan = new Scanner(System.in);
	}
	
	
	/*
	 * establishes which type of game the player wishes to play
	 */
	public void setUp(){
		System.out.println("Would you like to send data over serial? (yes/no)");
		String choice1 = scan.nextLine();
		if(choice1.equals("yes")){
			useSerial = true;
			mySerial.initialize();
		} else if(choice1.equals("no")){
			useSerial = false;
		} else{
			this.setUp();
		}
		
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
			default:
				this.setUp();
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
			Chip one = this.contestant1.makeMove(board);
			if(useSerial){
				byte[] b = {(byte) one.get(), '\n'};
				mySerial.sendData(b);
			}
			gameFinished = board.update(one);
			board.printBoard();
			 if(gameFinished){
				System.out.println('+' + " is the WINNER!");
				break;
			}
			 System.out.println("Loading...");
			try {
			    Thread.sleep(15000);                 //1000 milliseconds is one second.
				} 
			catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			   }
			System.out.println("Motion complete. Resume.");
			 
			System.out.print("Contestant 2: ");
			Chip two = this.contestant2.makeMove(board);
			if(useSerial){
				byte[] b = {(byte)(two.get() + 7), '\n'};
				mySerial.sendData(b);
			}
			gameFinished = board.update(two);
			board.printBoard();
			if (gameFinished){
				System.out.println('x' + " is the WINNER!");
				break;
			}
			
			System.out.println("Loading...");
			try {
			    Thread.sleep(15000);                 //1000 milliseconds is one second.
				} 
			catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			   }
			System.out.println("Motion complete. Resume.");
		}	
		
	}

}
