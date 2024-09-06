package no.uib.inf101.tetris.view;

import java.awt.geom.Rectangle2D;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridDimension;

public class CellPositionToPixelConverter {
  //instansevariabler
  private Rectangle2D box;
  private GridDimension gd;
  private double margin;

  //konstruktør som inititliserer instansevariablene
  public CellPositionToPixelConverter(Rectangle2D box, GridDimension gd, double margin){
    this.box = box;
    this.gd = gd;
    this.margin = margin;
  }
  

  /** This method returns a rectangle-object with the right posision to the 
   * coordinate to the cell and the dimension og the grid.
   * 
   * @param cp The posisitiom of the cell
   * @return Rectangle 2D-object with the correct measures for the cell
   */

  public  Rectangle2D getBoundsForCell(CellPosition cp){
    
    double row = cp.row();
    double col = cp.col();

    //makes the variables for the framee
    double boxTopY = this.box.getY();
    double boxTopX = this.box.getX();
    double boxWidth = this.box.getWidth();
    double boxHeight = this.box.getHeight();

    
    double rows = gd.rows();
    double cols = gd.cols();
    double margin = this.margin;

    // calc
    double cellHeight = (boxHeight-margin*(rows+1))/rows;
    double cellWidth = (boxWidth-margin*(cols + 1))/cols;

    double cellX = boxTopX + col*cellWidth+ margin*(col + 1);
    double cellY = boxTopY + row*cellHeight + margin*(row +1 );

    return new Rectangle2D.Double(cellX,cellY,cellWidth,cellHeight);
  }
    
}
