/**
 * 
 */
package game.screens;

import controlP5.*;
import game.TicTacToe;


/**
 * @author Thomas Pridy
 *
 */
public class TimeScreen extends Screen {

	/**
	 * 
	 */
	public TimeScreen(TicTacToe game) 
	{
		super(game);
		setType(ScreenType.TIME);
        setForeground(game.getTheme().getGameForeground());
	}

	@Override
	protected void updateForeground() 
	{
		setForeground(game.getTheme().getGameForeground()); 
	}

	@Override
	protected void displayForeground() 
	{
		displaySlider();
		displayMessage();
	}
	

	/***************************************************************************
	 *      VARIABLES
	 **************************************************************************/
	
	/***************************************************************************
	 *      CONSTRUCTOR
	 **************************************************************************/

	/***************************************************************************
	 *      SETTERS/GETTERS
	 * @return 
	 **************************************************************************/

	/***************************************************************************
	 *      METHODS
	 **************************************************************************/

	private void displayMessage()
	{
		game.fill(getFontColor());
        game.textSize(30*getFontSize()/100);
        game.text("Please select how much time per turn", getWidth()/2, 22*getHeight()/32);
        game.text("Press Enter to Start Game ", getWidth()/2, 25*getHeight()/32);
       
	}


	private void displaySlider() 
	{
		game.getSlider().show();
		
	}
}
