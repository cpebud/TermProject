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
import processing.core.PConstants;
import processing.core.PImage;

public class GameTile extends Button
{
    /***************************************************************************
     *      VARIABLES
     **************************************************************************/
    
    private Symbol symbol;
    
    private PImage xSymbol;
    private PImage oSymbol;

    /***************************************************************************
     *      CONSTRUCTOR
     **************************************************************************/
    
    public GameTile(TicTacToe game, int x, int y)
    {
        super(game, x, y);
        this.symbol  = Symbol.EMPTY;
        this.xSymbol = game.getTheme().getXsymbol();
        this.oSymbol = game.getTheme().getOsymbol();
        setButtonType(ButtonType.GAME);
        setWidth(90);
        setHeight(90);  
    }
    
    /***************************************************************************
     *      SETTERS/GETTERS
     **************************************************************************/
    
    public void setTileSymbol(Symbol type)
    {
        this.symbol = type;
    }
    
    public Symbol getTileSymbol()
    {
        return symbol;
    }
    
    /***************************************************************************
     *      METHODS
     **************************************************************************/

    public Boolean isEmpty()
    {
        return getTileSymbol() == Symbol.EMPTY;
    }
    
    @Override
    public void display()
    {
        switch(symbol)
        {
        case EX:
            displayImage(xSymbol);
            break;
        case OH:
            displayImage(oSymbol);
            break;
        default:
            if (getHover())
            {
                game.stroke(0, 0);
                game.fill(100, 100, 100, 150); // Transparent grey
                game.rectMode(PConstants.CENTER);
                game.rect(getXcoord(), getYcoord(), getWidth(), getHeight(), 15);
            }
            break;
        }   
    }
    
    private void displayImage(PImage image)
    {
        int imageX = getXcoord() - getWidth()/2;
        int imageY = getYcoord() - getHeight()/2;
        game.image(image, imageX, imageY, getWidth(), getHeight());
    }
    
    @Override
    public void update()
    {
        this.xSymbol = game.getTheme().getXsymbol();
        this.oSymbol = game.getTheme().getOsymbol();
    }

    /***************************************************************************
     *      ENUMERATOR
     **************************************************************************/
    
    public enum Symbol
    {
        EX,
        OH,
        EMPTY;
    }
}
