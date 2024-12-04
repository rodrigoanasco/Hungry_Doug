package com.phase2;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * The MovingEnemy class represents enemies that move and apply penalties to the player.
 * It is an abstract class that requires subclasses to define the specific penalty behavior.
 */
public abstract class MovingEnemy extends GameObject{
    
    Handler handler;

    protected int penaltyPoints;
    protected EnemyType type;
    protected boolean moving = false;
    protected boolean facingRight = true;

    /**
     * Constructor for a MovingEnemy.
     * 
     * @param x the x-coordinate of the enemy
     * @param y the y-coordinate of the enemy
     * @param type the type of enemy (EnemyType)
     * @param penaltyPoints the penalty points applied to the player
     */
    public MovingEnemy(int x, int y, EnemyType type, int penaltyPoints) {
        super(x, y, ID.ENEMY);
        this.type = type;
        this.penaltyPoints = penaltyPoints;
        
        
    }

    // /**
    //  * Gets the type of the enemy.
    //  * 
    //  * @return the EnemyType of the enemy
    //  */
    // public EnemyType getType() {
    //     return type;
    // }

    /**
     * Gets the penalty points applied by the enemy.
     * 
     * @return the number of penalty points
     */
    public int getPenaltyPoints() {
        return penaltyPoints;
    }

    /**
     * Attempts to move the rat in the given direction if no obstacle blocks the way.
     *
     * @param dirX The horizontal movement direction (-1, 0, 1).
     * @param dirY The vertical movement direction (-1, 0, 1).
     * @return true if the movement is valid and sets velocity, false otherwise.
     */
    protected boolean tryMove(int dirX, int dirY) {
        handler = Handler.getHandlerInstance();
        if (dirX == 0 && dirY == 0) return false; // No movement
    
        // Predicted position
        int predictedX = x + dirX;
        int predictedY = y + dirY;
    
        // Predicted bounds
        Rectangle predictedBounds = new Rectangle(predictedX, predictedY, WIDTH, HEIGHT);
    
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

    protected void move() {
        Doug doug = Doug.getInstance();
    
        if (doug != null) {
            // Target position (Doug's position)
            int targetX = doug.getX();
            int targetY = doug.getY();
    
            // Determine preferred direction
            int preferredVelX = (x < targetX) ? 1 : (x > targetX) ? -1 : 0; // Move horizontally
            int preferredVelY = (y < targetY) ? 1 : (y > targetY) ? -1 : 0; // Move vertically
    
            // Try moving along horizontal axis first
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
    //  * Defines behavior on each tick of the game loop
    //  */
    // public void tick() {
    //     x = Game.clamp(x,0,Game.WIDTH - 30);
    //     y = Game.clamp(y,0,Game.HEIGHT - 30);
    // }
    

    
    

}