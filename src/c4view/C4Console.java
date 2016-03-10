package c4view;

import java.util.Scanner;

import c4model.C4Game;

public class C4Console implements C4View {
	Scanner sc;
	
	public C4Console(){		// constructor
		this.sc = new Scanner(System.in);
	}
	
	public void printInitialBoard(C4Game game) {
		printBoard(game);
	}
	
	public void printBoard(C4Game game){
		System.out.println("GAME BOARD");
		System.out.println("|1 2 3 4 5 6 7|");
		System.out.println("|-------------|");
		for (int row=5; row>=0; row--){					// print from top (row 6) to bottom (row 1)
			System.out.print("|");
			for (int column=0; column<=6; column++){		// print from left (col 1) to right (col 7)
				System.out.print(game.getBoard().getSlot(row,column) + (column < 6 ? "|" : ""));
			}
			System.out.println("|");
		}
		System.out.println("|-------------|");
	}
	
	public void printGameOver(C4Game game){
		switch (game.getWinner()) {
			case 1:
				System.out.println("Game over, you WIN!");
				break;
			case 2:
				System.out.println("Game over, you LOSE!");
				break;
			case 3:
				System.out.println("Game over, TIE - board is full!");
				break;
			default:
				System.out.println("Game over, 'quit game' was selected");
		}
	}
	
	public int firstMoveChoice(){	// returns 1 (human moves first) or 2 (computer moves first)
		System.out.println("Would you like to move first or second? Enter '1' or '2'");
		return sc.nextInt();
	}
	
	public String humanColorChoice(){	// returns "R" (human chose red) or "Y" (human chose yellow)
		System.out.println("Would you like to play the red or yellow chips? Enter 'R' or 'Y'");
		return sc.next();
	}
	
	public int difficultyChoice(){	// returns "R" (human chose red) or "Y" (human chose yellow)
		System.out.println("Set difficulty level '1'=easy, '2'=hard:");
		return sc.nextInt();
	}
	
	public String[] playerChoices(){
		String[] choices = new String[3];
		choices[0]=humanColorChoice();
		choices[1]=Integer.toString(firstMoveChoice());
		choices[2]=Integer.toString(difficultyChoice());
		return choices;
	}
	
	public int getHumanChoice(C4Game game) {
		System.out.println("Enter a column to play a chip (1 thru 7)");
		int columnChosen = sc.nextInt();
		if (columnChosen<1 || columnChosen>7){
			System.out.println("You must enter a number between 1 and 7...");
			return 0;
		} else if (!game.getBoard().getSlot(5,columnChosen-1).equals(" ")){
			System.out.println("That column is full!...");
			return 0;
		} else {
			return columnChosen;
		}
	}
	
	public void printComputerDelay(){
		System.out.println("Thinking...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void delay(int milli){		// required for interface, but not used in this view
	}
	
	public boolean terminate(){
		System.out.println("Play again? ('Y' or 'N')");
		String exitChoice = sc.next();
		if (exitChoice.equals("Y")) {
			return false;	// do not terminate if player wants to play again
		}
		sc.close();
		return true;		// terminate game if players doesn't want to play again
	}
}
