package com.phase2;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * The Obstacle class represents an obstacle in the game that blocks movement.
 * It is an abstract class requiring subclasses to define the specific blocking behavior.
 */
public abstract class Obstacle extends GameObject {
    
    protected ObstacleType type;

    /**
     * Constructor for an Obstacle.
     * 
     * @param x the x-coordinate of the obstacle
     * @param y the y-coordinate of the obstacle
     * @param type the type of obstacle (ObstacleType)
     */
    public Obstacle(int x, int y, ObstacleType type){
        super(x, y, ID.OBSTACLE);
        this.type = type;
    }

    // /**
    //  * Gets the type of the obstacle.
    //  * 
    //  * @return the ObstacleType
    //  */
    // public ObstacleType getType() {
    //     return type;
    // }
    
    /**
     * Gets the bounding rectangle of the reward for collision detection.
     */
    public abstract Rectangle getBounds();

    /**
     * Renders the visual representation of the object on the screen.
     * 
     * @param g the {@link Graphics} object used to draw the sprite
     */
    public abstract void render(Graphics g);
}
