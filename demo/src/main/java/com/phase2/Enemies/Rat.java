package com.phase2.Enemies;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import com.phase2.Handler;
import com.phase2.MovingEnemy;
import com.phase2.Trackers.Health;

import java.io.IOException;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

/**
 * Class for the moving enemy type Rat
 * @param x the x position the objects appears in
 * @param y the y position the objects appears in
 * @param id the type of object the object should be treated as
 */
public class Rat extends MovingEnemy {

    Handler handler;

    private BufferedImage[] idleSprites;
    private BufferedImage[] walkSprites;
    private int currentFrame = 0;
    private int frameDelay = 25; // Controls animation speed
    private int frameCount = 0;



    private static final double SCALE_FACTOR = 1.25;

    public Rat(int x, int y) {
        super(x,y,EnemyType.RAT, Health.HEALTH);
        this.handler = Handler.getHandlerInstance();
        this.WIDTH = 32;
        this.HEIGHT = 32;
        
        try {
            // Load the idle and walk sprite sheets
            BufferedImage idleSheet = ImageIO.read(getClass().getResource("/ratIdle.png"));
            BufferedImage walkSheet = ImageIO.read(getClass().getResource("/ratWalk.png"));

            // Extract frames for idle animation (assuming 4 frames, each 32x32)
            idleSprites = new BufferedImage[4];
            for (int i = 0; i < 4; i++) {
                idleSprites[i] = idleSheet.getSubimage(i * 32, 0, WIDTH, HEIGHT);
            }

            // Extract frames for walking animation (assuming 4 frames, each 32x32)
            walkSprites = new BufferedImage[4];
            for (int i = 0; i < 4; i++) {
                walkSprites[i] = walkSheet.getSubimage(i * 32, 0, WIDTH, HEIGHT);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        // Initially, the rat is not moving
        velX = 1;
        velY = 1;
    }


    /**
     * What the object should do on each tick
     */
    @Override
    public void tick() {
        move();
    }
    
    

    

   /**
     * Renders the visual representation of the object on the screen.
     * 
     * @param g the {@link Graphics} object used to draw the sprite
     */
    @Override
    public void render(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        
        // Rat is moving or idle
        BufferedImage spriteToDraw = moving ? walkSprites[currentFrame] : idleSprites[currentFrame];

        int scaledWidth = (int) (spriteToDraw.getWidth() * SCALE_FACTOR);
        int scaledHeight = (int) (spriteToDraw.getHeight() * SCALE_FACTOR);

        // Draw the sprite at the rat's current position
        if (facingRight) {
            // Draw normally if facing right
            g2d.drawImage(spriteToDraw, x, y-16, scaledWidth, scaledHeight, null);
        } 
        else {
            // Flip horizontally if facing left
            AffineTransform transform = new AffineTransform();
            transform.translate(x + scaledWidth, y-16); // Move to the correct position
            transform.scale(-SCALE_FACTOR, SCALE_FACTOR); // Flip horizontally
            g2d.drawImage(spriteToDraw, transform, null);
        }

        // Update frame for animation
        frameCount++;
        if (frameCount >= frameDelay) {
            frameCount = 0;
            currentFrame = (currentFrame + 1) % idleSprites.length; // Cycle through frames
        }
    }
}
