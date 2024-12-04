package com.phase2.GameObjects;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import com.phase2.ImageLoader;
import com.phase2.Rewards.Reward;
import com.phase2.Rewards.RewardType;


public class Apple extends Reward {

    private Image appleSprite;

    public Apple(int x, int y) {
        super(x,y, RewardType.APPLE, 10);
        this.WIDTH = 32;
        this.HEIGHT = 32;
        /* PREVIOUS
        try {
            appleSprite = ImageIO.read(getClass().getResource("/Apple.png"));
            appleSprite = appleSprite.getScaledInstance(OBJECT_SIZE[0], OBJECT_SIZE[1], Image.SCALE_DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        } */

        //REFACTORED
        appleSprite = ImageLoader.loadImage("/Apple.png", WIDTH, HEIGHT);
    }
    

    /**
     * Renders the visual representation of the apple on the screen.
     * 
     * If the object has not been collected, this method draws the corresponding sprite
     * at the object's current position on the screen.
     *
     * @param g the {@link Graphics} object used to draw the sprite
     */
    @Override
    public void render(Graphics g) {
        if (!collected) {
            g.drawImage(appleSprite, x, y, null);
        }
    }

}