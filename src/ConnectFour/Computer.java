package ConnectFour;

public class Computer implements IContestant{
	Chip chip;
	Chip oponentChip;
	
	Computer(char chipShape, char oponentShape){
		this.chip = new Chip(chipShape);
		this.oponentChip = new Chip(oponentShape);
	}
	
	/*
	 * Makes move based on simple connect 4 algorithm
	 */
	public Chip makeMove(Board board){
		
		System.out.println("Computer for the win!");
		
		/*
		 * Case 1: Take winning move if such exists
		 */
		for (int i=0; i<board.numCols(); i++){
			Board tempBoard = new Board(board);
			if (tempBoard.update(this.chip.set(i)))
				return this.chip;
		}
		
		/*
		 * Case 2: Take opponents winning move
		 */
		for (int i=0; i<board.numCols(); i++){
			Board tempBoard = new Board(board);
			if (tempBoard.update(this.oponentChip.set(i)))
				return this.chip.set(i);
		}
		
		/*
		 *	Case 3: TAke center over edges and corners 
		 */
		if (board.checkColFull(board.numCols()/2)){
			return this.chip.set(board.numCols()/2);
		} 
		
		/*
		 * Case 4&5: Take cols directly next to center if available
		 * and move outward.
		 */
		for(int i = 1; i < board.numCols()/2; i++){
			if (board.checkColFull(board.numCols()/2- i)){
				return this.chip.set(board.numCols()/2-1);
			} else if (board.checkColFull(board.numCols()/2+i)){
				return this.chip.set(board.numCols()/2+1);
			}
		}
		
		
		return this.chip;	
	}

}
