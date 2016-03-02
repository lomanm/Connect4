package gamesview;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import gamesmodel.C4Model;

public class C4App implements C4View {
	private C4BoardFrame app;;
	
	public C4App(){		// constructor
		this.app = new C4BoardFrame();
	}
	
	public void printInitialBoard(C4Model model){
		app.setTitle("Connect 4");
        app.setResizable(false);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setAlwaysOnTop(true);
        app.updateBoard(model);
        app.setVisible(true);
        app.repaint();
	}
	
	public void printBoard(C4Model model, int[]slotPlayed){
		String thisColor = model.getBoard().getSlot(slotPlayed[0], slotPlayed[1]);
		model.getBoard().setSlot(slotPlayed[0], slotPlayed[1], " ");	// temporarily erases current chip from board for animation purposes
		for (int i=5; i>=slotPlayed[0]; i--) {
			if (i!=5) delay(85);		// delay the animation by 85 milliseconds per frame
			model.getBoard().setSlot(i, slotPlayed[1], thisColor);
			app.updateBoard(model);
			app.setVisible(true);
			app.repaint();
			model.getBoard().setSlot(i, slotPlayed[1], " ");
		}
		model.getBoard().setSlot(slotPlayed[0], slotPlayed[1], thisColor);
	}
	
	public void printGameOver(int winner, String winText, C4Model model){ 
		switch (winner) {
			case 0:
				app.setTitle("APPLICATION TERMINATED");
				break;
			case 3:
				app.setTitle("THE GAME HAS ENDED IN A TIE!");
				break;
			default:
				app.setTitle("GAME OVER - YOU " + winText + "!");
				app.updateBoard(model);
				//app.winningBoard(model);
				app.setVisible(true);
				app.repaint();
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
		C4OptionFrame opt = new C4OptionFrame();
		
		// this line added when removed option panel
		opt.setLayout(new BoxLayout(opt.getContentPane(), BoxLayout.PAGE_AXIS));
		
		opt.setTitle("Connect 4 - Options");
        opt.setResizable(false);
        opt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        opt.setAlwaysOnTop(true);
        opt.setVisible(true);
        opt.repaint();
		choices = opt.gameOptions();
		opt.dispose();
		return choices;
	}
	
	public int getHumanChoice(C4Model model) {
		app.setTitle("YOUR MOVE:  click a column");	// get the click event here
		int x = app.getX();
		int y = app.getY();
		while (x==app.getX() && y==app.getY()){		// this loop is where the system waits for user decision
			delay(50);							// this slow-down mechanism is required for the loop to work
		}
		x = app.getX();
		y = app.getY();
		int columnChosen=0;
		if (x<=100) {				// now evaluate click position to get column chosen by human player
			columnChosen=1;
		} else if (x<=200) {
			columnChosen=2;
		} else if (x<=300) {
			columnChosen=3;
		} else if (x<=400) {
			columnChosen=4;
		} else if (x<=500) {
			columnChosen=5;
		} else if (x<=600) {
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
	
	public void terminate(){
	}
}
