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

import game.GameBoard;
import game.TicTacToe;
import game.buttons.GameTile;
import game.buttons.GameTile.Symbol;


public abstract class Player
{
    /***************************************************************************
     *      VARIABLES
     **************************************************************************/
    
    protected TicTacToe game;
    
    private PlayerType pType;
    
    private Symbol symbol;
    private int turn;
    
    private float[] times = new float[6];
    

    /***************************************************************************
     *      CONSTRUCTOR
     **************************************************************************/
    
    public Player(TicTacToe game)
    {
        this.game = game;
    }
    
    /***************************************************************************
     *      SETTERS/GETTERS
     **************************************************************************/
    
    public void setType(PlayerType type)
    {
        this.pType = type;
    }
    
    public PlayerType getType()
    {
        return pType;
    }
    
    public void setSymbol(Symbol symbol)
    {
        this.symbol = symbol;
    }
    
    public Symbol getSymbol()
    {
        return symbol;
    }
    
    public int getTurn()
    {
        return turn;
    }
    
    public float[] getTime()
    {
    	return times;
    }
    
    /***************************************************************************
     *      METHODS
     **************************************************************************/

    public void incTurn()
    {
        this.turn += 1;
    }
    
    public void decTurn()
    {
        if (this.turn > 0)
        {
            this.turn -= 1;
        }
    }
    
    public void resetTurn()
    {
        this.turn = 0;
    }
    
    public Symbol opponentSymbol()
    {
        if (this.symbol != null)
        {
            if (this.symbol == Symbol.EX)
            {
                return Symbol.OH;
            }
            else if (this.symbol == Symbol.OH)
            {
                return Symbol.EX;
            }
        }
        return null;
    }
    
    public Boolean isWinner()
    {
        //Boolean Variable
        boolean winner = false;
        
        Symbol s = getSymbol();
        
        GameBoard board = game.getBoard();
        
        int row1[]  = {0, 1, 2};
        int row2[]  = {3, 4, 5};
        int row3[]  = {6, 7, 8};
        
        int col1[]  = {0, 3, 6};
        int col2[]  = {1, 4, 7};
        int col3[]  = {2, 5, 8};
        
        int diag1[] = {0, 4, 8};
        int diag2[] = {2, 4, 6};
        
        int[] wins[] = {row1, row2, row3, col1, col2, col3, diag1, diag2};
        
        for (int[] win : wins)
        {
            winner = true;
            for (int tile : win)
            {
                winner &= board.getTile(tile).getTileSymbol() == s;
            }
            if (winner == true) 
            {
                return winner;
            }
        }
        return winner;
    }
    
    public abstract void takeTurn();
    
    public void addTime(float time)
    {
    	times[this.getTurn()] = time;
    }
    
    public float average()
    {
    	float sum = 0;
        for (int i = 0; i < times.length; i++) 
        {
        	if (times[i] == 0) break;
            sum += times[i];
        }
        
        return sum/game.getPlayer1().getTurn();
    }
    
    
    public void randomTurn()
    {
    	int random = (int)(Math.random() * 9);
    	GameTile tile = game.getBoard().getTile(random);
    	if (tile.isEmpty())
    	{
    		if (game.getSoundsOn()) { game.click.trigger(); }
            tile.setTileSymbol(game.getCurrentPlayer().getSymbol());
    	}
    	else
    	{
    		randomTurn();
    	}
    	
    }
    
    public void clearTurns()
    {
        for (int i = 0; i < times.length; i++) 
        {
        	times[i] = 0;
        }
        
    }
    /***************************************************************************
     *      ENUMERATOR
     **************************************************************************/

    public enum PlayerType
    {
        HUMAN("Human"),
        COMPUTER("Computer");
        
        private String label;
        
        public String getLabel()
        {
            return this.label;
        }
        
        private PlayerType(String label)
        {
            this.label = label;
        }
    }

	
}
