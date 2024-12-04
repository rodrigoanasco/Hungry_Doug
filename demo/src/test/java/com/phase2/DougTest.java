package com.phase2;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for the core functionality of the {@link Doug} class.
 */
public class DougTest {
    private Doug doug;
    private Handler handler;
    private Game game;


    @Before
    public void setUp() {
        game = new Game();
        handler = Handler.getHandlerInstance(game);
        doug = Doug.getInstance(100, 100, ID.DOUG);
    }

    /**
     * Sets up the test environment by initializing a {@link Handler} and
     * creating a singleton instance of {@link Doug}.
     */
    @Test
    public void testMovement() {
        doug.setVelX(5);
        doug.setVelY(5);
        int initialX = doug.getX();
        int initialY = doug.getY();

        doug.tick(); // Update Doug's position

        assertEquals(initialX + 3, doug.getX()); // velX / speed (5 / 1.5 ≈ 3)
        assertEquals(initialY + 3, doug.getY());
    }

    /**
     * Tests that Doug's position updates correctly based on its velocity.
     */
    @Test
    public void testBoundaryClamping() {
        doug.setX(Game.WIDTH + 100);
        doug.setY(Game.HEIGHT + 100);
        doug.tick();

        assertTrue(doug.getX() <= Game.WIDTH - 75);
        assertTrue(doug.getY() <= Game.HEIGHT - 30);
    }
}