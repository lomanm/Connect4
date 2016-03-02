package gamescontroller;

import java.util.Random;
import gamesmodel.C4Model;
import gamesview.C4View;

public class C4Controller {
	private C4View view;
	private C4Model model;
	private int c4Winner;		// represents outcome of game 0=forfeit, 1=human, 2=computer, 3=draw (board filled without winner)
	private int humanTurn;		// represents whether the human players moves first (1) or second (2)
	private int difficulty;		// represents difficulty level 1=easy, 2=hard (possibly add "3=expert")
	private Random r;
	
	public C4Controller(C4Model model, C4View view){		// constructor
		this.model = model;
		this.view = view;
	}
	
	public void playGame(){
		initializeBoard();					// clear out all board array elements (stuff with " ")
		r = new Random();					// initiate a new random number string
		String[] playerChoices = view.playerChoices();	// get the player options (color=red/yellow, move=1st/2nd)
		setColors(playerChoices[0]);		// set color variable based on user response
		humanTurn = Integer.parseInt(playerChoices[1]);		// set move variable based on user response
		difficulty = Integer.parseInt(playerChoices[2]);	// set difficulty level to 1=easy or 2=hard
		view.printInitialBoard(model);			// show the board's initial position (empty)
		c4Winner = gameLoop();				// enter the game play loop
		if (c4Winner>=1 && c4Winner<=2) model.getBoard().setGameOver(true);
		view.printGameOver(c4Winner, c4Winner==humanTurn?"WIN":"LOSE", model);		// show GAME OVER screen
		view.terminate();					// clean up (closes scanner - for console view only)
	}
	
	public int gameLoop(){					// primary game loop, returns winner (integer)
		int whoseTurn = 1;					// represents whose turn it is, 1=player 1, 2=player 2
		int columnChoice; 					// move choice (column) for both human and computer player
		int winningPlayer = 0;				// 0 = no winner, 1=player 1, 2=player 2, 3=TIE
		int[] slotPlayed = new int[2];		// variable for most recent game move (chip placement)
		while (winningPlayer==0){
			columnChoice = whoseTurn == humanTurn ? humanMove() : computerMove();		// make move (human or computer)
			slotPlayed = dropChip(columnChoice-1, whoseTurn==humanTurn ? model.getHuman().getColor() : model.getComputer().getColor());	// update the board with the chip, returns an int[] array with two elements [row,column]
			animate(slotPlayed);						// display the updated board
			if (checkForWinner(slotPlayed, false)) return whoseTurn;		// we have a winner, break from game loop
			whoseTurn = swapTurn(whoseTurn);						// change turns (human <--> computer)
			if (boardIsFull()) return 3;	// exit game loop if the game board gets filled up without a winner, "3" equals a "draw"
		}
		return winningPlayer;
	}
	
	public void initializeBoard(){
		model.getBoard().setSlots(" ");		// initialize all elements of slots array (stuff with " ")
	}
	
	public void setColors(String colorChoice){
		model.getHuman().setColor(colorChoice);							// set human player's color attribute
		model.getComputer().setColor(colorChoice.equals("R") ? "Y" : "R");	// set computer player's color attribute	
	}
	
	public void animate(int[] slotPlayed){
		view.printBoard(model, slotPlayed);
	}
	
	public int swapTurn(int lastMove){
		return lastMove==2 ? 1 : 2;
	}
	
	public int humanMove(){
		int colChoice = 0;
		while (colChoice==0){	
			colChoice = view.getHumanChoice(model);
		}
		return colChoice;
	}
	
	public int pickRandomEmptyColumn(){		// part of the AI includes the computer player choosing a random column
		int col=0;
		int[] colArray = randomColumns();
		for (int i=0;i<colArray.length;i++) {
			if (model.getBoard().getSlot(5,colArray[i]-1).equals(" ")){
				return colArray[i];
			}
		}
		return col;			// this code will never execute, its only there for it to compile correctly
	}
	
