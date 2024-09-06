package no.uib.inf101.tetris.model.Tetromino;

import java.util.Random;

public class RandomTetrominoFactory implements TetrominoFactory{

    private static final String TETROMINO_TYPES = "LJSZTIO";
    private final Random random;

    public RandomTetrominoFactory(){
        random = new Random();
    }


    @Override
    public Tetromino getNext(){
      char randomType = TETROMINO_TYPES.charAt(random.nextInt(TETROMINO_TYPES.length()));

      return Tetromino.newTetromino(randomType);
    }
    
}
