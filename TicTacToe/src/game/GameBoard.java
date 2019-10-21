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
package game;

import game.buttons.GameTile;
import game.buttons.GameTile.Symbol;

public class GameBoard
{
    /***************************************************************************
     *      VARIABLES
     **************************************************************************/
    
    public static final int NUM_TILES = 9;
    public static final int ROWS = 3;
    public static final int COLS = 3;
    
    private TicTacToe game;
    
    private GameTile[] tiles = new GameTile[NUM_TILES];

    /***************************************************************************
     *      CONSTRUCTOR
     **************************************************************************/
    
    public GameBoard(TicTacToe game)
    {
        this.game = game;
        create();
    }
    
    /***************************************************************************
     *      SETTERS/GETTERS
     **************************************************************************/
    
    public GameTile[] getTiles()
    {
        return tiles;
    }
    
    public GameTile getTile(int i)
    {
        return tiles[i];
    }
    
    /***************************************************************************
     *      METHODS
     **************************************************************************/

    private void create()
    {
        for (int i = 0; i < ROWS; i++)
        {
            for (int j = 0; j < COLS; j++)
            {
                int index = ROWS*i + j;
                tiles[index] = new GameTile(this.game, 186 + 113*i, 286 + 113*j);
            }
        }
    }
    
    public void clear()
    {
        for (int i = 0; i < NUM_TILES; i++)
        {
            tiles[i].setTileSymbol(Symbol.EMPTY);
        }
    }
    
    public void display()
    {
        for (int i = 0; i < NUM_TILES; i++)
        {
            tiles[i].display();
        }
    }
    
    public Boolean isFull()
    {
        for (int i = 0; i < NUM_TILES; i++)
        {
            if (tiles[i].getTileSymbol() == Symbol.EMPTY)
            {
                return false;
            }
        }
        return true;
    }
}
