package game.buttons;

import game.TicTacToe;
import processing.core.PConstants;

public class GameTile extends Button
{
    private Tile tType;
    
    public GameTile(TicTacToe game, int x, int y)
    {
        super(game);
        setButtonType(ButtonType.TILE);
        this.tType = Tile.EMPTY;
        setWidth(90);
        setHeight(90);
        setXcoord(x);
        setYcoord(y);        
    }
    
    public void setTileType(Tile type)
    {
        this.tType = type;
    }
    
    public Tile getTileType()
    {
        return tType;
    }

    @Override
    public void display()
    {
        switch(tType)
        {
        case EX:
            break;
        case OH:
            break;
        default:
            if (getHover())
            {
                game.stroke(0, 0);
                game.fill(100, 100, 100, 100);
                game.rectMode(PConstants.CENTER);
                game.rect(getXcoord(), getYcoord(), getWidth(), getHeight(), 15);
            }
            break;
        }   
    }
    
    public enum Tile
    {
        EX,
        OH,
        EMPTY;
    }
}
