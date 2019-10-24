/*******************************************************************************
 * File Name:			
 * Project:			
 * 
 * Designer(s):		Garrett Cross,
 * 					Omar Kermiche,
 * 					Autumn Nguyen,
 * 					Thomas Pridy
 * 
 * Copyright © 2019. All rights reserved.
 ******************************************************************************/
package game.players;

import java.util.*;

import game.GameBoard;
import game.TicTacToe;
import game.TicTacToe.Difficulty;
import game.buttons.GameTile.Symbol;


public class Computer extends Player
{    
    /***************************************************************************
     *      VARIABLES
     **************************************************************************/
    
    private Difficulty difficulty;
    private ArrayList<Integer> scores = new ArrayList<Integer>();
    private ArrayList<Integer> possMoves = new ArrayList<Integer>();



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
    
    private Player setOpposingToken(Player currentPlayer)
    {
    	if(currentPlayer.getSymbol() == Symbol.EX)
    		currentPlayer.setSymbol(Symbol.OH);
    	else if(currentPlayer.getSymbol() == Symbol.OH)
    		currentPlayer.setSymbol(Symbol.EX); 
    	
    	if(currentPlayer.getType() == PlayerType.COMPUTER)
    		currentPlayer.setType(PlayerType.HUMAN);
    	else if(currentPlayer.getType() == PlayerType.HUMAN)
    		currentPlayer.setType(PlayerType.COMPUTER);
    		
    	return currentPlayer;
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
        	if(super.getTurn() >= 2)
        		easyTurn();
        	else
        		minimax(game,0,this);
        		
        		
            break;
        }
        incTurn();
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
    
    public int score(int depth)
    {
    	if(isWinner() && getType() == PlayerType.COMPUTER)
    		return 10 - depth;
    	else if(isWinner() && getType() == PlayerType.HUMAN)
    		return depth - 10;
    	else
    		return 0;    		
    }
    
    public int minimax(TicTacToe game, int currDepth, Player currTurn)
    {
    	if((currDepth > 9) || game.getBoard().isWinner(super.getSymbol()))
    		return score(currDepth);
    	
    	currDepth += 1;
    	
    	//uses recursion to check every possible iteration of moves for each open tile
    	for(int i = 0; i < 9; i++)
    	{
    		if(!game.getBoard().getTile(i).isEmpty())
			{
				TicTacToe possibleGame = game; //create a copy of the current game and test the new move
				possibleGame.getBoard().getTile(i).setTileSymbol(getSymbol());
				scores.add(minimax(possibleGame,currDepth,setOpposingToken(currTurn)));	//switch players and PC tries possible human move
				
				possMoves.add(i);
			}
    	}
    	
    	//calculates maximum possible point move for self(PC) and the minimum possible point move if not self(human)
    	if(currTurn == this)
    	{
    		int maxScoreIndex = Collections.max(scores);
    		
    		//once pc finds best possible move for self make move and display on board
    		game.getBoard().getTile(maxScoreIndex).setTileSymbol(getSymbol());
    		
    		return scores.get(maxScoreIndex);
    	}
    	else
    	{
    		int minScoreIndex = Collections.min(scores);
    		game.getBoard().getTile(minScoreIndex).setTileSymbol(getSymbol());
    		
    		//if not currentTurn then pc picks move that results in least amount of points for human
    		game.getBoard().getTile(minScoreIndex).setTileSymbol(getSymbol());

    		
    		return scores.get(minScoreIndex);
    	}
		
       	
    }
}
