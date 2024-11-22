package com.phase2;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics;


public class Apple extends Reward {

    private Image appleSprite;

    public Apple(int x, int y) {
        super(x,y, RewardType.APPLE, 10);
        try {
            appleSprite = ImageIO.read(getClass().getResource("/Apple.png"));
            appleSprite = appleSprite.getScaledInstance(OBJECT_SIZE[0], OBJECT_SIZE[1], Image.SCALE_DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Gets the bounding rectangle of Doug for collision detection.
     * 
     * @return A Rectangle representing Doug's bounds.
     */
    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,OBJECT_SIZE[0],OBJECT_SIZE[1]);

    }
    @Override
    public void render(Graphics g) {
        if (!collected) {
            g.drawImage(appleSprite, x, y, null);
        }
    }

    @Override
    public void tick() {}

}