package com.phase2;

import java.awt.Color;
import java.awt.Graphics;


/**
 * The Score class manages the player's score and displays it on the screen.
 * It tracks the overall score and the number of bones collected.
 */
public class Score {

    public static int boneScore = 0;
    public static int SCORE = 0;
    public static int boneTotal = 1;

    /**
     * Updates the score to ensure it stays within the valid range.
     * This method is called on each game tick.
     */
    public void tick() {
        SCORE = Game.clamp(SCORE, 0, 1000000);
    } 

    /**
     * Renders the current score on the screen.
     * 
     * @param g The Graphics object used to draw the score.
     */
    public void render(Graphics g) {
        // testing only
        g.setColor(Color.BLACK);
        g.drawString("SCORE: " + SCORE, 25, 75); // Adjust the position
    }

}
