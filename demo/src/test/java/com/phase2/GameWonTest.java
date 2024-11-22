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
        handler = new Handler(game);
        doug = Doug.getInstance(100, 100, ID.DOUG, handler);
        handler.addObject(doug);
        exit = new Exit(100, 100);
        handler.addObject(exit);
        Score.boneScore = Score.boneTotal; // Assume all bones collected
    }

    @Test
    public void testGameWon() {
        doug.tick(); // Should detect collision with exit

        assertTrue(game.isGameWon());
    }
}
