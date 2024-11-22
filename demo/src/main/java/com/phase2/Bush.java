package com.phase2;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Bush extends Obstacle {

    private BufferedImage bushImage;
    private static final int WIDTH = 30;
    private static final int HEIGHT = 30;

    public Bush(int x, int y) {
        super(x, y, ObstacleType.BUSH);
        hitBox = new Rectangle(x, y, WIDTH, HEIGHT);
        try {
            bushImage = ImageIO.read(getClass().getResource("/bush.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(Graphics g) {
        if (bushImage != null) {
            g.drawImage(bushImage, x, y, WIDTH, HEIGHT, null);
        }
    }

    @Override
    public Rectangle getBounds() {
        return hitBox;
    }

    @Override
    public void tick() {}

}
