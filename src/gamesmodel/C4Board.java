package gamesmodel;

import java.util.Arrays;

public class C4Board {
	private String[][] slots;
	private boolean gameOver;
	private int[] winCoords;	// the coordinates of the winning 'connect 4'
	
	public C4Board(){					// constructor
		this.slots = new String[6][7];		// creates an array of slots (6 rows by 7 columns)
		this.winCoords = new int[4];
	}
	
	public void setSlots(String stuffer) {
		for (String[] row: slots)
		    Arrays.fill(row, stuffer);	// stuffs ALL elements in slots array with a String which is passed in as a parameter
	}
	
	public String[][] getSlots(){
		return this.slots;				// GETS the entire slots array
	}
	
	public void setSlot(int row, int column, String stuffer) {
		slots[row][column] = stuffer;	// stuff the passed in element with the passed in String
	}
	
	public String getSlot(int row, int column){
		return this.slots[row][column];				// GETS a single element (slot)
	}
	
	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public int[] getWinCoords() {
		return winCoords;
	}

	public void setWinCoords(int[] winCoords) {
		this.winCoords = winCoords;
	}
	
	// indicates if a given row/col (i.e. chip location is part of the winning 'Connect 4'
	public boolean isWinningSlot(int i, int j) {
		if (gameOver) {
			
			//if ((i==winCoords[0] && j==winCoords[1]) || (i==winCoords[2] && j==winCoords[3])) {
			//	return true;
			//}
			if (winCoords[0]==winCoords[2]) {		// winning connection is horizontal
				if (i==winCoords[0] && j>=winCoords[1] && j<=winCoords[3]){
					return true;
				}
			} else if (winCoords[1]==winCoords[3]) {		// winning connection is vertical
				if (j==winCoords[1] && i>=winCoords[0] && i<=winCoords[2]){
					return true;
				}
			} else if (winCoords[0]<winCoords[2]) {		// winning connection is diag (up/right)
				int m=winCoords[0];
				int n=winCoords[1];
				for (int k=1; k<=4; k++) {
					if (i==m && j==n){
						return true;
					}
					m += 1;
					n += 1;
				}
			} else {		// winning connection is diag (down/right)
				int m=winCoords[0];
				int n=winCoords[1];
				for (int k=1; k<=4; k++) {
					if (i==m && j==n){
						return true;
					}
					m -= 1;
					n += 1;
				}
			}
		}
		return false;
	}

}
