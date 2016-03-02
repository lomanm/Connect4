package gamescontroller;

import java.applet.Applet;
import gamesmodel.C4Model;
import gamesview.C4App;
import gamesview.C4View;

public class C4Applet extends Applet {
	
	public void init(){
	}
	public void destroy(){
	}
	public void start() {
		C4Model model = new C4Model();								// create the [M]odel
		C4View view = new C4App();									// create the [V]iew interface			
		C4Controller controller = new C4Controller(model, view);	// create the [C]ontroller
		controller.playGame();				// play the game
	}
	public void paint(){
	}
}
