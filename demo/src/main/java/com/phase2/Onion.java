package com.phase2;

import java.awt.Graphics;


public class Onion extends Punishment {
    public Onion(int x, int y) {
        super(x,y, PunishmentType.ONION, 10);
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