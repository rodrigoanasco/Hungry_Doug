package com.phase2;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Bush extends Obstacle {

    private BufferedImage bushImage;
    
    // private static final int WIDTH = 30;
    // private static final int HEIGHT = 30;

    

    public Bush(int x, int y) {
        super(x, y, ObstacleType.BUSH);
        this.WIDTH = 30;
        this.HEIGHT = 30;
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
