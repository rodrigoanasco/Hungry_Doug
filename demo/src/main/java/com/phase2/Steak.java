package com.phase2;

import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.Graphics;


public class Steak extends Reward {

    private Image steakSprite;

    public Steak(int x, int y,RewardType rewardType) {
        super(x,y, RewardType.STEAK, 1);
        try {
            steakSprite = ImageIO.read(getClass().getResource("/Steak.png"));
            steakSprite = steakSprite.getScaledInstance(OBJECT_SIZE[0], OBJECT_SIZE[1], Image.SCALE_DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    public void tick() {
        //to be implemented: checks if colliding with doug

    }

    public void render(Graphics g) {
        g.drawImage(steakSprite, x, y, null);
    }
    public void applyReward(Doug doug) {

    }
}