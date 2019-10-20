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

public class DifficultyMenu extends MenuScreen
{
    /***************************************************************************
     *      VARIABLES
     **************************************************************************/
    
    private int i = 0;


    /***************************************************************************
     *      CONSTRUCTOR
     **************************************************************************/
    
    public DifficultyMenu(TicTacToe game)
    {
        super(game);
        setMenuType(Menu.DIFFICULTY);
        setMenuTitle();
        setLabel(i++, "Easy");
        setLabel(i++, "Medium");
        setLabel(i++, "Hard");
        setLabel(i++, "Back");
    }
}
