package gamesview;

import gamesmodel.C4Model;

public interface C4View {
	
	void printInitialBoard(C4Model model);
	
	void printBoard(C4Model model, int[]slotPlayed);
		
	void printGameOver(int winner, String winText, C4Model model);
	
	int firstMoveChoice();
		
	String humanColorChoice();
	
	int difficultyChoice();
	
	String[] playerChoices();
	
	int getHumanChoice(C4Model model);
	
	void printComputerDelay();
	
	void delay(int milli);
	
	void terminate();
	
}
