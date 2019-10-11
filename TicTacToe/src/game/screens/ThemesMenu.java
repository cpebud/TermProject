package game.screens;

import game.Theme;
import game.TicTacToe;

public class ThemesMenu extends MenuScreen
{
    private int i = 0;
    public ThemesMenu(TicTacToe game)
    {
        super(game);
        setMenuType(Menu.THEMES);
        setMenuTitle();
        setLabels();
        setLabel(i++, "Back");
    }

   private void setLabels()
    {
        for (Theme theme : Theme.values())
        {
            setLabel(i++, theme.getLabel());
        }
    }
}
