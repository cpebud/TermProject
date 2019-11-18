/*******************************************************************************
 * File Name:			
 * Project:			
 * 
 * Designer(s):		Garrett Cross,
 * 					Omar Kermiche,
 * 					Autumn Nguyen,
 * 					Thomas Pridy
 * 
 * Copyright Â© 2019. All rights reserved.
 ******************************************************************************/
package game.players;

import java.util.*;

import game.GameBoard;
import game.TicTacToe;
import game.TicTacToe.Difficulty;
import game.buttons.GameTile.Symbol;
import game.screens.MenuScreen.Menu;
import game.screens.Screen.ScreenType;



public class Computer extends Player
{    
    /***************************************************************************
     *      VARIABLES
     **************************************************************************/
    
    private Difficulty difficulty;
   
    int theDepth = 0;
    int nextMove = -1;
    Boolean nextMoveWins = false;

    /***************************************************************************
     *      CONSTRUCTOR
     **************************************************************************/
    
    public Computer(TicTacToe game)
    {
        super(game);
        
        setType(PlayerType.COMPUTER);

        this.difficulty = game.getDifficulty();
    }
    
    /***************************************************************************
     *      SETTERS/GETTERS
     **************************************************************************/
    
    private void setDifficulty(Difficulty difficulty)
    {
        this.difficulty = difficulty;
    }
    
   
    
    /***************************************************************************
     *      METHODS
     **************************************************************************/
    
    public void updateDifficulty()
    {
        setDifficulty(game.getDifficulty());
    }
    
    
    @Override
    public void takeTurn()
    {
        game.delay(1500);
        switch(difficulty)
        {
        case EASY:
            easyTurn();
            break;
        case MEDIUM:
            break;
        case HARD:
        	
        		int ultimateScore = minimax(this.game,0);
        		
        		if(ultimateScore != 0 && nextMove != -1)
        		{
        			
        			game.getBoard().getTile(nextMove).setTileSymbol(game.player2.getSymbol());
        		}
        		
        		/*
        		game.switchTokens();
        		int bestMoveBlock = chooseSpace(game);
        		game.switchTokens();
        		
        		int bestMoveWin = chooseSpace(game);
        		
        		
        		game.getBoard().getTile(bestMoveWin).setTileSymbol(getSymbol());

        		//computer tries to win but if the move doesnt end up in a dub for pc then it
        		//plays defense
        		if(!game.player2.isWinner())
        		{
        			game.getBoard().resetSpace(bestMoveWin);
        			
        			game.getBoard().getTile(bestMoveBlock).setTileSymbol(getSymbol());
        		}
        			
        		*/
        	
		
            break;
                      
        }
        incTurn();
        
        if (!isWinner() && !game.getBoard().isFull()) 
        {
            game.nextPlayer();
        }
        else
        {
            game.changeScreen(ScreenType.WIN, Menu.MAIN);
        }
       
    }
    
    private void easyTurn()
    {
        GameBoard board = game.getBoard();
        if (!board.isFull())
        {
            int random = (int)(Math.random() * GameBoard.NUM_TILES);
            while (!board.getTile(random).isEmpty())
            {
                random = (int)(Math.random() * GameBoard.NUM_TILES);
            }
            board.getTile(random).setTileSymbol(getSymbol());
        }
    }
    
    public void hardEasyTurn()
    {
    	 if (!game.getBoard().isFull())
         {
             int random = (int)(Math.random() * GameBoard.NUM_TILES);
             while (!game.getBoard().getTile(random).isEmpty())
             {
                 random = (int)(Math.random() * GameBoard.NUM_TILES);
             }
             game.getBoard().getTile(random).setTileSymbol(getSymbol());
         }
    }
    
   
    
    public boolean isGameOver(TicTacToe game)
    {
    	
    	
    	return(game.getBoard().isFull());
    	
    	
    	
    }
    
    int score(TicTacToe game, int depth)
    {
    	if(game.player2.isWinner())
    		return 10 - depth;
    	else if(game.player1.isWinner())
    		return depth - 10;
    	else 
    		return 0;
    }
    	
    
    int minimax(TicTacToe game, int depth)
    {
    	if(game.getCurrentPlayer().isWinner() || game.getBoard().isFull())
    	{
    		return score(game, depth);
    	}
    		
    	
    	depth += 1;
    	
    	 ArrayList<Integer> scores = new ArrayList<Integer>();
    	 ArrayList<Integer> moves = new ArrayList<Integer>();

    	TicTacToe possibleGame = game;
    	
    	for(int i = 0; i < 9; i++)
    	{    		
    		if(game.getBoard().getTile(i).isEmpty())
    		{
    			possibleGame.getNewState(possibleGame, i);
    			 
    			if(game.getCurrentPlayer().isWinner())
    				nextMoveWins = true;
    			
    			scores.add(minimax(possibleGame, depth));
    			
    			moves.add(i);
    			
    			
    			possibleGame.getBoard().resetSpace(i); 	
    			
    		}    	
    	}
    	
    	if(nextMoveWins)
    	{
    		int max_score_index = 0;
    		
    		int maxScore = 0;
    		
    		for(int i = 0; i < scores.size(); i++)
    		{
    			System.out.println(scores.get(i));
    			if(scores.get(i) > maxScore)
    			{
    				maxScore = scores.get(i);
        			max_score_index = i;
    			}
    				
    		}
    		
    		nextMove = moves.get(max_score_index);
    		
    		
    		System.out.println("try to win");
    		return scores.get(max_score_index);
    	}
    	else
    	{
    		int min_score_index = 0;
    		int minScore = 0;
    		    		
    		for(int i = 0; i < scores.size(); i++)
    		{
    			if(scores.get(i) < minScore)
    			{
    				minScore = scores.get(i);
        			min_score_index = i;
    			}
    				
    		}
    		nextMove = moves.get(min_score_index);
    		
    		System.out.println("try to block");
    		return scores.get(min_score_index);
    	}
    	
    	
    	
	}
} 
    
    /*           
    public int chooseSpace(TicTacToe aGame)
    {
    	return minimax(aGame,0, new HashMap<>());
    }
   
private int minimax(TicTacToe aGame, int depth, Map<Integer,Integer> potentialOutcomes)
    {
    	if(catsGame(aGame))
    		//end recursion if the game is tied
    		return 0;
    	else if(aGame.getBoard().isWinner(Symbol.EX) || aGame.getBoard().isWinner(Symbol.OH))
    		//end recursion if here's a winner
    		return -1;
    	else
    	{
    		for(int space =  0; space < 9; space++)
    		{
    			//will try a hypthetical move only if the tile is empty
    			if(aGame.getBoard().getTile(space).isEmpty())
    			{  
    				//try the hypothetical move
    				aGame.getBoard().getTile(space).setTileSymbol(aGame.getCurrentPlayer().getSymbol());
    				//switch tokens so computer can calculate hypothetical opponent move
    				aGame.nextPlayer();
        			potentialOutcomes.put(space, (-1 * minimax(aGame, depth + 1, new HashMap<>())));
        			aGame.getBoard().resetSpace(space);  
        			aGame.nextPlayer();
        		}    			
    		}
    		
    		if(depth == 0 || aGame.getBoard().isWinner(aGame.player2.getSymbol()))
    		{
    			//if a move was found at the top layer of depth or the computer has won the hypthetical game, find the move using the map that
    			//maximizes the computer's chances of winning
    			return potentialOutcomes.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();

    		}
    		else
    		{
    			//minimize opponent's chances
    			return potentialOutcomes.entrySet().stream().min(Map.Entry.comparingByValue()).get().getKey();

    		}
 
    	}
    } 
*/


