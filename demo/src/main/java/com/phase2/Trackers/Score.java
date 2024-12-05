package com.phase2.Trackers;

import java.awt.Color;
import java.awt.Graphics;

import com.phase2.Game;

/**
 * The {@code Score} class manages the player's score and displays it on the screen.
 * <p>
 * It tracks the total score, the number of bones collected, and the total bones available in the game.
 * This class provides methods to update the score dynamically and render it during gameplay.
 * </p>
 */
public class Score {

    /**
     * The number of bones collected by the player.
     */
    public static int boneScore = 0;

    /**
     * The total score of the player.
     */
    public static int SCORE = 0;

    /**
     * The total number of bones available in the game.
     */
    public static int boneTotal = 0;

    /**
     * Updates the score to ensure it stays within a valid range (0 to 1,000,000).
     * <p>
     * This method is called on each game tick to adjust the score based on gameplay events.
     * </p>
     */
    public void tick() {
        SCORE = Game.clamp(SCORE, 0, 1000000);
    }

    /**
     * Renders the current score on the screen.
     * <p>
     * Displays the player's score in the top-left corner of the screen during gameplay.
     * </p>
     * 
     * @param g The {@link Graphics} object used to draw the score.
     */
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawString("SCORE: " + SCORE, 25, 75); // Draw the score at the specified position
    }
}
