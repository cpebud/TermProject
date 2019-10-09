package game.screens;

import processing.core.PApplet;
import processing.core.PImage;

public class DifficultyMenu extends MenuScreen
{
    private int i = 0;
    public DifficultyMenu(PApplet parent, PImage foreground, PImage background)
    {
        super(parent, foreground, background);
        setMenuType(Menu.DIFFICULTY);
        setMenuTitle();
        setLabel(i++, "Easy");
        setLabel(i++, "Medium");
        setLabel(i++, "Hard");
        setLabel(i++, "Back");
    }
}
