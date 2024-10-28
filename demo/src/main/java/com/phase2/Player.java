package com.phase2;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject {


    public Player(int x, int y, ID id){
        super(x,y,id);

        // used for testing only
        velX = 1;
        velY = 1;
        //
    }

    public void tick(){
        // used for testing only
        // moves +1 in x and y direction each tick of the game
        // x += velX;
        // y += velY;
        //
    }
    public void render(Graphics g){

        // used for testing only
        g.setColor(Color.WHITE);
        g.fillRect(x, y, 32, 32);
        //

    }


}
