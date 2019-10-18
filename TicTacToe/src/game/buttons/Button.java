package game.buttons;

import game.TicTacToe;

public abstract class Button
{   
    /***************************************************************************
     *      VARIABLES
     **************************************************************************/
    
    protected TicTacToe game;
    private ButtonType bType;
        
    private int xCoord;
    private int yCoord;
    
    private int width;
    private int height;
    
    private int fontSize;
    private int fontColor;
    private int fontHover;
    private int fontHighlight;
    
    
    private Boolean hover = false;

    /***************************************************************************
     *      CONSTRUCTOR
     **************************************************************************/
    
    public Button(TicTacToe game) {
        this.game = game;
        
        this.fontSize      = game.getTheme().getFontSize();
        this.fontColor     = game.getTheme().getFontColor();
        this.fontHover     = game.getTheme().getFontHover();
        this.fontHighlight = game.getTheme().getfontHighlight();
    }
    
    /***************************************************************************
     *      SETTERS/GETTERS
     **************************************************************************/
    
    protected void setButtonType(ButtonType bType)
    {
        this.bType = bType;
    }
    
    public ButtonType getButtonType() 
    {
        return this.bType;
    }
    
    protected void setXcoord(int x)
    {
        this.xCoord = x;
    }
    
    public int getXcoord()
    {
        return this.xCoord;
    }
    
    protected void setYcoord(int y)
    {
        this.yCoord = y;
    }
    
    public int getYcoord()
    {
        return this.yCoord;
    }
    
    protected void setCoord(int x, int y)
    {
        setXcoord(x);
        setYcoord(y);
    }
        
    protected void setWidth(int width)
    {
        this.width = width;
    }
    
    public int getWidth()
    {
        return this.width;
    }
    
    protected void setHeight(int height)
    {
        this.height = height;
    }
    
    public int getHeight()
    {
        return this.height;
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
    
    public void setHover(Boolean hover)
    {
        this.hover = hover;
    }
    
    public Boolean getHover()
    {
        return this.hover;
    }
    
    /***************************************************************************
     *      METHODS
     **************************************************************************/
    public void update()
    {
        this.fontSize      = game.getTheme().getFontSize();
        this.fontColor     = game.getTheme().getFontColor();
        this.fontHover     = game.getTheme().getFontHover();
        this.fontHighlight = game.getTheme().getfontHighlight();
    }
    
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
    
    public abstract void display();
    
    /***************************************************************************
     *      ENUMERATOR
     **************************************************************************/
    
    public enum ButtonType
    {
        MENU,
        TILE;
    }
}
