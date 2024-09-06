package no.uib.inf101.grid.Model.Tetromino;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import no.uib.inf101.tetris.model.Tetromino.Tetromino;
import no.uib.inf101.tetris.model.Tetromino.TetrominoFactory;

public class TestPatternedTetrominoFactory {
    
    @Test
    public void sanityTestPatternedTetrominoFactory() {
        TetrominoFactory factory = new PatternedTetrominoFactory("TSZ");

        assertEquals(Tetromino.newTetromino('T'), factory.getNext());
        assertEquals(Tetromino.newTetromino('S'), factory.getNext());
        assertEquals(Tetromino.newTetromino('Z'), factory.getNext());
        assertEquals(Tetromino.newTetromino('T'), factory.getNext());
        assertEquals(Tetromino.newTetromino('S'), factory.getNext());
    }

}
