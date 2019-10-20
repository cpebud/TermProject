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

/**
 * <tt>InitScreen</tt> class.Intial game screen on startup.
 * Extends <tt>Screen</tt>.
 * 
 * @author  Garrett Cross
 * 
 * @version 1.1.0
 * @since   10/05/2019
 *
 * @see Screen
 */
public class InitScreen extends Screen
{
    /***************************************************************************
     *      CONSTRUCTOR
     **************************************************************************/
    
    /**
     * <tt>InitScreen</tt> class.Intial game screen on startup.
     * Extends <tt>Screen</tt>.
     * 
     * @param game     : Parent applet
     * @param foreground : Foreground image for screen
     * @param background : Background image for screen
     * 
     * @see Screen
     * @see InitScreen
     */
    public InitScreen(TicTacToe game)
    {
        super(game);
        setType(ScreenType.INIT);
        setForeground(game.getTheme().getInitForeground());
    }
    
    /***************************************************************************
     *      METHODS
     **************************************************************************/

    @Override
    protected void updateForeground()
    {
        setForeground(game.getTheme().getInitForeground());        
    } 
    
    @Override
    public void displayForeground()
    {
        // Display foreground image with given dimensions
        displayForegroundImage(330, 330);
        
        // Display text to initiate game
        game.fill(getFontColor());
        game.textSize(30*getFontSize()/100);
        game.text("click anywhere to play", getWidth()/2, 13*getHeight()/16);
    
    }
}
