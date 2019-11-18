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

public class SettingsMenu extends MenuScreen
{
    /***************************************************************************
     *      VARIABLES
     **************************************************************************/
    
    private int i = 0;

    /***************************************************************************
     *      CONSTRUCTOR
     **************************************************************************/
    
    public SettingsMenu(TicTacToe game)
    {
        super(game);
        setMenuType(Menu.SETTINGS);
        setMenuTitle();
        setLabel(i++, "Themes");
        //setLabel(i++, "Sounds");
        setLabel(i++, "Back");
    }
}
