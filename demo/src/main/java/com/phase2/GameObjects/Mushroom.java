package com.phase2.GameObjects;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import com.phase2.ImageLoader;
import com.phase2.Reward;
import com.phase2.Rewards.RewardType;

/**
 * Represents a Mushroom reward in the game.
 * The Mushroom provides points when collected by the player.
 */
public class Mushroom extends Reward {

    private Image mushroomSprite;

    /**
     * Constructs a Mushroom object at the specified coordinates.
     * Initializes the mushroom sprite image.
     * 
     * @param x The x-coordinate of the Mushroom.
     * @param y The y-coordinate of the Mushroom.
     */
    public Mushroom(int x, int y) {
        super(x,y, RewardType.MUSHROOM, 10);
        this.WIDTH = 48;
        this.HEIGHT = 48;
        /* BEFORE
        try {
            mushroomSprite = ImageIO.read(getClass().getResource("/Mushroom.png"));;
            mushroomSprite = mushroomSprite.getScaledInstance(OBJECT_SIZE[0]+16, OBJECT_SIZE[1]+16, Image.SCALE_DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
        //REFACTORED
        mushroomSprite = ImageLoader.loadImage("/Mushroom.png", WIDTH, HEIGHT);
    }
    


    /**
     * Renders the visual representation of the mushroom on the screen.
     * 
     * If the object has not been collected, this method draws the corresponding sprite
     * at the object's current position on the screen.
     *
     * @param g the {@link Graphics} object used to draw the sprite
     */
    @Override
    public void render(Graphics g) {
        if (!collected) {
            g.drawImage(mushroomSprite, x, y, null);
        }
    }
}