	public int[] randomColumns(){			// returns an array of integers 1-7 in random order
		int[] columnNums = new int[7];
		int col = r.nextInt(7)+1;							// pick a random column from 1 to 7
		for (int k=0; k<=6; k++){
			col = (col==7 ? 1 : col + 1);
			columnNums[k] = col;
		}
		return columnNums;
	}
	
	public boolean boardIsFull(){		// evaluates if the board is full of chips, this occurs after the program
		for (int i=0; i<=6; i++) {		// has determined there is no winner, so if the board is full then the game is a draw
			if (model.getBoard().getSlot(5,i).equals(" ")) return false;	// return false when finding the first open slot on the board
		}
		return true;
	}
	
	public int[] dropChip(int col, String colorDropped){	// adds a "chip" to our board array
		int[] coords = new int[2];
		for (int row=0; row<=5; row++){		// check this column row by row to see where the chip will end up	
			if (model.getBoard().getSlot(row, col).equals(" ")){
				model.getBoard().setSlot(row, col, colorDropped);
				coords[0] = row;
				coords[1] = col;
				return coords;
			}
		}
		return coords;	// this line is necessary for compiler but will never execute
	}
	
	public int getLowRow(int colToCheck){	// for a given column (1-7), check for the lowest open row
		for (int i=0; i<=5; i++) {
			if (model.getBoard().getSlot(i,colToCheck-1).equals(" ")) return i;
		}
		return -1;							// return -1 if the column is full of chips (therefore this column is unavailable for a move/play)
	}
	
	// checks around the last chip dropped - looks for 4+ chips in a row/column/diagonalx2
	// the first parameter is the last chip dropped (or proposed chip when isPretend==true)
	public boolean checkForWinner(int[] spot, boolean isPretend){		
		if (checkHorizontal(spot,model.getBoard().getSlot(spot[0],spot[1]), isPretend) >= 4) return true;
		if (checkVertical(spot,model.getBoard().getSlot(spot[0],spot[1]), isPretend) >= 4) return true;
		if (checkDiag1(spot,model.getBoard().getSlot(spot[0],spot[1]), isPretend) >= 4) return true;
		if (checkDiag2(spot,model.getBoard().getSlot(spot[0],spot[1]), isPretend) >= 4) return true;
		return false;
	}
	
	/*---------------------------------------------------------------------------------------------
	 *  The board checking methods begin here.  There are four methods and they check the board
	 *  for connections of chips in the four possible directions: horizontal, vertical, and 
	 *  diagonal (x2).  They can be called to evaluate actual positions (for example checking
	 *  to see if the most recent move created a 'Connect 4' and ended the game) or to evaluate
	 *  hypothetical positions.  The AI will use this functionality to determine which move to play.
	 *---------------------------------------------------------------------------------------------*/
	
	// Returns the size of the horizontal row of chips that exists (or would hypothetically be
	// created) when looking at a specific chip slot on the board
	public int checkHorizontal(int[] spot, String thisColor, boolean isPretend){		// check horizontally to both sides for chips of the same color	
		int rowLength=1;	// the chip just dropped (or hypothetical chip) counts as a row of '1'
		model.getBoard().setWinCoords(new int[] {spot[0],spot[1],spot[0],spot[1]});
		for (int i=spot[1]-1; i>=0; i--){	// first check to left 
			if (model.getBoard().getSlot(spot[0],i).equals(thisColor) || (isPretend && model.getBoard().getSlot(spot[0],i).equals(" "))) {
				rowLength++;
				model.getBoard().setWinCoords(new int[] {model.getBoard().getWinCoords()[0],i,model.getBoard().getWinCoords()[2],model.getBoard().getWinCoords()[3]});
			} else {
				break;	// found empty cell or the other color, no need to continue searching in this direction 
			}
		}
		for (int i=spot[1]+1; i<=6; i++){		// second check to right 
			if (model.getBoard().getSlot(spot[0],i).equals(thisColor) || (isPretend && model.getBoard().getSlot(spot[0],i).equals(" "))) {
				rowLength++;
				model.getBoard().setWinCoords(new int[] {model.getBoard().getWinCoords()[0],model.getBoard().getWinCoords()[1],model.getBoard().getWinCoords()[2],i});
			} else {
				break;	// found empty cell or the other color, no need to continue searching in this direction 
			}
		}
		if (rowLength<4 || isPretend) model.getBoard().setWinCoords(new int[] {0,0,0,0});
		return rowLength;
	}
	
