package com.phase2;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.Graphics;


public class Mushroom extends Reward {

    private Image mushroomSprite;

    public Mushroom(int x, int y,RewardType rewardType,int rewardAmount) {
        super(x,y, RewardType.MUSHROOM, rewardAmount);
        try {
            mushroomSprite = ImageIO.read(getClass().getResource("/Mushroom.png"));;
            mushroomSprite = mushroomSprite.getScaledInstance(OBJECT_SIZE[0], OBJECT_SIZE[1], Image.SCALE_DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Rectangle getBounds() {
        return new Rectangle(x,y,OBJECT_SIZE[0]-16,OBJECT_SIZE[1]-16);
    }

    public void tick() {
        //to be implemented: checks if colliding with doug

    }

    public void render(Graphics g) {
        if (!collected) {
            g.drawImage(mushroomSprite, x, y, null);
        }
    }
}