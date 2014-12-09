package ConnectFour;

import java.util.Arrays;

public class Board {
	
	char[][] board;
	int numRows = 6;
	int numCols = 7;
	
	/*
	 * Constructor for the class. Initializes an empty character array
	 */
	public Board(){
		this.board = new char[this.numRows][this.numCols]; 
	}
	
	/*
	 * Constructor to copy one board into another
	 */
	public Board(Board b){
		
		this.board = new char[this.numRows][this.numCols]; 
		
		for (int i=0; i<b.numRows(); i++){
			for (int j=0; j<b.numCols(); j++){
				this.board[i][j] = b.getBoard()[i][j];
			}
		}
		this.numRows = b.numRows;
		this.numCols = b.numCols;
	}
	
	/*
	 * Fills up the character array with no chip symbol
	 */
	public void setUpBoard(){
		for(int i = 0; i < numRows; i++){
			for(int j = 0; j<numCols; j++){
				this.board[i][j] = '_';
			}
		}
		
		this.printBoard();
	}
	
	/*
	 * Prints the board matrix
	 */
	public void printBoard(){
		System.out.println(" 0 1 2 3 4 5 6");
		//System.out.println("|- - - - - - -|");
		for(int i = 0; i < this.numRows; i++){
			System.out.print("|");
			for(int j = 0; j < this.numCols; j++){
				System.out.print(this.board[i][j]);
				if(j != this.numRows){
					System.out.print(' ');
				}
			}
			System.out.println("|"); 
		}
	}
	
	/*
	 * Places a new chip on the board
	 */
	public boolean update(Chip c){
		int col = c.get();
		for(int i = this.numRows-1; i > -1 ; i--){
			if(this.board[i][col] == '_'){
				this.board[i][col] = c.getShape();
				break;
			}
		}
		
		return checkWin(c);
		
	}
	
	/*
	 * Returns if a column in the board if full
	 */
	public boolean checkColFull(int col){
		if(this.board[0][col] == '_'){
			return true;
		}
		return false;
	}
	
	/*
	 * Checks for win state: 4 horizontal, 4 vertical, 4 diagonal, tie if full
	 */
	public boolean checkWin(Chip c){
		return checkHWin(c) || checkVWin(c) || checkDWin(c) || checkTie();
		
	}
	
	/*
	 * Checks for four consecutive pieces in a horizontal line in the board
	 */
	public boolean checkHWin(Chip c){	
		for(int i = 0; i < numRows; i++){
			int count = 0; 
			for(int j = 0; j < numCols; j++){
				if(this.board[i][j] == c.getShape()){
					count++;
					if(count == 4){
						return true;
					} 
				} else {
					count = 0;
				}
			}
		}
		
		return false;
	}
	
	/*
	 * Checks for four consecutive pieces in a vertical line in the board
	 */
	public boolean checkVWin(Chip c){
		for(int i = 0; i < numCols; i++){
			int count = 0; 
			for(int j = 0; j < numRows; j++){
				if(this.board[j][i] == c.getShape()){
					count++;
					if(count == 4){
						return true;
					} 
				} else {
					count = 0;
				}
			}
		}
		return false;
	}
	
	/*
	 * Checks for four consecutive pieces in a Diagonal line in the board
	 */
	public boolean checkDWin(Chip c){
		//First half of right Diagonals
		for(int i = 0; i< numRows; i++){
			int k = i;
			int count = 0;
			for(int j = 0; j <= i; j++){
				if(this.board[k][j] == c.getShape()){
					count++;
					if(count == 4){
						return true;
					}
				}
				k--;
			}
		}
		
		//Second Half of right Diagonals
		for(int i = 1; i < numCols; i++){
			int count = 0;
			int z = i-1;
			int k = i;
			for(int j = numRows-1; j >= z; j--){
				if(this.board[j][k] == c.getShape()){
					count++;
					if(count == 4){
						return true;
					}
				}
				if(k < numCols){
					k++;
				}
			}
		}
		
		//First Half of left Diagonals
		for(int i = 0; i < numRows; i++){
			int count = 0;
			int k = i;
			for(int j = 0; j < numCols-1-i; j++){
				if(this.board[k][j] == c.getShape()){
					count++;
					if(count == 4){
						return true;
					}
				}
				k++;
			}
		}
		
		//Second half of left Diagonals
		int z = 0;
		for(int i = numRows-1; i >= 0; i--){
			int count = 0;
			int k = i;
			for(int j = numCols-1; j >z; j--){
				if(this.board[k][j] == c.getShape()){
					count++;
					if(count == 4){
						return true;
					}
				}
				k--;
			}
			z++;	
		}
		
		return false;
	}
	
	/*
	 * Checks if the board is full ie a tie ! 
	 */
	public boolean checkTie(){
		for(int i = 0; i < numCols; i++){
			if(board[0][i] == '_')
				return false;
		}
		
		return true;
	}
	
	/*
	 * Returns the number of rows in board
	 */
	public int numRows(){
		return this.numRows;
	}
	
	/*
	 * Returns the number of cols in board
	 */
	public int numCols(){
		return this.numCols;
	}
	
	/*
	 * Getter method for the board
	 */
	public char[][] getBoard(){
		return this.board;
	}
	
	
	
	
}
