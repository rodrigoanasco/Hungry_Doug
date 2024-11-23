package com.phase2;

import java.awt.Canvas;
import java.awt.event.KeyEvent;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class DougKeyInputTest {
    private Doug doug;
    private Handler handler;
    private KeyInput keyInput;
    private Game game;

    @Before
    public void setUp() {
        game = new Game();
        handler = game.getHandler();
        doug = Doug.getInstance(100, 100, ID.DOUG, handler);
        keyInput = new KeyInput(handler, game);
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
