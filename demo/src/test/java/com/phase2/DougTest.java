package com.phase2;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class DougTest {
    private Doug doug;
    private Handler handler;

    @Before
    public void setUp() {
        handler = new Handler(new Game());
        doug = Doug.getInstance(100, 100, ID.DOUG, handler);
    }

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

    @Test
    public void testBoundaryClamping() {
        doug.setX(Game.WIDTH + 100);
        doug.setY(Game.HEIGHT + 100);
        doug.tick();

        assertTrue(doug.getX() <= Game.WIDTH - 75);
        assertTrue(doug.getY() <= Game.HEIGHT - 30);
    }
}
