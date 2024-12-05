package com.phase2.Trackers;

import java.awt.Color;
import java.awt.Graphics;

import com.phase2.Game;

/**
 * The {@code Health} class manages the player's health in the game.
 * This class provides methods to update the player's health and render a health bar on the screen.
 * The health value is clamped between 0 and 200, representing the player's current status.
 * 
 */
public class Health {

    /**
     * The current health value of the player, initially set to 200.
     */
    public static int HEALTH = 200;

    /**
     * Updates the health value to ensure it stays within the valid range (0 to 200).
     * This method is called on each game tick to adjust the health based on game events.
     */
    public void tick() {
        HEALTH = Game.clamp(HEALTH, 0, 200);
    }

    /**
     * Renders the health bar on the screen.
     * 
     * The health bar consists of:
     * <ul>
     *   <li>A gray background bar.</li>
     *   <li>A green bar indicating the player's current health.</li>
     *   <li>A gray border outlining the health bar.</li>
     * </ul>
     * 
     * 
     * @param g The {@link Graphics} object used for rendering the health bar.
     */
    public void render(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(15, 15, 200, 32); // Background bar
        g.setColor(Color.GREEN);
        g.fillRect(15, 15, HEALTH, 32); // Health indicator
        g.setColor(Color.GRAY);
        g.drawRect(15, 15, 200, 32); // Border
    }
}
