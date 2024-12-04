package com.phase2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The GameOverScreen class displays a "GAME OVER" message and listens for "Enter"
 * to restart the game from the beginning.
 */
public class GameOverScreen implements KeyListener {

    private Game game;

    /**
     * Constructs a GameOverScreen that is associated with the main game.
     * 
     * @param game The main game instance.
     */
    public GameOverScreen(Game game) {
        this.game = game;
        game.addKeyListener(this); // Listen for "Enter" key press
    }

    /**
     * Renders the game over screen with a message and restart instruction.
     */
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);

        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 80));
        g.drawString("GAME OVER", Game.WIDTH / 2 - 200, Game.HEIGHT / 2 - 50);

        g.setFont(new Font("Arial", Font.PLAIN, 30));
        g.setColor(Color.WHITE);
        g.drawString("Press 'Enter' to restart", Game.WIDTH / 2 - 140, Game.HEIGHT / 2 + 50);
    }

    /**
     * Handles the "Enter" key press event to restart the game.
     * 
     * @param e The KeyEvent triggered by the key press.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER && game.checkGameOver() == true) {
            game.resetGame(); // Reseting the game (doug goes back to initial position, etc...)
        }
    }

    /*
    This is unused (TAKEN OUT IN REFACTORING)
    public void removeListener(){
        game.removeKeyListener(this);
    }
        */
    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}

