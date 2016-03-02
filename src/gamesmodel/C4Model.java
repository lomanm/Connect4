package gamesmodel;

public class C4Model {
	private C4Player human;
	private C4Player computer;
	private C4Board board;
	
	public C4Model() {
		this.human = new C4Player();	// create the human player
		this.computer = new C4Player();	// create the computer player
		this.board = new C4Board();		// create a board object, has slots array[6][7]
	}
	
	public C4Player getHuman(){
		return human;
	}
	
	public C4Player getComputer(){
		return computer;
	}
	
	public C4Board getBoard(){
		return board;
	}
}
