package com.phase2;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Class for the non-moving enemy type Chocolate
 * @param x the x position the objects appears in
 * @param y the y position the objects appears in
 * @param id the type of object the object should be treated as
*/
public class Chocolate extends Punishment {


    public Chocolate(int x, int y){
    super(x,y, PunishmentType.CHOCOLATE, 50);
            try {
            image = ImageIO.read(getClass().getResource("/Brownie.png"));;
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
    public Rectangle getBounds() {
        // 32 gets replaced with whatever the size of chocolate is
        return new Rectangle(x,y,32,32);
    }

    /**
     * What the object should do on each tick
     */
    public void tick() {

    }
    /**
     * How the object should look like
     */
    public void render(Graphics g) {
        if (!collected) {
            g.drawImage(image, x, y, null);
        }

    }

}
