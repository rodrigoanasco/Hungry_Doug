package com.phase2;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Wall extends Obstacle {
    public Wall(int x, int y) {
        super(x, y, ObstacleType.WALL);
    }

    public boolean blockMovement(Doug doug) {
        if (this.getX() == doug.getX() && 
            this.getY() == doug.getY()) {
            //Make some sort of sound play
                System.out.println("Doug can't pass through the wall!");
            return true;
        }
        return false;
    }

    // TODO 32 chanegs to dimensions of wall
    public Rectangle getBounds() {
        return new Rectangle(x,y,32,32);
    }

    public void tick(){

    }

    public void render(Graphics g){

    }
}
