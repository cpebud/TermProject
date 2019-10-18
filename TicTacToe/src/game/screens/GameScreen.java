package game.screens;

import game.TicTacToe;

public class GameScreen extends Screen
{
    public static final int NUM_TILES = 9;
    public static final int ROWS = 3;
    public static final int COLS = 3;
    
    public GameScreen(TicTacToe game)
    {
        super(game);
        setType(ScreenType.GAME);
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
        displayForegroundImage(330, 330);
        displayButtons();
        
        game.fill(getFontColor());
        game.textSize(30*getFontSize()/100);
        game.text("hit 'P' to pause game", getWidth()/2, 13*getHeight()/16);
    }
    
    private void displayButtons()
    {
        for (int i = 0; i < NUM_TILES; i++)
        {
            game.getTile(i).display();
        }
    }
}
