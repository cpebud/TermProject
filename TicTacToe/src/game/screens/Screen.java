/*******************************************************************************
 * File Name:		Screen.java
 * Project:			Tic-Tac-Toe Game
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
import processing.core.*;
import util.Reference;

/**
 * Abstract <tt>Screen</tt> class. Represents a screen within the applet.
 * Used as base for all screens.
 * 
 * @author  Garrett Cross,
 *          Omar Kermiche,
 *          Autumn Nguyen,
 *          Thomas Pridy
 * 
 * @version 1.1.0
 * @since   10/05/2019
 *
 */
public abstract class Screen
{   
    /***************************************************************************
     *      VARIABLES
     **************************************************************************/

    protected TicTacToe game;            // Game client
    
    private ScreenType sType;           // Type of Screen
    
    /*   Font Features   */
    private PFont font;
    private int   fontSize;
    private int   fontColor;
    private int   fontHover;
    private int   fontHighlight;
    
    /*   Image   */
    private PImage foreground; // Foreground image of screen
    private PImage background; // Background image of screen
        
    /*   CONSTANTS   */
    private final int width  = Reference.WIDTH;  // Screen width
    private final int height = Reference.HEIGHT; // Screen height
    
    /***************************************************************************
     *      CONSTRUCTOR
     **************************************************************************/

    /**
     * Abstract <tt>Screen</tt> class represents a screen within the applet.
     * Used as base for all screens. 
     * 
     * @param game       : Game client
     * @param background : Background image for screen
     * 
     * @see Screen
     */
    public Screen(TicTacToe game)
    {
        this.game = game;
        
        this.font          = game.getTheme().getFont();
        this.fontSize      = game.getTheme().getFontSize();
        this.fontColor     = game.getTheme().getFontColor();
        this.fontHover     = game.getTheme().getFontHover();
        this.fontHighlight = game.getTheme().getFontHighlight();
        
        this.background    = game.getTheme().getBackground();
    }
    
    /***************************************************************************
     *      SETTERS/GETTERS
     **************************************************************************/

    protected TicTacToe getGame()
    {
        return game;
    }
    
    /** Sets screen type 
     * @param type : Type of screen (INIT, MENU, GAME, or END)
     */
    protected void setType(ScreenType type)
    {
        this.sType = type;
    }
    
    /** Gets screen type 
     * @see Screen */
    public ScreenType getType()
    {
        return this.sType;
    }
    
    protected PFont getFont()
    {
        return font;
    }
    
    protected int getFontSize()
    {
        return fontSize;
    }
      
    protected int getFontColor()
    {
        return fontColor;
    }
    
    protected int getFontHover()
    {
        return fontHover;
    }
    
    protected int getFontHighlight()
    {
        return fontHighlight;
    }
    
    /** Sets foreground image
     * @param image : image to be set as foreground
     */
    protected void setForeground(PImage image)
    {
        this.foreground = image;
    }
    
    public int getWidth()
    {
        return this.width;
    }
    
    public int getHeight()
    {
        return this.height;
    }
    
    /***************************************************************************
     *      METHODS
     **************************************************************************/

    public void update()
    {
        updateBackground();
        updateForeground();
    }
    
    private void updateBackground()
    {
        this.font          = game.getTheme().getFont();
        this.fontSize      = game.getTheme().getFontSize();
        this.fontColor     = game.getTheme().getFontColor();
        this.fontHover     = game.getTheme().getFontHover();
        this.fontHighlight = game.getTheme().getFontHighlight();

        this.background = game.getTheme().getBackground();
    }
    
    protected abstract void updateForeground();

    /** Displays the screen
     * @see Screen */
    public void display()
    {
        displayBackground();
        displayForeground();
    }
    
        
    /** Displays the background */
    protected void displayBackground()
    {
        // Background Image
        game.image(background, 0, 0, width, height);
        
        game.textFont(font);
        game.textAlign(PConstants.CENTER, PConstants.CENTER);
        game.fill(fontColor);

        // Title Text
        game.textSize(88*fontSize/100);
        game.text(Reference.TITLE, width/2, height/7);
        
        // Copyright Text
        game.textSize(14*fontSize/100);
        game.text("Copyright © " + Reference.YEAR + " " + 
                String.join(", ", Reference.AUTHORS), width/2, 27*height/30);
    }
    
    /** Displays the foreground */
    protected abstract void displayForeground();
    
    /**
     * Sets the foreground image dimensions.
     * Position based on center-point. 
     * @param imageWidth  : Width of the image
     * @param imageHeight : Height of the image
     * 
     * @see Screen
     */
    protected void displayForegroundImage(int imageWidth, int imageHeight)
    {
        int imageX = (width - imageWidth)/2;
        int imageY = (height - imageHeight)/2;
        
        game.image(foreground, imageX, imageY, imageWidth, imageHeight);
    }

    /***************************************************************************
     *      ENUMERATOR
     **************************************************************************/
    
    public enum ScreenType
    {
        INIT, // Initial game screen on startup
        MENU, // Base menu screen
        GAME, // Screen during gameplay
        WIN;  // Screen for victory or gameover
    }
}
