package com.phase2.Displays;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.phase2.Game;

/**
 * The {@code GameOverScreen} class handles the display and behavior of the
 * "Game Over" screen in the game. It provides a message to the player
 * and listens for the "Enter" key press to restart the game.
 */
public class GameOverScreen implements KeyListener {

    /**
     * The main game instance associated with this screen.
     */
    private Game game;

    /**
     * Constructs a {@code GameOverScreen} instance associated with the main game.
     * Registers the screen as a {@link KeyListener} to handle user input.
     * 
     * @param game the main game instance.
     */
    public GameOverScreen(Game game) {
        this.game = game;
        game.addKeyListener(this); // Listen for "Enter" key press
    }

    /**
     * Renders the "Game Over" screen.
     * Displays a "GAME OVER" message and instructions to restart the game.
     * 
     * @param g the {@link Graphics} object used for rendering.
     */
    public void render(Graphics g) {
        // Fill the screen with black
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);

        // Display "GAME OVER" text
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 80));
        g.drawString("GAME OVER", Game.WIDTH / 2 - 200, Game.HEIGHT / 2 - 50);

        // Display restart instructions
        g.setFont(new Font("Arial", Font.PLAIN, 30));
        g.setColor(Color.WHITE);
        g.drawString("Press 'Enter' to restart", Game.WIDTH / 2 - 140, Game.HEIGHT / 2 + 50);
    }

    /**
     * Handles key press events. If the "Enter" key is pressed and the game is
     * marked as over, the game is reset to its initial state.
     * 
     * @param e the {@link KeyEvent} triggered by the key press.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER && game.checkGameOver()) {
            game.resetGame(); // Resetting the game (Doug goes back to the initial position, etc.)
        }
    }

    /**
     * This method is not used but is required by the {@link KeyListener} interface.
     * 
     * @param e the {@link KeyEvent} triggered by the key release.
     */
    @Override
    public void keyReleased(KeyEvent e) {
    }

    /**
     * This method is not used but is required by the {@link KeyListener} interface.
     * 
     * @param e the {@link KeyEvent} triggered by the key typing.
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }
}
