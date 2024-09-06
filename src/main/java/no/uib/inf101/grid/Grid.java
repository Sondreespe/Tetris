package no.uib.inf101.grid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Grid<E> implements IGrid<E> {
    private int rows;
    private int cols;
    List<List<E>> grid;
    public Grid(int rows, int cols, E defaultValue){
        this.rows = rows;
        this.cols = cols;
        grid = new ArrayList<>();
        for( int i = 0; i <this.rows;i++){
            List<E> rowList = new ArrayList<>();
            for ( int j = 0; j < this.cols;j++){
                rowList.add(defaultValue);
            }
            grid.add(rowList);
        }  
    }
    public Grid(int row, int col){
        this(row, col, null);
    }
    @Override
    public E get(CellPosition pos) throws IndexOutOfBoundsException {
        if(!positionIsOnGrid(pos)){
            throw new IndexOutOfBoundsException("Position is not on grid");
        }else{
        return grid.get(pos.row()).get(pos.col());
        }
    }
    @Override 
    public boolean positionIsOnGrid(CellPosition pos) {
        boolean isOnRow = pos.row() >= 0 && pos.row()<this.rows();
        boolean isOnCOl = pos.col()>= 0 && pos.col() < this.cols();
        return isOnCOl && isOnRow;
    }
    @Override
    public void set(CellPosition pos, E value) {
            if(!positionIsOnGrid(pos)){
                throw new IndexOutOfBoundsException("Position is not on grid");
            }else{
            grid.get(pos.row()).set(pos.col(),value);
            }
    }
    @Override
    public int rows() {
        return this.rows;
    }
    @Override
    public int cols() {
        return this.cols;
    }
    @Override
    public Iterator<GridCell<E>> iterator() {
        ArrayList<GridCell<E>> cellList = new ArrayList<>();
        for ( int i = 0; i<this.rows; i++){
            for(int j = 0; j< this.cols;j++){
                CellPosition pos = new CellPosition(i, j);
                GridCell<E> cell = new GridCell<>(pos,get(pos));
                cellList.add(cell);
            }
        }
        return cellList.iterator();   
    }
}
