package no.uib.inf101.tetris;

import java.awt.Dimension;
import javax.swing.JFrame;

import no.uib.inf101.tetris.controller.TetrisController;
import no.uib.inf101.tetris.model.TetrisBoard;
import no.uib.inf101.tetris.model.TetrisModel;
import no.uib.inf101.tetris.model.Tetromino.RandomTetrominoFactory;
import no.uib.inf101.tetris.model.Tetromino.TetrominoFactory;
import no.uib.inf101.tetris.view.TetrisView;

public class TetrisMain {
  public static final String WINDOW_TITLE = "SONDRE SIN TETRIS";
  private static final int CELLWIDTH = 40;
  private static final int INNER_MARGIN = 1;
  private static final int OUTER_MARGIN = 15;
  private static final int OPENING_X_VALUE = 500;
  private static final int OPENING_Y_VALUE = 100;
  
  
  public static void main(String[] args) {
    //creates the objects, and uses them to create further objects
    TetrisBoard tetrisBoard = new TetrisBoard(20, 10);
    TetrominoFactory tetrominoFactory = new RandomTetrominoFactory();
    TetrisModel tetrisModel = new TetrisModel(tetrisBoard,tetrominoFactory);
    TetrisView tetrisView = new TetrisView(tetrisModel);
    TetrisController tetrisController = new TetrisController(tetrisModel, tetrisView);
    
    //Calculate height and width on the game.
    //Inspired by step 2 from the assignement
    int preferredWidth = (CELLWIDTH*INNER_MARGIN)*tetrisBoard.cols() + INNER_MARGIN + 2*OUTER_MARGIN ;
    int preferredHeight = (CELLWIDTH*INNER_MARGIN)*tetrisBoard.rows() + INNER_MARGIN + 2*OUTER_MARGIN;
    
    // The JFrame is the "root" application window.
    // We here set som properties of the main window, 
    // and tell it to display our tetrisView
    JFrame frame = new JFrame(WINDOW_TITLE);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle(WINDOW_TITLE);
    frame.setPreferredSize(new Dimension(preferredWidth,preferredHeight));
    frame.setBounds(OPENING_X_VALUE, OPENING_Y_VALUE,  preferredWidth, preferredHeight);
    
    // Here we set which component to view in our window
    frame.setContentPane(tetrisView);
    
    // Call these methods to actually display the window
    frame.pack();
    frame.setVisible(true);
  }
  
}
