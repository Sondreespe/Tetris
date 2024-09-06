package no.uib.inf101.tetris.view;

import java.awt.Color;

public class DefaultColorTheme implements ColorTheme{
    
    @Override
    public Color getCellColor(char value){
        Color color = switch(value) {
            case 'r' -> Color.RED;
            case 'g' -> Color.GREEN;
            case 'y' -> Color.YELLOW;
            case 'b' -> Color.BLUE;
            case 'Z' -> Color.RED;
            case 'L' -> Color.ORANGE;
            case 'J' -> Color.BLUE;
            case 'I' -> Color.CYAN;
            case 'S' -> Color.GREEN;
            case 'T' -> Color.MAGENTA;
            case 'O' -> Color.YELLOW;
            case '-' -> Color.BLACK;
            default -> throw new IllegalArgumentException(
                "No available color for '" + value + "'");
          };
          return color;
    }

    @Override
    public Color getFrameColor(){
        Color frameColor = new Color(0, 0, 0,0);
        return frameColor; 
    }

    @Override
    public Color getBackgroundColor(){
        Color backgroundColor = Color.LIGHT_GRAY;
        return backgroundColor;
    }

    @Override
    public Color getTransparantBlack() {
        Color gameOverColor = new Color(0,0,0,128);
        return gameOverColor;
    } 
}
