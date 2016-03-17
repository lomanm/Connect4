package hangman;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Random;
import java.util.Scanner;

public class HangmanController {

	public void getwords(HangmanModel model){															// Get words from file, divide to lists
		URL url = HangmanController.class.getClassLoader().getResource("words.csv");
		String filesPathAndName = url.getPath();    	
			
		
		try {
			Scanner filePath = new Scanner(new File(filesPathAndName)).useDelimiter("line.sepatator");
			String word;

			while (filePath.hasNext()){
				word = filePath.nextLine();
				model.wordList.add(word);
				if(word.length()<= 4){
					model.extraHardWord.add(word);
				}else if (word.length() > 4 && word.length() < 8){
					model.hardWord.add(word);
				}else if(word.length() >= 8 && word.length() < 12){
					model.medWord.add(word);
				}else if(word.length() > 12)
					model.easyWord.add(word);
			}

		} catch (FileNotFoundException e) {
			System.err.println("File not Found:");
			e.printStackTrace();
		}
	}

	public String oneWord(HangmanModel model){														// Get one word from list
		int max = model.List().size();
		int min = 1;
		Random ran = new Random();
		int randomNum = ran.nextInt((max - min) + 1) + min;
		model.setWordPlay((model.List()).get(randomNum));
		return model.getWordPlay();
	}


	public int gameChecker(HangmanModel model){														// Check to See if user guess all Letters

		for(int i = 0; i < model.getWordPlay().length(); i++){
			 if(!model.getGuessList().contains(model.getWordPlay().charAt(i))){
				return 1;
			}
			
		}
		return 2;
	}


	public void guessChecker(HangmanModel model){													// Check if last guess is in word, add to counters
		String guess = model.getGuess() + "";

		if(model.getWordPlay().contains(guess)){
			model.setCorrectCount(model.getCorrectCount() + 1);
			System.out.println("right");
			return;

		} else{
			model.setGameCounter(model.getGameCounter() + 1);
			System.out.println("wrong");
			return;
		}
	}


	public void printLetters(HangmanModel model){													// Print guessed letter or __							
		String temp = "";

		for(int i = 0; i < model.getWordPlay().length(); i++){
			if((model.getGuessList()).contains(model.getWordPlay().charAt(i))){
				temp =  temp + " " + model.getWordPlay().charAt(i) + " ";
			}else{
				temp = temp + " __ ";
			}
		}
		model.setLetterList(temp);
	}


}