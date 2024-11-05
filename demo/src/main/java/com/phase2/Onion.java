package com.phase2;

import java.awt.Graphics;
import java.awt.Rectangle;


public class Onion extends Punishment {
    public Onion(int x, int y) {
        super(x,y, PunishmentType.ONION, 10);
    }
    
    // TODO 32 gets replaced with whatever the size of onion 
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
    @Override
    public void applyPenalty(Doug doug) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'applyPenalty'");
    }
}