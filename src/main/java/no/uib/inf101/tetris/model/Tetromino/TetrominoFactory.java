package no.uib.inf101.tetris.model.Tetromino;

public interface TetrominoFactory {
    /**
     * This method gets the next tetromino 
     * @return The new Tetromino
     */
    Tetromino getNext();
}
