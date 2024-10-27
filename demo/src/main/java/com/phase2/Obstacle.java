package com.phase2;

public abstract class Obstacle {
    protected String type;
    private int positionX;
    private int positionY;

    public Obstacle(String type, int positionX, int positionY) {
        this.type = type;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public String getType() {
        return type;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    // Abstract method to block movement
    public abstract boolean blockMovement(Doug doug);
}
