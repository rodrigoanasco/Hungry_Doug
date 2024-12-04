package com.phase2;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.phase2.Trackers.Health;

/**
 * Unit tests for the game-over condition in the {@link Game} class.
 */
public class GameOverTest {
    private Game game;

    /**
     * Sets up the test environment by initializing a {@link Game} instance and
     * resetting {@link Health#HEALTH} to a default value.
     */
    @Before
    public void setUp() {
        game = new Game();
        Health.HEALTH = 200;
    }

    /**
     * Tests that the {@code checkGameOver()} method correctly identifies
     * the game-over condition when {@link Health#HEALTH} is zero or negative.
     */
    @Test
    public void testGameOver() {
        Health.HEALTH = 0;
        assertTrue(game.checkGameOver());

        Health.HEALTH = -10;
        assertTrue(game.checkGameOver());
    }

    /**
     * Tests that the {@code checkGameOver()} method correctly identifies
     * that the game is not over when {@link Health#HEALTH} is greater than zero.
     */
    @Test
    public void testGameNotOver() {
        Health.HEALTH = 50;
        assertFalse(game.checkGameOver());
    }

    /**
     * resets objects after each test
     */
    @After
    public void reset() {
        Handler.setHandlerInstance();
    }
}