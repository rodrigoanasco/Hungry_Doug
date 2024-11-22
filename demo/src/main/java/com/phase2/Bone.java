package com.phase2;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics;

public class Bone extends Reward {

    private Image boneSprite;

    public Bone(int x, int y) {
        super(x,y, RewardType.BONE, 15);
        Score.boneTotal++;
        try {
            boneSprite = ImageIO.read(getClass().getResource("/Bone.png"));
            boneSprite = boneSprite.getScaledInstance(OBJECT_SIZE[0], OBJECT_SIZE[1], Image.SCALE_DEFAULT);
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
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        if (!collected) {
            g.drawImage(boneSprite, x, y, null);
        }
    }

}