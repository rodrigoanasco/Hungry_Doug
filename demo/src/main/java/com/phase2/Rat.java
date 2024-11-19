package com.phase2;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
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

    private BufferedImage[] idleSprites;
    private BufferedImage[] walkSprites;
    private int currentFrame = 0;
    private int frameDelay = 5; // Controls animation speed
    private int frameCount = 0;

    private boolean moving = false;
    private boolean facingRight = true;

    private static final double SCALE_FACTOR = 1.25;

    // private double speed = 1.5;

    public Rat(int x, int y) {
        super(x,y,EnemyType.RAT, Health.HEALTH);
        
        try {
            // Load the idle and walk sprite sheets
            BufferedImage idleSheet = ImageIO.read(getClass().getResource("/ratIdle.png"));
            BufferedImage walkSheet = ImageIO.read(getClass().getResource("/ratWalk.png"));

            // Extract frames for idle animation (assuming 4 frames, each 32x32)
            idleSprites = new BufferedImage[4];
            for (int i = 0; i < 4; i++) {
                idleSprites[i] = idleSheet.getSubimage(i * 32, 0, 32, 32);
            }

            // Extract frames for walking animation (assuming 4 frames, each 32x32)
            walkSprites = new BufferedImage[4];
            for (int i = 0; i < 4; i++) {
                walkSprites[i] = walkSheet.getSubimage(i * 32, 0, 32, 32);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Initially, the rat is not moving
        velX = 1;
        velY = 1;
    }

    /**
     * Gets the bounding rectangle of Doug for collision detection.
     * 
     * @return A Rectangle representing Doug's bounds.
     */
    public Rectangle getBounds() {
        return new Rectangle(x,y,32,32);
    }

    /**
     * What the object should do on each tick
     */
    public void tick() {
        
        // Access the singleton instance of Doug
        Doug doug = Doug.getInstance();

        if (doug != null) {
            // Simple movement logic: move horizontally or vertically toward Doug
            if (x != doug.getX()) {
                velX = (x < doug.getX()) ? 1 : -1;
                facingRight = (x < doug.getX()) ? true : false;
                velY = 0; // Only move horizontally
            } else if (y != doug.getY()) {
                velX = 0;
                velY = (y < doug.getY()) ? 1 : -1; // Only move vertically
            }
        }
    
        // Update position based on velocity
        x += velX;
        y += velY;

        // Flips direction upon hitting game boundary
        if(y < 0 || y >= Game.HEIGHT - 100) velY *= -1;
        if(x < 0 || x >= Game.WIDTH - 100) velX *= -1;

    }
    
 
    /**
     * Visually renders the object
     */
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
        //g2d.drawImage(spriteToDraw, x, y-16, scaledWidth, scaledHeight, null);

        // Update frame for animation
        frameCount++;
        if (frameCount >= frameDelay) {
            frameCount = 0;
            currentFrame = (currentFrame + 1) % idleSprites.length; // Cycle through frames
        }
    }

}
