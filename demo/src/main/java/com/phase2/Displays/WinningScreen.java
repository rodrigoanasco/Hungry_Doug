package com.phase2.Displays;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.phase2.Game;

/**
 * The {@code WinningScreen} class displays a congratulatory screen when the player successfully
 * completes the game phase. It provides a message indicating the game's progression and an
 * instruction to exit the game by pressing the 'ESC' key.
 */
public class WinningScreen {

    /**
     * The main game instance associated with this winning screen.
     */
    private Game game;

    /**
     * Constructs a {@code WinningScreen} associated with the specified game instance.
     * 
     * @param game the {@link Game} instance associated with this winning screen.
     */
    public WinningScreen(Game game) {
        this.game = game;
    }

    /**
     * Renders the winning screen with a congratulatory message and exit instructions.
     * 
     * @param g the {@link Graphics} object used to draw the winning screen elements.
     */
    public void render(Graphics g) {
        // Fill the background with black
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);

        // Draw the main title
        g.setFont(new Font("Arial", Font.BOLD, 70));
        g.setColor(Color.YELLOW);
        String title = "LEVEL 1 COMPLETE";
        int titleWidth = g.getFontMetrics().stringWidth(title);
        g.drawString(title, (Game.WIDTH - titleWidth) / 2, Game.HEIGHT / 2 - 50);

        // Draw the subtitle
        g.setFont(new Font("Arial", Font.PLAIN, 30));
        String subtitle = "New levels coming soon...";
        int subtitleWidth = g.getFontMetrics().stringWidth(subtitle);
        g.drawString(subtitle, (Game.WIDTH - subtitleWidth) / 2, Game.HEIGHT / 2 + 50);

        // Draw the exit prompt
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        String exitPrompt = "Press 'ESC' to exit.";
        int exitPromptWidth = g.getFontMetrics().stringWidth(exitPrompt);
        g.drawString(exitPrompt, (Game.WIDTH - exitPromptWidth) / 2, Game.HEIGHT / 2 + 120);
    }
}
