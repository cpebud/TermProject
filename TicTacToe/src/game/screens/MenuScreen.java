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

public class MenuScreen extends Screen
{
    /***************************************************************************
     *      VARIABLES
     **************************************************************************/
    
    public static final int NUM_BUTTONS = 6;

    private Menu mType;
    private String title;
    private String[] labels = {"","","","","",""};

    /***************************************************************************
     *      CONSTRUCTOR
     **************************************************************************/
    
    public MenuScreen(TicTacToe game, Menu mType)
    {
        super(game);
        this.mType = mType;

        setType(ScreenType.MENU);
        setForeground(game.getTheme().getMenuForeground());

        setMenuTitle();
        setLabels();
    }
    
    /***************************************************************************
     *      SETTERS/GETTERS
     **************************************************************************/
    
    public Menu getMenuType()
    {
        return this.mType;
    }
    
    private void setMenuTitle()
    {
        this.title = getMenuType().getTitle();
    }
    
    public String getTitle()
    {
        return this.title;
    }
    
    private void setLabel(int i, String label)
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
    
    

    private void displayButtons()
    {
        for (int i = 0; i < labels.length; i++)
        {
            game.getOption(i).setLabel(this.labels[i]);
            game.getOption(i).display();
        }
    }
    
    private void setLabels()
    {
        int i = 0;
        switch (mType)
        {
        case MAIN:
            setLabel(i++, "New Game");
            setLabel(i++, "Versus");
            setLabel(i++, "Difficulty");
            setLabel(i++, "Settings");
            setLabel(i++, "Quit");            
            break;
            
        case PAUSE:
            setLabel(i++, "Continue Game");
            setLabel(i++, "New Game");
            setLabel(i++, "Settings");
            setLabel(i++, "Main Menu");
            setLabel(i++, "Quit");
            break;
            
        case VERSUS:
            setLabel(i++, "Computer");
            setLabel(i++, "Human");
            setLabel(i++, "Back");
            break;
            
        case DIFFICULTY:
            setLabel(i++, "Easy");
            //setLabel(i++, "Medium");
            setLabel(i++, "Hard");
            setLabel(i++, "Back");
            break;
            
        case SETTINGS:
            setLabel(i++, "Themes");
            //setLabel(i++, "Sounds");
            setLabel(i++, "Back");
            break;
            
        case THEMES:
            for (Theme theme : Theme.values())
            {
                setLabel(i++, theme.getLabel());
            }
            setLabel(i++,"Back");
            break;
            
        case SOUNDS:
            setLabel(i++, "On");
            setLabel(i++, "Off");
            setLabel(i++, "Back");
            break;   
            
        default:
            break;
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
        THEMES("Themes"),
        SOUNDS("Sounds");
        
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

