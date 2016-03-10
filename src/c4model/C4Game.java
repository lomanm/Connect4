package c4model;

public class C4Game {
	private C4Player human;
	private C4Player computer;
	private C4Board board;
	private int difficulty, winner;			// difficulty (1=east,2=hard), winner (0=game not over, 1=human won, 2=computer won, 3=game ended in a tie)
	private boolean isGameStarted, isGameOver, isWebBased;
	private boolean isHumansTurn;			// TRUE = human's turn, FALSE = computer's turn
	private int[] lastPlay;
	
	public C4Game() {					// CONSTRUCTOR FOR APP OR CONSOLE VIEW (no options set yet)
		this.human = new C4Player();	// create the human player
		this.computer = new C4Player();	// create the computer player
		this.board = new C4Board();		// create a board object, has slots array[6][7]
		setDifficulty(1);				// set difficulty to 1 until we learn more information
		setGameOver(false);
		setGameStarted(false);
		setWebBased(false);
		setHumansTurn(true);			// set to "human's move" until we learn more information	
	}
	public C4Game(String humanColor, int humanMove, int difficulty) {	// CONSTRUCTOR FOR WEB VIEW (options sent into constructor)
		this.human = new C4Player(humanColor);	// create the human player
		this.computer = new C4Player(humanColor.equals("R")?"Y":"R");	// create the computer player
		this.board = new C4Board();		// create a board object, has slots array[6][7]
		setDifficulty(difficulty);
		setGameOver(false);
		setGameStarted(true);
		setWebBased(true);
		setHumansTurn(humanMove==1 ? true : false);
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
	public int getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	public boolean isGameOver() {
		return isGameOver;
	}
	public void setGameOver(boolean isGameOver) {
		this.isGameOver = isGameOver;
	}
	public boolean isGameStarted() {
		return isGameStarted;
	}
	public void setGameStarted(boolean isGameStarted) {
		this.isGameStarted = isGameStarted;
	}
	public boolean isWebBased() {
		return isWebBased;
	}
	public void setWebBased(boolean isWebBased) {
		this.isWebBased = isWebBased;
	}
	public boolean isHumansTurn() {
		return isHumansTurn;
	}
	public void setHumansTurn(boolean isHumansTurn) {
		this.isHumansTurn = isHumansTurn;
	}
	public int getWinner() {
		return winner;
	}
	public void setWinner(int winner) {
		this.winner = winner;
	}
	public int[] getLastPlay() {
		return lastPlay;
	}
	public void setLastPlay(int[] lastPlay) {
		this.lastPlay = lastPlay;
	}
}
