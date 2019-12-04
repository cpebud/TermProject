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
import game.players.Player;

/**
 * <tt>WinScreen</tt> class.Win game screen when winner is determined.
 * Extends <tt>Screen</tt>.
 * 
 * @author  Autumn Nguyen
 * 
 * @version 1.1.0
 * @since   10/05/2019
 *
 * @see Screen
 */
public class WinScreen extends Screen
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
    public WinScreen(TicTacToe game)
    {
        super(game);
        setType(ScreenType.WIN);
        setForeground(game.getTheme().getGameForeground());
    }
    
    /***************************************************************************
     *      METHODS
     **************************************************************************/

    @Override
    protected void updateForeground()
    {
        setForeground(game.getTheme().getGameForeground());        
    } 
    
    @Override
    public void displayForeground()
    {
        // Display foreground image with given dimensions
        displayForegroundImage(330, 330);
        displayBoard();        
        displayMessage();
    }
    
    private void displayBoard()
    {
        game.getBoard().display();
    }
    
    private void displayMessage()
    {
        Player player = game.getCurrentPlayer();
        
        // Display text to initiate game
        game.fill(getFontColor());
        game.textSize(30*getFontSize()/100);
        if (game.getBoard().isFull() && !player.isWinner())
        {
            game.text("It's a tie! The bot is happy :D", getWidth()/2, 25*getHeight()/32);
        }
        else
        {
        
            if (player == game.player1)
            {             
                game.text("You won... Good job... :I", getWidth()/2, 25*getHeight()/32);
            }
            else if (player == game.player2)
            {
                switch (player.getType())
                {
                case HUMAN:                
                    game.text("You won... Good job... :I", getWidth()/2, 25*getHeight()/32);
                    break;
                case COMPUTER:
                    game.text("The Computer won! You're the loser! Good Job! :D", getWidth()/2, 25*getHeight()/32);
                    break;
                }
            }
        }
        game.text("Number of Turns: " + player.getTurn(), getWidth()/2, 27*getHeight()/32);
        game.text("Average Time of Turn: " + game.player1.average() + " seconds", getWidth()/2, 25*getHeight()/32);
    }
}
