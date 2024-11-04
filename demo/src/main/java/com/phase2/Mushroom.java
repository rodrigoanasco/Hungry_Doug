package com.phase2;

import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.Graphics;


public class Mushroom extends FlashingReward {

    private Image mushroomSprite;

    public Mushroom(int x, int y,FlashingRewardType rewardType,int rewardAmount) {
        super(x,y, FlashingRewardType.MUSHROOM, 1);
        try {
            mushroomSprite = ImageIO.read(getClass().getResource("/Mushroom.png"));;
            mushroomSprite = mushroomSprite.getScaledInstance(OBJECT_SIZE[0], OBJECT_SIZE[1], Image.SCALE_DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    public void tick() {
        //to be implemented: checks if colliding with doug

    }

    public void render(Graphics g) {
        g.drawImage(mushroomSprite, x, y, null);
    }
}