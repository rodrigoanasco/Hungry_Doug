package com.phase2.Trackers;

import java.awt.Color;
import java.awt.Graphics;

import com.phase2.Game;

/**
 * The Health class manages the player's health in the game.
 * It includes methods to update and render the health bar.
 */
public class Health {

    public static int HEALTH = 200; // The current health value, initially set to 200.

    /**
     * Updates the health value to ensure it stays within the valid range.
     * This method is called on each game tick.
     */
    public void tick() {
        HEALTH = Game.clamp(HEALTH, 0, 200);
    } 

    /**
     * Renders the health bar on the screen.
     * 
     * @param g The Graphics object used for rendering the health bar.
     */
    public void render(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(15, 15, 200, 32);
        g.setColor(Color.GREEN);
        g.fillRect(15, 15, HEALTH, 32);
        g.setColor(Color.GRAY);
        g.drawRect(15, 15, 200, 32);
    }
    
}
