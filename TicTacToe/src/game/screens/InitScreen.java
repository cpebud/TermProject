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

import processing.core.*;
/**
 * <tt>InitScreen</tt> class.Intial game screen on startup.
 * Extends <tt>Screen</tt>.
 * 
 * @author  Garrett Cross
 * 
 * @version 1.0.0
 * @since   10/05/2019
 *
 * @see Screen
 */
public class InitScreen extends Screen
{
    /**
     * <tt>InitScreen</tt> class.Intial game screen on startup.
     * Extends <tt>Screen</tt>.
     * 
     * @param parent     : Parent applet
     * @param foreground : Foreground image for screen
     * @param background : Background image for screen
     * 
     * @see Screen
     * @see InitScreen
     */
    public InitScreen(PApplet parent, PImage foreground, PImage background)
    {
        super(parent, background);
        setType(ScreenType.INIT);
        setForeground(foreground);
    }
    
    @Override
    public void displayForeground()
    {
        // Set foreground image dimensions
        setImage(330, 330);
        
        // Display text to initiate game
        parent.fill(255, 255, 255);
        parent.textSize(30);
        parent.text("click anywhere to play", getWidth()/2, 13*getHeight()/16);
    
    } 
}
