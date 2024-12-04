package com.phase2;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import com.phase2.GameObjects.Steak;

/**
 * Unit tests for the {@link Steak} class.
 */
public class SteakTest {
    private Steak steak;


    @Before
    public void setUp() {
        steak = new Steak(0, 0);
    }

    /**
     * Tests that the {@code setAlive(true)} method correctly updates the steak's alive status
     * to {@code true}.
     */
    @Test
    public void testSteakSetAliveTrue() {
        steak.setAlive(true);
       assertTrue(steak.getAlive());
    }

    /**
     * Tests that the {@code setAlive(false)} method correctly updates the steak's alive status
     * to {@code false}.
     */
    @Test
    public void testSteakSetAliveFalse() {
        steak.setAlive(false);
       assertFalse(steak.getAlive());
    }


    /**
     * Tests the {@code tick()} method to verify its behavior when called.
     * Currently, the test ensures that calling the method does not throw any exceptions.
     */
    @Test
    public void testSteakTick() {
        steak.tick();
    }

}
