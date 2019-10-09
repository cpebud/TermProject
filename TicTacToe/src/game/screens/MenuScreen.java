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
import processing.core.PApplet;
import processing.core.PImage;

public abstract class MenuScreen extends Screen
{
    Menu mType;
    private String title;
    private String[] labels = {"","","","","",""};
    public static final int NUMBUTTONS = 6;
    
    public MenuScreen(PApplet parent, PImage foreground, PImage background)
    {
        super(parent, background);
        setType(ScreenType.MENU);
        setForeground(foreground);
    }
    
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
    
    public void setLabel(int i, String label)
    {
        this.labels[i] = label;
    }
    
    @Override
    protected void displayForeground()
    {
        setImage(300,400);
        parent.textSize(48);
        parent.text(getTitle(), getWidth()/2, 5*getHeight()/16);
        
        displayButtons();
    }

    protected void displayButtons()
    {
        for (int i = 0; i < labels.length; i++)
        {
            TicTacToe.options[i].display(this.labels[i]);
        }
    }
    
    public enum Menu
    {
        MAIN("Main Menu"),
        PAUSE("Paused"),
        DIFFICULTY("Difficulty"),
        SETTINGS("Settings");
        
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

