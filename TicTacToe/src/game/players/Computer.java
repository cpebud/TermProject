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
    private ArrayList<Integer> scores = new ArrayList<Integer>();
    private ArrayList<Integer> possMoves = new ArrayList<Integer>();
    int theDepth = 0;


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
        	
        	if(getTurn() == 0)
        		hardEasyTurn();
        	else
        	{
        		
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
        			
        	}
        	
		
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
    
    public boolean isGameTied(TicTacToe temp)
    {
    	
    	return (temp.getBoard().isFull()
    		&& !temp.getBoard().isWinner(Symbol.EX)
    		&& !temp.getBoard().isWinner(Symbol.OH));
	}
    
    public boolean isGameOver(TicTacToe game)
    {
    	
    	
    	return(game.getBoard().isFull());
    }
    
    public int chooseSpace(TicTacToe aGame)
    {
    	return calcBestMove(aGame,0, new HashMap<>());
    }
    
    private int calcBestMove(TicTacToe aGame, int depth, Map<Integer,Integer> potentialOutcomes)
    {
    	if(isGameTied(aGame))
    		return 0;
    	else if(aGame.getBoard().isWinner(Symbol.EX) || aGame.getBoard().isWinner(Symbol.OH))
    		return -1;
    	else
    	{
    		for(int space = 0; space < 9; space++)
    		{
    			if(aGame.getBoard().getTile(space).isEmpty())
    			{  
    				System.out.println("open space:" + space);
    				aGame.getBoard().getTile(space).setTileSymbol(aGame.getCurrentPlayer().getSymbol());
    				aGame.nextPlayer();
        			potentialOutcomes.put(space, (-1 * calcBestMove(aGame, depth + 1, new HashMap<>())));
        			aGame.getBoard().resetSpace(space);  
        			aGame.nextPlayer();
        		}    			
    		}
    		
			System.out.println("depth: " + depth + " bestMove: " + potentialOutcomes.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey()) ;
    		if(depth == 0 || aGame.getBoard().isWinner(aGame.player2.getSymbol()))
    		{
    			return potentialOutcomes.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();

    		}
    		else
    		{
    			return potentialOutcomes.entrySet().stream().min(Map.Entry.comparingByValue()).get().getKey();

    		}
 
    	}
    }
    

    
}
