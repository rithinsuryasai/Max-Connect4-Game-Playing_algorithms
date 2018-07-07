package maxconnect;

import java.util.*;

public class AiPlayer 
{

	public AiPlayer( ) 
	{
		

	}


	public int findBestPlay( GameBoard currentGame, int depth ) 
	{
		
	//int currentplayer=0,oppositeplayer=0;
		int playChoice = 0;
	//	 currentplayer = turn;

		playChoice= depth_minimax(currentGame,depth);
		return playChoice;
	}

public int depth_minimax(GameBoard currentGame, int givenDepth) {		//minimax algorithm with a specified depth level

	int current_state[][];
	current_state=currentGame.getGameBoard();
	int final_col=0;
	int turn=currentGame.getCurrentTurn();
		int v = 999999;			// v as infinity
		for (int i = 0; i < 7; i ++) {			//looping all the columns
			if (currentGame.isValidPlay(i)) {	//checking for a valid state
				GameBoard SuccGame = new GameBoard(current_state);		//creating copies
				SuccGame.playPiece(i);
				int max_util = Max_Util(SuccGame, givenDepth, -999999, 999999);		//max utility retreived
				if (v > max_util) {
					final_col = i;
					v = max_util;
				}
			}
		}
	return final_col;		// returning the column values to the maxconnect4 program
}


private int Max_Util(GameBoard succGame, int depth, int i, int j) {		//max util value function:: i,j as alpha and beta
	// TODO Auto-generated method stub
	int current_state[][];
	current_state=succGame.getGameBoard();
	int final_col=0;
	int turn=succGame.getCurrentTurn();
	int v=-999999;			//v as negative infinity

	if((succGame.getPieceCount()<42) && (depth > 0)) {
		for(int p=0; p < 7; p++) {
			if(succGame.isValidPlay(p)) {
				GameBoard SuccPlay =new GameBoard(current_state);
				SuccPlay.playPiece(p);
				int min_util= Min_Util(succGame, depth - 1,i,j);		//passing to min utility
				if(v < min_util) {
					v = min_util;
				}
				if(v >=j) {
					return v;
				}
				if(i < v) {
					i = v;
				}
			}
		}
		return v;			
	}else {
		int ret=eval_Fun(succGame);   //terminal node so call eval function
		return ret;
	}

}

private int Min_Util(GameBoard succGame, int depth, int i, int j) {  //min util value function:: i,j as aplha and beta
	// TODO Auto-generated method stub
	int current_state[][];
	current_state=succGame.getGameBoard();
	int final_col=0;
	int turn=succGame.getCurrentTurn();
	int v=999999;				//v as infinity

	if((succGame.getPieceCount() < 42) && (depth > 0)) {
		for(int p=0; p < 7; p++) {
			if(succGame.isValidPlay(p)) {
				GameBoard SuccPlay =new GameBoard(current_state);
				SuccPlay.playPiece(p);
				int max_util= Max_Util(succGame, depth - 1,i,j);
				if(v > max_util) 
					v = max_util;					
				if(v <= i) 
					return v;					
				if(j > v) 
					j = v;					
			}
		}
		return v;			
	}else {
		int ret=eval_Fun(succGame);
		return ret;
	}

}


private int eval_Fun(GameBoard succGame) {		// eval function clearly in eval_explanation.txt
	int fun_val=0, rival=0, turn=0;
	turn=succGame.getCurrentTurn();

	if (turn == 1) {
		rival =2;
	}else if(turn == 2){
		rival=1;
	}
	if(succGame.getPieceCount()<42) {
		if(((succGame.getScore(turn))-(succGame.getScore(rival)))>0) {
			fun_val=999;
		}else if(((succGame.getScore(turn))-(succGame.getScore(rival))) ==0) {
			fun_val=0;
		} else {
			fun_val=-999;
		}	
	}else if(succGame.getPieceCount() == 42)  {
		int turn_four=(succGame.getScore(turn)) * 1000;
		int turn_three=(succGame.getThrees(turn)) * 500;
		int rival_four=(succGame.getScore(rival)) * 1000;
		int rival_three=(succGame.getThrees(rival)) * 500;
		
		int sum_four= (turn_four) + (turn_three) ;
		int sum_three=(rival_four) + (rival_three);

		
		fun_val=(sum_four) - (sum_three) ;
	}	
	return fun_val;	
}





}
