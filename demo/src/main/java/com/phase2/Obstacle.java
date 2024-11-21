package com.phase2;

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

    /**
     * Gets the type of the obstacle.
     * 
     * @return the ObstacleType
     */
    public ObstacleType getType() {
        return type;
    }

    /**
     * Abstract method to check if the obstacle blocks the movement of the player.
     * 
     * @param doug the player character
     * @return true if the movement is blocked, false otherwise
     */
    public abstract boolean blockMovement(Doug doug);
}
