package gamescontroller;

import gamesmodel.C4Model;
import gamesview.C4App;
import gamesview.C4Console;
import gamesview.C4View;

public class Demo {
	
	public static void main(String[] args) {
		C4Model model = new C4Model();								// create the [M]odel
		C4View view;												// create the [V]iew interface
		//if (true) {
			view = new C4App();										// option 1 - JFrame view			
		//} else {
		//	view = new C4Console();									// option 2 - Console view
		//}
		C4Controller controller = new C4Controller(model, view);	// create the [C]ontroller
		controller.playGame();				// play the game
	}
}
