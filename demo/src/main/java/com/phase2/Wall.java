package com.phase2;

public class Wall extends Obstacle {
    public Wall(int x, int y) {
        super("Wall", x, y);
    }

    @Override
    public boolean blockMovement(Doug doug) {
        if (this.getPositionX() == doug.getPositionX() && 
            this.getPositionY() == doug.getPositionY()) {
            //Make some sort of sound play
                System.out.println("Doug can't pass through the wall!");
            return true;
        }
        return false;
    }
}
