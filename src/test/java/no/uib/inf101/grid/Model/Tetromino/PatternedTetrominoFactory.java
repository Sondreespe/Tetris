package no.uib.inf101.grid.Model.Tetromino;

import no.uib.inf101.tetris.model.Tetromino.Tetromino;
import no.uib.inf101.tetris.model.Tetromino.TetrominoFactory;

public class PatternedTetrominoFactory implements TetrominoFactory{

    private String pattern;
    private int index;


    public PatternedTetrominoFactory(String pattern){
        this.pattern = pattern;
        this .index = 0;
    }

    @Override
    public Tetromino getNext() {
        char SymbolNext = pattern.charAt(index);
        index = (index + 1) % pattern.length();
        return Tetromino.newTetromino(SymbolNext);
    }
    
}
