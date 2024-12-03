package com.phase2;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Bone extends Reward {

    private Image boneSprite;

    public Bone(int x, int y) {
        super(x,y, RewardType.BONE, 15);
        Score.boneTotal++;
        /*
        PREVIOUS
        try {
            boneSprite = ImageIO.read(getClass().getResource("/Bone.png"));
            boneSprite = boneSprite.getScaledInstance(OBJECT_SIZE[0], OBJECT_SIZE[1], Image.SCALE_DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        } */
       //REFACTORED
       boneSprite = ImageLoader.loadImage("/Bone.png", OBJECT_SIZE[0], OBJECT_SIZE[1]);

    }
    
   /**
     * Gets the bounding rectangle of the bone for collision detection.
     *
     * @return a {@link Rectangle} representing the bounds of the object.
     */
    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,OBJECT_SIZE[0],OBJECT_SIZE[1]);
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