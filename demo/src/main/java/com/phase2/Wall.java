package com.phase2;

import java.awt.Graphics;

public class Wall extends Obstacle {
    public Wall(int x, int y) {
        super(x, y, ID.OBSTAClE, ObstacleType.WALL);
    }

    @Override
    public boolean blockMovement(Doug doug) {
        if (this.getX() == doug.getX() && 
            this.getY() == doug.getY()) {
            //Make some sort of sound play
                System.out.println("Doug can't pass through the wall!");
            return true;
        }
        return false;
    }

    public void tick(){

    }

    public void render(Graphics g){

    }
}
