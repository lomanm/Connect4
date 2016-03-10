package c4view;

import javax.swing.JFrame;

import c4model.C4Game;

public class C4App implements C4View {
	private C4BoardFrame app;
	private int gridSize;
	
	public C4App(){		// constructor
		gridSize = 75;
	}
	
	public void printInitialBoard(C4Game game){
		app = new C4BoardFrame(gridSize);		// parameter is size of board slot
		app.setTitle("Connect 4");
        app.setResizable(false);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setAlwaysOnTop(true);
        app.updateBoard(game, false);
        app.setVisible(true);
        app.repaint();
	}
	
	public void printBoard(C4Game game){
		int[]slotPlayed = game.getLastPlay();
		String thisColor = game.getBoard().getSlot(slotPlayed[0], slotPlayed[1]);
		game.getBoard().setSlot(slotPlayed[0], slotPlayed[1], " ");	// temporarily erases current chip from board for animation purposes
		for (int i=5; i>=slotPlayed[0]; i--) {
			if (i!=5) delay(85);		// delay the animation by 85 milliseconds per frame
			game.getBoard().setSlot(i, slotPlayed[1], thisColor);
			app.updateBoard(game, false);
			app.setVisible(true);
			app.repaint();
			game.getBoard().setSlot(i, slotPlayed[1], " ");
		}
		game.getBoard().setSlot(slotPlayed[0], slotPlayed[1], thisColor);
	}
	
	public void printGameOver(C4Game game){ 
		switch (game.getWinner()) {
		case 1:
			app.setTitle("GAME OVER - YOU WIN!");
			app.updateBoard(game, true);
			app.setVisible(true);
			app.repaint();
			break;
		case 2:
			app.setTitle("GAME OVER - YOU LOSE!");
			app.updateBoard(game, true);
			app.setVisible(true);
			app.repaint();
			break;
		case 3:
			app.setTitle("THE GAME HAS ENDED IN A TIE!");
			break;

		default:
			app.setTitle("APPLICATION TERMINATED");
		}
	}
	
	public int firstMoveChoice(){		// required for interface but not used in this view
		return 0;
	}
		
	public String humanColorChoice(){	// required for interface but not used in this view
		return null;
	}
	
	public int difficultyChoice(){// required for interface but not used in this view
		return 0;
	}
	
	public String[] playerChoices(){
		String[] choices = new String[] {"R", "1", "1"};
		C4OptionFrame opt = new C4OptionFrame(gridSize);
		choices = opt.gameOptions();
		opt.dispose();
		return choices;
	}
	
	public int getHumanChoice(C4Game game) {
		app.setTitle("YOUR MOVE:  click a column");	// get the click event here
		app.waitForInput();
		int x = app.getX();
		
		int columnChosen=0;
		if (x<=gridSize*1) {				// now evaluate click position to get column chosen by human player
			columnChosen=1;
		} else if (x<=gridSize*2) {
			columnChosen=2;
		} else if (x<=gridSize*3) {
			columnChosen=3;
		} else if (x<=gridSize*4) {
			columnChosen=4;
		} else if (x<=gridSize*5) {
			columnChosen=5;
		} else if (x<=gridSize*6) {
			columnChosen=6;
		} else {
			columnChosen=7;
		}
		return columnChosen;
	}
	
	public void printComputerDelay(){
		app.setTitle("COMPUTER'S MOVE:  thinking....");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void delay(int milli){
		try {
			Thread.sleep(milli);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public boolean terminate(){
		app.setAlwaysOnTop(false);
		C4PlayAgainFrame app2 = new C4PlayAgainFrame();
		boolean playAgain = app2.playAgain();
        app2.dispose();
        app.dispose();			// dispose JFrame for either choice (we will create it again)	
		return !playAgain;		// terminate game if player doesn't want to play again
	}
}
