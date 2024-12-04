package com.phase2;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bush extends Obstacle {

    private BufferedImage bushImage;
    private static final int WIDTH = 30;
    private static final int HEIGHT = 30;

    public Bush(int x, int y) {
        super(x, y, ObstacleType.BUSH);
        try {
            bushImage = ImageIO.read(getClass().getResource("/bush.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Renders the visual representation of the bush on the screen.
     *
     * @param g the {@link Graphics} object used to draw the sprite
     */
    @Override
    public void render(Graphics g) {
        if (bushImage != null) {
            g.drawImage(bushImage, x, y, WIDTH, HEIGHT, null);
        }
    }

    /**
     * Gets the bounding rectangle of the bush for collision detection.
     * 
     * @return a {@link Rectangle} representing the bush's bounds.
     */
    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,OBJECT_SIZE[0],OBJECT_SIZE[1]);
    }

}
