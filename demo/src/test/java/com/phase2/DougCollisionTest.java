package com.phase2;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class DougCollisionTest {
    private Doug doug;
    private Handler handler;

    @Before
    public void setUp() {
        handler = new Handler(new Game());
        Doug.setInstance(); // Reset Doug instance
        doug = Doug.getInstance(100, 100, ID.DOUG, handler);
        Health.HEALTH = 200;
        Score.SCORE = 0;
    }

    @Test
    public void testCollisionWithEnemy() {
        Rat rat = new Rat(100, 100, handler); // Position rat at the same location as Doug
        handler.addObject(rat);

        doug.tick(); // This should trigger a collision

        assertTrue("Health should decrease after collision with enemy", Health.HEALTH < 200);
    }

    @Test
    public void testCollisionWithReward() {
        Bone bone = new Bone(100, 100); // Position bone at the same location as Doug
        handler.addObject(bone);

        doug.tick(); // This should trigger a collision

        assertTrue("Score should increase after collecting reward", Score.SCORE > 0);
    }

    @Test
    public void testCollisionWithPunishment() {
        Onion onion = new Onion(100, 100);
        handler.addObject(onion);

        doug.tick();

        assertTrue("Health should decrease after collecting punishment", Health.HEALTH < 200);
    }
}