package com.phase2;

import java.awt.Image;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Onion extends Punishment {
    
    public Onion(int x, int y) {
        super(x,y, PunishmentType.ONION, 20);
        try {
            image = ImageIO.read(getClass().getResource("/Onion.png"));;
            image = image.getScaledInstance(OBJECT_SIZE[0], OBJECT_SIZE[1], Image.SCALE_DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the bounding rectangle of Doug for collision detection.
     * 
     * @return A Rectangle representing Doug's bounds.
     */
    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,32,32);
    }

    /**
     * What the object should do on each tick
     */
    @Override
    public void tick() {

    }
    /**
     * How the object should look like
     */
    @Override
    public void render(Graphics g) {
        //render graphics
        if (!collected) {
            g.drawImage(image, x, y, null);
        }
    }

}