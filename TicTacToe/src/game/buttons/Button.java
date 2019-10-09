package game.buttons;

import ddf.minim.AudioSample;
import processing.core.PApplet;

public abstract class Button
{   
    PApplet parent;
    ButtonType bType;
    
    private int xCoord;
    private int yCoord;
    
    private int width;
    private int height;
    
    private String label;
    private AudioSample sound;
    
    private Boolean hover;
    
    public Button(PApplet parent) {
        setParent(parent);
    }
    
    private void setParent(PApplet parent)
    {
        this.parent = parent;
    }
    
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
    
    public void setLabel(String label)
    {
        this.label = label;
    }
    
    public String getLabel()
    {
        return this.label;
    }
    
    protected void setSound(AudioSample sound)
    {
        this.sound = sound;
    }
    
    public AudioSample getSound()
    {
        return this.sound;
    }
    
    public void setHover(Boolean hover)
    {
        this.hover = hover;
    }
    
    public Boolean getHover()
    {
        return this.hover;
    }
    
    public abstract Boolean isInside(float mx, float my);
    
    public abstract void display(String label);
    
    public enum ButtonType
    {
        MENU, TILE;
    }
}
