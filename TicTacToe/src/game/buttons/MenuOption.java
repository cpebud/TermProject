package game.buttons;

import game.Theme;
import game.TicTacToe;
import game.TicTacToe.Difficulty;
import game.screens.MenuScreen.Menu;
import game.screens.Screen.ScreenType;
import processing.core.PConstants;

public class MenuOption extends Button
{
    private String label;
    
    public MenuOption(TicTacToe game, int x, int y)
    {
        super(game);
        setButtonType(ButtonType.MENU);
        setWidth(190);
        setHeight(30);
        setXcoord(x);
        setYcoord(y);
    }
    
    public void setLabel(String label)
    {
        this.label = label;
    }
    
    public String getLabel()
    {
        return this.label;
    }
    
    @Override
    public void display()
    {
        setLabel(label);
        
        if (getHover())
        {
            game.fill(getFontHover());
        }
        else
        {
            if (game.getDifficulty().getLabel() == label || game.getTheme().getLabel() == label)
            {
                game.fill(getFontHighlight());
            }
            else
            {
                game.fill(getFontColor());
            }
        }
        
        game.textSize(32*getFontSize()/100);
        game.text(getLabel(), getXcoord(), getYcoord());
        game.rectMode(PConstants.CENTER);
        //parent.rect(getXcoord() , getYcoord(), getWidth(), getHeight());
       
    }
    
    public void getNextScreen(String label)
    {
        switch(label)
        {
        case "New Game":
            game.changeScreen(ScreenType.GAME, Menu.PAUSE);
            //GameBoard.clear();
            break;
        case "Continue Game":
            game.changeScreen(ScreenType.GAME, Menu.PAUSE);
            break;
        case "Main Menu":
            game.goMainMenu();
            break;
        case "Difficulty":
            game.changeScreen(ScreenType.MENU, Menu.DIFFICULTY);
            break;
        case "Easy":
            game.setDifficulty(Difficulty.EASY);
            break;
        case "Medium":
            game.setDifficulty(Difficulty.MEDIUM);
            break;
        case "Hard":
            game.setDifficulty(Difficulty.HARD);
            break;
        case "Settings":
            game.changeScreen(ScreenType.MENU, Menu.SETTINGS);
            break;
        case "Themes":
            game.changeScreen(ScreenType.MENU, Menu.THEMES);
            break;
        case "chalk":
            game.updateCurrentTheme(Theme.CHALK);
            break;
        case "retro":
            game.updateCurrentTheme(Theme.RETRO);
            break;
        case "Back":
            game.goBackScreen();
            break;
        case "Quit":
            game.exit();
            break;
        case "":
            break;
        default:
            game.changeScreen(ScreenType.INIT, Menu.MAIN);
            break;
        }
    }
}
