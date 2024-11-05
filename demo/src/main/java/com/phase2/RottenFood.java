package com.phase2;

import java.awt.Graphics;
import java.awt.Rectangle;

public class RottenFood extends Punishment{
    
    public RottenFood(int x, int y){
        super(x, y, PunishmentType.ROTTEN_FOOD, 10);
    }

    // TODO 32 change to whatever size of rotten food
    public Rectangle getBounds() {
        return new Rectangle(x,y,32,32);
    }

    public void tick() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'tick'");
    }

    public void render(Graphics g) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'render'");
    }
}