	// Returns the size of the vertical row of chips that exists (or would hypothetically be
	// created) when looking at a specific chip slot on the board	
	public int checkVertical(int[] spot, String thisColor, boolean isPretend){		// check vertically in both directions for chips of the same color	
		int rowLength=1;	// the chip just dropped (or hypothetical chip) counts as a row of '1'
		model.getBoard().setWinCoords(new int[] {spot[0],spot[1],spot[0],spot[1]});
		for (int i=spot[0]-1; i>=0; i--){		// first check below 
			if (model.getBoard().getSlot(i,spot[1]).equals(thisColor) || (isPretend && model.getBoard().getSlot(i,spot[1]).equals(" "))) {
				rowLength++;
				model.getBoard().setWinCoords(new int[] {i,model.getBoard().getWinCoords()[1],model.getBoard().getWinCoords()[2],model.getBoard().getWinCoords()[3]});
			} else {
				break;	// found empty cell or the other color, no need to continue searching in this direction 
			}
		}
		for (int i=spot[0]+1; i<=5; i++){		// second check above 
			if (model.getBoard().getSlot(i,spot[1]).equals(thisColor) || (isPretend && model.getBoard().getSlot(i,spot[1]).equals(" "))) {
				rowLength++;
				model.getBoard().setWinCoords(new int[] {model.getBoard().getWinCoords()[0],model.getBoard().getWinCoords()[1],i,model.getBoard().getWinCoords()[3]});
			} else {
				break;	// found empty cell or the other color, no need to continue searching in this direction 
			}
		}
		if (rowLength<4 || isPretend) model.getBoard().setWinCoords(new int[] {0,0,0,0});
		return rowLength;
	}
	
	// Returns the size of the diagonal row (up/left to down/right of chips that
	// exists (or would hypothetically be created) when looking at a specific chip slot on the board
	public int checkDiag1(int[] spot, String thisColor, boolean isPretend){		// check diagonally, up/left to down/right	
		int rowLength=1;	// the chip just dropped (or hypothetical chip) counts as a row of '1'
		model.getBoard().setWinCoords(new int[] {spot[0],spot[1],spot[0],spot[1]});
		int i = spot[0]+1;	// look at row above
		int j = spot[1]-1;	// and column to left
		while (i<=5 && j>=0) {
			if (model.getBoard().getSlot(i,j).equals(thisColor) || (isPretend && model.getBoard().getSlot(i,j).equals(" "))) {
				rowLength++;
				model.getBoard().setWinCoords(new int[] {i,j,model.getBoard().getWinCoords()[2],model.getBoard().getWinCoords()[3]});
			} else {
				break;	// found empty cell or the other color, no need to continue searching in this direction 
			}
			i++;
			j--;
		}
		i = spot[0]-1;	// look at row below
		j = spot[1]+1;	// and column to right
		while (i>=0 && j<=6) {
			if (model.getBoard().getSlot(i,j).equals(thisColor) || (isPretend && model.getBoard().getSlot(i,j).equals(" "))) {
				rowLength++;
				model.getBoard().setWinCoords(new int[] {model.getBoard().getWinCoords()[0],model.getBoard().getWinCoords()[1],i,j});
			} else {
				break;	// found empty cell or the other color, no need to continue searching in this direction 
			}
			i--;
			j++;
		}
		if (rowLength<4 || isPretend) model.getBoard().setWinCoords(new int[] {0,0,0,0});
		return rowLength;
	}
	
