package com.phase2;

public class Doug {
    private int positionX;
    private int positionY;
    private int health;
    private int score;

    private final int speed = 5; // speed of movement

    public Doug() {
        this.positionX = 250;
        this.positionY = 250;
        this.score = 0;
        this.health = 100;
    }

    // move method to update Doug's position based on direction
    public void move(String direction) {
        switch (direction) {
            case "UP":
                positionY -= speed;
                break;
            case "DOWN":
                positionY += speed;
                break;
            case "LEFT":
                positionX -= speed;
                break;
            case "RIGHT":
                positionX += speed;
                break;
        }
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public int getScore() {
        return score;
    }

}
