package c4view;

import c4model.C4Game;

public interface C4View {
	
	void printInitialBoard(C4Game game);
	
	void printBoard(C4Game game);
		
	void printGameOver(C4Game game);
	
	int firstMoveChoice();
		
	String humanColorChoice();
	
	int difficultyChoice();
	
	String[] playerChoices();
	
	int getHumanChoice(C4Game game);
	
	void printComputerDelay();
	
	void delay(int milli);
	
	boolean terminate();
	
}
