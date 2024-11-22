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
    private double speed = 1.5;

    @Before
    public void setUp() throws Exception {
        Game game = new Game();
        handler = new Handler(game);
        doug = Doug.getInstance(200, 200, ID.DOUG, handler); // Initial position
        keyInput = new KeyInput(handler, game);
        handler.addObject(doug); // Add Doug to the handler
    }

    @Test
    public void testMoveUp() {
        KeyEvent upKey = new KeyEvent(new Canvas(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_W, 'W');
        keyInput.keyPressed(upKey);
        doug.setVelY(-5); // Set velocity for testing

        doug.tick(); // Update Doug's position

        // Calculate expected position
        int predictedY = 200 + (int) (doug.getVelY() / speed);
        predictedY = Game.clamp(predictedY, 0, Game.HEIGHT - 30); // Apply clamping logic

        assertEquals(predictedY, doug.getY());
        assertEquals(200, doug.getX()); // X remains unchanged
    }

    @Test
    public void testMoveDown() {
        KeyEvent downKey = new KeyEvent(new Canvas(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_S, 'S');
        keyInput.keyPressed(downKey);
        doug.setVelY(5); // Set velocity for testing

        doug.tick(); // Update Doug's position

        // Calculate expected position
        int predictedY = 200 + (int) (doug.getVelY() / speed);
        predictedY = Game.clamp(predictedY, 0, Game.HEIGHT - 30); // Apply clamping logic

        assertEquals(predictedY, doug.getY());
        assertEquals(200, doug.getX()); // X remains unchanged
    }

    @Test
    public void testMoveLeft() {
        KeyEvent leftKey = new KeyEvent(new Canvas(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'A');
        keyInput.keyPressed(leftKey);
        doug.setVelX(-5); // Set velocity for testing

        doug.tick(); // Update Doug's position

        // Calculate expected position
        int predictedX = 200 + (int) (doug.getVelX() / speed);
        predictedX = Game.clamp(predictedX, 0, Game.WIDTH - 75); // Apply clamping logic

        assertEquals(predictedX, doug.getX());
        assertEquals(200, doug.getY()); // Y remains unchanged
    }

    @Test
    public void testMoveRight() {
        KeyEvent rightKey = new KeyEvent(new Canvas(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_D, 'D');
        keyInput.keyPressed(rightKey);
        doug.setVelX(5); // Set velocity for testing

        doug.tick(); // Update Doug's position

        // Calculate expected position
        int predictedX = 200 + (int) (doug.getVelX() / speed);
        predictedX = Game.clamp(predictedX, 0, Game.WIDTH - 75); // Apply clamping logic

        assertEquals(predictedX, doug.getX());
        assertEquals(200, doug.getY()); // Y remains unchanged
    }
}
