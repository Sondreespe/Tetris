package no.uib.inf101.tetris.controller;

import no.uib.inf101.tetris.model.GameState;

public interface ControllableTetrisModel {
    

    /**
     * Moves the tetromino by the deltavalues
     * @param deltaRow
     * @param deltaCol
     * @return a boolean value if the moove was completed
     */
    boolean moveTetromino(int deltaRow,int deltaCol);

    /**
     * Rotates the falling tetromino clockwise
     * @return a boolean if the rotation is a legal move
     */
    boolean rotatedTetromino();

    /**
     * Drops the tetromino down until the next move is not legal.
     * when the next move is not legal it fixes the falling tetromino to that position on the board
     * @return a boolean to indicate if the falling tetromino is fixed to the lowest position
     */
    boolean dropTetromino();

    /**
     * Gets the state of the game; ACTIVE_GAME , PAUSED_GAME etc.
     * @return The state of the game
     */
    GameState getGameState();

    /**
     * This method returns an Int witch represents  milliseconds between ticks.
     * 
     * @return the milliseconds between each tick
     */
    int getSeconds();

    /**
     * This method is called whenever the clockTicks , then calls on the repaint method
     * @return
     */
    boolean clockTick();
}
