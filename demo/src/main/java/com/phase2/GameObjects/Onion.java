package com.phase2.GameObjects;

import java.awt.Graphics;

import com.phase2.ImageLoader;
import com.phase2.Punishments.Punishment;
import com.phase2.Punishments.PunishmentType;

/**
 * The {@code Onion} class represents a punishment object in the game.
 * <p>
 * Onions are harmful objects that the player should avoid collecting. 
 * They impose a penalty on the player's health or score when collected.
 * This class handles the rendering and properties of the onion punishment.
 * </p>
 * 
 * @see Punishment
 */
public class Onion extends Punishment {

    /**
     * Constructs an {@code Onion} object at the specified position.
     * <p>
     * The onion is initialized with its type, penalty value, and sprite image.
     * </p>
     * 
     * @param x the x-coordinate where the onion will appear
     * @param y the y-coordinate where the onion will appear
     */
    public Onion(int x, int y) {
        super(x, y, PunishmentType.ONION, 20);
        this.WIDTH = 32;
        this.HEIGHT = 32;

        // Load the onion sprite and scale it to the appropriate size
        image = ImageLoader.loadImage("/Onion.png", WIDTH, HEIGHT);
    }

    /**
     * Renders the visual representation of the onion on the screen.
     * <p>
     * If the onion has not been collected, this method draws the onion sprite
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
