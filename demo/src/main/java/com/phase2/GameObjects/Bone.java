package com.phase2.GameObjects;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import com.phase2.ImageLoader;
import com.phase2.Rewards.Reward;
import com.phase2.Rewards.RewardType;
import com.phase2.Trackers.Score;

public class Bone extends Reward {

    private Image boneSprite;

    public Bone(int x, int y) {
        super(x,y, RewardType.BONE, 15);
        Score.boneTotal++;
        this.WIDTH = 32;
        this.HEIGHT = 32;
        /*
        PREVIOUS
        try {
            boneSprite = ImageIO.read(getClass().getResource("/Bone.png"));
            boneSprite = boneSprite.getScaledInstance(OBJECT_SIZE[0], OBJECT_SIZE[1], Image.SCALE_DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        } */
       //REFACTORED
       boneSprite = ImageLoader.loadImage("/Bone.png", WIDTH, HEIGHT);

    }
    

    /**
     * Renders the visual representation of the bone on the screen.
     * 
     * If the object has not been collected, this method draws the corresponding sprite
     * at the object's current position on the screen.
     *
     * @param g the {@link Graphics} object used to draw the sprite
     */
    @Override
    public void render(Graphics g) {
        if (!collected) {
            g.drawImage(boneSprite, x, y, null);
        }
    }

}