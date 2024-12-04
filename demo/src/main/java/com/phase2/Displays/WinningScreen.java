package com.phase2.Displays;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.phase2.Game;

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
    
        g.setFont(new Font("Arial", Font.BOLD, 70));
        g.setColor(Color.YELLOW);
        String title = "LEVEL 1 COMPLETE";
        String subtitle = "New levels coming soon...";
        String exitPrompt = "Press 'ESC' to exit.";
    
        // Calculate the width of the string and center it
        int titleWidth = g.getFontMetrics().stringWidth(title);
        g.drawString(title, (Game.WIDTH - titleWidth) / 2, Game.HEIGHT / 2 - 50);

        g.setFont(new Font("Arial", Font.PLAIN, 30));
        int subtitleWidth = g.getFontMetrics().stringWidth(subtitle);
        g.drawString(subtitle, (Game.WIDTH - subtitleWidth) / 2, Game.HEIGHT / 2 + 50);
    
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        int exitPromptWidth = g.getFontMetrics().stringWidth(exitPrompt);
        g.drawString(exitPrompt, (Game.WIDTH - exitPromptWidth) / 2, Game.HEIGHT / 2 + 120);
    }
    
}

