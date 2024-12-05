package com.phase2;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.phase2.Obstacles.Obstacle;
import com.phase2.Obstacles.ObstacleType;

/**
 * The {@code Bush} class represents a bush obstacle in the game.
 * <p>
 * The bush blocks the player's movement and is displayed as a static object on the screen.
 * </p>
 */
public class Bush extends Obstacle {

    private BufferedImage bushImage;

    /**
     * Constructs a Bush object at the specified coordinates.
     * <p>
     * The bush image is loaded from the resources directory, and its dimensions are set to 32x32 pixels.
     * </p>
     *
     * @param x The x-coordinate of the bush.
     * @param y The y-coordinate of the bush.
     */
    public Bush(int x, int y) {
        super(x, y, ObstacleType.BUSH);
        this.WIDTH = 32;
        this.HEIGHT = 32;
        try {
            bushImage = ImageIO.read(getClass().getResource("/bush.png"));
        } catch (IOException e) {
            System.err.println("Failed to load bush image: " + e.getMessage());
        }
    }

    /**
     * Renders the visual representation of the bush on the screen.
     * <p>
     * If the bush image is successfully loaded, it will be drawn at its current position with the specified dimensions.
     * </p>
     *
     * @param g The {@link Graphics} object used to draw the sprite.
     */
    @Override
    public void render(Graphics g) {
        if (bushImage != null) {
            g.drawImage(bushImage, x, y, WIDTH, HEIGHT, null);
        }
    }
}
