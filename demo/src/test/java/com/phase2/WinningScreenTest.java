package com.phase2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class WinningScreenTest {
    private Game game;

    @Before
    public void setUp() {
        game = new Game();
    }

    @Test
    public void testGameWonState() {
        // Ensure game is not won initially
        assertFalse(game.isGameWon());

        // Simulate winning the game
        game.setGameWon(true);

        // Check that game is now won
        assertTrue(game.isGameWon());
    }

    @Test
    public void testWinningScreenRender() {
        // Create a mock Graphics object
        Graphics g = mock(Graphics.class);

        // Create WinningScreen instance
        WinningScreen winningScreen = new WinningScreen(game);

        // Call render method
        winningScreen.render(g);

        // Verify that certain Graphics methods are called
        verify(g, atLeastOnce()).setColor(any(Color.class));
        verify(g, atLeastOnce()).fillRect(anyInt(), anyInt(), anyInt(), anyInt());
        verify(g, atLeastOnce()).setFont(any(Font.class));
        verify(g, atLeastOnce()).drawString(anyString(), anyInt(), anyInt());
    }
}
