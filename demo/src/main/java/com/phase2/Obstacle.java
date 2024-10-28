package com.phase2;

public abstract class Obstacle extends GameObject {
    protected ObstacleType type;


    public Obstacle(int x, int y, ID id, ObstacleType type){
        super(x,y,id);

        this.type = type;
    }

    public ObstacleType getType() {
        return type;
    }

    // Abstract method to block movement
    public abstract boolean blockMovement(Doug doug);
}