	// Returns the size of the diagonal row (down/left to up/right of chips that
	// exists (or would hypothetically be created) when looking at a specific chip slot on the board
	public int checkDiag2(int[] spot, String thisColor, boolean isPretend){		// check diagonally, down/left to up/right	
		int rowLength=1;	// the chip just dropped (or hypothetical chip) counts as a row of '1'
		model.getBoard().setWinCoords(new int[] {spot[0],spot[1],spot[0],spot[1]});
		int i = spot[0]-1;	// look at row below
		int j = spot[1]-1;	// and column to left
		while (i>=0 && j>=0) {
			if (model.getBoard().getSlot(i,j).equals(thisColor) || (isPretend && model.getBoard().getSlot(i,j).equals(" "))) {
				rowLength++;
				model.getBoard().setWinCoords(new int[] {i,j,model.getBoard().getWinCoords()[2],model.getBoard().getWinCoords()[3]});
			} else {
				break;	// found empty cell or the other color, no need to continue searching in this direction 
			}
			i--;
			j--;
		}
		i = spot[0]+1;	// look at row above
		j = spot[1]+1;	// and column to right
		while (i<=5 && j<=6) {
			if (model.getBoard().getSlot(i,j).equals(thisColor) || (isPretend && model.getBoard().getSlot(i,j).equals(" "))) {
				rowLength++;
				model.getBoard().setWinCoords(new int[] {model.getBoard().getWinCoords()[0],model.getBoard().getWinCoords()[1],i,j});
			} else {
				break;	// found empty cell or the other color, no need to continue searching in this direction 
			}
			i++;
			j++;
		}
		if (rowLength<4 || isPretend) model.getBoard().setWinCoords(new int[] {0,0,0,0});
		return rowLength;
	}
	
	/*---------------------------------------------------------------------------------------------
	 *  The AI methods begin here. computerMove() simply creates a delay so that the computer 
	 *  player doesn't play too quickly (unnaturally).  Next, getComputerChoice() is called and
	 *  this method contains a hierarchy of possibilities the computer uses to decide where
	 *  to drop a chip.  For example, the highest ranked possibility is that the computer has a
	 *  move which wins the game immediately.  So this possibility will be checked for first and,
	 *  if found, none of the other possibilities will be searched.   
	 *---------------------------------------------------------------------------------------------*/
	public int computerMove(){			// determine the computer's move
		view.printComputerDelay();		// add a delay to make the game flow more natural
		return getComputerChoice();		// get the move here
	}
	
	public int getComputerChoice(){		// this is the AI decision making process
		int columnChosen = 0;
		columnChosen = lookForMoveThatConnects4(model.getComputer().getColor());	// First, look for a winning move
		if (columnChosen>0) return columnChosen;
		columnChosen = lookForMoveThatConnects4(model.getHuman().getColor());		// Next, look for a winning move for opponent, and block
		if (columnChosen>0) return columnChosen;
		columnChosen = lookForMoveThatConnects3(model.getComputer().getColor());	// Next, see if computer has two in a row plus an open spot to one side (for +difficulty also look for an open spot after that for the potential winning move)
		if (columnChosen>0) return columnChosen;
		columnChosen = lookForMoveThatConnects3(model.getHuman().getColor());		// Next, check the same for human, and play blocking move
		if (columnChosen>0) return columnChosen;
		columnChosen = lookForMoveThatConnects2(model.getComputer().getColor());	// Next, see if computer has one in a row plus an open spot to one side (for +difficulty also look for two open spots after that for the potential winning move)
		if (columnChosen>0) return columnChosen;
		columnChosen = lookForMoveThatConnects2(model.getHuman().getColor());		// Next, check the same for human, and play blocking move
		if (columnChosen>0) return columnChosen;
		columnChosen = lookForMoveThatConnects1(model.getComputer().getColor());	// Next, see if computer has a spot where a new row of four can be started (for +difficulty and later connected into a row of 4)
		if (columnChosen>0) return columnChosen;
		return pickRandomEmptyColumn();			// if all else fails we will choose a random empty column
	}
	
