package com.phase2.GameObjects;

import java.awt.Image;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.phase2.Punishments.Punishment;
import com.phase2.Punishments.PunishmentType;


public class Onion extends Punishment {
    
    public Onion(int x, int y) {
        super(x,y, PunishmentType.ONION, 20);
        this.WIDTH = 32;
        this.HEIGHT = 32;
        try {
            image = ImageIO.read(getClass().getResource("/Onion.png"));;
            image = image.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Renders the visual representation of the object on the screen.
     * 
     * @param g the {@link Graphics} object used to draw the sprite
     */
    @Override
    public void render(Graphics g) {
        //render graphics
        if (!collected) {
            g.drawImage(image, x, y, null);
        }
    }

}