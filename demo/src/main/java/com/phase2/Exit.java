package com.phase2;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.Graphics;


public class Exit extends GameObject {

    private Image sprite;

    public Exit(int x, int y) {
        super(x,y, ID.EXIT);
        try {
            sprite = ImageIO.read(getClass().getResource("/Exit.png"));
            sprite = sprite.getScaledInstance(OBJECT_SIZE[0], OBJECT_SIZE[1], Image.SCALE_DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Rectangle getBounds() {
        return new Rectangle(x,y,OBJECT_SIZE[0],OBJECT_SIZE[1]);

    }

    public void tick() {

    }

    public void render(Graphics g) {
        // renderHitBox(g,OBJECT_SIZE[0],OBJECT_SIZE[1]);
            if (Score.boneScore >= Score.boneTotal) {
                g.drawImage(sprite, x, y, null);
        }
    }

}