package no.uib.inf101.tetris.view;

import java.awt.Color;

public interface ColorTheme {
    /**
     * 
     * @param value
     * @return The color corresponding to the char/value of the cell
     */
    Color getCellColor(char value);

    /**
     * 
     * @return the preset Framecolor
     */
    Color getFrameColor();


    /**
     * 
     * @return the preset backgroundcolor
     */
    Color getBackgroundColor();

    /**
     * 
     * @return the color of GameOver-screen color
     */
    Color getTransparantBlack();
    
}
