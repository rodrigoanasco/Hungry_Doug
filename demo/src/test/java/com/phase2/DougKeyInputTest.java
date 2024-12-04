package com.phase2;

import java.awt.Canvas;
import java.awt.event.KeyEvent;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for the {@link KeyInput} class and its interaction with the {@link Doug} class.
 * 
 * The tests utilize mocked key events to simulate keyboard input and verify
 * Doug's response through changes in velocity and game state.
 */
public class DougKeyInputTest {

    private Doug doug;
    private Handler handler;
    private KeyInput keyInput;
    private Game game;


    /**
     * Sets up the test environment by initializing a {@link Game}, {@link Handler}, and
     * {@link Doug} instance, as well as the {@link KeyInput} handler.
     */
    @Before
    public void setUp() {
        game = new Game();
        handler = Handler.getHandlerInstance();
        doug = Doug.getInstance(100, 100, ID.DOUG);
        keyInput = new KeyInput(game);
        handler.addObject(doug);

        // Unpause the game to allow movement
        if (game.isPaused()) {
            game.togglePause();
        }

        // Debugging assertions
        assertNotNull(doug);
        assertNotNull(handler);
        assertTrue(handler.objects.contains(doug));
    }

    /**
     * Tests Doug's upward movement when the 'W' key is pressed.
     */
    @Test
    public void testMoveUp() {
        KeyEvent upKey = new KeyEvent(new Canvas(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_W, 'W');
        keyInput.keyPressed(upKey);
        doug.tick();

        // Assert expected velocity
        assertEquals(-5, doug.getVelY());
        assertEquals(0, doug.getVelX());

        // Reset key state
        KeyEvent releaseKey = new KeyEvent(new Canvas(), KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_W, 'W');
        keyInput.keyReleased(releaseKey);
        doug.tick();

        // Assert Doug stopped
        assertEquals(0, doug.getVelY());
        assertEquals(0, doug.getVelX());
    }

    /**
     * Tests Doug's downward movement when the '5' key is pressed.
     */
    @Test
    public void testMoveDown() {
        KeyEvent downKey = new KeyEvent(new Canvas(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_S, 'S');
        keyInput.keyPressed(downKey);
        doug.tick();
        assertEquals(5, doug.getVelY());
        assertEquals(0, doug.getVelX());

        KeyEvent releaseKey = new KeyEvent(new Canvas(), KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_S, 'S');
        keyInput.keyReleased(releaseKey);
        doug.tick();
        assertEquals(0, doug.getVelY());
        assertEquals(0, doug.getVelX());
    }

    /**
     * Tests Doug's left movement when the 'A' key is pressed.
     */
    @Test
    public void testMoveLeft() {
        KeyEvent leftKey = new KeyEvent(new Canvas(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'A');
        keyInput.keyPressed(leftKey);
        doug.tick();
        assertEquals(-5, doug.getVelX());
        assertEquals(0, doug.getVelY());

        KeyEvent releaseKey = new KeyEvent(new Canvas(), KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'A');
        keyInput.keyReleased(releaseKey);
        doug.tick();
        assertEquals(0, doug.getVelX());
        assertEquals(0, doug.getVelY());
    }

    /**
     * Tests Doug's right movement when the 'D' key is pressed.
     */
    @Test
    public void testMoveRight() {
        KeyEvent rightKey = new KeyEvent(new Canvas(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_D, 'D');
        keyInput.keyPressed(rightKey);
        doug.tick();
        assertEquals(5, doug.getVelX());
        assertEquals(0, doug.getVelY());

        KeyEvent releaseKey = new KeyEvent(new Canvas(), KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_D, 'D');
        keyInput.keyReleased(releaseKey);
        doug.tick();
        assertEquals(0, doug.getVelX());
        assertEquals(0, doug.getVelY());
    }

    /**
     * Tests the pause functionality when the Escape key is pressed.
     */
    @Test
    public void testPauseToggle() {
        // Ensure the game starts unpaused
        assertFalse(game.isPaused());

        // Simulate pressing the Escape key to toggle pause
        KeyEvent escapeKey = new KeyEvent(new Canvas(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_ESCAPE, (char) KeyEvent.VK_ESCAPE);
        keyInput.keyPressed(escapeKey);
        assertTrue(game.isPaused());

        // Simulate pressing the Escape key again to unpause
        keyInput.keyPressed(escapeKey);
        assertFalse(game.isPaused());
    }

    /**
     * Tests that Doug cannot move when the game is paused.
     */
    @Test
    public void testPausedStatePreventsMovement() {
        // Pause the game
        KeyEvent escapeKey = new KeyEvent(new Canvas(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_ESCAPE, (char) KeyEvent.VK_ESCAPE);
        keyInput.keyPressed(escapeKey);
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
