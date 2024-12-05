package com.phase2.GameObjects;

import java.awt.Graphics;

import com.phase2.ImageLoader;
import com.phase2.Punishments.Punishment;
import com.phase2.Punishments.PunishmentType;

/**
 * The {@code Whiskey} class represents a punishment object in the game.
 * <p>
 * Whiskey is a harmful object that the player should avoid collecting. 
 * It imposes a penalty on the player's health or score when collected.
 * This class handles the rendering and properties of the whiskey punishment.
 * </p>
 * 
 * @see Punishment
 */
public class Whiskey extends Punishment {

    /**
     * Constructs a {@code Whiskey} object at the specified position.
     * <p>
     * The whiskey is initialized with its type, penalty value, and sprite image.
     * </p>
     * 
     * @param x the x-coordinate where the whiskey will appear
     * @param y the y-coordinate where the whiskey will appear
     */
    public Whiskey(int x, int y) {
        super(x, y, PunishmentType.WHISKEY, 25);
        this.WIDTH = 32;
        this.HEIGHT = 32;

        // Load the whiskey sprite and scale it to the appropriate size
        image = ImageLoader.loadImage("/Whiskey.png", WIDTH, HEIGHT);
    }

    /**
     * Renders the visual representation of the whiskey on the screen.
     * <p>
     * If the whiskey has not been collected, this method draws the whiskey sprite
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
