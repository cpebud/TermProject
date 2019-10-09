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

import ddf.minim.AudioSample;
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
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;


/**
 * <tt>TicTacToe</tt> is game created using Processing in combination with
 * Eclipse.
 * 
 * @author  Garrett Cross, Omar Kermiche, Autumn Nguyen, Thomas Pridy
 * 
 * @version 0.0.1
 * @since   10/04/2019
 *
 */
public class TicTacToe extends PApplet
{   
    /***************************************************************************
     *      VARIABLES
     **************************************************************************/
    
    public static final int WIDTH  = 600;   // Applet CONSTANT width
    public static final int HEIGHT = 800;   // Applet CONSTANT height
    
    /*   Fonts    */    
    PFont font;                             // Font of text throughout applet
    
    /*   Images   */
    PImage background;                      // Background image throughout applet
    PImage initForeground;                  // Initial startup screen
    PImage menuForeground;                  // Menu image 
    PImage gameForeground;                  // Image during game play
    //PImage winForeground;                   // Image when player wins game
    //PImage lossForeground;                  // Image when player loses game
    
    /*   Sounds   */
    Minim minim = new Minim(this);
    AudioSample chalkX;
    AudioSample chalkO;
    AudioSample gameStart;
    AudioSample menuClick;
    public static Boolean soundsOn = true;
    
    /*   Current Screens   */
    public static ScreenType currentScreen = ScreenType.INIT;     // Current screen of applet
    public static Menu currentMenu = Menu.MAIN;                   // Current menu of applet
    public static Menu previousMenu = Menu.MAIN;
    
    /*   Game Difficulty   */
    public static Difficulty difficulty = Difficulty.EASY;
    
    /*   Themes   */
    
    
    // Creates the Processing Applet
    public static void main(String[] args)
    {
        PApplet.main("game.TicTacToe");
    }
    
    Map<String, Screen> screens = new HashMap<>();
    public static MenuOption[] options = new MenuOption[MenuScreen.NUMBUTTONS];
    
    /***************************************************************************
     *      SETTINGS
     **************************************************************************/
    /** Applet settings */
    public void settings()
    {
        size(WIDTH, HEIGHT);
    }
    
    /***************************************************************************
     *      SETUP
     **************************************************************************/
    /** Initial setup */
    public void setup()
    {
        loadFonts();
        loadImages();
        loadSounds();
        
        screenInit();
        
        for (int i = 0; i < MenuScreen.NUMBUTTONS; i++)
        {   
            options[i] = new MenuOption(this, width/2, 310 + 45*i, menuClick);
        }
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
     *      MOUSE FUNCTIONS
     **************************************************************************/
    /** Mouse button event */
    public void mousePressed()
    {
        if (currentScreen == ScreenType.INIT) 
        {
            menuClick.trigger();
            currentScreen = ScreenType.MENU;
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
    /** Loads fonts. List all fonts here. */
    private void loadFonts()
    {
        font = createFont("data/fonts/Grafipaint.ttf", 30);
        textFont(font);
        textAlign(CENTER, CENTER);
    }

    /** Loads Images. List all images here */
    private void loadImages()
    {
        background     = loadImage("data/images/chalkboard-background.png");
        initForeground = loadImage("data/images/game-start.png");
        menuForeground = loadImage("data/images/game-menu.png");
        gameForeground = loadImage("data/images/game-board.png");
    }
    
    /** Loads Images. List all sounds here */
    private void loadSounds()
    {
        chalkX    = minim.loadSample("data/sounds/chalk-x.wav");
        chalkO    = minim.loadSample("data/sounds/chalk-o.wav");
        gameStart = minim.loadSample("data/sounds/game-start.wav");
        menuClick = minim.loadSample("data/sounds/chalk-click.wav"); 
    }
    
    /***************************************************************************
     *      OTHER FUNCTIONS
     **************************************************************************/

    private void screenInit()
    {
        screens.put("Initial", new InitScreen(this, initForeground, background));
        screens.put("Main", new MainMenu(this, menuForeground, background));
        screens.put("Game", new GameScreen(this, gameForeground, background));
        screens.put("Pause", new PauseMenu(this, menuForeground, background));
        screens.put("Difficulty", new DifficultyMenu(this, menuForeground, background));
    }
    
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
