# Max-Connect4-Game-Playing_algorithms
  

Description
---

This program is to implement an agent that plays the Max-Connect4 game using search.   
*	Figure 1 shows the first few moves of a game.   
*	The game is played on a 6x7 grid, with six rows and seven columns.   
*	There are two players, player A (red) and player B (green).   
*	The two players take turns placing pieces on the board: the red player can only place red pieces, and the green player can only place green pieces.  

  
It is best to think of the board as standing upright. We will assign a number to every row and column, as follows: columns are numbered from left to right, with numbers 1, 2, ..., 7. Rows are numbered from bottom to top, with numbers 1, 2, ..., 6. When a player makes a move, the move is completely determined by specifying the COLUMN where the piece will be placed. If all six positions in that column are occupied, then the move is invalid, and the program rejects it and force the player to make a valid move. In a valid move, once the column is specified, the piece is placed on that column and "falls down", until it reaches the lowest unoccupied position in that column.
  
The game is over when all positions are occupied. Obviously, every complete game consists of 42 moves, and each player makes 21 moves. The score, at the end of the game is determined as follows: consider each quadruple of four consecutive positions on board, either in the horizontal, vertical, or each of the two diagonal directions (from bottom left to top right and from bottom right to top left). The red player gets a point for each such quadruple where all four positions are occupied by red pieces. Similarly, the green player gets a point for each such quadruple where all four positions are occupied by green pieces. The player with the most points wins the game.
  
This program will run in two modes: 
*	An interactive mode, that is best suited for the program playing against a human player, and a one-move mode, where the program reads the current state of the game from an input file, makes a single move, and writes the resulting state to an output file. 
*	The one-move mode can be used to make programs play against each other. Note that THE PROGRAM MAY BE EITHER THE RED OR THE GREEN PLAYER, THAT WILL BE SPECIFIED BY THE STATE, AS SAVED IN THE INPUT FILE.
All time measurements will report CPU time, not total time elapsed. CPU time does not depend on other users of the system, and thus is a meaningful measurement of the efficiency of the implementation.
  
  
  <p align="center"> 
  <img src="https://user-images.githubusercontent.com/11136682/42414355-75ffff34-81f9-11e8-87cc-ac750667a9bc.gif">
  </p>
   
   
### Eval Function
---
The eval function is used to return a utility value when a terminal node occurs or the maximum given depth is reached.  
  
1. If the game is not completed and the depth is reached then the optimal path will be where the currentplayer has greater score than the opponent.  
So, depending upon the difference the weights are given and the value of eval is returned.  
SNIPPET:  
```
if(succGame.getPieceCount()<42) {  
		if(((succGame.getScore(turn))-(succGame.getScore(rival)))>0) {
			fun_val=999;
			}else if(((succGame.getScore(turn))-(succGame.getScore(rival))) ==0) {
				fun_val=0;
			} else {
				fun_val=-999;
			}	
```

2. If the game is completed then the terminal node is reached and the utility value is returned as the sum of the difference between the scores of players multiplied by 1000 and the difference
between the three piece count of players multiplied by 500.  
SNIPPET:
```

if(succGame.getPieceCount() == 42){	
		fun_val=((succGame.getScore(turn)) * 1000)  + 	(succGame.getThrees(turn)) * 500)) - (((succGame.getScore(rival)) * 1000) + (succGame.getThrees(rival)) * 500);
	}	
```
 
### Interactive Mode
---
  
  java maxconnect4 interactive [input_file] [computer-next/human-next] [depth]  
  
  For example:    
      java maxconnect4 interactive input1.txt computer-next 7  
 * Argument interactive specifies that the program runs in interactive mode.
 * Argument [input_file] specifies an input file that contains an initial board state. This way we can start the program from a non-empty board state. If the input file does not exist, the program should just create an empty board state and start again from there.
 * Argument [computer-first/human-first] specifies whether the computer should make the next move or the human.
 * Argument [depth] specifies the number of moves in advance that the computer should consider while searching for its next move. In other words, this argument specifies the depth of the search tree. Essentially, this argument will control the time takes for the computer to make a move.
    
  After reading the input file, the program gets into the following loop:  
  1. If computer-next, goto 2, else goto 5.
  2. Print the current board state and score. If the board is full, exit.
  3. Choose and make the next move.
  4. Save the current board state in a file called computer.txt (in same format as input file).
  5. Print the current board state and score. If the board is full, exit.
  6. Ask the human user to make a move (make sure that the move is valid, otherwise repeat request to the user).
  7. Save the current board state in a file called human.txt (in same format as input file).
  8. Goto 2.
  
    
    
### One-Move Mode
---
  
  java maxconnect4 one-move [input_file] [output_file] [depth]
  
  For example:  
    
  java maxconnect4 one-move red_next.txt green_next.txt 5
    
  * In this case, the program simply makes a single move and terminates. In particular, the program should:
  * Read the input file and initialize the board state and current score, as in interactive mode.
  * Print the current board state and score. If the board is full, exit.
  * Choose and make the next move.
  * Print the current board state and score.
  * Save the current board state to the output file IN EXACTLY THE SAME FORMAT THAT IS USED FOR INPUT FILES.
  * Exit      
### Code Structure
---

The package maxconnect contains 3 java files: AiPlayer.java, Gameboard.java, maxconnect4.java
  
AiPlayer.java: It contains the main algorithm and eval function. This class has the core part of AI play.  
Gameboard.java: This class consists of instance of gameboard and all related functions like getscore, getpiece, playpiece and isValid.  
maxconnect4.java: This contains the main function and all the primary things like selecting the mode and reading the input from command line the parses the arguments to Aiplayer.java for computer version of the play.   


#### HOW TO RUN:
---
1. In the maxconnect package go to 'src' then 'maxconnect' folder.  
2. compile all the files by using the command:: javac *.java  
3. then Copy the "input.txt" file to src  
4. execute the command::   
FOR ONE-MOVE MODE:: Format: java maxconnect/maxconnect4 one-move [input_file] [output_file] [depth]  
(eg: java maxconnect/maxconnect4 one-move input1.txt output.txt 4 ) from the src folder  
FOR INTERACTIVE MODE:: Format: java maxconnect/maxconnect4 interactive [input_file] [computer-next/human-next] [depth]  
give input as a number from range [1-7]:  
(eg: java maxconnect/maxconnect4 interactive input1.txt human-next 4 ) from the src folder  
(eg: java maxconnect/maxconnect4 interactive input1.txt computer-next 4)  



#### Links
---
[Algorithm](http://omega.uta.edu/~gopikrishnav/classes/common/4308_5360/slides/alpha_beta.pdf)  
[Assignment](http://omega.uta.edu/~gopikrishnav/classes/2018/spring/4308_5360/assmts/assmt4.html)
