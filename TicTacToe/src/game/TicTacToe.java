/*******************************************************************************
 * File Name:		TicTacToe.java
 * Project:			Tic-Tac-Toe Game
 * 
 * Designer(s):		Garrett Cross,
 * 					Omar Kermiche,
 * 					Autumn Nguyen,
 * 					Thomas Pridy
 * 
 * Copyright © 2019. All rights reserved.
 ******************************************************************************/
package game;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import ddf.minim.Minim;
import game.buttons.MenuOption;
import game.screens.DifficultyMenu;
import game.screens.GameScreen;
import game.screens.InitScreen;
import game.screens.MainMenu;
import game.screens.MenuScreen;
import game.screens.MenuScreen.Menu;
import game.screens.PauseMenu;
import game.screens.Screen;
import game.screens.Screen.ScreenType;
import game.screens.SettingsMenu;
import game.screens.ThemesMenu;
import processing.core.PApplet;
import util.Reference;


/**
 * <tt>TicTacToe</tt> is game created using Processing in combination with
 * Eclipse.
 * 
 * @author  Garrett Cross, Omar Kermiche, Autumn Nguyen, Thomas Pridy
 * 
 * @version 0.3.0
 * @since   10/04/2019
 *
 */
public class TicTacToe extends PApplet
{   
    // Creates the Processing Applet
    public static void main(String[] args)
    {
        PApplet.main("game.TicTacToe");
    }
    /***************************************************************************
     *      VARIABLES
     **************************************************************************/
    
    private Theme currentTheme = Theme.CHALK;

    /*   Current Screens   */
    private Stack<ScreenType> currentScreens = new Stack<>();;
    private ScreenType currentScreen;// = currentScreens.peek();     // Current screen of applet
    
    private Stack<Menu> currentMenus = new Stack<>();;
    private Menu currentMenu;// = currentMenus.peek();                   // Current menu of applet
    
    /*   Game Difficulty   */
    private Difficulty difficulty = Difficulty.EASY;
        
    
    public Boolean soundsOn = true;

    
    Minim minim = new Minim(this);
    
    private Map<String, Screen> screens = new HashMap<>();
    
    
    
    private MenuOption[] options = new MenuOption[MenuScreen.NUMBUTTONS];
    

    
    /***************************************************************************
     *      SETTINGS
     **************************************************************************/
    /** Applet settings */
    public void settings()
    {
        size(Reference.WIDTH, Reference.HEIGHT);
    }
    
    /***************************************************************************
     *      SETUP
     **************************************************************************/
    /** Initial setup */
    public void setup()
    {
        loadThemes();
        loadScreens();
        loadButtons();
        
        currentScreens.push(ScreenType.INIT);
        setCurrentScreen();
        
        currentMenus.push(Menu.MAIN);
        setCurrentMenu();
    }

    /***************************************************************************
     *      DRAW
     **************************************************************************/
    /** Draw images. Updated at 60Hz */
    public void draw()
    {
        switch(currentScreen)
        {
        case INIT:
            screens.get("Initial").display();
            break;
        case MENU:
            switch (currentMenu)
            {
            case MAIN:
                screens.get("Main").display();
                break;
            case PAUSE:
                screens.get("Pause").display();
                break;
            case DIFFICULTY:
                screens.get("Difficulty").display();
                break;
            case SETTINGS:
                screens.get("Settings").display();
                break;
            case THEMES:
                screens.get("Themes").display();
                break;
            default:
                break;
            }
            break;
        case GAME:
            screens.get("Game").display();
            break;
        case END:
            break;
        default:
            screens.get("Initial").display();
            break;
        }
    }
    
