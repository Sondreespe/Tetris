package no.uib.inf101.tetris.view;

import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import no.uib.inf101.grid.GridCell;
import no.uib.inf101.tetris.model.GameState;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.Color;
import java.awt.Font;


public class TetrisView extends JPanel{
  
    private ViewableTetrisModel tetrisModel;
    private ColorTheme colorTheme;
    private static final double OUTER_MARGIN = 40;
    private static final double INNER_MARGIN = 1;
    
   
    public TetrisView(ViewableTetrisModel tetrisModel){
        this.tetrisModel = tetrisModel;
        this.setFocusable(true);
        this.setPreferredSize(new DimensionUIResource(300, 400));
        this.colorTheme = new DefaultColorTheme();
    
        Color backroundColor = colorTheme.getBackgroundColor();
        this.setBackground(backroundColor);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawGame(g2);
        drawInfo(g2);

        //draws the gameover screem
        if(tetrisModel.getGameState() == GameState.GAME_OVER){
            g2.setColor(new Color(0,0,0,128));
            g2.fill(getBounds());

            g2.setFont(new Font("ARIAL",Font.BOLD, 40));
            g2.setColor(Color.WHITE);
            g2.drawString("GAME OVER",getWidth()/2-120,getHeight()/2); 

            g2.setFont(new Font("ARIAL",Font.BOLD, 20));
            g2.setColor(Color.WHITE);
            g2.drawString("Din score ble: " + tetrisModel.getScore(),getWidth()/2-120,getHeight()/2 + 20);

        }
        //Draws the welcome screen
        if(tetrisModel.getGameState() == GameState.WELCOME_SCREEN){
            g2.setColor(new Color(0,0,0,128));
            g2.fill(getBounds());

            g2.setFont(new Font("ARIAL",Font.BOLD, 30));
            g2.setColor(Color.WHITE);
            g2.drawString("Velkommen til Tetris",getWidth()/2-140,getHeight()/2 -40);

            g2.setFont(new Font("ARIAL",Font.BOLD, 20));
            g2.drawString("Pil ned for å starte",getWidth()/2-80,getHeight()/2 + 40);
            g2.drawString("P for pause",getWidth()/2-60,getHeight()/2);
        }

        //Draws the Pause screen
        if(tetrisModel.getGameState() == GameState.GAME_PAUSE){
            g2.setColor(new Color(0,0,0,128));
            g2.fill(getBounds());

            g2.setFont(new Font("ARIAL",Font.BOLD, 30));
            g2.setColor(Color.WHITE);
            g2.drawString("Spillet ditt er satt på pause",getWidth()/2-180,getHeight()-getHeight()+200);

            g2.setFont(new Font("ARIAL",Font.BOLD, 20));
            g2.setColor(Color.WHITE);
            g2.drawString("Trykk på enter for å starte igjen",getWidth()/2-140,getHeight()-getHeight()+300);
        }
    }
    
    // tegne rectanglet der cellene skal tegnes inni
    /**
     * Draws a frame on the Graphics2D object
     * @param g2
     */
    public void drawGame(Graphics2D g2){
        Rectangle2D outerRectangle = new Rectangle2D.Double(OUTER_MARGIN, OUTER_MARGIN, getWidth()-2*OUTER_MARGIN, 
        getHeight()-2*OUTER_MARGIN);
        g2.setColor(colorTheme.getBackgroundColor());
        g2.fill(outerRectangle);
        

        CellPositionToPixelConverter converter = new CellPositionToPixelConverter(outerRectangle,
        tetrisModel.getDimension(), INNER_MARGIN);

        //Draws the cells of the game
        drawCells(g2, tetrisModel.getTilesOnBoard(), converter, colorTheme);
        //Draws the cells of the tetromino
        drawCells(g2, tetrisModel.getFallingTetromino(), converter, colorTheme);
        
    }

    //metode som går gjennom hver celle , finner pixelpos og verdien, gjør så om verdien til farge og tegner den.
    /**
     * Draws each individual cell from the iterabele<GridCell<Character>> object.
     * @param canvas
     * @param celler
     * @param converter
     * @param colorTheme
     */
    public static void drawCells(Graphics2D canvas, Iterable<GridCell<Character>> celler,
     CellPositionToPixelConverter converter, ColorTheme colorTheme  ){

        for (GridCell<Character> cell: celler){
            char celltype = cell.value();
            Color color = colorTheme.getCellColor(celltype);
            Rectangle2D bounds = converter.getBoundsForCell(cell.pos());
            canvas.setColor(color);
            canvas.fill(bounds);
        }
    }
   /**
    * Draw the game-info on the game screen
    * @param g2
    */
    public void drawInfo(Graphics2D g2){
        //score
        g2.setFont(new Font("Arial", Font.BOLD, 20));
        g2.setColor(Color.WHITE);
        g2.drawString("Din score " + tetrisModel.getScore(),getWidth()-150, 35);

        //level
        g2.setFont(new Font("Arial", Font.BOLD, 20));
        g2.setColor(Color.WHITE);
        g2.drawString("Level: " + tetrisModel.getLevel(), 40, 35);
    }
}
