package com.phase2;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for the {@link Health} and {@link Score} classes.
 */
public class HealthScoreTest {
    private Health health;
    private Score score;

    /**
     * Sets up the test environment by initializing instances of {@link Health} and {@link Score}.
     */
    @Before
    public void setUp() {
        health = new Health();
        score = new Score();
        Health.HEALTH = 200;
        Score.SCORE = 0;
    }

    /**
     * Tests that {@link Health#HEALTH} decreases correctly.
     */
    @Test
    public void testHealthDecrease() {
        Health.HEALTH -= 50;
        health.tick();
        assertEquals(150, Health.HEALTH);
    }

    /**
     * Tests that {@link Score#SCORE} increases correctly.
     */
    @Test
    public void testScoreIncrease() {
        Score.SCORE += 10;
        score.tick();
        assertEquals(10, Score.SCORE);
    }
}