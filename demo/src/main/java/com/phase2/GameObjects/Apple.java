package com.phase2.GameObjects;

import java.awt.Graphics;
import java.awt.Image;

import com.phase2.ImageLoader;
import com.phase2.Reward;
import com.phase2.Rewards.RewardType;

/**
 * The {@code Apple} class represents a collectible reward in the game.
 * <p>
 * Apples can be collected by the player to earn points. This class handles
 * the rendering and properties of the apple reward.
 * </p>
 * 
 * @see Reward
 */
public class Apple extends Reward {

    /**
     * The sprite image representing the apple.
     */
    private Image appleSprite;

    /**
     * Constructs an {@code Apple} object at the specified position.
     * <p>
     * The apple is initialized with its type, reward value, and sprite image.
     * </p>
     * 
     * @param x the x-coordinate where the apple will appear
     * @param y the y-coordinate where the apple will appear
     */
    public Apple(int x, int y) {
        super(x, y, RewardType.APPLE, 10);
        this.WIDTH = 32;
        this.HEIGHT = 32;

        // Load the apple sprite and scale it to the appropriate size
        appleSprite = ImageLoader.loadImage("/Apple.png", WIDTH, HEIGHT);
    }

    /**
     * Renders the visual representation of the apple on the screen.
     * <p>
     * If the apple has not been collected, this method draws the apple sprite
     * at its current position on the screen.
     * </p>
     * 
     * @param g the {@link Graphics} object used to draw the sprite
     */
    @Override
    public void render(Graphics g) {
        if (!collected) {
            g.drawImage(appleSprite, x, y, null);
        }
    }
}
