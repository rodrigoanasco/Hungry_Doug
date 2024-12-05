package com.phase2;

import java.awt.Graphics;
import java.awt.Image;

import com.phase2.GameObjects.GameObject;
import com.phase2.Trackers.ID;
import com.phase2.Trackers.Score;

/**
 * Represents the Exit object in the game.
 * The Exit becomes visible and interactive when the player collects all required bones.
 */
public class Exit extends GameObject {

    private Image sprite;
    private boolean hidden = true; // Indicates whether the Exit is hidden or visible

    /**
     * Constructs an Exit object with the specified coordinates.
     * Loads and scales the sprite for the Exit.
     * 
     * @param x The x-coordinate of the Exit.
     * @param y The y-coordinate of the Exit.
     */
    public Exit(int x, int y) {
        super(x, y, ID.EXIT);
        this.WIDTH = 32;
        this.HEIGHT = 32;

        // Load and scale the sprite using ImageLoader
        sprite = ImageLoader.loadImage("/Exit.png", WIDTH, HEIGHT);
    }

    /**
     * Returns whether the Exit is hidden.
     * 
     * @return {@code true} if the Exit is hidden; {@code false} otherwise.
     */
    public boolean getHiddenStatus() {
        return hidden;
    }

    /**
     * Updates the state of the Exit.
     * Makes the Exit visible when the player collects all required bones.
     */
    @Override
    public void tick() {
        if (Score.boneScore >= Score.boneTotal && hidden) {
            SoundEffect.play("/reward.wav"); // Play reward sound
            hidden = false; // Make the Exit visible
        }
    }

    /**
     * Renders the Exit on the screen.
     * The Exit is drawn only when it is no longer hidden.
     * 
     * @param g The Graphics object used to draw the Exit.
     */
    @Override
    public void render(Graphics g) {
        if (!hidden) {
            g.drawImage(sprite, x, y, null);
        }
    }
}
