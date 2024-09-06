package no.uib.inf101.grid.Model.Tetromino;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;


import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.Grid;
import no.uib.inf101.grid.GridCell;

import no.uib.inf101.tetris.model.Tetromino.Tetromino;

public class TestTetromino {
    @Test
    public void testHashCodeAndEquals() {
        Tetromino t1 = Tetromino.newTetromino('T');
        Tetromino t2 = Tetromino.newTetromino('T');
        Tetromino t3 = Tetromino.newTetromino('T').shiftedBy(1, 0);
        Tetromino s1 = Tetromino.newTetromino('S');
        Tetromino s2 = Tetromino.newTetromino('S').shiftedBy(0, 0);

        assertEquals(t1, t2);
        assertEquals(s1, s2);
        assertEquals(t1.hashCode(), t2.hashCode());
        assertEquals(s1.hashCode(), s2.hashCode());
        assertNotEquals(t1, t3);
        assertNotEquals(t1, s1);
    }

    @Test
    public void tetrominoIterationOfT() {
        // Create a standard 'T' tetromino placed at (10, 100) to test
        Tetromino tetro = Tetromino.newTetromino('T');
        tetro = tetro.shiftedBy(10, 100);

        // Collect which objects are iterated through
        List<GridCell<Character>> objs = new ArrayList<>();
        for (GridCell<Character> gc : tetro) {
            objs.add(gc);
        }

        // Check that we got the expected GridCell objects
        assertEquals(4, objs.size());
        assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 100), 'T')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 101), 'T')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 102), 'T')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(12, 101), 'T')));
    }   

    @Test
    public void tetrominoIterationOfS(){
         // Create a standard 'S' tetromino placed at (10, 100) to test
         Tetromino tetro = Tetromino.newTetromino('S');
         tetro = tetro.shiftedBy(10, 100);
 
         // Collect which objects are iterated through
         List<GridCell<Character>> objs = new ArrayList<>();
         for (GridCell<Character> gc : tetro) {
             objs.add(gc);
         }
 
         // Check that we got the expected GridCell objects
         assertEquals(4, objs.size());
         assertTrue(objs.contains(new GridCell<>(new CellPosition(12, 100), 'S')));
         assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 101), 'S')));
         assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 102), 'S')));
         assertTrue(objs.contains(new GridCell<>(new CellPosition(12, 101), 'S')));

    }

    @Test
    public void tetrominoMovedTwiceAsFarAsSingelMove(){
        //Create an 'I' tetromino with startpos (0,0)
        Tetromino tetro  = Tetromino.newTetromino('I');
        CellPosition startPositiom = new CellPosition(0, 0);

        int deltaRow = 3;
        int deltaCol = 4;

        //Calculate the final positions after one and two moves
        CellPosition finalPosSingleMove = new CellPosition(startPositiom.row()+deltaRow, startPositiom.col()+deltaCol);
        CellPosition finalPosSecondMove = new CellPosition(startPositiom.row()+ 2*deltaRow, startPositiom.col() + 2*deltaCol);

        //moves the tetromino one time and test if the pos equals the calculated pos
        Tetromino tetro1 = tetro.shiftedBy(deltaRow, deltaCol);
        assertEquals(finalPosSingleMove, tetro1.getPos());

        //moves the tetromino another time and test if the pos equals the calculated pos
        Tetromino tetro2 = tetro1.shiftedBy(deltaRow, deltaCol);
        assertEquals(finalPosSecondMove, tetro2.getPos());
    }

    @Test
    public void shiftedToTopCenterOf(){
       // Create a 4x4 'I' tetromino
       Tetromino tetromino = Tetromino.newTetromino('I');

       // Define grid dimensions
       GridDimension gridDimensionEven = new Grid<>(10, 10); // Even number of columns
       GridDimension gridDimensionOdd = new Grid<>(10, 9); // Odd number of columns

       // Shift Even-tetromino to top center of even columns grid
       Tetromino tetrominoEven = tetromino.shiftedToTopCenterOf(gridDimensionEven);
       CellPosition posEven = tetrominoEven.getPos();

       // Shift Odd-tetromino to top center of odd columns grid
       Tetromino tetrominoOdd = tetromino.shiftedToTopCenterOf(gridDimensionOdd);
       CellPosition posOdd = tetrominoOdd.getPos();

       // Check if Even-tetromino is at top center in even columns grid
       assertEquals(-1, posEven.row()); // Top row
       assertEquals(3, posEven.col()); // Center column

       // Check if Odd-tetromino is at top center in odd columns grid
       assertEquals(-1, posOdd.row()); // Top row
       assertEquals(2, posOdd.col()); // Center column
    }
    
}

    



