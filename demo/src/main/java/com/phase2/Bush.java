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
        super(x, y, ID.OBSTAClE);

        try {
            bushImage = ImageIO.read(getClass().getResource("/bush.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void tick() {
        // Bushes are static, so no updates are needed
    }

    @Override
    public void render(Graphics g) {
        if (bushImage != null) {
            g.drawImage(bushImage, x, y, WIDTH, HEIGHT, null);
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    @Override
    public boolean blockMovement(Doug doug) {
        // Prevent Doug from moving through the bush
        return this.getBounds().intersects(doug.getBounds());
    }
}
