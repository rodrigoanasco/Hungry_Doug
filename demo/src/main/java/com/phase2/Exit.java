package com.phase2;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 * Represents the Exit object in the game.
 * The Exit becomes active when the player collects all required bones.
 * The Exit is rendered on the screen once the condition is met.
 */
public class Exit extends GameObject {

    private Image sprite;
    private boolean hidden = true;

    /**
     * Constructs an Exit object with the specified coordinates.
     * Loads and scales the sprite for the Exit.
     * 
     * @param x The x-coordinate of the Exit.
     * @param y The y-coordinate of the Exit.
     */
    public Exit(int x, int y) {
        super(x,y, ID.EXIT);
        /*
        BEFORE
        try {
            sprite = ImageIO.read(getClass().getResource("/Exit.png"));
            sprite = sprite.getScaledInstance(OBJECT_SIZE[0], OBJECT_SIZE[1], Image.SCALE_DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        //REFACTORED RODRIGO
        sprite = ImageLoader.loadImage("/Exit.png", OBJECT_SIZE[0], OBJECT_SIZE[1]);

    }
    
    /**
     * Gets the bounding rectangle of the Exit for collision detection.
     * 
     * @return A Rectangle representing the bounds of the Exit.
     */
    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,OBJECT_SIZE[0],OBJECT_SIZE[1]);
    }

    /**
     * returns hidden boolen
     */
    public boolean getHiddenStatus() {
        return hidden;
    }

    /**
     * Updates the state of the Exit.
     */
    public void tick() {
   
        if (Score.boneScore >= Score.boneTotal && hidden) {
            SoundEffect.play("/reward.wav");
            hidden = false;
        }
    }

    /**
     * Renders the Exit on the screen.
     * The Exit is only drawn when the player has collected all required bones.
     * 
     * @param g The Graphics object used to draw the Exit.
     */
    @Override
    public void render(Graphics g) {
            if (Score.boneScore >= Score.boneTotal) {
                g.drawImage(sprite, x, y, null);
                
                
                

        }
    }
}