package com.phase2;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class HealthScoreTest {
    private Health health;
    private Score score;

    @Before
    public void setUp() {
        health = new Health();
        score = new Score();
        Health.HEALTH = 200;
        Score.SCORE = 0;
    }

    @Test
    public void testHealthDecrease() {
        Health.HEALTH -= 50;
        health.tick();
        assertEquals(150, Health.HEALTH);
    }

    @Test
    public void testScoreIncrease() {
        Score.SCORE += 10;
        score.tick();
        assertEquals(10, Score.SCORE);
    }
}
