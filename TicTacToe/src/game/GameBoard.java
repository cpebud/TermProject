/*******************************************************************************
 * File Name:			
 * Project:			
 * 
 * Designer(s):		Garrett Cross,
 * 					Omar Kermiche,
 * 					Autumn Nguyen,
 * 					Thomas Pridy
 * 
 * Copyright � 2019. All rights reserved.
 ******************************************************************************/
package game;

import java.util.*;

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
    
    private String hint = "";
    
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
    
    public String getHint()
    {
        return hint;
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
                tiles[index] = new GameTile(this.game, 186 + 113*j, 286 + 113*i);
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
    
    public boolean isFull()
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
    
    public boolean isEmpty()
    {
        for (int i = 0; i < NUM_TILES; i++)
        {
            if (tiles[i].getTileSymbol() != Symbol.EMPTY)
            {
                return false;
            }
        }
        return true;
    }
    
    public void updateHint(int tIndex)
    {
        if (tIndex != -1)
        {
            if (!checkWinTile(tIndex, true))
            {
                this.hint = "Are you sure?";
            }
            else if (!checkBlockTile(tIndex, true))
            {
                if (!checkWinTile(tIndex, false))
                {
                    this.hint = "Careful now...";
                }
            }
            else if (!checkWinFork(tIndex, true))
            {
                if (!checkWinTile(tIndex, false) && !checkBlockTile(tIndex, false))
                {
                    this.hint = "You could be forking.";
                }
            }
            else if (!checkBlockFork(tIndex, true))
            {
                if (!checkWinTile(tIndex, false) && !checkBlockTile(tIndex, false) && !checkWinFork(tIndex, false))
                {
                    this.hint = "Don't get forked.";
                }
            }
            else
            {
                this.hint = "";
            }
        }
        else
        {
            this.hint = "";
        }
        
    }
    
    public boolean checkWinTile(int tIndex, boolean isHint)
    {        
        boolean isGoodTile = isHint;
        
        int[][] lineScores = lineScores(tiles);
        for(int[] line : lineScores)
        {
            if (line[0] == 2)
            {
                if (line[1] == tIndex)
                {
                    return true;
                }
                else
                {
                    isGoodTile = false;
                }
            }
        }
        return isGoodTile;
    }
    
    public boolean checkBlockTile(int tIndex, boolean isHint)
    {        
        boolean isGoodTile = isHint;
        
        int[][] lineScores = lineScores(tiles);
        for (int[] line : lineScores)
        {
            if (line[0] == -2)
            {
                if (line[1] == tIndex)
                {
                    return true;
                }
                else
                {
                    isGoodTile = false;
                }
            }
        }
        return isGoodTile;
    }
    
    public boolean checkWinFork(int tIndex, boolean isHint)
    {   
        boolean isGoodTile = isHint;
        
        for (int i = 0; i < tiles.length; i++)
        {
            if (tiles[i].isEmpty())
            {
                if (forkWinTile(i))
                {
                    if (i == tIndex)
                    {
                        return true;
                    }
                    else
                    {
                        isGoodTile = false;
                    }
                }
            }
        }
        return isGoodTile;
    }
    
    private boolean forkWinTile(int tIndex)
    {
        boolean isGoodTile = false;
        
        Symbol pSymbol = game.getCurrentPlayer().getSymbol();
        GameTile[] tempBoard = copyBoard(tiles);
        tempBoard[tIndex].setTileSymbol(pSymbol);
        
        int count = 0;
        int[][] lineScores = lineScores(tempBoard);
        for (int[] line : lineScores)
        {
            if (line[0] == 2)
            {
                count++;
            }
        }
        if (count == 2)
        {
            isGoodTile = true;
        }
        tempBoard = null;
        return isGoodTile;
    }
    
    public boolean checkBlockFork(int tIndex, boolean isHint)
    {       
        boolean isGoodTile = isHint;
        
        HashSet<Integer> fIndices = new HashSet<>();
        
        int fIndex = -1;
        int count = 0;
        for (int i = 0; i < tiles.length; i++)
        {
            if (tiles[i].isEmpty())
            {
                if (forkBlockTile(i, tiles))
                {
                    //System.out.println("fork found at index: " + i);
                    count++;
                    fIndex = i;
                    fIndices.add(fIndex);
                }
            }
        }
        if (count >= 1)
        {
            isGoodTile = false;
            
            Symbol pSymbol = game.getCurrentPlayer().getSymbol();
            GameTile[] tempBoard = copyBoard(tiles);
            
            tempBoard[tIndex].setTileSymbol(pSymbol);
            
            for (int[] line : lineScores(tempBoard))
            {
                if (line[0] == 2)
                {
                    //System.out.println("will defend");
                    for (int index : fIndices)
                    {
                        //System.out.println("index: "+index);
                        if (line[1] == index)
                        {
                            return false;
                        }
                        else
                        {
                            isGoodTile = true;
                        }
                    }
                }
            }
            if (isGoodTile)
            {
                return true;
            }
            for (int i = 0; i < tiles.length; i++)
            {
                if (tempBoard[i].isEmpty())
                {
                    if (forkBlockTile(i, tempBoard))
                    {
                        //System.out.println(i);
                        return false;
                    }
                    else
                    {
                        isGoodTile = true;
                    }
                }
            }
            tempBoard = null;
        }
        fIndices = null;
        
        return isGoodTile;
    }
    
    private boolean forkBlockTile(int tIndex, GameTile[] tiles)
    {
        boolean isGoodTile = false;
        
        Symbol oSymbol = game.getCurrentPlayer().opponentSymbol();
        GameTile[] tempBoard = copyBoard(tiles);
        tempBoard[tIndex].setTileSymbol(oSymbol);
        
        int count = 0;
        int[][] lineScores = lineScores(tempBoard);
        for (int[] line : lineScores)
        {
            if (line[0] == -2)
            {
                count++;
            }
        }
        if (count == 2)
        {
            isGoodTile = true;
        }
        tempBoard = null;
        
        return isGoodTile;
    }
    
    private int[][] lineScores(GameTile[] board)
    {        
        Symbol pSymbol = game.getCurrentPlayer().getSymbol();
        Symbol oSymbol = game.getCurrentPlayer().opponentSymbol();
        
        final int row1[]  = {0, 1, 2};
        final int row2[]  = {3, 4, 5};
        final int row3[]  = {6, 7, 8};
        
        final int col1[]  = {0, 3, 6};
        final int col2[]  = {1, 4, 7};
        final int col3[]  = {2, 5, 8};
        
        int diag1[] = {0, 4, 8};
        int diag2[] = {2, 4, 6};
        
        int[] lines[] = {row1, row2, row3, col1, col2, col3, diag1, diag2};
        
        int[] lineScores[] = new int[lines.length][2];
        
        for (int i = 0; i < lines.length; i++)
        {
            int count = 0;
            int tile = -1;
            for (int tIndex : lines[i])
            {
                Symbol tSymbol = board[tIndex].getTileSymbol();
                if (tSymbol == pSymbol)
                {
                    count++;
                }
                else if (tSymbol == oSymbol)
                {
                    count--;
                }
                else if (tSymbol == Symbol.EMPTY)
                {
                    tile = tIndex;
                }
                lineScores[i][0] = count;
                lineScores[i][1] = tile;
            }
        }
        return lineScores;
    }
    
    public Boolean isWinner(Symbol s)
    {
    	//Boolean Variable
    	boolean winner = false;
    	//Check Row Winners
    	if ((getTile(0).getTileSymbol() == s) && (getTile(1).getTileSymbol() == s) && (getTile(2).getTileSymbol() == s))
		{
			winner = true;
		}
    	else if ((getTile(3).getTileSymbol() == s) && (getTile(4).getTileSymbol() == s) && (getTile(5).getTileSymbol() == s))
    	{
			winner = true;
    	}
    	else if ((getTile(6).getTileSymbol() == s) && (getTile(7).getTileSymbol() == s) && (getTile(8).getTileSymbol() == s))
    	{
			winner = true;
    	}
    	//Check Column Winners
    	else if ((getTile(0).getTileSymbol() == s) && (getTile(3).getTileSymbol() == s) && (getTile(6).getTileSymbol() == s))
		{
			winner = true;
		}
    	else if ((getTile(1).getTileSymbol() == s) && (getTile(4).getTileSymbol() == s) && (getTile(7).getTileSymbol() == s))
    	{
			winner = true;
    	}
    	else if ((getTile(2).getTileSymbol() == s) && (getTile(5).getTileSymbol() == s) && (getTile(8).getTileSymbol() == s))
    	{
			winner = true;
    	}
    	//Check Diagonal Winners
    	else if ((getTile(0).getTileSymbol() == s) && (getTile(4).getTileSymbol() == s) && (getTile(8).getTileSymbol() == s))
		{
			winner = true;
		}
    	else if ((getTile(2).getTileSymbol() == s) && (getTile(4).getTileSymbol() == s) && (getTile(6).getTileSymbol() == s))
    	{
			winner = true;
    	}
    	
        return winner;
    }
    
    //for minimax alg
// public GameTile[] getAvailableSpaces()
//    {
//    	GameTile[] availSpaces = new GameTile[9];
//    	int totAvailableSp = 0;
//    	for(int i = 0; i < 9; i++)
//    	{    		
//    		if(game.getBoard().getTile(i).isEmpty())
//    			totAvailableSp++;
//    	}
//    	
//    	
//    	
//    	return availTiles;
//    }
    
    
    
    public void resetTile(int tIndex)
    {
    	tiles[tIndex].setTileSymbol(Symbol.EMPTY);   	
    }
    
    private GameTile[] copyBoard(GameTile[] tiles)
    {
        GameTile[] tempTiles = new GameTile[tiles.length];
        for (int i = 0; i < tiles.length; i++ )
        {
            tempTiles[i] = new GameTile(this.game, 0, 0);
            tempTiles[i].setTileSymbol(tiles[i].getTileSymbol());
        }
        return tempTiles;
    }
}
