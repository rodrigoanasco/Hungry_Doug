package com.phase2;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class GameOverTest {
    private Game game;

    @Before
    public void setUp() {
        game = new Game();
        Health.HEALTH = 200;
    }

    @Test
    public void testGameOver() {
        Health.HEALTH = 0;
        assertTrue(game.checkGameOver());

        Health.HEALTH = -10;
        assertTrue(game.checkGameOver());
    }

    @Test
    public void testGameNotOver() {
        Health.HEALTH = 50;
        assertFalse(game.checkGameOver());
    }
}