package com.phase2;

import java.awt.Graphics;

public class Cat extends MovingEnemy {
    
    // TODO make penalty points equal to number of points Doug has
    public Cat(int x, int y, int penaltyPoints) {
        super(x,y, EnemyType.CAT, penaltyPoints);
    }
    public void tick() {

    }
    public void render(Graphics g) {

    }
    @Override
    public void applyPenalty(Doug doug) {
        // TODO Auto-generated method stub
       
    }
}
