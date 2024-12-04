package com.phase2;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class ExitTest {
    Exit exit;
    Score score;

    @Before
    public void setUp() {
        Score.boneScore=0;
        Score.boneTotal=0;
        exit = new Exit(0,0);
    }

    @Test
    public void testHidden() {
        Bone bone = new Bone(0,10);
        exit.tick();
        assertTrue(exit.getHiddenStatus());
    }

    @Test
    public void testActivated() {
        
        Bone bone = new Bone(0,10);
        Score.boneScore++;
        exit.tick();

        assertFalse(exit.getHiddenStatus());
    }

    @Test
    public void testActivatedNoBone() {
        
        exit.tick();
        assertFalse(exit.getHiddenStatus());
    }




}
