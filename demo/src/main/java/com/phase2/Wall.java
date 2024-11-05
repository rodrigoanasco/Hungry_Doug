package com.phase2;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Represents a wall obstacle in the game.
 * The Wall blocks Doug's movement and may trigger a response when collided with.
 */
public class Wall extends Obstacle {

    /**
     * Constructs a Wall object at the specified coordinates.
     * 
     * @param x The x-coordinate of the Wall.
     * @param y The y-coordinate of the Wall.
     */
    public Wall(int x, int y) {
        super(x, y, ObstacleType.WALL);
    }

    /**
     * Checks if Doug is attempting to move through the Wall.
     * If so, blocks Doug's movement and triggers a response.
     * 
     * @param doug The Doug object attempting to move.
     * @return True if Doug's movement is blocked, false otherwise.
     */
    public boolean blockMovement(Doug doug) {
        if (this.getX() == doug.getX() && 
            this.getY() == doug.getY()) {
            //Make some sort of sound play
                System.out.println("Doug can't pass through the wall!");
            return true;
        }
        return false;
    }

    /**
     * Gets the bounding rectangle of the Wall for collision detection.
     * 
     * @return A Rectangle representing the bounds of the Wall.
     */
    public Rectangle getBounds() {
        return new Rectangle(x,y,32,32);
    }

    public void tick(){

    }

    public void render(Graphics g){

    }
}
