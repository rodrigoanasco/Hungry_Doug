package com.phase2;

import java.awt.Graphics;

public class Rat extends MovingEnemy {
    public Rat(int x, int y, int penaltyPoints) {
        super(x,y,EnemyType.RAT, penaltyPoints); 
    }
    public void tick() {

    }
    public void render(Graphics g) {

    }
    @Override
    public void applyPenalty(Doug doug) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'applyPenalty'");
    }
}
