package hangman;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class HangmanModel {

	///	*********** 		word List **********************
	public 	ArrayList<String> wordList 			= new ArrayList<String>();			// All Words
	public	ArrayList<String> easyWord			= new ArrayList<String>();			// 1 to 4 letter words
	public	ArrayList<String> medWord 			= new ArrayList<String>();			// 4 to 8 letter words
	public	ArrayList<String> hardWord			= new ArrayList<String>();			// 8 to 12 letter word
	public	ArrayList<String> extraHardWord 	= new ArrayList<String>();			// 12 or more letter words
	private ArrayList<Character> guessList		= new ArrayList<Character>();		// List guesses
	private ArrayList<String> list;													// List to be assign what word list to use
	///***********************	END		*************************************

	/// ***********		support variables	**********************
	private char guess = ' ';														// last guess
	private String wordPlay;														// single word from list
	private int listSelect;															// input VAR to select what list to play, used in switch
	private String letterList;
	///***********************	END		*************************************

	//// ***********		Counters	*************************************
	private int gameCounter = 0;													// counter for wrong guesses
	private int correctCount = 0;													// counter for correct guesses
	private int livesCounter = 0;													// counter for number of lives used (Unused)
	///***********************	END		*************************************


	/*public void sortLength(){														// sort by length
		Comparator<String> x = new Comparator<String>(){
			public int compare(String o1, String o2){
				if(o1.length() < o2.length())				// < ascending order
					return -1;								// > descending order
				if(o2.length() < o1.length()){
					return 1;}
				return 0;
			}
		};
		Collections.sort(wordList,x);
	}
	public void sortABC(){															// sort alphabetically
		Collections.sort(wordList);					
	}
	public void shuffleList(){														// shuffle word list
		Collections.shuffle(wordList);				
	}*/


	/// ***********  Getters and Setters  **********************
	public ArrayList<Character> getGuessList() {
		return guessList;
	}
	public void setGuessList(ArrayList<Character> guessList) {
		this.guessList = guessList;
	}

	public ArrayList<String> List() {
		return list;
	}
	public void setList(ArrayList<String> list) {
		this.list = list;
	}

	public char getGuess() {
		return guess;
	}
	public void setGuess(char chIn) {
		this.guess = chIn;
	}

	public String getWordPlay() {
		return wordPlay;
	}
	public void setWordPlay(String wordPlay) {
		this.wordPlay = wordPlay;
	}

	public int getGameCounter() {
		return gameCounter;
	}
	public void setGameCounter(int gameCounter) {
		this.gameCounter = gameCounter;
	}

	public int getCorrectCount() {
		return correctCount;
	}
	public void setCorrectCount(int correctCount) {
		this.correctCount = correctCount;
	}

	public int getListSelect() {
		return listSelect;
	}
	public void setListSelect(int listSelect) {
		this.listSelect = listSelect;
	}

	public int getLivesCounter() {
		return livesCounter;
	}
	public void setLivesCounter(int livesCounter) {
		this.livesCounter = livesCounter;
	}
	
	public String getLetterList() {
		return letterList;
	}
	public void setLetterList(String letterList) {
		this.letterList = letterList;
	}
	///***********************	END		*************************************

	
}// close class