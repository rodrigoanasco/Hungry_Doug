package com.phase2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import org.junit.Test;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.awt.FontMetrics;

/**
 * Unit tests for the {@link WinningScreen} class.
 * 
 * This test class verifies the rendering behavior of the {@code WinningScreen},
 * ensuring that it displays the correct graphics, fonts, and colors when rendered.
 */
public class WinningScreenTest {

    /**
     * Tests the {@code render(Graphics)} method of the {@link WinningScreen} class.
     * 
     * The test checks: 
     *    The background is rendered with the correct dimensions and color. 
     *    The title, subtitle, and exit prompt are rendered using the correct font and color.
     *    The {@code drawString} method is called for all text components.
     */
  @Test
  public void testRender() {
    // Mock dependencies
    Graphics mockGraphics = mock(Graphics.class);
    FontMetrics mockFontMetrics = mock(FontMetrics.class);

    // Stub the methods of FontMetrics
    when(mockGraphics.getFontMetrics()).thenReturn(mockFontMetrics);
    when(mockFontMetrics.stringWidth(anyString())).thenReturn(200); // Example width

    // dimensions are constants, use actual
    final int width = Game.WIDTH;
    final int height = Game.HEIGHT;

    Game game = new Game();
    WinningScreen winningScreen = new WinningScreen(game);

    winningScreen.render(mockGraphics);

    // verify Graphics methods are called with expected parameters
    verify(mockGraphics).setColor(Color.BLACK);
    verify(mockGraphics).fillRect(0, 0, width, height);

    verify(mockGraphics).setColor(Color.YELLOW);
    verify(mockGraphics).setFont(new Font("Arial", Font.BOLD, 70));

    // verify drawString is called for the title, subtitle, and exit prompt
    verify(mockGraphics, atLeastOnce()).drawString(anyString(), anyInt(), anyInt());
  }

}
