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
package game.screens;

import game.TicTacToe;

public  class PauseMenu extends MenuScreen
{
    /***************************************************************************
     *      VARIABLES
     **************************************************************************/
    
    private int i = 0;

    /***************************************************************************
     *      CONSTRUCTOR
     **************************************************************************/
    
    public PauseMenu(TicTacToe game)
    {
        super(game);
        setMenuType(Menu.PAUSE);
        setMenuTitle();
        setLabel(i++, "Continue Game");
        setLabel(i++, "New Game");
        //setLabel(i++, "Settings");
        setLabel(i++, "Main Menu");
        setLabel(i++, "Quit");
    }
}