	// method looks for any play that results in a connection of four chips (i.e. a move that wins the game)
	public int lookForMoveThatConnects4(String color){
		int[] spotToCheck = new int[2];
		int[] randCol = randomColumns();		// creates an integer array with '1-7' in random order
		for (int i=1; i<=7; i++){
			spotToCheck[0] = getLowRow(randCol[i-1]);		// set row coordinate to evaluate
			if (spotToCheck[0] >=0) {			// skip checking if the column is full
				spotToCheck[1] = randCol[i-1]-1;			// set column coordinate to evaluate
				if (checkHorizontal(spotToCheck,color,false) >= 4) return randCol[i-1];
				if (checkVertical(spotToCheck,color,false) >= 4) return randCol[i-1];		// the 'false' parameter indicates we are looking for a real connect 4 based on the next move for this color chip in this spot, not a hypothetical one that may occur down the road
				if (checkDiag1(spotToCheck,color,false) >= 4) return randCol[i-1];
				if (checkDiag2(spotToCheck,color,false) >= 4) return randCol[i-1];
			}
		}
		return 0;
	}
	
	// method looks for any play that results in a connection of three chips
	public int lookForMoveThatConnects3(String color){
		int[] spotToCheck = new int[2];
		int[] randCol = randomColumns();		// creates an integer array with '1-7' in random order
		for (int i=1; i<=7; i++){
			spotToCheck[0] = getLowRow(randCol[i-1]);		// set row coordinate to evaluate
			if (spotToCheck[0] >=0) {			// skip checking if the column is full
				spotToCheck[1] = randCol[i-1]-1;			// set column coordinate to evaluate
				// in these next 4 lines, the additional advanced checks only execute for greater difficulty levels (>1)
				if (checkHorizontal(spotToCheck,color,false) >= 3 && (difficulty==1 || !wins4NextPlayer(spotToCheck,model.getComputer().getColor())) && (difficulty==1 || connect4IsPossible(spotToCheck,model.getComputer().getColor()))) return randCol[i-1];
				if (checkVertical(spotToCheck,color,false) >= 3 && (difficulty==1 || !wins4NextPlayer(spotToCheck,model.getComputer().getColor())) && (difficulty==1 || connect4IsPossible(spotToCheck,model.getComputer().getColor()))) return randCol[i-1];
				if (checkDiag1(spotToCheck,color,false) >= 3 && (difficulty==1 || !wins4NextPlayer(spotToCheck,model.getComputer().getColor())) && (difficulty==1 || connect4IsPossible(spotToCheck,model.getComputer().getColor()))) return randCol[i-1];
				if (checkDiag2(spotToCheck,color,false) >= 3 && (difficulty==1 || !wins4NextPlayer(spotToCheck,model.getComputer().getColor())) && (difficulty==1 || connect4IsPossible(spotToCheck,model.getComputer().getColor()))) return randCol[i-1];
			}
		}		
		return 0;
	}
	
