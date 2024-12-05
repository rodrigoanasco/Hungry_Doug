package com.phase2.GameObjects;

import java.awt.Graphics;
import java.awt.Image;

import com.phase2.ImageLoader;
import com.phase2.Reward;
import com.phase2.Rewards.RewardType;
import com.phase2.Trackers.Score;

/**
 * The {@code Bone} class represents a collectible reward in the game.
 * <p>
 * Bones are special rewards that players can collect to earn points and
 * increase their overall bone total in the game. This class manages the
 * rendering and properties of the bone reward.
 * </p>
 * 
 * @see Reward
 */
public class Bone extends Reward {

    /**
     * The sprite image representing the bone.
     */
    private Image boneSprite;

    /**
     * Constructs a {@code Bone} object at the specified position.
     * <p>
     * The bone is initialized with its type, reward value, and sprite image.
     * The total number of bones in the game is incremented upon creation.
     * </p>
     * 
     * @param x the x-coordinate where the bone will appear
     * @param y the y-coordinate where the bone will appear
     */
    public Bone(int x, int y) {
        super(x, y, RewardType.BONE, 15);
        Score.boneTotal++; // Increment the total bone count
        this.WIDTH = 32;
        this.HEIGHT = 32;

        // Load the bone sprite and scale it to the appropriate size
        boneSprite = ImageLoader.loadImage("/Bone.png", WIDTH, HEIGHT);
    }

    /**
     * Renders the visual representation of the bone on the screen.
     * <p>
     * If the bone has not been collected, this method draws the bone sprite
     * at its current position on the screen.
     * </p>
     * 
     * @param g the {@link Graphics} object used to draw the sprite
     */
    @Override
    public void render(Graphics g) {
        if (!collected) {
            g.drawImage(boneSprite, x, y, null);
        }
    }
}
