package com.phase2;

import static org.junit.Assert.assertSame;
import org.junit.Test;

/**
 * Unit tests for the singleton implementation of the {@link Doug} class.
 */
public class DougSingletonTest {

    /**
     * Tests that the {@link Doug#getInstance(int, int, ID)} method
     * returns the same instance of {@link Doug} when called multiple times.
     */
    @Test
    public void testSingletonInstance() {
        Doug doug1 = Doug.getInstance(100, 100, ID.DOUG);
        Doug doug2 = Doug.getInstance();

        assertSame(doug1, doug2);
    }

    /**
     * Tests that calling {@link Doug#getInstance()} without initializing
     * the singleton instance first throws an {@link IllegalStateException}.
     * 
     * @throws IllegalStateException if {@link Doug#getInstance()} is called
     *         without initialization.
     */
    @Test(expected = IllegalStateException.class)
    public void testSingletonWithoutInitialization() {
        Doug.setInstance(); // Reset the singleton instance
        Doug.getInstance(); // Should throw IllegalStateException
    }
}
