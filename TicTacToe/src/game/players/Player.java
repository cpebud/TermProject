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
    
    public Boolean isWinner()
    {
        return false;
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
