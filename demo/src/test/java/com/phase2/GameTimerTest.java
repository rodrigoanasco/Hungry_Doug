package com.phase2;

import org.junit.Test;
import static org.junit.Assert.*;

public class GameTimerTest {

    @Test
    public void testTimerStarts() {
        // Initialize the game
        Game game = new Game();
        game.start(); // Start the game

        // Check that the game is running
        assertTrue("The game should be running after start() is called", game.isRunning());

        // Stop the game
        game.stop();
    }

    @Test
    public void testTimerStops() {
        // Initialize the game
        Game game = new Game();
        game.start(); // Start the game

        // Stop the game
        game.stop();

        // Check that the game is not running
        assertFalse("The game should not be running after stop() is called", game.isRunning());
    }

    @Test
    public void testTickUpdates() throws InterruptedException {
        // Initialize the game and handler
        Game game = new Game();
        Handler handler = game.getHandler();

        // Start the game
        game.start();

        // Allow the game to run for 1 second
        Thread.sleep(1000);

        // Stop the game
        game.stop();

        // Verify tick count is greater than 0
        int tickCount = handler.getTickCount();
        assertTrue("Tick count should be greater than 0 after 1 second", tickCount > 0);
    }



    @Test
    public void testFixedDuration() throws InterruptedException {
        // Initialize the game
        Game game = new Game();
        game.start();

        // Allow the game to run for 1 second
        Thread.sleep(1000);

        // Stop the game
        game.stop();

        // Check that the game is not running anymore
        assertFalse("The game should have stopped after 1 second", game.isRunning());
    }
}
