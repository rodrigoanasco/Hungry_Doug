package com.phase2;

import java.awt.Graphics;

/**
 * Class for the moving enemy type Cat
 * @param x the x position the objects appears in
 * @param y the y position the objects appears in
 * @param id the type of object the object should be treated as
*/
public class Cat extends MovingEnemy {
    
    // TODO make penalty points equal to number of points Doug has
    public Cat(int x, int y, int penaltyPoints) {
        super(x,y, EnemyType.CAT, penaltyPoints);

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
    
    @Override
    public void applyPenalty(Doug doug) {
        // TODO Auto-generated method stub
       
    }
}
