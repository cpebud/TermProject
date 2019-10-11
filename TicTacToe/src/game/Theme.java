package game;

import ddf.minim.AudioSample;
import ddf.minim.Minim;
import processing.core.PFont;
import processing.core.PImage;
/**
 * <tt> Theme </tt> enum represents a design theme, including:
 * fonts, colors, images, and sounds.
 * <p>
 * NOTE: 
 * <p><tt>fonts</tt> must have filepath "/data/fonts/[Theme Label]-font.ttf"
 * <p><tt>images</tt> must have filepath "/data/images/
 *                                        [Theme Label]-[Image Designation].png"
 * <p><tt>sounds</tt> must have filepath "/data/sounds/
 *                                        [Theme Label]-[Sound Designation].png"
 * 
 * @author  Garrett Cross,
 *          Omar Kermiche,
 *          Autumn Nguyen,
 *          Thomas Pridy
 * 
 * @version 1.0.0
 * @since   10/09/2019
 *
 */
public enum Theme
{
    RETRO("retro", 45, 0xff64f00e, 0xffec1c24, 0xffffffff),
    //DEFAULT("default", 0xff000000, 0xff6464, 0xffd63c3c),
    CHALK("chalk", 100, 0xffffffff, 0Xff646464, 0xffd63c3c); // white, grey, red
    
    
    /***************************************************************************
     *      VARIABLES
     **************************************************************************/

    private TicTacToe game;
    private Minim minim;
    
    private String label;
    
    private String fontFilePath;
    private PFont font;
    private int fontSize;
    private int fontColor;
    private int fontHover;
    private int fontHighlight;
    
    private String imageFilePath;
    private PImage background;
    private PImage initForeground;
    private PImage menuForeground;
    private PImage gameForeground;
    
    private String soundFilePath;
    private AudioSample menuClick;
    
    /***************************************************************************
     *      CONSTRUCTOR
     **************************************************************************/
    
    private Theme(String label, int fontSize, int fontColor, int fontHover, int fontHighlight)
    {
        this.label = label;
        this.imageFilePath = "/data/images/" + label;
        this.fontFilePath = "/data/fonts/" + label;
        this.soundFilePath = "/data/sounds/" + label;
        this.fontSize = fontSize;
        this.fontColor = fontColor;
        this.fontHover = fontHover;
        this.fontHighlight = fontHighlight;
    }
    
    /***************************************************************************
     *      SETTERS/GETTERS
     **************************************************************************/
    
    public String getLabel()
    {
        return label;
    }

    public PFont getFont()
    {
        return font;
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
    
    public int getfontHighlight()
    {
        return fontHighlight;
    }
    
    public PImage getBackground()
    {
        return background;
    }
    
    public PImage getInitForeground()
    {
        return initForeground;
    }
    
    public PImage getMenuForeground()
    {
        return menuForeground;
    }
    
    public PImage getGameForeground()
    {
        return gameForeground;
    }
    
    public AudioSample getMenuClick()
    {
        return menuClick;
    }
    
    /***************************************************************************
     *      METHODS
     **************************************************************************/
    
    public void load(TicTacToe game, Minim minim)
    {
        this.game = game;
        this.minim = minim;
        loadFont();
        loadImages();
        //loadSounds();
    }
    
    public void loadFont()
    {
        this.font = game.createFont(fontFilePath + "-font.ttf", 20);
    }
    
    public void loadImages()
    {
        this.background     = game.loadImage(imageFilePath + "-background.png");
        this.initForeground = game.loadImage(imageFilePath + "-start.png");
        this.menuForeground = game.loadImage(imageFilePath + "-menu.png");
        this.gameForeground = game.loadImage(imageFilePath + "-board.png");
    }
    
    public void loadSounds()
    {
        this.menuClick = minim.loadSample(soundFilePath + "-click.wav");
    }
}
