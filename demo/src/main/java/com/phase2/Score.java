package com.phase2;

import java.awt.Color;
import java.awt.Graphics;

public class Score {
    
    public static int SCORE = 0;

    public void tick() {
        SCORE = Game.clamp(SCORE, 0, 1000000);
    } 

    public void render(Graphics g) {
        // testing only
        g.setColor(Color.BLACK);
        g.drawString("SCORE: " + SCORE, 25, 75); // Adjust the position
    }
}
