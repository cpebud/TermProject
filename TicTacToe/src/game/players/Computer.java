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
package game.players;

import game.GameBoard;
import game.TicTacToe;
import game.TicTacToe.Difficulty;

public class Computer extends Player
{    
    /***************************************************************************
     *      VARIABLES
     **************************************************************************/
    
    private Difficulty difficulty;


    /***************************************************************************
     *      CONSTRUCTOR
     **************************************************************************/
    
    public Computer(TicTacToe game)
    {
        super(game);
        
        setType(PlayerType.COMPUTER);

        this.difficulty = game.getDifficulty();
    }
    
    /***************************************************************************
     *      SETTERS/GETTERS
     **************************************************************************/
    
    private void setDifficulty(Difficulty difficulty)
    {
        this.difficulty = difficulty;
    }
    
    /***************************************************************************
     *      METHODS
     **************************************************************************/
    
    public void updateDifficulty()
    {
        setDifficulty(game.getDifficulty());
    }
    
    @Override
    public void takeTurn()
    {
        

        game.setCurrentPlayer(this);
        game.thread("pause");
        switch(difficulty)
        {
        case EASY:
            easyTurn();
            break;
        case MEDIUM:
            break;
        case HARD:
            break;
        }
        incTurn();
        game.setCurrentPlayer(game.player1);
    }
    
    private void easyTurn()
    {
        GameBoard board = game.getBoard();
        if (!board.isFull())
        {
            int random = (int)(Math.random() * GameBoard.NUM_TILES);
            while (!board.getTile(random).isEmpty())
            {
                random = (int)(Math.random() * GameBoard.NUM_TILES);
            }
            board.getTile(random).setTileSymbol(getSymbol());
        }
    }
}
