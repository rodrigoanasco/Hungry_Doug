package com.phase2;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class WinningScreenTest {
    private Game game;

    @Before
    public void setUp() {
        game = new Game();
    }

    @Test
    public void testGameWonState() {
        // Ensure game is not won initially
        assertFalse("Game should not be won initially", game.isGameWon());

        // Simulate winning the game
        game.setGameWon(true);

        // Check that game is now won
        assertTrue("Game should be won after setting gameWon to true", game.isGameWon());
    }
}
