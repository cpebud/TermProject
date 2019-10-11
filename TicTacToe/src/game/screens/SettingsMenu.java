package game.screens;

import game.TicTacToe;

public class SettingsMenu extends MenuScreen
{
    private int i = 0;
    public SettingsMenu(TicTacToe game)
    {
        super(game);
        setMenuType(Menu.SETTINGS);
        setMenuTitle();
        setLabel(i++, "Themes");
        setLabel(i++, "Sounds");
        setLabel(i++, "Back");
    }
}
