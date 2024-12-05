package com.phase2.GameObjects;

import java.awt.Graphics;

import com.phase2.ImageLoader;
import com.phase2.Punishments.Punishment;
import com.phase2.Punishments.PunishmentType;

/**
 * The {@code Chocolate} class represents a non-moving punishment in the game.
 * <p>
 * Chocolate serves as a harmful object that the player should avoid collecting.
 * It decreases the player's health or score when collected. This class handles
 * the rendering and properties of the chocolate punishment.
 * </p>
 * 
 * @see Punishment
 */
public class Chocolate extends Punishment {

    /**
     * Constructs a {@code Chocolate} object at the specified position.
     * <p>
     * The chocolate is initialized with its type, penalty value, and sprite image.
     * </p>
     * 
     * @param x the x-coordinate where the chocolate will appear
     * @param y the y-coordinate where the chocolate will appear
     */
    public Chocolate(int x, int y) {
        super(x, y, PunishmentType.CHOCOLATE, 50);
        this.WIDTH = 32;
        this.HEIGHT = 32;

        // Load the chocolate sprite and scale it to the appropriate size
        image = ImageLoader.loadImage("/Brownie.png", WIDTH, HEIGHT);
    }

    /**
     * Renders the visual representation of the chocolate on the screen.
     * <p>
     * If the chocolate has not been collected, this method draws the chocolate sprite
     * at its current position on the screen.
     * </p>
     * 
     * @param g the {@link Graphics} object used to draw the sprite
     */
    @Override
    public void render(Graphics g) {
        if (!collected) {
            g.drawImage(image, x, y, null);
        }
    }
}
