package com.phase2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * The WinningScreen class displays a congratulatory message when the player completes the game phase.
 * It provides an option to exit the game by pressing the 'ESC' key.
 */
public class WinningScreen {
    
    private Game game;

    /**
     * Constructs a WinningScreen associated with the specified game.
     * 
     * @param game The game instance associated with this winning screen.
     */
    public WinningScreen(Game game) {
        this.game = game;
    }

    /**
     * Renders the winning screen with a congratulatory message and instructions to exit.
     * 
     * @param g The Graphics object used to draw the winning screen.
     */
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
    
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Arial", Font.BOLD, 70));
        String title = "Phase 1 complete!";
        String subtitle = "New phases coming soon...";
        String exitPrompt = "Press 'ESC' to exit.";
    
        // Calculate the width of each string and center them
        int titleWidth = g.getFontMetrics().stringWidth(title);
        int subtitleWidth = g.getFontMetrics().stringWidth(subtitle);
        int exitPromptWidth = g.getFontMetrics().stringWidth(exitPrompt);
    
        g.drawString(title, (Game.WIDTH - titleWidth) / 2, Game.HEIGHT / 2 - 50);
        g.drawString(subtitle, (Game.WIDTH - subtitleWidth) / 2, Game.HEIGHT / 2 + 50);
    
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString(exitPrompt, (Game.WIDTH - exitPromptWidth) / 2, Game.HEIGHT / 2 + 120);
    }
    
}

