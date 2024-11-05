package com.phase2;

import java.awt.Graphics;
import java.awt.Rectangle;

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

    // TODO adjust size of rectangle
    public Rectangle getBounds() {
        // 32 gets replaced with whatever the size of chocolate is
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
    @Override
    public void applyPenalty(Doug doug) {
        // TODO Auto-generated method stub
    }

}
