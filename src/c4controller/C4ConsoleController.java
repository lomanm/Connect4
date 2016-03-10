package c4controller;

import c4businesslogic.C4Logic;
import c4model.C4Game;
import c4view.C4View;

public class C4ConsoleController {

	public void playGame(C4Game game, C4View view){
		int humanChoice = 0;
		C4Logic logic = new C4Logic();
		
		while (true){							// PRIMARY GAME LOOP (FOR CONSOLE / JFRAME VIEWS)
			
			// DO SOME THINGS WHEN THE GAME IS OVER
			if (game.isGameOver()) {
				view.printGameOver(game);			// show game over screen
				if (view.terminate()) {			
					break;							// EXIT THE GAME FOR GOOD
				} else {							
					game.setGameOver(false);		// set things up to play again
					game.setGameStarted(false);
				}
			}
			
			// DO SOME THINGS WHEN A NEW GAME IS STARTED
			if (!game.isGameStarted()) {
				// SET OPTIONS
				String[] choices = view.playerChoices();	// get the player options (color=red/yellow, move=1st/2nd)
				game.getHuman().setColor(choices[0]);	
				game.getComputer().setColor(choices[0].equals("R") ? "Y" : "R");
				game.setHumansTurn(choices[1].equals("1") ? true : false);	
				game.setDifficulty(Integer.parseInt(choices[2]));
				// SET UP THE BOARD
				game.getBoard().setSlots(" ");							// clear out the chips
				game.getBoard().setWinCoords(new int[] {0,0,0,0});		// clear the winning coordinates (for 2nd+ game)
				// DISPLAY INITIAL BOARD
				view.printInitialBoard(game);		// show the board's initial position (empty)
				// START THE GAME
				game.setGameStarted(true);
			}
			
			// DO SOME THINGS WHEN THE GAME IS ACTIVE
			if (game.isHumansTurn()) {
				humanChoice = 0;
				while (humanChoice==0){	
					humanChoice = view.getHumanChoice(game);
				}
			} else {
				view.printComputerDelay();		// add a delay to the computer's move to make the game flow more natural
				humanChoice = 0;
			}
			game = logic.takeTurn(game, humanChoice);					// RETURN'S AN UPDATED GAME OBJECT
			view.printBoard(game);
		}
	}
}
