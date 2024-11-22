package com.phase2;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class GameWonTest {
    private Game game;
    private Handler handler;
    private Doug doug;
    private Exit exit;

    @Before
    public void setUp() {
        game = new Game();
        handler = game.getHandler();
        Doug.setInstance();
        doug = Doug.getInstance(100, 100, ID.DOUG, handler);
        handler.addObject(doug);
        exit = new Exit(100, 100);
        handler.addObject(exit);
        Score.boneScore = Score.boneTotal = 1; // Assume all bones collected
    }

    @Test
    public void testGameWon() {
        doug.tick(); // Should detect collision with exit

        assertTrue("Game should be won after reaching exit", game.isGameWon());
    }
}
