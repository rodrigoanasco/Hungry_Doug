package com.phase2;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.phase2.Obstacles.Obstacle;
import com.phase2.Obstacles.ObstacleType;

public class Bush extends Obstacle {

    private BufferedImage bushImage;
    
    // private static final int WIDTH = 30;
    // private static final int HEIGHT = 30;

    

    public Bush(int x, int y) {
        super(x, y, ObstacleType.BUSH);
        this.WIDTH = 32;
        this.HEIGHT = 32;
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


}
