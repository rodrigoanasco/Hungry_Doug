package com.phase2;

public class Doug {
    private int positionX;
    private int positionY;
    private int health;
    private int score;

    public Doug() {
        this.positionX = 0;
        this.positionY = 0;
        this.score = 0;
        this.health = 100;
    }

    public void move(String direction) {
        // implement move logic here
    }

    public int getScore() {
        return score;
    }

    public int getPositionX(){
        return positionX;
    }

    public int getPositionY(){
        return positionY;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
