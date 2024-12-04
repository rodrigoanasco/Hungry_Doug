package com.phase2;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.phase2.Enemies.Rat;
import com.phase2.GameObjects.Bone;
import com.phase2.GameObjects.Onion;
import com.phase2.Trackers.Health;
import com.phase2.Trackers.ID;
import com.phase2.Trackers.Score;


/**
 * Unit tests for the collision handling functionality of the {@link Doug} class.
 * 
 * The tests ensure that collisions are handled correctly, including
 * changes to the {@link Health} and {@link Score} systems based on 
 * the type of object collided with.
 */
public class DougCollisionTest {
    private Doug doug;
    private Handler handler;
    private Game game;

    /**
     * Sets up the test environment by initializing a new {@link Handler} and
     * resetting Doug's singleton instance. This ensures a clean state for each test.
     */
    @Before
    public void setUp() {
        game = new Game();
        handler = Handler.getHandlerInstance(game);
        Doug.setInstance(); // Reset Doug instance
        doug = Doug.getInstance(100, 100, ID.DOUG);
        Health.HEALTH = 200;
        Score.SCORE = 0;
    }

    /**
     * Tests the collision behavior between Doug and an enemy object (e.g., {@link Rat}).
     */
    @Test
    public void testCollisionWithEnemy() {
        Rat rat = new Rat(100, 100); // Position rat at the same location as Doug
        handler.addObject(rat);

        doug.tick(); // This should trigger a collision

        assertTrue("Health should decrease after collision with enemy", Health.HEALTH < 200);
    }

    /**
     * Tests the collision behavior between Doug and a reward object (e.g., {@link Bone}).
     */
    @Test
    public void testCollisionWithReward() {
        Bone bone = new Bone(100, 100); // Position bone at the same location as Doug
        handler.addObject(bone);

        doug.tick(); // This should trigger a collision

        assertTrue("Score should increase after collecting reward", Score.SCORE > 0);
    }

    /**
     * Tests the collision behavior between Doug and a punishment object (e.g., {@link Onion}).
     */
    @Test
    public void testCollisionWithPunishment() {
        Onion onion = new Onion(100, 100);
        handler.addObject(onion);

        doug.tick();

        assertTrue("Health should decrease after collecting punishment", Health.HEALTH < 200);
    }

        /**
     * resets objects after each test
     */
    @After
    public void reset() {
        Handler.setHandlerInstance();
    }
}