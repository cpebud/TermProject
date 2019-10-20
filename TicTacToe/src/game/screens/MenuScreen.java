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

public abstract class MenuScreen extends Screen
{
    /***************************************************************************
     *      VARIABLES
     **************************************************************************/
    
    private Menu mType;
    private String title;
    private String[] labels = {"","","","","",""};
    public static final int NUM_BUTTONS = 6;

    /***************************************************************************
     *      CONSTRUCTOR
     **************************************************************************/
    
    public MenuScreen(TicTacToe game)
    {
        super(game);
        setType(ScreenType.MENU);
        setForeground(game.getTheme().getMenuForeground());
    }
    
    /***************************************************************************
     *      SETTERS/GETTERS
     **************************************************************************/
    
    protected void setMenuType(Menu mType) {
        this.mType = mType;
    }
    
    public Menu getMenuType()
    {
        return this.mType;
    }
    
    protected void setMenuTitle()
    {
        this.title = getMenuType().getTitle();
    }
    
    public String getTitle()
    {
        return this.title;
    }
    
    protected void setLabel(int i, String label)
    {
        this.labels[i] = label;
    }
    
    /***************************************************************************
     *      METHODS
     **************************************************************************/

    @Override
    protected void updateForeground()
    {
        setForeground(game.getTheme().getMenuForeground());
    }
    
    @Override
    protected void displayForeground()
    {
        displayForegroundImage(300,400);
        game.textSize(48*getFontSize()/100);
        game.text(getTitle(), getWidth()/2, 5*getHeight()/16);
        
        displayButtons();
    }

    protected void displayButtons()
    {
        for (int i = 0; i < labels.length; i++)
        {
            game.getOption(i).setLabel(this.labels[i]);
            game.getOption(i).display();
        }
    }

    /***************************************************************************
     *      ENUMERATOR
     **************************************************************************/

    public enum Menu
    {
        MAIN("Main Menu"),
        PAUSE("Paused"),
        VERSUS("Versus"),
        DIFFICULTY("Difficulty"),
        SETTINGS("Settings"),
        THEMES("Themes");
        
        private String title;
        
        public String getTitle()
        {
            return this.title;
        }
        
        private Menu(String title)
        {
            this.title = title;
        }
    }
}

