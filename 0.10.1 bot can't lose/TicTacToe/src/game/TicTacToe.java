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

import ddf.minim.AudioSample;
import ddf.minim.Minim;
import game.buttons.GameTile;
import game.buttons.GameTile.Symbol;
import game.buttons.MenuOption;
import game.players.Computer;
import game.players.Human;
import game.players.Player;
import game.players.Player.PlayerType;
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
import game.screens.WinScreen;
import processing.core.PApplet;
import util.Reference;


/**
 * <tt>TicTacToe</tt> is  a game created using Processing 
 *      in combination with Eclipse.
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
    
    /*   Current Theme   */
    private Theme currentTheme = Theme.CHALK;

    /*   Current Screens   */
    private Stack<ScreenType> currentScreens = new Stack<>();
    private ScreenType currentScreen;     // Current screen of applet
    
    private Stack<Menu> currentMenus = new Stack<>();
    private Menu currentMenu;                   // Current menu of applet
    
    private Player currentPlayer;
    
    /*   Game Difficulty   */
    private Difficulty difficulty = Difficulty.EASY;
        
    /*   Sounds   */
    Minim minim = new Minim(this);
    public AudioSample click;
    public AudioSample win;
    private boolean soundsOn = true;
    
    public Player player1 = new Human(this);
    public Player player2 = new Computer(this);
    public Player playerAlt = new Human(this);
    
    private Map<String, Screen> screens = new HashMap<>();
    
    private MenuOption[] options = new MenuOption[MenuScreen.NUM_BUTTONS];
    
    private GameBoard board;
    
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
        
        click = getTheme().getMenuClick();
        win = getTheme().getGameWin();
        
        board = new GameBoard(this);
        
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
        case WIN:
            screens.get("Win").display();
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
        switch(currentScreen)
        {
        case INIT:
            if (getSoundsOn()) { click.trigger(); }
            currentScreens.push(ScreenType.MENU);
            setCurrentScreen();
            break;
            
        case MENU:
            for (int i = 0; i < MenuScreen.NUM_BUTTONS; i++)
            {
                if (options[i].isInside(mouseX, mouseY))
                {
                    if (getSoundsOn()) { click.trigger(); }
                    options[i].getFunction(options[i].getLabel());
                }
            }
            break;
            
        case GAME:
            if (currentPlayer.getType() == PlayerType.HUMAN)
            {
                for (int i = 0; i < GameBoard.NUM_TILES; i++)
                {
                    GameTile tile = board.getTile(i);
                    if (tile.isEmpty() && tile.isInside(mouseX, mouseY)) 
                    {
                        if (getSoundsOn()) { click.trigger(); }
                        tile.setTileSymbol(currentPlayer.getSymbol());
                        currentPlayer.takeTurn();
                    }
                }
            }
            break;
        case WIN:
           
            goMainMenu();
            break;
        default:
            break;
        }
    }
    
    /** Mouse move event */
    public void mouseMoved()
    {
        redraw();
        switch (currentScreen)
        {
        case MENU:
            for (int i = 0; i < MenuScreen.NUM_BUTTONS; i++)
            {
                options[i].setHover(options[i].isInside(mouseX, mouseY));
            }
            break;
            
        case GAME:
            for (int i = 0; i < GameBoard.NUM_TILES; i++)
            {
                GameTile tile = board.getTile(i);

                tile.setHover(tile.isInside(mouseX, mouseY));
            }

            if (getCurrentPlayer().getType() == PlayerType.COMPUTER)
            {
                getCurrentPlayer().takeTurn();
            }
            break;
            
        default:
            break;
        }   
    }
    
    /** Key press event */
    public void keyPressed()
    {          
        if (currentScreen == ScreenType.GAME)
        {
            if (keyCode == 80)  // P
            {
                changeScreen(ScreenType.MENU, Menu.PAUSE);
            }
        }
    }
    
    /***************************************************************************
     *      LOAD FUNCTIONS
     **************************************************************************/
    
    /** Load themes */
    private void loadThemes()
    {
        for (Theme theme : Theme.values())
        {
            theme.load(this, minim);
        }
    }
    
    /** Load screens */
    private void loadScreens()
    {
        screens.put("Initial", new InitScreen(this));
        screens.put("Main", new MainMenu(this));
        screens.put("Game", new GameScreen(this));
        screens.put("Pause", new PauseMenu(this));
        screens.put("Difficulty", new DifficultyMenu(this));
        screens.put("Settings", new SettingsMenu(this));
        screens.put("Themes", new ThemesMenu(this));
        screens.put("Win", new WinScreen(this));
    }
    
    /** Load buttons */
    private void loadButtons()
    {
        for (int i = 0; i < MenuScreen.NUM_BUTTONS; i++)
        {   
            options[i] = new MenuOption(this, width/2, 310 + 45*i);
        }
    }
    
    /***************************************************************************
     *      SETTERS/GETTERS
     **************************************************************************/
    
    /** 
     * Gets the current <tt>Theme</tt>
     * @return current theme
     */
    public Theme getTheme()
    {
        return currentTheme;
    }
    
    /** Sets the current <tt>ScreenType</tt>
     *       to the top screen of the stack */
    private void setCurrentScreen()
    {
        this.currentScreen = currentScreens.peek();
    }
    
    /** 
     * Gets the current <tt>ScreenType</tt> 
     * @return current screen
     */
    public ScreenType getCurrentScreen()
    {
        return currentScreen;
    }
    
    /** Sets the current <tt>Menu</tt> 
     *       to the top menu of the stack */
    private void setCurrentMenu()
    {
        this.currentMenu = currentMenus.peek();
    }
    
    /** 
     * Gets the current <tt>Menu</tt> 
     * @return current menu
     */
    public Menu getCurrentMenu()
    {
        return currentMenu;
    }
    
    /**
     * @param currentPlayer : the currentPlayer to set
     */
    private void setCurrentPlayer(Player currentPlayer)
    {
        this.currentPlayer = currentPlayer;
    }
    
    /**
     * @return the currentPlayer
     */
    public Player getCurrentPlayer()
    {
        return currentPlayer;
    }
    
    public Player getOtherPlayer(Player currentPlayer)
    {
    	Player otherPlayer = currentPlayer;
    	
    	 if (currentPlayer == player1)
         {
             otherPlayer = player2;
         }
         else if (currentPlayer == player2)
         {
             otherPlayer = player1;
         }
    	 
    	 return otherPlayer;
    }
    
    public TicTacToe getNewState(int move)
    {
   
    	getBoard().getTile(move).setTileSymbol(currentPlayer.getSymbol());
    	
    	return this;
    }
    
  
    
    public Player setOpposingToken()
    {
    	if(currentPlayer.getSymbol() == Symbol.EX)
    		currentPlayer.setSymbol(Symbol.OH);
    	else if(currentPlayer.getSymbol() == Symbol.OH)
    		currentPlayer.setSymbol(Symbol.EX); 
    	
    	if(currentPlayer.getType() == PlayerType.COMPUTER)
    		currentPlayer.setType(PlayerType.HUMAN);
    	else if(currentPlayer.getType() == PlayerType.HUMAN)
    		currentPlayer.setType(PlayerType.COMPUTER);
    		
    	return currentPlayer;
    }

    /** 
     * Sets the difficulty of the computer player
     * @param difficulty : difficulty to set
     */
    private void setDifficulty(Difficulty difficulty)
    {
        this.difficulty = difficulty;
    }
    
    /** 
     * Gets the current difficulty of the computer player
     * @return
     */
    public Difficulty getDifficulty()
    {
        return difficulty;
    }
    
    /**
     * Gets the <tt>MenuOption <i>Button</i></tt>
     *      at the given index
     * @param i : index of <tt><i>Button</i></tt>
     * @return  
     */
    public MenuOption getOption(int i)
    {
        return options[i];
    }
    
    public GameBoard getBoard()
    {
        return board;
    }
    
    private void setSoundsOn(Boolean soundsOn)
    {
        this.soundsOn = soundsOn;
    }
    
    public Boolean getSoundsOn()
    {
        return soundsOn;
    }

    /***************************************************************************
     *      OTHER FUNCTIONS
     **************************************************************************/
    
    /**
     * Pushes the given <tt>ScreenType</tt> and <tt>Menu</tt>
     *      to their respective stack and sets them as the 
     *      current screen and menu.
     * @param screen : New screen to switch to
     * @param menu : New menu to switch to
     */
    public void changeScreen(ScreenType screen, Menu menu)
    {
        currentScreens.push(screen);
        setCurrentScreen();
        
        currentMenus.push(menu);
        setCurrentMenu();
    }
    
    /**
     * Changes the current screen to MENU and current menu to MAIN  
     */
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
    
    public void assignPlayerSymbol()
    {
        int random = (int)(Math.random() * 2); // Randomly select 0 or 1
        if (random == 0)
        {
            player1.setSymbol(Symbol.OH);
            player2.setSymbol(Symbol.EX);
            setCurrentPlayer(player2);
        }
        else
        {
            player1.setSymbol(Symbol.EX);
            player2.setSymbol(Symbol.OH);
            setCurrentPlayer(player1);
        }
    }
    
    public void switchTokens()
    {
    	if(player1.getSymbol() == Symbol.EX)
    	{
    		player2.setSymbol(Symbol.EX);
    		player1.setSymbol(Symbol.OH);
    	}
    	else if(player1.getSymbol() == Symbol.OH)
    	{
    		player2.setSymbol(Symbol.OH);
    		player1.setSymbol(Symbol.EX);
    	}
    }
    
    
    
    public void nextPlayer()
    {
        if (currentPlayer == player1)
        {
            setCurrentPlayer(player2);
        }
        else if (currentPlayer == player2)
        {
            setCurrentPlayer(player1);
        }
    }
    
    
    public void updateDifficulty(Difficulty difficulty)
    {
        setDifficulty(difficulty);
        if (player2.getType() == PlayerType.COMPUTER)
        {
            ((Computer)player2).updateDifficulty();
        }
    }
    
    public void updateCurrentTheme(Theme theme)
    {
        this.currentTheme = theme;
        updateTheme();
    }
    
    private void updateTheme()
    {
        click = getTheme().getMenuClick();
        win = getTheme().getGameWin();
        for (Screen screen : screens.values())
        {
            screen.update();
        }
        
        for (MenuOption option : options)
        {
            option.update();
        }
        
        for (GameTile tile : board.getTiles())
        {
            tile.update();
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
