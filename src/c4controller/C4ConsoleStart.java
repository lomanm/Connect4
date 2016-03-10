package c4controller;

import c4model.C4Game;
import c4view.C4App;
import c4view.C4Console;
import c4view.C4View;

public class C4ConsoleStart {
	
	public static void main(String[] args) {
		int viewChoice = 1;
		C4Game game = new C4Game();								// create the [M]odel
		C4View view;												// create the [V]iew interface
		if (viewChoice==1) {
			view = new C4App();										// App view (JFrame)
		} else {
			view = new C4Console();									// Console view
		}
		C4ConsoleController controller = new C4ConsoleController();	// create the [C]ontroller
		controller.playGame(game, view);				// play the game
	}
}
