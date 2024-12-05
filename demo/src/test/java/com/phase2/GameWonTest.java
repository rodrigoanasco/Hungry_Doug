package com.phase2;

import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import com.phase2.Trackers.ID;
import com.phase2.Trackers.Score;

/**
 * Unit tests for the win condition of the {@link Game} class.
 */
public class GameWonTest {
    private Game game;
    private Handler handler;
    private Doug doug;
    private Exit exit;


    /**
     * Sets up the test environment by initializing the game, its handler,
     * and the necessary objects: {@link Doug} and {@link Exit}.
     */
    @Before
    public void setUp() {
        game = new Game();
        handler = Handler.getHandlerInstance(game);
        Doug.setInstance();
        //handler = Handler.getHandlerInstance();
        //Doug.setInstance();
        doug = Doug.getInstance(100, 100, ID.DOUG);
        handler.addObject(doug);
        exit = new Exit(100, 100);
        handler.addObject(exit);
        Score.boneScore = Score.boneTotal = 10; // Assume all bones collected
    }

    /**
     * Tests that the game correctly identifies the win condition when Doug
     * reaches the exit after collecting all required items.
     */
    @Test
    public void testGameWon() {
        doug.tick(); // Should detect collision with exit

        assertTrue("Game should be won after reaching exit", game.isGameWon());
    }

    /**
     * resets objects after each test
     */
    @After
    public void reset() {
        Handler.setHandlerInstance();
    }
}
