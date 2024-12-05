package com.phase2.GameObjects;

import java.awt.Graphics;
import java.awt.Image;

import com.phase2.ImageLoader;
import com.phase2.Reward;
import com.phase2.Rewards.RewardType;

/**
 * The {@code Mushroom} class represents a collectible reward in the game.
 * <p>
 * Mushrooms provide points to the player when collected. This class manages
 * the rendering and properties of the mushroom reward.
 * </p>
 * 
 * @see Reward
 */
public class Mushroom extends Reward {

    /**
     * The sprite image representing the mushroom.
     */
    private Image mushroomSprite;

    /**
     * Constructs a {@code Mushroom} object at the specified position.
     * <p>
     * The mushroom is initialized with its type, reward value, and sprite image.
     * </p>
     * 
     * @param x the x-coordinate where the mushroom will appear
     * @param y the y-coordinate where the mushroom will appear
     */
    public Mushroom(int x, int y) {
        super(x, y, RewardType.MUSHROOM, 10);
        this.WIDTH = 48;
        this.HEIGHT = 48;

        // Load the mushroom sprite and scale it to the appropriate size
        mushroomSprite = ImageLoader.loadImage("/Mushroom.png", WIDTH, HEIGHT);
    }

    /**
     * Renders the visual representation of the mushroom on the screen.
     * <p>
     * If the mushroom has not been collected, this method draws the mushroom sprite
     * at its current position on the screen.
     * </p>
     * 
     * @param g the {@link Graphics} object used to draw the sprite
     */
    @Override
    public void render(Graphics g) {
        if (!collected) {
            g.drawImage(mushroomSprite, x, y, null);
        }
    }
}
