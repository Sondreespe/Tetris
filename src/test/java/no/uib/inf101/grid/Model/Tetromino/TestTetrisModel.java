package no.uib.inf101.grid.Model.Tetromino;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.tetris.model.TetrisBoard;
import no.uib.inf101.tetris.model.TetrisModel;
import no.uib.inf101.tetris.model.Tetromino.RandomTetrominoFactory;
import no.uib.inf101.tetris.model.Tetromino.Tetromino;
import no.uib.inf101.tetris.model.Tetromino.TetrominoFactory;
import no.uib.inf101.tetris.view.ViewableTetrisModel;

public class TestTetrisModel {
    @Test
    public void initialPositionOfO() {
        //Create a new Tetromino for testing
        TetrisBoard board = new TetrisBoard(20, 10);
        TetrominoFactory factory = new PatternedTetrominoFactory("O");
        ViewableTetrisModel model = new TetrisModel(board, factory);

    //adds all cells occupied of the falling tetrominocells to the tetroCells list
    List<GridCell<Character>> tetroCells = new ArrayList<>();
    for (GridCell<Character> gc : model.getFallingTetromino()) {
        tetroCells.add(gc);
    }

    assertEquals(4, tetroCells.size());
    assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 4), 'O')));
    assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 5), 'O')));
    assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(1, 4), 'O')));
    assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(1, 5), 'O')));
    }

    @Test
    public void initialPositionOfI() {
        //creates a new tetromino for testing
        TetrisBoard board = new TetrisBoard(20, 10);
        TetrominoFactory factory = new PatternedTetrominoFactory("I");
        ViewableTetrisModel model = new TetrisModel(board, factory);
        // adds all cells occupied by the falling tetromino to the list tetroCells
        List<GridCell<Character>> tetroCells = new ArrayList<>();
        for (GridCell<Character> gc : model.getFallingTetromino()) {
        tetroCells.add(gc);
        }

        assertEquals(4, tetroCells.size());
        assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0,3 ), 'I')));
        assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 4), 'I')));
        assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 5), 'I')));
        assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 6), 'I')));
    }

    @Test
    public void testMoveTetromino() {
        // Create a new tetrismodel for testing
        TetrisBoard tetrisBoard = new TetrisBoard(10, 10);
        TetrominoFactory tetrominoFactory = new RandomTetrominoFactory();
        TetrisModel tetrisModel = new TetrisModel(tetrisBoard, tetrominoFactory);

        // Flytt tetrominoen til høyre
        boolean movedRight = tetrisModel.moveTetromino(0, 1);
        assertTrue(movedRight, "Move Tetromino to the right should return true");

        // Flytt tetrominoen ned
        boolean movedDown = tetrisModel.moveTetromino(1, 0);
        assertTrue(movedDown, "Move Tetromino down should return true");

        // Flytt tetrominoen utenfor brettet (feil flytting)
        boolean movedOutOfBounds = tetrisModel.moveTetromino(0, 20);
        assertFalse(movedOutOfBounds, "Move Tetromino out of bounds should return false");
    }

    @Test
    public void testRotatedTetromino_T(){
        Tetromino tetro = Tetromino.newTetromino('T');
        Tetromino rotatedTetro = tetro.rotatedTetromino();

        boolean[][] expectetShape = {{false,true,false},{true,true,false},{false,true,false}};

        assertArrayEquals(expectetShape, rotatedTetro.getShape());
    }

    @Test
    public void testRotatedTetromino_O(){
        Tetromino tetro = Tetromino.newTetromino('O');
        Tetromino rotatedTetromino = tetro.rotatedTetromino();

        boolean[][] expectetShape = {
        {false,false,false,false},
        {false,true,true,false},
        {false,true,true,false},
        {false,false,false,false}};

        assertArrayEquals(expectetShape, rotatedTetromino.getShape());
    }
    @Test
    public void testDropTetromino(){
        TetrisBoard tetrisBoard = new TetrisBoard(10, 10);
        TetrominoFactory tetromino = new RandomTetrominoFactory();
        TetrisModel tetrisModel = new TetrisModel(tetrisBoard, tetromino);

        boolean droppedTetro = tetrisModel.dropTetromino();
        assertTrue(droppedTetro);
        
    }
    @Test
    public void testRotateFallingTetro(){

        //createsm a new tetrisboard, tetrominofactory and tetrismodel
        TetrisBoard tetrisBoard = new TetrisBoard(10, 10);
        TetrominoFactory tetrominoFactory = new RandomTetrominoFactory();
        TetrisModel tetrisModel = new TetrisModel(tetrisBoard, tetrominoFactory);
        //assigns the shape of the new tetromino
        boolean[][] shape = {{false,false,false,false},
        {true,true,true,true},
        {false,false,false,false},
        {false,false,false,false}};
        //makes the new tetromino
        tetrisModel.fallingTetromino = new Tetromino('I',shape, new CellPosition(5, 5));
        boolean rotate = tetrisModel.rotatedTetromino();
        Tetromino tetromino = tetrisModel.fallingTetromino;

        //checks if the rotation was done
        assertTrue(rotate);
        //checks if the original shape is different from the new rotated tetrominos pos
        assertNotEquals(tetromino.getShape(), shape);

    }

    @Test
    public void testClockTick(){
        TetrisBoard tetrisboard = new TetrisBoard(10, 10);
        TetrominoFactory tetrominoFactory = new RandomTetrominoFactory();

        TetrisModel tetrismodel = new TetrisModel(tetrisboard, tetrominoFactory);
        boolean [][] shape ={
        {false,false,false,false},
        {false,true,true,false},
        {false,true,true,false},
        {false,false,false,false}};

        tetrismodel.fallingTetromino = new Tetromino('O', shape ,new CellPosition(0, 5));
        boolean moved = tetrismodel.clockTick();

        assertTrue(moved);

        assertEquals(new CellPosition(1, 5), tetrismodel.fallingTetromino.getPos());
    }

    @Test
    public void testClockTickNotMovable(){
        TetrisBoard tetrisBoard = new TetrisBoard(10,10);
        TetrominoFactory tetrominoFactory = new RandomTetrominoFactory();

        boolean [][] shape ={
            {false,false,false,false},
            {false,true,true,false},
            {false,true,true,false},
            {false,false,false,false}};

            TetrisModel tetrismodel = new TetrisModel(tetrisBoard, tetrominoFactory);
            tetrismodel.fallingTetromino = new Tetromino('O', shape ,new CellPosition(7, 5));
            boolean notMoved = tetrismodel.clockTick();

            assertFalse(notMoved);
    }

}
