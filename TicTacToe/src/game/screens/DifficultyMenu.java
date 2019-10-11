package game.screens;

import game.TicTacToe;

public class DifficultyMenu extends MenuScreen
{
    private int i = 0;
    public DifficultyMenu(TicTacToe game)
    {
        super(game);
        setMenuType(Menu.DIFFICULTY);
        setMenuTitle();
        setLabel(i++, "Easy");
        setLabel(i++, "Medium");
        setLabel(i++, "Hard");
        setLabel(i++, "Back");
    }
}
