package com.phase2;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import org.junit.Test;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class WinningScreenTest {

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
