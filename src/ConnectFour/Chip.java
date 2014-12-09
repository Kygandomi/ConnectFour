package ConnectFour;

public class Chip {
	int column;
	char shape;
	
	Chip(char shape){
		this.shape = shape;
	}
	
	public Chip set(int column){
		this.column = column;
		return this;
	}
	
	public int get(){
		return this.column;
	}
	
	public char getShape(){
		return this.shape;
	}

}
