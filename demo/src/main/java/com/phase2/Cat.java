package com.phase2;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Class for the moving enemy type Cat
 * @param x the x position the objects appears in
 * @param y the y position the objects appears in
 * @param id the type of object the object should be treated as
*/
public class Cat extends MovingEnemy {
    //TODO Cat hasn't been utilized yet

    public Cat(int x, int y) {
        super(x,y, EnemyType.CAT, Health.HEALTH);
    }

    /**
     * Gets the bounding rectangle of Doug for collision detection.
     * 
     * @return A Rectangle representing Doug's bounds.
     */
    public Rectangle getBounds() {
        return new Rectangle(x,y,32,32);
    }

    /**
     * What the object should do on each tick
     */
    public void tick() {

    }
    
    /**
     * How the object should look like
     */
    public void render(Graphics g) {

    }
}
