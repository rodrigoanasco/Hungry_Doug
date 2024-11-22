package com.phase2;

import static org.junit.Assert.assertSame;
import org.junit.Test;

public class DougSingletonTest {

    @Test
    public void testSingletonInstance() {
        Doug doug1 = Doug.getInstance(100, 100, ID.DOUG, new Handler(new Game()));
        Doug doug2 = Doug.getInstance();

        assertSame(doug1, doug2);
    }

    @Test(expected = IllegalStateException.class)
    public void testSingletonWithoutInitialization() {
        Doug.setInstance(); // Reset the singleton instance
        Doug.getInstance(); // Should throw IllegalStateException
    }
}
