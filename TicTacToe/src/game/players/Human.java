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
import game.screens.MenuScreen.Menu;
import game.screens.Screen.ScreenType;

public class Human extends Player
{
    /***************************************************************************
     *      VARIABLES
     **************************************************************************/
    
    //private String name;
    //private boolean hasGone = false;

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
    
//    public void setHasGone(Boolean hasGone)
//    {
//        this.hasGone = hasGone;
//    }
    
    /***************************************************************************
     *      METHODS
     **************************************************************************/

    @Override
    public void takeTurn()
    {
        incTurn();
        if (!game.getCurrentPlayer().isWinner() && !game.getBoard().isFull())
        {
            game.nextPlayer();
        }
        else
        {
            game.changeScreen(ScreenType.WIN,Menu.MAIN);
            if (game.getSoundsOn() && game.getCurrentPlayer().isWinner())
            {
                game.win.trigger(); 
            }
        }
    }
}
