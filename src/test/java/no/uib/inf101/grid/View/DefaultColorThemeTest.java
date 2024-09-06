package no.uib.inf101.grid.View;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import no.uib.inf101.tetris.view.ColorTheme;
import no.uib.inf101.tetris.view.DefaultColorTheme;

public class DefaultColorThemeTest {

    @Test
    public void sanityDefaultColorThemeTest() {
    ColorTheme colors = new DefaultColorTheme();
    assertEquals(new Color(0, 0, 0, 0), colors.getFrameColor());
    assertEquals(Color.BLACK, colors.getCellColor('-'));
    assertEquals(Color.RED, colors.getCellColor('r'));
    assertThrows(IllegalArgumentException.class, () -> colors.getCellColor('\n'));
    }

    
}
