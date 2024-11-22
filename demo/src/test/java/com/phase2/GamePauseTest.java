// src/test/java/com/phase2/GamePauseTest.java

package com.phase2;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class GamePauseTest {
    private Game game;

    @Before
    public void setUp() {
        game = new Game();
    }

    @Test
    public void testTogglePause() {
        assertTrue(game.isPaused());
        game.togglePause();
        assertFalse(game.isPaused());
        game.togglePause();
        assertTrue(game.isPaused());
    }
}
