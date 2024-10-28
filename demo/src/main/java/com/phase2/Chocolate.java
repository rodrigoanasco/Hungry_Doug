package com.phase2;

import java.awt.Graphics;

/**
 * Class for the non-moving enemy type Chocolate
 * @param x the x position the objects appears in
 * @param y the y position the objects appears in
 * @param id the type of object the object should be treated as
*/

public class Chocolate extends Punishment {
    public Chocolate(int x, int y){
    super(x,y, PunishmentType.CHOCOLATE, 20);

    }
<<<<<<< HEAD
=======
    /**
     * What the object should do on each tick
     */
>>>>>>> enemy
    public void tick() {


    }
<<<<<<< HEAD
=======
    /**
     * How the object should look like
     */
>>>>>>> enemy
    public void render(Graphics g) {


    }
    @Override
    public void applyPenalty(Doug doug) {
        // TODO Auto-generated method stub
    }

}
