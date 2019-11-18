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
package game.buttons;

import game.TicTacToe;

public abstract class Button
{   
    /***************************************************************************
     *      VARIABLES
     **************************************************************************/
    
    protected TicTacToe game;
    private ButtonType bType;
        
    private int xCoord;
    private int yCoord;
    
    private int width;
    private int height;
    
    private Boolean hover = false;

    /***************************************************************************
     *      CONSTRUCTOR
     **************************************************************************/
    
    public Button(TicTacToe game, int x, int y) 
    {
        this.game = game;
        this.xCoord = x;
        this.yCoord = y;
    }
    
    /***************************************************************************
     *      SETTERS/GETTERS
     **************************************************************************/
    
    protected void setButtonType(ButtonType bType)
    {
        this.bType = bType;
    }
    
    public ButtonType getButtonType() 
    {
        return bType;
    }
    
    public int getXcoord()
    {
        return xCoord;
    }
    
    public int getYcoord()
    {
        return yCoord;
    }
        
    protected void setWidth(int width)
    {
        this.width = width;
    }
    
    public int getWidth()
    {
        return width;
    }
    
    protected void setHeight(int height)
    {
        this.height = height;
    }
    
    public int getHeight()
    {
        return height;
    }
    
    public void setHover(Boolean hover)
    {
        this.hover = hover;
    }
    
    public Boolean getHover()
    {
        return hover;
    }
    
    /***************************************************************************
     *      METHODS
     **************************************************************************/
    
    public Boolean isInside(float mx, float my) 
    {
        if (mx >= getXcoord() - getWidth()/2 && mx <= getXcoord() + getWidth()/2)
        {
            if (my >= getYcoord() - getHeight()/2 && my <= getYcoord() + getHeight()/2)
            {
                return true;
            }
        }
        return false;
    }
    
    public abstract void display();
    public abstract void update();
    
    /***************************************************************************
     *      ENUMERATOR
     **************************************************************************/
    
    public enum ButtonType
    {
        MENU,
        GAME;
    }
}
