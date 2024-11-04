package com.phase2;

import java.awt.Image;
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
    

    public void tick() {
        //to be implemented: checks if colliding with doug

    }

    public void render(Graphics g) {
        g.drawImage(appleSprite, x, y, null);
    }

}