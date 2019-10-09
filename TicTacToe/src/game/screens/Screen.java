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

/**
 * Abstract <tt>Screen</tt> class. Represents a screen within the applet.
 * Used as base for all screens.
 * 
 * @author  Garrett Cross
 * 
 * @version 1.0.0
 * @since   10/05/2019
 *
 */
public abstract class Screen
{   
    PApplet    parent;         // Parent Applet
    ScreenType type;           // Type of Screen
    
    private PImage foreground; // Foreground image of screen
    private PImage background; // Background image of screen
        
    private final int width  = TicTacToe.WIDTH;  // Screen width
    private final int height = TicTacToe.HEIGHT; // Screen height
    
    /**
     * Abstract <tt>Screen</tt> class represents a screen within the applet.
     * Used as base for all screens. 
     * 
     * @param parent     : Parent applet
     * @param background : Background image for screen
     * 
     * @see Screen
     */
    public Screen(PApplet parent, PImage background)
    {
        setParent(parent);
        setBackground(background);
    }
    
    /** Sets parent applet
     * @param parent : parent applet
     */
    private void setParent(PApplet parent)
    {
        this.parent = parent;
    }
    
    /** Sets screen type 
     * @param type : Type of screen (INIT, MENU, GAME, or END)
     */
    protected void setType(ScreenType type)
    {
        this.type = type;
    }
    
    /** Gets screen type 
     * @see Screen */
    public ScreenType getType()
    {
        return this.type;
    }
    
    /** Sets foreground image
     * @param image : image to be set as foreground
     */
    protected void setForeground(PImage image)
    {
        this.foreground = image;
    }
    
    /** Sets background image
     * @param image : image to be set as background 
     */
    protected void setBackground(PImage image)
    {
        this.background = image;
    }
    
    public int getWidth()
    {
        return this.width;
    }
    
    public int getHeight()
    {
        return this.height;
    }
    
    /** Displays the screen
     * @see Screen */
    public void display()
    {
        displayBackground();
        displayForeground();
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
    protected void setImage(int imageWidth, int imageHeight)
    {
        int imageX = (width - imageWidth)/2;
        int imageY = (height - imageHeight)/2;
        
        parent.image(foreground, imageX, imageY, imageWidth, imageHeight);
    }
    
    /** Displays the background */
    protected void displayBackground()
    {
        // Background Image
        parent.image(background, 0, 0, width, height);
        
        // Title Text
        parent.fill(255, 255, 255);
        parent.textSize(88);
        parent.text("Tic-Tac-Toe", width/2, height/7);
        
        // Copyright Text
        parent.fill(255, 255, 255);
        parent.textSize(14);
        parent.text("Copyright © 2019 by Garrett Cross, Omar Kermiche, Autumn Nguyen, Thomas Pridy",
                width/2, 14*height/15);
    }
    
    public enum ScreenType
    {
        INIT, // Initial game screen on startup
        MENU, // Base menu screen
        GAME, // Screen during gameplay
        END;  // Screen for victory or gameover
    }
}
