package game.screens;

import processing.core.PApplet;
import processing.core.PImage;

public class GameScreen extends Screen
{
    public GameScreen(PApplet parent, PImage foreground, PImage background)
    {
        super(parent, background);
        setType(ScreenType.GAME);
        setForeground(foreground);
    }

    @Override
    protected void displayForeground()
    {
        setImage(330, 330);
        displayButtons();
        
        parent.fill(255, 255, 255);
        parent.textSize(30);
        parent.text("hit 'P' to pause game", getWidth()/2, 13*getHeight()/16);
    }
    
    private void displayButtons()
    {
        
    }
}
