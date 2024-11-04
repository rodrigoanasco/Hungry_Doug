package com.phase2;

import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.Graphics;


public class Bone extends Reward {

    private Image boneSprite;

    public Bone(int x, int y,RewardType rewardType,int rewardAmount) {
        super(x,y, RewardType.BONE, 1);
        try {
            boneSprite = ImageIO.read(getClass().getResource("/Bone.png"));
            boneSprite = boneSprite.getScaledInstance(OBJECT_SIZE[0], OBJECT_SIZE[1], Image.SCALE_DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    public void tick() {
        //to be implemented: checks if colliding with doug

    }

    public void render(Graphics g) {
        g.drawImage(boneSprite, x, y, null);
    }

}