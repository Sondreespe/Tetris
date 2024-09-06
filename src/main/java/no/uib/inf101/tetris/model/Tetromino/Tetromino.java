package no.uib.inf101.tetris.model.Tetromino;


import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Arrays;
import java.util.Objects;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;

public final class Tetromino implements Iterable<GridCell<Character>> {
    
    private char value;
    public boolean[][] shape;
    private CellPosition pos;

    public Tetromino(char value, boolean[][] shape, CellPosition pos){ 
        this.shape = shape;
        this.value = value;
        this.pos = pos;
    }

    /**
     * This method makes a new tetromino by using the value/char and determines wich shape it is, it also
     * asserts the start position
     * If the value/char is unknown, throw IllegalArgumentException
     * @param value
     * @return Tetromino-object 
     */
    public static Tetromino newTetromino(char value) {
        switch (value) {
            case 'I':
            return new Tetromino('I', new boolean[][]{{false,false,false,false},{true,true,true,true},
            {false,false,false,false},{false,false,false,false}}, new CellPosition(0, 0));

            case 'L':
            return new Tetromino('L', new boolean[][]{{false,false,false},{true,true,true},
            {true,false,false}}, new CellPosition(0, 0));

            case 'J':
            return new Tetromino('J', new boolean[][]{{false,false,false},{true,true,true},
            {false,false,true}}, new CellPosition(0, 0));

            case 'Z':
            return new Tetromino('Z', new boolean[][]{{false,false,false},{true,true,false},
            {false,true,true}}, new CellPosition(0, 0));

            case'S':
            return new Tetromino('S', new boolean[][]{{false,false,false},{false,true,true},
            {true,true,false}}, new CellPosition(0, 0));

            case 'T':
            return new Tetromino('T', new boolean[][]{{false,false,false,},{true,true,true},
            {false,true,false}}, new CellPosition(0, 0));

            case 'O':
            return new Tetromino('O', new boolean[][]{{false,false,false,false},{false,true,true,false},
            {false,true,true,false},{false,false,false,false}}, new CellPosition(0, 0));

            default:
            throw new IllegalArgumentException("Unknown tetromino-symbol for " + value);       
        }
    }

    /**
     * This method takes the two delta parameters and adds it to the current pos
     * @param deltaRow
     * @param deltaCol
     * @return A shifted Tetromino-object
     */
    public Tetromino shiftedBy(int deltaRow, int deltaCol){
        return new Tetromino(value, shape, new CellPosition(pos.row()+deltaRow,pos.col() +deltaCol));
    }

    /**
     * This method shifts the Tetromino-object to the top centrer of the gameboard
     * @param gridDimension
     * @return The tetromino with a pos that is centered to the top center of gameboard
     */
    public Tetromino shiftedToTopCenterOf (GridDimension gridDimension){
        int deltaCol = (gridDimension.cols()-shape[0].length)/2;
        int deltaRow = -1;

        CellPosition newCenterPosition = new CellPosition(deltaRow, deltaCol);

        return new Tetromino(value, shape, newCenterPosition);

    }
   
    @Override
    public Iterator<GridCell<Character>> iterator() {
        List<GridCell<Character>> cellList = new ArrayList<>();

        for ( int row = 0; row < shape.length;row++){
            for(int col = 0;col <shape[row].length;col++){
                if (shape[row][col]){
                    int boardRow = pos.row() + row;
                    int boardCol = pos.col() + col;
                    cellList.add(new GridCell<>(new CellPosition(boardRow, boardCol), value));
                }
            }
        }
        return cellList.iterator();
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.value, Arrays.deepHashCode(this.shape), this.pos);
    }

    @Override
    public boolean equals(Object obj){
       if(this == obj){
        return true;
       }
       if(obj == null || getClass() != obj.getClass()){
        return false;
       }
       Tetromino other = (Tetromino) obj;
       return value == other.value && Arrays.deepEquals(shape, other.shape) && Objects.equals(pos, other.pos);
    }
    /**
     * This method rotates the tetromino clockwise.
     * This method was inspired by: https://stackoverflow.com/questions/68978616/rotating-an-n-m-matrix-in-java
     * @return a clockwise rotateted tetromino
     */
    public Tetromino rotatedTetromino(){
        int numRows = shape.length;
        int numCols = shape[0].length;
        //make a new matrix for the new shape
        boolean[][] rotatedShape = new boolean[numRows][numCols];
        //Double for loop to assign the rotated values to the new matrix
        
        for(int i = 0;i < numRows;i++){
            for(int j = 0; j < numCols; j++){
                rotatedShape[j][numRows-1-i] = shape[i][j];
            }
        }
        return new Tetromino(value, rotatedShape, pos);
    }

    /**
     * 
     * @return the shape of the tetromino
     */
    public boolean[][] getShape(){
        return shape;
    }
    /**
     * 
     * @return the pos of the tetromino
     */
    public CellPosition getPos(){
        return pos;
    }
    /**
     * 
     * @return the value og the tetromino
     */
    public char getValue(){
        return value;
    }
}

