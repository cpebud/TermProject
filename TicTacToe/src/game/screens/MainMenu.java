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

public class MainMenu extends MenuScreen
{   
    private int i = 0;
    public MainMenu(TicTacToe game)
    {
        super(game);
        setMenuType(Menu.MAIN);
        setMenuTitle();
        setLabel(i++, "New Game");
        setLabel(i++, "Difficulty");
        setLabel(i++, "Settings");
        setLabel(i++, "Quit");
    }  
}
