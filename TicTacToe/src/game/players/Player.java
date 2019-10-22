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
    
    protected void setType(PlayerType type)
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
    
    public Boolean isWinner()
    {
        //Boolean Variable
        boolean winner = false;
        
        Symbol s = getSymbol();
        
        GameBoard board = game.getBoard();
        //Check Row Winners
        if ((board.getTile(0).getTileSymbol() == s) && (board.getTile(1).getTileSymbol() == s) && (board.getTile(2).getTileSymbol() == s))
        {
            winner = true;
        }
        else if ((board.getTile(3).getTileSymbol() == s) && (board.getTile(4).getTileSymbol() == s) && (board.getTile(5).getTileSymbol() == s))
        {
            winner = true;
        }
        else if ((board.getTile(6).getTileSymbol() == s) && (board.getTile(7).getTileSymbol() == s) && (board.getTile(8).getTileSymbol() == s))
        {
            winner = true;
        }
        //Check Column Winners
        else if ((board.getTile(0).getTileSymbol() == s) && (board.getTile(3).getTileSymbol() == s) && (board.getTile(6).getTileSymbol() == s))
        {
            winner = true;
        }
        else if ((board.getTile(1).getTileSymbol() == s) && (board.getTile(4).getTileSymbol() == s) && (board.getTile(7).getTileSymbol() == s))
        {
            winner = true;
        }
        else if ((board.getTile(2).getTileSymbol() == s) && (board.getTile(5).getTileSymbol() == s) && (board.getTile(8).getTileSymbol() == s))
        {
            winner = true;
        }
        //Check Diagonal Winners
        else if ((board.getTile(0).getTileSymbol() == s) && (board.getTile(4).getTileSymbol() == s) && (board.getTile(8).getTileSymbol() == s))
        {
            winner = true;
        }
        else if ((board.getTile(2).getTileSymbol() == s) && (board.getTile(4).getTileSymbol() == s) && (board.getTile(6).getTileSymbol() == s))
        {
            winner = true;
        }
        
        return winner;
    }
    
    public abstract void takeTurn();
    
    /***************************************************************************
     *      ENUMERATOR
     **************************************************************************/

    public enum PlayerType
    {
        HUMAN,
        COMPUTER;
    }
}
