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
    int nextMove = 0;
    int firstMove = 0;
    int immediateBlock = 0;
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
        	
        		//Will make sure that the bot's first move is the center tile unless the human's first move is the center tile,
        		//in which case it is impossible for the bot to get forked.
        		if(getTurn() == 0)
        		{
        			if(game.player1.getSymbol() == Symbol.EX)
         			{
        				
    					if(game.getBoard().getTile(4).isEmpty())
         				{
         					game.getBoard().getTile(4).setTileSymbol(getSymbol());
         					firstMove = 4;
         				}     					
         				else
         					game.getBoard().getTile(0).setTileSymbol(getSymbol());
        				
         				
         			}
         			else
         			{
     					game.getBoard().getTile(4).setTileSymbol(getSymbol());
     					firstMove = 4;         				
     					
         			}
        		}
        		else
        		{
        			if(firstMove == 4 && game.player1.getSymbol() == Symbol.EX && game.getBoard().getTile(4).getTileSymbol()== game.player1.getSymbol())
        			{
        	        		cantGetForkedTurn();
        			}
        			else
        			{
        				int blockFork = -1;
        			
        				if(!checkDiagFork())		
        					blockFork = stopFork();
        				else
        					blockFork = blockDiagFork();
        				        				
        				if(blockFork != -1)
        				{
         					game.getBoard().getTile(blockFork).setTileSymbol(getSymbol());
        				}
        				else
        				{
        	        		cantGetForkedTurn();
        				}
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
    
    public boolean checkDiagFork()
    {
        return (game.getBoard().getTile(0).getTileSymbol() == game.player1.getSymbol() 
        	&&  game.getBoard().getTile(8).getTileSymbol() == game.player1.getSymbol()) ||
     		   (game.getBoard().getTile(2).getTileSymbol() == game.player1.getSymbol() 
     	    &&  game.getBoard().getTile(6).getTileSymbol() == game.player1.getSymbol());
    }
    
    public int blockDiagFork()
    {
    	int row2[] = {1,4,7};
    	
    	int blockDiagFork = -1;

    		if(checkDiagFork())
    		{
    			for(int tile: row2)
    				if(game.getBoard().getTile(tile).getTileSymbol() == Symbol.EMPTY)
    					blockDiagFork = tile;
    		}
	
    	return blockDiagFork;
    }
    
    public int stopFork()
    {
    	int row1[]  = {0, 3, 6};
        int row3[]  = {2, 5, 8};
        int col1[]  = {0, 1, 2};
        int col3[]  = {6, 7, 8};
        
        int stopFork = -1;
        int corners[] = {0,2,6,8};
        
       for(int i = 0; i<4;i++)
       {
    	   int score = 0;
    	   
    	   switch(corners[i])
    	   {
    	   case 0: 
    		   score = scoreRow(row1, score);
    		   score = scoreRow(col1, score);
    		   break;
    	   case 2: 
    		   score = scoreRow(col1, score);
    		   score = scoreRow(row3, score);
    		   break;
    	   case 6:
    		   score = scoreRow(row1, score);
    		   score = scoreRow(col3, score);
    		   break;
    	   case 8:
    		   score = scoreRow(row3, score);
    		   score = scoreRow(col3, score);
    		   break;
    	   }
    	   
    	   if(score == 2 && game.getBoard().getTile(corners[i]).getTileSymbol() == Symbol.EMPTY)
    	   {
    		   stopFork = corners[i];
    		   break;
    	   }
    		   
       }
   
        return stopFork;
        	
    }
    
    public int scoreRow(int[] tileArr, int score)
    {
       for(int tile: tileArr)
	   {
		   if(game.getBoard().getTile(tile).getTileSymbol() == game.player1.getSymbol())
			   score++;
		   else if(game.getBoard().getTile(tile).getTileSymbol() == game.player2.getSymbol())
			   score--;
	   }
       
       return score;
    }
    
    private void cantGetForkedTurn()
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
      
             
    public int chooseSpace(TicTacToe aGame)
    {
    	return minimax(aGame,0, new HashMap<>());
    }
    
    public boolean catsGame(TicTacToe game)
    {
    	
    	return (game.getBoard().isFull()
    		&& !game.getBoard().isWinner(Symbol.EX)
    		&& !game.getBoard().isWinner(Symbol.OH));
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
    
    
}    