    /***************************************************************************
     *      MOUSE/KEY FUNCTIONS
     **************************************************************************/
    /** Mouse button event */
    public void mousePressed()
    {
        if (currentScreen == ScreenType.INIT) 
        {
            //currentTheme.getMenuClick().trigger();
            currentScreens.push(ScreenType.MENU);
            setCurrentScreen();
        }
        else if (currentScreen == ScreenType.MENU)
        {
            for (int i = 0; i < MenuScreen.NUMBUTTONS; i++)
            {
                if (options[i].isInside(mouseX, mouseY))
                {
                    
                    options[i].getNextScreen(options[i].getLabel());
                }
            }
        }
    }
    
    /** Mouse move event */
    public void mouseMoved()
    {
        for (int i = 0; i < MenuScreen.NUMBUTTONS; i++)
        {
            options[i].setHover(options[i].isInside(mouseX, mouseY));
        }
    }
    
    /** Key press event */
    public void keyPressed()
    {          
        if (currentScreen == ScreenType.GAME)
        {
            if (keyCode == 80)  // P
            {
                currentScreen = ScreenType.MENU;
            }
        }
    }
    
    /***************************************************************************
     *      LOAD FUNCTIONS
     **************************************************************************/
    
    private void loadThemes()
    {
        for (Theme theme : Theme.values())
        {
            theme.load(this, minim);
        }
    }
    
    private void loadScreens()
    {
        screens.put("Initial", new InitScreen(this));
        screens.put("Main", new MainMenu(this));
        screens.put("Game", new GameScreen(this));
        screens.put("Pause", new PauseMenu(this));
        screens.put("Difficulty", new DifficultyMenu(this));
        screens.put("Settings", new SettingsMenu(this));
        screens.put("Themes", new ThemesMenu(this));
    }
    
    private void loadButtons()
    {
        for (int i = 0; i < MenuScreen.NUMBUTTONS; i++)
        {   
            options[i] = new MenuOption(this, width/2, 310 + 45*i, currentTheme.getMenuClick());
        }
    }
    
    /***************************************************************************
     *      SETTERS/GETTERS
     **************************************************************************/

    public Theme getTheme()
    {
        return currentTheme;
    }
    
    private void setCurrentScreen()
    {
        this.currentScreen = currentScreens.peek();
    }
    
    public ScreenType getScreen()
    {
        return currentScreen;
    }
    
    private void setCurrentMenu()
    {
        this.currentMenu = currentMenus.peek();
    }
    
    public Menu getCurrentMenu()
    {
        return currentMenu;
    }
    
    public void setDifficulty(Difficulty difficulty)
    {
        this.difficulty = difficulty;
    }
    
    public Difficulty getDifficulty()
    {
        return difficulty;
    }
    
    public MenuOption getOptions(int i)
    {
        return options[i];
    }
    
    /***************************************************************************
     *      OTHER FUNCTIONS
     **************************************************************************/
    
    public void changeScreen(ScreenType screen, Menu menu)
    {
        currentScreens.push(screen);
        setCurrentScreen();
        
        currentMenus.push(menu);
        setCurrentMenu();
    }
    
    public void goMainMenu()
    {
        currentScreens.clear();
        currentScreens.push(ScreenType.MENU);
        setCurrentScreen();
        
        currentMenus.clear();
        currentMenus.push(Menu.MAIN);
        setCurrentMenu();
    }
    public void goBackScreen()
    {
        currentScreens.pop();
        setCurrentScreen();
        
        currentMenus.pop();
        setCurrentMenu();
    }
    
    public void updateCurrentTheme(Theme theme)
    {
        this.currentTheme = theme;
        updateTheme();
    }
    
    private void updateTheme()
    {
        for (Screen screen : screens.values())
        {
            screen.update();
        }
        
        for (MenuOption option : options)
        {
            option.update();
        }
    }
    
    /***************************************************************************
     *      ENUMERATORS
     **************************************************************************/
    
    public enum Difficulty
    {
        EASY("Easy"),
        MEDIUM("Medium"),
        HARD("Hard");
        
        private String label;
        
        public String getLabel()
        {
            return this.label;
        }
        
        private Difficulty(String label)
        {
            this.label = label;
        }
    }  
}
