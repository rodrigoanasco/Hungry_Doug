package com.phase2;

import java.awt.Graphics;

/**
 * Class for the moving enemy type Rat
 * @param x the x position the objects appears in
 * @param y the y position the objects appears in
 * @param id the type of object the object should be treated as
*/
public class Rat extends MovingEnemy {
    public Rat(int x, int y, int penaltyPoints) {
        super(x,y,EnemyType.RAT, penaltyPoints); 
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
        throw new UnsupportedOperationException("Unimplemented method 'applyPenalty'");
    }
}
