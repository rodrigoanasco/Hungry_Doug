package com.phase2.Obstacles;

import com.phase2.GameObjects.GameObject;
import com.phase2.Trackers.ID;

/**
 * The {@code Obstacle} class represents an obstacle in the game that blocks movement.
 * <p>
 * Obstacles are static objects that the player and other entities must navigate around.
 * This abstract class provides the base implementation for obstacles, requiring subclasses
 * to define specific behaviors and properties.
 * </p>
 * 
 * @see GameObject
 */
public abstract class Obstacle extends GameObject {

    /**
     * The type of obstacle (e.g., bush, wall) represented by an {@link ObstacleType}.
     */
    protected ObstacleType type;

    /**
     * Constructs an {@code Obstacle} with the specified position and type.
     * 
     * @param x    the x-coordinate of the obstacle
     * @param y    the y-coordinate of the obstacle
     * @param type the type of obstacle represented by {@link ObstacleType}
     */
    public Obstacle(int x, int y, ObstacleType type) {
        super(x, y, ID.OBSTACLE);
        this.type = type;
    }

    /**
     * Gets the type of the obstacle.
     * 
     * @return the {@link ObstacleType} of the obstacle
     */
    public ObstacleType getType() {
        return type;
    }

    /**
     * Sets the type of the obstacle.
     * 
     * @param type the {@link ObstacleType} to set
     */
    public void setType(ObstacleType type) {
        this.type = type;
    }
}
