package com.phase2;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class DougCollisionTest {
    private Doug doug;
    private Handler handler;
    private Health health;
    private Score score;

    @Before
    public void setUp() {
        handler = new Handler(new Game());
        doug = Doug.getInstance(100, 100, ID.DOUG, handler);
        health = new Health();
        score = new Score();
        Health.HEALTH = 200;
        Score.SCORE = 0;

    }

    @Test
    public void testCollisionWithEnemy() {
        Rat rat = new Rat(100, 100); // Position rat at the same location as Doug
        handler.addObject(rat);
    
        doug.tick(); // This should trigger a collision

        assertTrue(Health.HEALTH < 200); // Health should decrease
    }

    @Test
    public void testCollisionWithReward() {
        Bone bone = new Bone(100, 100); // Position bone at the same location as Doug
        handler.addObject(bone);

        doug.tick(); // This should trigger a collision

        assertTrue(Score.SCORE > 0); // Score should increase
    }

}
