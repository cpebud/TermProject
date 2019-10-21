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

public class Human extends Player
{
    /***************************************************************************
     *      VARIABLES
     **************************************************************************/
    
    private String name;
    private Boolean hasGone = false;

    /***************************************************************************
     *      CONSTRUCTOR
     **************************************************************************/
    
    public Human(TicTacToe game)
    {
        super(game);
        setType(PlayerType.HUMAN);
    }
    
    /***************************************************************************
     *      SETTERS/GETTERS
     **************************************************************************/
    
    public void setHasGone(Boolean hasGone)
    {
        this.hasGone = hasGone;
    }
    
    /***************************************************************************
     *      METHODS
     **************************************************************************/

    @Override
    public void takeTurn()
    {
        while(!hasGone)
        {
            game.delay(1);
        }
        setHasGone(false);
        incTurn();
    }
}
