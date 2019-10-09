package game.buttons;

import ddf.minim.AudioSample;
import game.TicTacToe;
import game.TicTacToe.Difficulty;
import game.screens.MenuScreen.Menu;
import game.screens.Screen.ScreenType;
import processing.core.PApplet;
import processing.core.PConstants;

public class MenuOption extends Button
{
    public MenuOption(PApplet parent, int x, int y, AudioSample sound)
    {
        super(parent);
        setButtonType(ButtonType.MENU);
        setWidth(190);
        setHeight(30);
        setXcoord(x);
        setYcoord(y);
        setSound(sound);
    }

    public void display(String label)
    {
        setLabel(label);
        if (getHover())
        {
            parent.fill(100, 100, 100);
        }
        else
        {
            if (TicTacToe.difficulty.getLabel() == label)
            {
                parent.fill(250, 50, 50);
            }
            else
            {
                parent.fill(255, 255, 255);
            }
        }
        parent.textSize(32);
        parent.text(getLabel(), getXcoord(), getYcoord());
        parent.rectMode(PConstants.CENTER);
        //parent.rect(getXcoord() , getYcoord(), getWidth(), getHeight());
       
    }
    
    @Override
    public Boolean isInside(float mx, float my) 
    {
        if (mx >= getXcoord() - getWidth()/2 && mx <= getXcoord() + getWidth()/2)
        {
            if (my >= getYcoord() - getHeight()/2 && my <= getYcoord() + getHeight()/2)
            {
                return true;
            }
        }
        return false;
    }
    
    public void getNextScreen(String label)
    {
        switch(label)
        {
        case "New Game":
            TicTacToe.currentScreen = ScreenType.GAME;
            TicTacToe.currentMenu = Menu.PAUSE;
            //GameBoard.clear();
            break;
        case "Continue Game":
            TicTacToe.currentScreen = ScreenType.GAME;
            TicTacToe.currentMenu = Menu.PAUSE;
            break;
        case "Main Menu":
            TicTacToe.currentScreen = ScreenType.MENU;
            TicTacToe.currentMenu = Menu.MAIN;
            break;
        case "Difficulty":
            TicTacToe.previousMenu = TicTacToe.currentMenu;
            TicTacToe.currentScreen = ScreenType.MENU;
            TicTacToe.currentMenu = Menu.DIFFICULTY;
            break;
        case "Easy":
            TicTacToe.difficulty = Difficulty.EASY;
            break;
        case "Medium":
            TicTacToe.difficulty = Difficulty.MEDIUM;
            break;
        case "Hard":
            TicTacToe.difficulty = Difficulty.HARD;
            break;
        case "Settings":
            break;
        case "Back":
            TicTacToe.currentMenu = TicTacToe.previousMenu;
            break;
        case "Quit":
            parent.exit();
            break;
        case "":
            break;
        default:
            TicTacToe.currentScreen = ScreenType.INIT;
            TicTacToe.currentMenu = Menu.MAIN;
            break;
        }
    }
}
