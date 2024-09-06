package no.uib.inf101.tetris.view;

import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.tetris.model.GameState;

public interface ViewableTetrisModel {
   
    /**
     * 
     * @return The dimensions of the grid
     */
    GridDimension getDimension();

    /**
     * 
     * @return An object wich when called upon gives all the cells and its value
     */
    Iterable<GridCell<Character>> getTilesOnBoard();

    /**
     * 
     * @return an iterable object over the tiles of the tetromino
     */
    
    Iterable<GridCell<Character>> getFallingTetromino();

    /**
     * Gets the state of the game; ACTIVE_GAME , PAUSED_GAME etc.
     * @return The state of the game
     */
    GameState getGameState();

    /**
     * The score is based on how many rows you remove and wich level you currently
     * play on.
     * @return current score of the game
     */
    int getScore();

    /**
     * 
     * @return current level of the game
     */
    int getLevel();



    




    
}
