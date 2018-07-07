package maxconnect;

import java.io.*;
import java.lang.*;
import java.util.Scanner;

public class maxconnect4 {

	public static void main(String[] args) 
	{
	// check for the correct number of arguments
		if( args.length != 4 ) 
		{
			System.out.println("Four command-line arguments are needed:\n"
				+ "Usage: java [program name] interactive [input_file] [computer-next / human-next] [depth]\n"
				+ " or:  java [program name] one-move [input_file] [output_file] [depth]\n");

			exit_function( 0 );
		}

	// parse the input arguments
	String game_mode = args[0].toString();				// the game mode
	String input = args[1].toString();					// the input game file
	int depthLevel = Integer.parseInt( args[3] );  		// the depth level of the ai search

	//  variables to keep up with the game
	int playerCol = 99;				//  the players choice of column to play
	boolean playMade = false;			//  set to true once a play has been made

	if( game_mode.equalsIgnoreCase( "interactive" ) ) 
	{
		//System.out.println("interactive mode is currently not implemented\n");
		String nextOne = args[2].toString();				// the output game file
		boolean isAiTurn = false;			//  set to true once a play has been made

		File f = new File(input);
		if(!(f.exists())) { 
			    // he input file does not exist, the program should just create an empty board state and start again from there.
			try {
				PrintWriter writer = new PrintWriter("input.txt", "UTF-8");
				writer.println("0000000");
				writer.println("0000000");
				writer.println("0000000");
				writer.println("0000000");
				writer.println("0000000");
				writer.println("0000000");
				writer.println("1");
				writer.close();
				
				GameBoard currentGame1 = new GameBoard( "input.txt" ); // assigning the game board instance of the created file 
				AiPlayer calculon = new AiPlayer();
				System.out.print("\nMaxConnect-4 game - INTERACTIVE MODE\n"); //the 
				System.out.print("game state before move:\n");
				currentGame1.printGameBoard();
				System.out.println( "Score: Player 1 = " + currentGame1.getScore( 1 ) +", Player2 = " + currentGame1.getScore( 2 ) + "\n " );

				if(nextOne.equalsIgnoreCase("computer-next")) {			//specified as computer next or human next. filter here
					isAiTurn=true;

				} else if(nextOne.equalsIgnoreCase("human-next")) {
					isAiTurn=false;	   
				}
				try {
					while(currentGame1.getPieceCount()<42) {			//game should be completed otherwise loop

						if(isAiTurn) {
							currentGame1.setAiTurn(true);			// Ai turn is true
							playerCol = calculon.findBestPlay(currentGame1,depthLevel );	//send to AiPlayer and retreive the column
							computerNext(currentGame1, playerCol);		//function for computer
							isAiTurn=false;
						}
						else
						{
							do
							{
								BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
								System.out.print("\nMake A Valid Move : ");		//ask the user to input the column
								playerCol = Integer.parseInt(reader.readLine()) - 1;
							}while(!currentGame1.isValidPlay(playerCol));
							currentGame1.setAiTurn(false);
							humanNext(currentGame1, playerCol);		//function for human
							isAiTurn=true;
						}
					} 
					System.out.println("\nThe Board is Full\n\nGame Over\\n\\n");		//after the loop
					currentGame1.printGameBoard();		//print board
					System.out.println( " FINAL SCORE: Player 1 = " + currentGame1.getScore( 1 ) +", Player2 = " + currentGame1.getScore( 2 ) + "\n " );	//print score
					System.out.println("\n Thank You For Playing\n\n");
					if((currentGame1.getScore(1)) > (currentGame1.getScore(2))) {	//win announcement
						System.out.println("PLAYER 1 WINS");
					}else if((currentGame1.getScore(1)) < (currentGame1.getScore(2))) {
						System.out.println("PLAYER 2 WINS");
					}else {
						System.out.println("IT IS A TIE");
					}								
					playMade = true;
					return;
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (IOException ex) {
			    // Report
			} 
			} else {		//if file exists do this 
				// create and initialize the game board
				GameBoard currentGame = new GameBoard( input );

		// create the Ai Player
				AiPlayer calculon = new AiPlayer();

				if(nextOne.equalsIgnoreCase("computer-next")) {
					isAiTurn=true;

				} else if(nextOne.equalsIgnoreCase("human-next")) {
					isAiTurn=false;	   
				}
				try {
					while(currentGame.getPieceCount()<42) {

						if(isAiTurn) {
							currentGame.setAiTurn(true);
							playerCol = calculon.findBestPlay(currentGame,depthLevel );
							computerNext(currentGame, playerCol);
							isAiTurn=false;
						}
						else
						{
							do
							{
								BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
								System.out.print("\nMake A Valid Move : ");
								playerCol = Integer.parseInt(reader.readLine()) - 1;
							}while(!currentGame.isValidPlay(playerCol));
							currentGame.setAiTurn(false);
							humanNext(currentGame, playerCol);
							isAiTurn=true;
						}
					} 
					System.out.println("\nThe Board is Full\n\nGame Over\\n\\n");
					currentGame.printGameBoard();
					System.out.println( " FINAL SCORE: Player 1 = " + currentGame.getScore( 1 ) +", Player2 = " + currentGame.getScore( 2 ) + "\n " );
					System.out.println("\n Thank You For Playing\n\n");
					if((currentGame.getScore(1)) > (currentGame.getScore(2))) {
						System.out.println("PLAYER 1 WINS");
					}else if((currentGame.getScore(1)) < (currentGame.getScore(2))) {
						System.out.println("PLAYER 2 WINS");
					}else {
						System.out.println("IT IS A TIE");
					}								
					playMade = true;
					return;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		else if( !game_mode.equalsIgnoreCase( "one-move" ) ) 
		{
			System.out.println( "\n" + game_mode + " is an unrecognized game mode \n try again. \n" );
			return;
		}

	/////////////   one-move mode ///////////
	// get the output file name
	String output = args[2].toString();				// the output game file
	// create and initialize the game board
	GameBoard currentGame = new GameBoard( input );

// create the Ai Player
	AiPlayer calculon = new AiPlayer();

	System.out.print("\nMaxConnect-4 game\n");
	System.out.print("game state before move:\n");

	//print the current game board
	currentGame.printGameBoard();
	// print the current scores
	System.out.println( "Score: Player 1 = " + currentGame.getScore( 1 ) +", Player2 = " + currentGame.getScore( 2 ) + "\n " );

	// ****************** this chunk of code makes the computer play
	if( currentGame.getPieceCount() < 42 ) 
	{
		int current_player = currentGame.getCurrentTurn();
		// AI play - random play
		playerCol = calculon.findBestPlay( currentGame, depthLevel );

		// play the piece
		currentGame.playPiece( playerCol );

		// display the current game board
		System.out.println("move " + currentGame.getPieceCount() + ": Player " + current_player+ ", column " + playerCol);
		System.out.print("game state after move:\n");
		currentGame.printGameBoard();

		// print the current scores
		System.out.println( "Score: Player 1 = " + currentGame.getScore( 1 ) +", Player2 = " + currentGame.getScore( 2 ) + "\n " );

		currentGame.printGameBoardToFile( output );
	} 
	else 
	{
		System.out.println("\nI can't play.\n\nThe Board is Full\n\nGame Over");
	}

	//************************** end computer play
	return;

} // end of main()

/**
 * This method is used when to exit the program prematurly.
 * @param value an integer that is returned to the system when the program exits.
 */

public static void computerNext(GameBoard currentGame, int playerCol) {  //computer paly and print the game board
	currentGame.playPiece( playerCol );
	System.out.println("move " + currentGame.getPieceCount() + ": Player " + currentGame.getCurrentTurn()+ ", column " + playerCol);		
	currentGame.printGameBoardToFile( "computer.txt" );
	System.out.print("game state after move:\n");
	currentGame.printGameBoard();
	System.out.println( "Score: Player 1 = " + currentGame.getScore( 1 ) +", Player2 = " + currentGame.getScore( 2 ) + "\n " );
}

public static void humanNext(GameBoard currentGame, int playerCol) {	// human play and print the game board
	currentGame.playPiece( playerCol );
	System.out.println("move " + currentGame.getPieceCount() 
		+ ": Player " + currentGame.getCurrentTurn()
		+ ", column " + playerCol);
	currentGame.printGameBoardToFile( "human.txt" ); 	
	System.out.print("game state after move:\n");
	currentGame.printGameBoard();
	System.out.println( "Score: Player 1 = " + currentGame.getScore( 1 ) +", Player2 = " + currentGame.getScore( 2 ) + "\n " );
}

private static void exit_function( int value )
{
	System.out.println("exiting from MaxConnectFour.java!\n\n");
	System.exit( value );
}

}
