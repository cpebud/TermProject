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
     *      CONSTRUCTOR
     **************************************************************************/
    
    public Human(TicTacToe game)
    {
        super(game);
        setType(PlayerType.HUMAN);
    }
  
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
