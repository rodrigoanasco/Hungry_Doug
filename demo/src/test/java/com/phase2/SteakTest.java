package com.phase2;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class SteakTest {
    private Steak steak;


    @Before
    public void setUp() {

        steak = new Steak(0, 0);


    }


    @Test
    public void testSteakSetAliveTrue() {
        steak.setAlive(true);
       assertTrue(steak.getAlive());
        
        
    }

    @Test
    public void testSteakSetAliveFalse() {
        steak.setAlive(false);
       assertFalse(steak.getAlive());
        
        
    }

    @Test
    public void testSteakTick() {
        steak.tick();
    }

}