	// method checks for a possible connection of 2 chips for a given color (parameter)
	public int lookForMoveThatConnects2(String color){
		int[] spotToCheck = new int[2];
		int[] randCol = randomColumns();		// creates an integer array with '1-7' in random order
		for (int i=1; i<=7; i++){
			spotToCheck[0] = getLowRow(randCol[i-1]);		// set row coordinate to evaluate
			if (spotToCheck[0] >=0) {			// skip checking if the column is full
				spotToCheck[1] = randCol[i-1]-1;			// set column coordinate to evaluate
				if (checkHorizontal(spotToCheck,color,false) >= 2 && (difficulty==1 || !wins4NextPlayer(spotToCheck,model.getComputer().getColor())) && (difficulty==1 || connect4IsPossible(spotToCheck,model.getComputer().getColor()))) return randCol[i-1];
				if (checkVertical(spotToCheck,color,false) >= 2 && (difficulty==1 || !wins4NextPlayer(spotToCheck,model.getComputer().getColor())) && (difficulty==1 || connect4IsPossible(spotToCheck,model.getComputer().getColor()))) return randCol[i-1];
				if (checkDiag1(spotToCheck,color,false) >= 2 && (difficulty==1 || !wins4NextPlayer(spotToCheck,model.getComputer().getColor())) && (difficulty==1 || connect4IsPossible(spotToCheck,model.getComputer().getColor()))) return randCol[i-1];
				if (checkDiag2(spotToCheck,color,false) >= 2 && (difficulty==1 || !wins4NextPlayer(spotToCheck,model.getComputer().getColor())) && (difficulty==1 || connect4IsPossible(spotToCheck,model.getComputer().getColor()))) return randCol[i-1];
			}
		}
		return 0;
	}
	
	public int lookForMoveThatConnects1(String color){
		// we don't do much here, but if the difficulty level is > 1 we find the first column that can be
		// played without presenting a winning opportunity for the opponent
		int[] spotToCheck = new int[2];
		if (difficulty>0) {
			int[] randCol = randomColumns();		// creates an integer array with '1-7' in random order
			for (int i=1; i<=7; i++){				// loop through the columns
				spotToCheck[0] = getLowRow(randCol[i-1]);		// set row coordinate to evaluate
				if (spotToCheck[0] >=0) {						// skip checking if the column is full
					spotToCheck[1] = randCol[i-1]-1;			// set column coordinate to evaluate
					if (!wins4NextPlayer(spotToCheck,model.getComputer().getColor()) && connect4IsPossible(spotToCheck,model.getComputer().getColor())) return randCol[i-1];
				}
			}
		}
		return 0;
	}
	
	// helps the AI to look ahead and see if a proposed move presents the opponent with a winning move
	// returns true if opponent would have an immediate winning move, otherwise returns false
	public boolean wins4NextPlayer(int[] proposedMove, String color){
		boolean humanWillWin = false;
		// Step #1 - drop the computer's chip in the contemplated spot
		model.getBoard().setSlot(proposedMove[0], proposedMove[1], color);
		// Step #2 - evaluate the board for a winning position for the opponent  
		if (lookForMoveThatConnects4(model.getHuman().getColor()) > 0) {
			// indicates that the contemplated move presents a winning opportunity for the next player 
			humanWillWin = true;
		}
		// Step #3 - remove the computer's chip from the board that was inserted in step #1
		model.getBoard().setSlot(proposedMove[0], proposedMove[1], " ");
		return humanWillWin;
	}
	
	// method helps AI to look ahead and see if a proposed move spot can be made into a connect4
	// at a later time in the game.  Returns true if it can, otherwise returns false
	// Example: playing a move the makes a connection of '3' isn't valuable unless that move is 
	// contributing to future connect 4.
	public boolean connect4IsPossible(int[] proposedMove, String color){
		boolean connect4Possible = false;
		// Step #1 - drop the computer's chip in the contemplated spot
		model.getBoard().setSlot(proposedMove[0], proposedMove[1], color);
		// Step #2 - evaluate this spot on the board for a possible connect 4 at a later time  
		if (checkForWinner(proposedMove, true)) {
			connect4Possible = true;
		}
		// Step #3 - remove the computer's chip from the board that was inserted in step #1
		model.getBoard().setSlot(proposedMove[0], proposedMove[1], " ");
		return connect4Possible;
	}	
}



