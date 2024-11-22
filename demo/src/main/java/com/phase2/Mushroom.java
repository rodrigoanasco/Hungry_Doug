package com.phase2;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics;

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
        try {
            mushroomSprite = ImageIO.read(getClass().getResource("/Mushroom.png"));;
            mushroomSprite = mushroomSprite.getScaledInstance(OBJECT_SIZE[0]+16, OBJECT_SIZE[1]+16, Image.SCALE_DEFAULT);
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
        //to be implemented: checks if colliding with doug
    }

    @Override
    public void render(Graphics g) {
        if (!collected) {
            g.drawImage(mushroomSprite, x-8, y-8, null);
        }
    }
}