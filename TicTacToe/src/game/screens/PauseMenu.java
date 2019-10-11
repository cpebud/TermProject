package game.screens;

import game.TicTacToe;

public  class PauseMenu extends MenuScreen
{
    private int i = 0;
    public PauseMenu(TicTacToe game)
    {
        super(game);
        setMenuType(Menu.PAUSE);
        setMenuTitle();
        setLabel(i++, "Continue Game");
        //setLabel(i++, "New Game");
        //setLabel(i++, "Settings");
        setLabel(i++, "Main Menu");
        setLabel(i++, "Quit");
    }
}
