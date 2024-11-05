package com.phase2;

import java.awt.Graphics;
import java.awt.Rectangle;

public class RottenFood extends Punishment{
    // TODO RottenFood not being used yet

    public RottenFood(int x, int y){
        super(x, y, PunishmentType.ROTTEN_FOOD, 10);
    }

    /**
     * Gets the bounding rectangle of Doug for collision detection.
     * 
     * @return A Rectangle representing Doug's bounds.
     */
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
