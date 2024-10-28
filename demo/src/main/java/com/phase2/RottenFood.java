package com.phase2;

import java.awt.Graphics;

public class RottenFood extends Punishment{
    public RottenFood(int x, int y){
        super(x, y, PunishmentType.ROTTEN_FOOD, 10);
    }

    @Override
    public void applyPenalty(Doug doug){
        doug.setHealth(doug.getHealth() - penaltyPoints);
    }

    @Override
    public void tick() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'tick'");
    }

    @Override
    public void render(Graphics g) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'render'");
    }
}
