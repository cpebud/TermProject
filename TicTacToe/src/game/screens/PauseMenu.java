package game.screens;

import processing.core.*;

public  class PauseMenu extends MenuScreen
{
    private int i = 0;
    public PauseMenu(PApplet parent, PImage foreground, PImage background)
    {
        super(parent, foreground, background);
        setMenuType(Menu.PAUSE);
        setMenuTitle();
        setLabel(i++, "Continue Game");
        //setLabel(i++, "New Game");
        //setLabel(i++, "Settings");
        setLabel(i++, "Main Menu");
        setLabel(i++, "Quit");
    }
}
