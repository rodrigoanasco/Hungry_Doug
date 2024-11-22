package com.phase2;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.awt.event.KeyEvent;
import java.awt.Canvas;

public class DougKeyInputTest {
    private Doug doug;
    private Handler handler;
    private KeyInput keyInput;
    private Game game;

    @Before
    public void setUp() {
        // Set up the game environment
        game = new Game();
        handler = game.getHandler();
        doug = Doug.getInstance(100, 100, ID.DOUG, handler);
        keyInput = new KeyInput(handler, game);
        handler.addObject(doug);
    }

    @Test
    public void testMoveUp() {
        KeyEvent upKey = new KeyEvent(new Canvas(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_W, 'W');
        keyInput.keyPressed(upKey);
        doug.tick();
        assertEquals(-5, doug.getVelY());
        assertEquals(0, doug.getVelX());
    }

    @Test
    public void testMoveDown() {
        KeyEvent downKey = new KeyEvent(new Canvas(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_S, 'S');
        keyInput.keyPressed(downKey);
        doug.tick();
        assertEquals(5, doug.getVelY());
        assertEquals(0, doug.getVelX());
    }

    @Test
    public void testMoveLeft() {
        KeyEvent leftKey = new KeyEvent(new Canvas(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'A');
        keyInput.keyPressed(leftKey);
        doug.tick();
        assertEquals(-5, doug.getVelX());
        assertEquals(0, doug.getVelY());
    }

    @Test
    public void testMoveRight() {
        KeyEvent rightKey = new KeyEvent(new Canvas(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_D, 'D');
        keyInput.keyPressed(rightKey);
        doug.tick();
        assertEquals(5, doug.getVelX());
        assertEquals(0, doug.getVelY());
    }

    @Test
    public void testDiagonalMovementPrevention() {
        // Simulate pressing W and D simultaneously
        KeyEvent upKey = new KeyEvent(new Canvas(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_W, 'W');
        KeyEvent rightKey = new KeyEvent(new Canvas(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_D, 'D');
        keyInput.keyPressed(upKey);
        keyInput.keyPressed(rightKey);

        doug.tick();
        // Diagonal movement should be prevented
        assertNotEquals(-5, doug.getVelY());
        assertEquals(0, doug.getVelX());
    }

    @Test
    public void testPauseToggle() {
        // Ensure game starts unpaused
        assertFalse(game.isPaused());

        // Simulate pressing the P key to toggle pause
        KeyEvent pauseKey = new KeyEvent(new Canvas(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_P, 'P');
        keyInput.keyPressed(pauseKey);
        assertTrue(game.isPaused());

        // Press again to unpause
        keyInput.keyPressed(pauseKey);
        assertFalse(game.isPaused());
    }

    @Test
    public void testPausedStatePreventsMovement() {
        // Pause the game
        KeyEvent pauseKey = new KeyEvent(new Canvas(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_P, 'P');
        keyInput.keyPressed(pauseKey);
        assertTrue(game.isPaused());

        // Attempt movement while paused
        KeyEvent upKey = new KeyEvent(new Canvas(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_W, 'W');
        keyInput.keyPressed(upKey);
        doug.tick();

        // Velocities should remain zero while paused
        assertEquals(0, doug.getVelX());
        assertEquals(0, doug.getVelY());
    }
}
