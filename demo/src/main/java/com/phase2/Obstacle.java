package com.phase2;

public abstract class Obstacle extends GameObject {
    protected ObstacleType type;


    public Obstacle(int x, int y, ObstacleType type){
        super(x, y, ID.OBSTAClE);
        this.type = type;
    }

    public ObstacleType getType() {
        return type;
    }

    // Abstract method to block movement
    public abstract boolean blockMovement(Doug doug);
}
