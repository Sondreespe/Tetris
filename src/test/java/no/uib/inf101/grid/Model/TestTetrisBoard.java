package no.uib.inf101.grid.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.tetris.model.TetrisBoard;

public class TestTetrisBoard {
    
    @Test
    public void prettyStringTest() {
        TetrisBoard board = new TetrisBoard(3, 4);
        board.set(new CellPosition(0, 0), 'g');
        board.set(new CellPosition(0, 3), 'y');
        board.set(new CellPosition(2, 0), 'r');
        board.set(new CellPosition(2, 3), 'b');
        String expected = String.join("\n", new String[] {
            "g--y",
            "----",
            "r--b"
        });
        assertEquals(expected, board.prettyString());
    }

    @Test
    public void testRemoveFullRows() {
        TetrisBoard board =  getTetrisBoardWithContents(new String[] {
            "-T",
            "TT",
            "LT",
            "L-",
            "LL"
        });
        assertEquals(3, board.removeFullRows());
        String expected = String.join("\n", new String[] {
            "--",
            "--",
            "--",
            "-T",
            "L-"
        });
        assertEquals(expected, board.prettyString());
    }

    @Test
    public void testLowestRowMustBeKept(){
        TetrisBoard board = getTetrisBoardWithContents(new String[]{
            "TT",
            "TT",
            "TT",
            "TT",
            "-T"
        });

        assertEquals(4, board.removeFullRows());
        String expected = String.join("\n", new String[]{
            "--",
            "--",
            "--",
            "--",
            "-T"
        });
        assertEquals(expected, board.prettyString());
    }

    @Test
    public void removeTopRow(){
        TetrisBoard board = getTetrisBoardWithContents(new String[]{
            "TT",
            "-T",
            "L-",
            "J-"
        });
        assertEquals(1, board.removeFullRows());
        String expected = String.join("\n", new String[]{
            "--",
            "-T",
            "L-",
            "J-"
        });
        assertEquals(expected, board.prettyString());
    }
    // A test for a 
    @Test
    public void testWithWierdBoardDimension(){
        TetrisBoard board = getTetrisBoardWithContents(new String[]{
            "-L-L-L-",
            "TTTTTTT"
        });
        assertEquals(1, board.removeFullRows());
        String expected = String.join("\n", new String[]{
            "-------",
            "-L-L-L-"
        });
        assertEquals(expected, board.prettyString());
    }

    // A private method to turn strings into tetrisboards
    private TetrisBoard getTetrisBoardWithContents(String[] string) {
        TetrisBoard tetrisBoard = new TetrisBoard(string.length,string[0].length());
        for(int row = 0; row < string.length;row++){
            for( int col = 0; col <string[0].length(); col++){  
                Character value = string[row].charAt(col);
                tetrisBoard.set(new CellPosition(row, col), value);
            }
        }
        return tetrisBoard;
    }

   

      


   

}