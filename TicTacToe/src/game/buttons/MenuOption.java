/*******************************************************************************
 * File Name:			
 * Project:			
 * 
 * Designer(s):		Garrett Cross,
 * 					Omar Kermiche,
 * 					Autumn Nguyen,
 * 					Thomas Pridy
 * 
 * Copyright © 2019. All rights reserved.
 ******************************************************************************/
package game.buttons;

import game.Theme;
import game.TicTacToe;
import game.TicTacToe.Difficulty;
import game.players.Computer;
import game.players.Player.PlayerType;
import game.screens.MenuScreen.Menu;
import game.screens.Screen.ScreenType;
import processing.core.PConstants;

public class MenuOption extends Button
{
    /***************************************************************************
     *      VARIABLES
     **************************************************************************/
    
    private String label;
    
    private int fontSize;
    private int fontColor;
    private int fontHover;
    private int fontHighlight;

    /***************************************************************************
     *      CONSTRUCTOR
     **************************************************************************/
    
    public MenuOption(TicTacToe game, int x, int y)
    {
        super(game, x, y);
        
        setButtonType(ButtonType.MENU);
        setWidth(190);
        setHeight(30);
        
        this.fontSize      = game.getTheme().getFontSize();
        this.fontColor     = game.getTheme().getFontColor();
        this.fontHover     = game.getTheme().getFontHover();
        this.fontHighlight = game.getTheme().getFontHighlight();
    }
    
    /***************************************************************************
     *      SETTERS/GETTERS
     **************************************************************************/
    
    public void setLabel(String label)
    {
        this.label = label;
    }
    
    public String getLabel()
    {
        return this.label;
    }
    
    public int getFontSize()
    {
        return fontSize;
    }
    
    public int getFontColor()
    {
        return fontColor;
    }
    
    public int getFontHover()
    {
        return fontHover;
    }
    
    public int getFontHighlight()
    {
        return fontHighlight;
    }
    
    /***************************************************************************
     *      METHODS
     **************************************************************************/

    @Override
    public void display()
    {        
        if (getHover())
        {
            game.fill(fontHover);
        }
        else
        {
            if (game.getDifficulty().getLabel() == label || 
                game.getTheme().getLabel() == label ||
                game.getPlayer2().getType().getLabel() == label)
            {
                game.fill(fontHighlight);
            }
            else
            {
                game.fill(fontColor);
            }
        }
        
        game.textSize(32*getFontSize()/100);
        game.text(getLabel(), getXcoord(), getYcoord());
        game.rectMode(PConstants.CENTER);
        //parent.rect(getXcoord() , getYcoord(), getWidth(), getHeight()); 
    }
    
    @Override
    public void update()
    {
        this.fontSize      = game.getTheme().getFontSize();
        this.fontColor     = game.getTheme().getFontColor();
        this.fontHover     = game.getTheme().getFontHover();
        this.fontHighlight = game.getTheme().getFontHighlight();
    }
    
    public void getFunction(String label)
    {
        switch(label)
        {
        case "New Game":
            game.changeScreen(ScreenType.TIME, Menu.MAIN);
            game.getBoard().clear();
            game.getPlayer1().resetTurn();
            game.getPlayer2().resetTurn();
            game.assignPlayerSymbol();
            break;
        case "Continue Game":
            game.changeScreen(ScreenType.GAME, Menu.PAUSE);
            break;
        case "Main Menu":
            game.goMainMenu();
            break;
        case "Versus":
            game.changeScreen(ScreenType.MENU, Menu.VERSUS);
            break;
        case "Computer":
            game.switchPlayer2(PlayerType.COMPUTER);
            ((Computer)game.getPlayer2()).updateDifficulty();
            break;
        case "Human":
            game.switchPlayer2(PlayerType.HUMAN);
            break;
        case "Difficulty":
            game.changeScreen(ScreenType.MENU, Menu.DIFFICULTY);
            break;
        case "Easy":
            game.updateDifficulty(Difficulty.EASY);
            break;
        case "Medium":
            game.updateDifficulty(Difficulty.MEDIUM);
            break;
        case "Hard":
            game.updateDifficulty(Difficulty.HARD);
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
        case "christmas":
            game.updateCurrentTheme(Theme.CHRISTMAS);
            break;
        case "retro":
            game.updateCurrentTheme(Theme.RETRO);
            break;
        case "Sounds":
            game.changeScreen(ScreenType.MENU, Menu.SOUNDS);
            break;
        case "On":
            if (game.getCurrentMenu() == Menu.SOUNDS)
            {
                game.setSoundsOn(true);
            }
            break;
        case "Off":
            if (game.getCurrentMenu() == Menu.SOUNDS)
            {
                game.setSoundsOn(false);
            }
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
