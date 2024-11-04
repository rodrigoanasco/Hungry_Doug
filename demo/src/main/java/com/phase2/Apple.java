package com.phase2;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.Graphics;


public class Apple extends Reward {

    private Image appleSprite;

    public Apple(int x, int y,RewardType rewardType) {
        super(x,y, RewardType.APPLE, 1);
        try {
            appleSprite = ImageIO.read(getClass().getResource("/Apple.png"));
            appleSprite = appleSprite.getScaledInstance(OBJECT_SIZE[0], OBJECT_SIZE[1], Image.SCALE_DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Rectangle getBounds() {
        // return new Rectangle(x,y,OBJECT_SIZE[0],OBJECT_SIZE[1]);
        return new Rectangle(x,y,32,32);

    }

    public void tick() {
        //to be implemented: checks if colliding with doug
        x += velX;
        y += velY;

        // TODO account for borders
        // atttribute for horizontal/vertical movement
        if(y < 0 || y >= Game.HEIGHT - 100) velY *= -1;
        if(x < 0 || x >= Game.WIDTH - 100) velX *= -1;
    }

    public void render(Graphics g) {
        g.drawImage(appleSprite, x, y, null);
    }

}