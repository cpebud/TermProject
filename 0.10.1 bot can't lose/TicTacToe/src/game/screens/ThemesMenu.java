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

import game.Theme;
import game.TicTacToe;

public class ThemesMenu extends MenuScreen
{
    /***************************************************************************
     *      VARIABLES
     **************************************************************************/
    
    private int i = 0;

    /***************************************************************************
     *      CONSTRUCTOR
     **************************************************************************/
    
    public ThemesMenu(TicTacToe game)
    {
        super(game);
        setMenuType(Menu.THEMES);
        setMenuTitle();
        setLabels();
        setLabel(i++, "Back");
    }
    
    /***************************************************************************
     *      SETTERS/GETTERS
     **************************************************************************/
    
    private void setLabels()
    {
        for (Theme theme : Theme.values())
        {
            setLabel(i++, theme.getLabel());
        }
    }
}
