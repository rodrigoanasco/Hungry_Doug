package com.phase2;

import java.awt.Color;
import java.awt.Graphics;

public class Health {

    public static int HEALTH = 200;

    public void tick() {
        HEALTH = Game.clamp(HEALTH, 0, 200);
    } 

    public void render(Graphics g) {
        // testing only
        g.setColor(Color.GRAY);
        g.fillRect(15, 15, 200, 32);
        g.setColor(Color.GREEN);
        g.fillRect(15, 15, HEALTH, 32);
        g.setColor(Color.GRAY);
        g.drawRect(15, 15, 200, 32);
    }
    
}
