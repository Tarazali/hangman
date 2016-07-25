package project4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * <p>
 * Title: Hangman - Project 4
 * </p>
 *
 * <p>
 * Description: The Hangman Game
 * </p>
 *
 * <p>
 * Copyright: Copyright © 2015
 * </p>
 *
 * @author Zhangali Pernebayev
 * Based on the work of the previous author - Lynn Robert Carter and Jack Park
 *
 * @version 1.00
 */

public class Hangman {
	private static Scanner keyboard = new Scanner(System.in);	// The scanner for the keyboard


	/**********
	 * This private method plays a single game and returns true or false based on the outcome
	 *
	 * @param theSecretWord		The secret word the player needs to guess
	 * @return					True if the player wins, else false
	 */
	private static boolean thePlayerWonTheGame(String theSecretWord){
		//TODO: Design gameplay based on flowcharts and exercises
		//Make use of System.out.println("...") to help you debug your program

		/**
		 * PLAY THE GAME USING SECRET WORD
		 */
		String input="";
		int counter=0;

		String display="";
		for(int i=0; i<theSecretWord.length(); i++)
			display+='?';

		for(int a=0; a<display.length(); a++){
		while(display.charAt(a)=='?'){
			if(counter<10){
				System.out.println("The secret word is: "+display);
				System.out.println("Inter letter: ");
					String guess=keyboard.nextLine().trim().toUpperCase();

				if(guess.equals(theSecretWord)){
					return true;
				}else
					if(guess.length()==1)
					{

						if(input.indexOf(guess)==-1 ){
							counter++;
							char aChar=' ';
							display=theSecretWord;
							for(int indx=0; indx<display.length(); indx++ ){

								aChar=theSecretWord.charAt(indx);

								if(guess.indexOf(aChar)==-1 && input.indexOf(aChar)==-1){



									display=display.replace(aChar,'?');
								}
							}

							input=input.concat(guess);

						}

						else{
							input=input.concat(guess);

							System.out.println("You can not write the same letter twice");
							System.out.println("Try a different letter!");
						}
					}
					else{
						counter++;
					}

			} else	return false;
		}
		}


		return true;
	}




	/**********
	 * This public method is the mainline that starts up the program and plays the game
	 *
	 * @param args	not used
	 */
	public static void main(String [] args){
		String DO_YOU_WISH_TO_PLAY = 			// Common string used to prompt input
			"Would you like to play? (Enter 'Y' or 'y' and then enter if yes!): ";
		Random random = new Random();	// A random number generator

		String [] secretWords = null;					// This an array of secret words
		//From MainlineFlowchart.pdf: Read the SecretWord list
		// Set up to read the file that contains the secret words based on the name that
		// is passed into the method via theFileName parameter.
		Scanner fileReader = null;
		try {
			// Try opening the file and set up a scanner to read it.  If the file is there,
			// the program will continue to the statement following this try block.
			fileReader = new Scanner(new File("SecretWords.txt"));
		} catch (FileNotFoundException e) {
			// If the file is not there, an exception will be thrown and the program flow
			// will directed here.  An error message is displayed and the program stops.
			System.out.println("The file 'SecretWords.txt' was not found!");
			System.out.println("The program terminates now.");
			System.exit(0);
		}

		// If we get here, the file is there and can be read.  So we use the "nextInt" method
		// to determine how many words to read in.  We then use that value to allocate an
		// array of just the right size
		int numWords = fileReader.nextInt();
		secretWords = new String[numWords];

		// We then use the size to drive a for loop to read in the words and store them into
		// the array, converting the letters to UPPER CASE
		for (int ndx = 0; ndx < numWords; ndx ++)
			secretWords[ndx] = fileReader.next().toUpperCase();

		//From MainlineFlowchart.pdf: set number of games won and lost to 0

		int numberOfGamesWon = 0;
		int numberOfGamesLost = 0;

		/****
		 * Show name of game and
		 * Instruction for the game
		 */




		System.out.print(DO_YOU_WISH_TO_PLAY);
		String answer = keyboard.nextLine().trim();

		/**
		 * The current SecretWord being guessed
		 */
		String currentGoalState = "";

		/**
		 * This is the main program loop.  As long as the user signals "yes", the program will
		 * play another game.  It generates a random number based on the number of the words
		 * in the secret word array and then uses that index to select one of the secret words
		 * to be send to the method to play the game.  Based on whether or not the user won or
		 * lost the game, the won or the lost counter is incremented and we again ask the user
		 * if they want to play another game.
		 */
		while (answer.length() > 0  ) {
			if(answer.charAt(0) == 'Y' || answer.charAt(0) == 'y'){

			currentGoalState = secretWords[random.nextInt(secretWords.length)];




			boolean gameWon = thePlayerWonTheGame(currentGoalState);
			if (gameWon) {
				// The player has won; show the secret word, give congratulations, and count the win
				System.out.println("The secret word was: " + currentGoalState);
				System.out.println();
				System.out.println("Congratulations on winning the game!");
				System.out.println();
				numberOfGamesWon++;
			} else {
				// The player has lost; show the secret word, give say sorry, and count the loss
				System.out.println("The secret word was: " + currentGoalState);
				System.out.println();
				System.out.println("I'm sorry, but you lost!");
				System.out.println();
				numberOfGamesLost++;
			}
			// Prompt the user to see if they wish to play another game
			System.out.print(DO_YOU_WISH_TO_PLAY);
			answer = keyboard.nextLine().trim();


		}
		else{
		System.out.println("Wonns: "+numberOfGamesWon);
        System.out.println("Losts: "+numberOfGamesLost);
		}
		}
	}
}
