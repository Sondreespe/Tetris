package no.uib.inf101.tetris.model;


import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.Grid;


public class TetrisBoard extends Grid<Character>{
   
 
    public TetrisBoard(int row, int col) {
        super(row, col, '-');
    }
    
    /**
     * A debug method that prints out the cells and corresponding values
     * @param tetrisBoard
     * @return a string that resembles the board
     */
    public String prettyString(){
        String string = "";
        for (int row = 0; row < this.rows();row++){
            for(int col= 0; col< this.cols();col++){
                string+= (get(new CellPosition(row, col)));
            }
            if(row< rows()-1){
                string+= ("\n");
            }
        }
        return string.toString();
    }

    
    /**
     * Checks if the row with the given index is empty. 
     * @param rowIndex
     * @return true if the row is empty, false otherwise
     */
    public boolean checkIfRowIsFull(int rowIndex){
       for(int col = 0; col < cols(); col++){
        if(get(new CellPosition(rowIndex, col)) == '-'){
            return false;
        }
       }
       return true;
    }

    /**
     * A method that sets the row with given row index to a given value
     * @param rowIndex
     */
    public void setRowToGivenValue(int rowIndex,char value){
        for(int col = 0; col <cols();col++){
            set(new CellPosition(rowIndex, col), value);
        }

    }

    /**
     * A method that takes all the elements from a wanted row with index: rowIndex, copies their values 
     * and mirror them to the new wanted row.
     * @param rowIndex index of row that is copied from
     * @param newRowIndex index of row copied to
     */
    public void copyRowToAnother(int rowIndex, int newRowIndex){
        for(int col = 0; col < cols();col++){
            char elementCelle = get(new CellPosition(rowIndex, col));
            set(new CellPosition(newRowIndex, col), elementCelle);
        }
    }

    /**
     * A method that removes full rows and returns the number of removed rowes
     * @return Number of removed rows
     */
    public int removeFullRows(){
        int rowsRemoved = 0;
        int a = rows()-1;
        int b  = rows()-1;
        
        while( a >= 0){
            while(b >= 0 && checkIfRowIsFull(b)){
                rowsRemoved ++;
                b --;
            }
            if(b>=0){
                copyRowToAnother(b, a);
            }else{
                setRowToGivenValue(a, '-');
            }
            a --;
            b--; 
            }

        return rowsRemoved;
    }    
}



    
    

