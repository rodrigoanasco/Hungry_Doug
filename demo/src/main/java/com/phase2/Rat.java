package com.phase2;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.LinkedList;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.util.LinkedList;
import java.lang.Math;

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

    private boolean moving = false;
    private boolean pathfinding = false;
    private boolean facingRight = true;
    //testing Rectangle assumedBushHitbox = new Rectangle(500, 10, 32, 500);

    private static final double SCALE_FACTOR = 1.25;

    // private double speed = 1.5;

    public Rat(int x, int y, Handler handler) {
        super(x,y,EnemyType.RAT, Health.HEALTH);
        this.handler = handler;
        
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

    // public void tick() {
    //     // Access the singleton instance of Doug
    //     Doug doug = Doug.getInstance();
    
    //     if (doug != null) {



    //         // Simple movement logic: move horizontally or vertically toward Doug
    //         if (x != doug.getX()) {
    //             velX = (x < doug.getX()) ? 1 : -1;
    //             facingRight = (x < doug.getX());
    //             velY = 0; // Only move horizontally
    //         } else if (y != doug.getY()) {
    //             velX = 0;
    //             velY = (y < doug.getY()) ? 1 : -1; // Only move vertically
    //         }
    //     }
    

    //     // Predicted position
    //     int predictedX = x + velX;
    //     int predictedY = y + velY;

    //     // Check collision with obstacles
    //     Rectangle predictedBounds = new Rectangle(predictedX, predictedY, 32, 32);

    //     // synchronized (handler.obstacles) {
    //         for (GameObject temp : handler.obstacles) {
    //             if (temp.getBounds().intersects(predictedBounds)) {
    //                 velX = 0;
    //                 velY = 0;
    //                 break;
    //             }
    //         }
    //     // }


    
    //     // Update position based on velocity
    //     x += velX;
    //     y += velY;
    
    //     // Flips direction upon hitting game boundary
    //     if (y < 0 || y >= Game.HEIGHT - 100) velY *= -1;
    //     if (x < 0 || x >= Game.WIDTH - 100) velX *= -1;
    // }
 


    // public void tick() {
    //     // Access the singleton instance of Doug
    //     Doug doug = Doug.getInstance();
    
    //     if (doug != null) {
    //         // Simple heuristic: Move closer to Doug (manhattan distance)
    //         int targetX = doug.getX();
    //         int targetY = doug.getY();
    
    //         // If already moving, keep direction unless there's an obstacle
    //         if (velX == 0 && velY == 0) {
    //             if (Math.abs(x - targetX) > Math.abs(y - targetY)) {
    //                 velX = (x < targetX) ? 1 : -1;
    //                 velY = 0;
    //             } else {
    //                 velY = (y < targetY) ? 1 : -1;
    //                 velX = 0;
    //             }
    //         }
    //     }
    
    //     // Predicted position
    //     int predictedX = x + velX;
    //     int predictedY = y + velY;
    
    //     // Check collision with obstacles
    //     Rectangle predictedBounds = new Rectangle(predictedX, predictedY, 32, 32);
    
    //     synchronized (handler.obstacles) {
    //         boolean collision = false;
    
    //         for (GameObject temp : handler.obstacles) {
    //             if (temp.getBounds().intersects(predictedBounds)) {
    //                 collision = true;
    //                 break;
    //             }
    //         }
    
    //         if (collision) {
    //             // Try new directions in priority order: Perpendicular to current direction
    //             if (velX != 0) {
    //                 // Switch to vertical direction
    //                 velX = 0;
    //                 velY = (y < doug.getY()) ? 1 : -1;
    //             } else if (velY != 0) {
    //                 // Switch to horizontal direction
    //                 velY = 0;
    //                 velX = (x < doug.getX()) ? 1 : -1;
    //             }
    
    //             // Recalculate predicted position
    //             predictedX = x + velX;
    //             predictedY = y + velY;
    //             predictedBounds = new Rectangle(predictedX, predictedY, 32, 32);
    
    //             // Check for collision in the new direction
    //             for (GameObject temp : handler.obstacles) {
    //                 if (temp.getBounds().intersects(predictedBounds)) {
    //                     // If still colliding, stop movement
    //                     velX = 0;
    //                     velY = 0;
    //                     break;
    //                 }
    //             }
    //         }
    //     }
    
    //     // Update position based on velocity
    //     x += velX;
    //     y += velY;
    
    //     // Flip direction upon hitting the game boundary
    //     if (y < 0 || y >= Game.HEIGHT - 100) velY *= -1;
    //     if (x < 0 || x >= Game.WIDTH - 100) velX *= -1;
    // }




    public void tick() {
        // Access the singleton instance of Doug
        Doug doug = Doug.getInstance();
    
        if (doug != null) {
            // Target position (Doug's position)
            int targetX = doug.getX();
            int targetY = doug.getY();
    
            // Determine the preferred direction
            int preferredVelX = (x < targetX) ? 1 : (x > targetX) ? -1 : 0; // Move horizontally
            int preferredVelY = (y < targetY) ? 1 : (y > targetY) ? -1 : 0; // Move vertically
    
            // Try moving along the horizontal axis first
            if (!tryMove(preferredVelX, 0)) {
                // If horizontal is blocked, try moving vertically
                if (!tryMove(0, preferredVelY)) {
                    // If both are blocked, stop moving
                    velX = 0;
                    velY = 0;
                }
            }
        }

    
        // Update position based on velocity
        x += velX;
        y += velY;
    
        // Flip direction upon hitting the game boundary
        if (y < 0 || y >= Game.HEIGHT - 100) velY = 0; // Stop movement if out of bounds
        if (x < 0 || x >= Game.WIDTH - 100) velX = 0;
    }


    // /**
    //  * Attempts to move the rat in the given direction if no obstacle blocks the way.
    //  *
    //  * @param dirX The horizontal movement direction (-1, 0, 1).
    //  * @param dirY The vertical movement direction (-1, 0, 1).
    //  * @return true if the movement is valid and sets velocity, false otherwise.
    //  */
    private boolean tryMove(int dirX, int dirY) {
        if (dirX == 0 && dirY == 0) return false; // No movement
    
        // Predicted position
        int predictedX = x + dirX;
        int predictedY = y + dirY;
    
        // Predicted bounds
        Rectangle predictedBounds = new Rectangle(predictedX, predictedY, 32, 32);
    
        // Check for collisions with obstacles
        synchronized (handler.obstacles) {
            for (GameObject temp : handler.obstacles) {
                if (temp.getBounds().intersects(predictedBounds)) {
                    return false; // Obstacle detected
                }
            }
        }
    
        // If no collision, set velocity and return true
        velX = dirX;
        velY = dirY;

        if (dirX != 0) {
            facingRight = dirX > 0; // Update facingRight based on horizontal movement
        }
        return true;
    }

 
    /**
     * Visually renders the object
     */
    public void render(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;


        //testing g.drawRect(500, 10, (int)assumedBushHitbox.getWidth(), (int)assumedBushHitbox.getHeight());
        
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
