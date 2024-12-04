// src/test/java/com/phase2/GamePauseTest.java

package com.phase2;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for the pause functionality of the {@link Game} class.
 */
public class GamePauseTest {
    private Game game;

    @Before
    public void setUp() {
        game = new Game();
    }

    /**
     * Tests the {@code togglePause()} method to ensure that it correctly switches
     * the game's state between paused and unpaused.
     */
    @Test
    public void testTogglePause() {
        assertTrue(game.isPaused());
        game.togglePause();
        assertFalse(game.isPaused());
        game.togglePause();
        assertTrue(game.isPaused());
    }

    /**
     * resets objects after each test
     */
    @After
    public void reset() {
        Handler.setHandlerInstance();
    }
}
