package no.uib.inf101.tetris.model;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.tetris.controller.ControllableTetrisModel;
import no.uib.inf101.tetris.model.Tetromino.Tetromino;
import no.uib.inf101.tetris.model.Tetromino.TetrominoFactory;
import no.uib.inf101.tetris.view.ViewableTetrisModel;

public class TetrisModel implements ViewableTetrisModel, ControllableTetrisModel{
    private TetrisBoard tetrisBoard;
    private TetrominoFactory tetrominoFactory;
    public Tetromino fallingTetromino;
    public static GameState GameState;
    public int score;
    public int level;
    public int totalRowsRemoved;

    
    public TetrisModel(TetrisBoard tetrisBoard, TetrominoFactory tetrominoFactory){
        this.tetrisBoard = tetrisBoard;
        this.tetrominoFactory = tetrominoFactory;
        this.fallingTetromino = tetrominoFactory.getNext().shiftedToTopCenterOf(tetrisBoard);
        this.GameState = GameState.WELCOME_SCREEN;
        this.score = 0;
        this.level = 1;
        this.totalRowsRemoved = 0;
    }

    @Override
    public GridDimension getDimension() {
        return this.tetrisBoard;
    }

    @Override
    public Iterable<GridCell<Character>> getTilesOnBoard() {
        return this.tetrisBoard;
    }

    @Override
    public Iterable<GridCell<Character>> getFallingTetromino() {
        return this.fallingTetromino;
    } 

    /**
     * This method checks if the tetromino is on a legal position
     * @param tetromino
     * @return false if the pos is illegal or true if the pos is legal
     */
    private boolean isLegalMove(Tetromino tetromino){
        int rows = tetrisBoard.rows();
        int cols = tetrisBoard.cols();

        for (GridCell<Character> cell:tetromino){
            CellPosition pos = cell.pos();

            //Checks if the move is within the frame of the game
            if(pos.row() < 0 || pos.row() >= rows || pos.col() < 0 || pos.col() >= cols){
                return false;
            }
            //Checks if the move is not occupied 
            if (tetrisBoard.get(new CellPosition(pos.row(), pos.col())) != '-'){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean moveTetromino(int deltaRow, int deltaCol) {
        //creates a new candidate for the new tetromino with the shifted position
       Tetromino candidate = fallingTetromino.shiftedBy(deltaRow, deltaCol);
        //checks if the candidate is within the legal the table and the cells isnt occupied
       if(isLegalMove(candidate)){
        //Changes the tetrominos pos to the candidates
        fallingTetromino = candidate;
        return true;
       }
       return false;
    }

    @Override
    public boolean rotatedTetromino() {
        // creates a new candidate for the new rotated tetromino
        Tetromino candidate = fallingTetromino.rotatedTetromino();

        if(isLegalMove(candidate)){
            fallingTetromino = candidate;
            return true;
        }
        return false;
    }

    @Override
    public boolean dropTetromino() {
        while ( moveTetromino(1, 0)){
        //moves the fallingTetromino down when the next move return true. stops when next move is false
        }
        //locks the falling tetromino to the tatrisboard
        lockFallingTetromino();
        //retunrs true to indicate the lock was made
        return true;
        
    }
    /**
     * This method gets the new tetromino and checks if it is enough place to make it
     * @return the new tetromino
     */
    private Tetromino getNewFallingTetromino(){
        Tetromino newTetro =  tetrominoFactory.getNext().shiftedToTopCenterOf(tetrisBoard); 
        if(!isLegalMove(newTetro)){
            this.GameState = GameState.GAME_OVER;
        } 
         return newTetro;
    }
    
   //locks the Tetromino to a pos, and calls on a new one
    private void lockFallingTetromino(){
        for( GridCell<Character> cell: fallingTetromino){
            CellPosition pos = cell.pos();
            tetrisBoard.set(pos,fallingTetromino.getValue());
        }
        updateScoreAndLevel(tetrisBoard.removeFullRows());
        
        fallingTetromino = getNewFallingTetromino();
    }

    @Override
    public GameState getGameState() {
      return GameState;
    }

    @Override
    public int getSeconds() {
        int speed = 1000- getLevel()*200;
        return speed;
    }

    @Override
    public boolean clockTick() {
        if(moveTetromino(1, 0)){
            //Moves the tetro if the next move is possible, returns true if the move was made
            return true;
        }else{
            //if not, lock the tetro to current pos
            lockFallingTetromino();
            return false;
        }
    }
    /**
     * Updates the scorelevel based on how many rows was removed
     * @param rowsRemoved
     */
    private void updateScoreAndLevel(int rowsRemoved){
        if (rowsRemoved == 1){
            totalRowsRemoved += 1;
            score += 100 * getLevel();
        }else if( rowsRemoved == 2){
            score += 300 * getLevel();
            totalRowsRemoved += 2;
        }else if(rowsRemoved == 3){
            score += 500 * getLevel();
            totalRowsRemoved += 3;
        }else if(rowsRemoved == 4){
            score += 800 * getLevel();
            totalRowsRemoved += 4;
        }
    }
    
    @Override
    public int getScore(){
        return score;
    }

    @Override
    public int getLevel() {
       return totalRowsRemoved/5+1;
    }
}
