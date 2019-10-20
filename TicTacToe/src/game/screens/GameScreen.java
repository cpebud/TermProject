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

public class GameScreen extends Screen
{
    /***************************************************************************
     *      CONSTRUCTOR
     **************************************************************************/
    
    public GameScreen(TicTacToe game)
    {
        super(game);
        setType(ScreenType.GAME);
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
    protected void displayForeground()
    {
        displayForegroundImage(330, 330);
        displayBoard();
        displayMessage();
        
        game.fill(getFontColor());
        game.textSize(30*getFontSize()/100);
        game.text("hit 'P' to pause game", getWidth()/2, 13*getHeight()/16);
    }
    
    private void displayBoard()
    {
        game.getBoard().display();
    }
    
    private void displayMessage()
    {
        game.fill(getFontColor());
        game.textSize(30*getFontSize()/100);
        if (game.getCurrentPlayer() == game.player1) 
        {
            game.text("Player 1's turn...", getWidth()/2, 4*getHeight()/16);
        } 
        else if (game.getCurrentPlayer() == game.player2)
        {
            game.text("Player 2's turn...", getWidth()/2, 4*getHeight()/16);
        }
    }
}
