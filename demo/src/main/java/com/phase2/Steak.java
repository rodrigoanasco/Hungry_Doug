package com.phase2;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics;

public class Steak extends Reward {

    private Image steakSprite;

    public Steak(int x, int y) {
        super(x,y, RewardType.STEAK, 20);
        try {
            steakSprite = ImageIO.read(getClass().getResource("/Steak.png"));
            steakSprite = steakSprite.getScaledInstance(OBJECT_SIZE[0], OBJECT_SIZE[1], Image.SCALE_DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Rectangle getBounds() {
        return new Rectangle(x,y,OBJECT_SIZE[0],OBJECT_SIZE[1]);
    }

    public void tick() {
        //to be implemented: checks if colliding with doug
    }

    public void render(Graphics g) {
        // renderHitBox(g,OBJECT_SIZE[0],OBJECT_SIZE[1]);
        if (!collected) {
            g.drawImage(steakSprite, x, y, null);
        }
    }

}