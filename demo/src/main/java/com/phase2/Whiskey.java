package com.phase2;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Whiskey extends Punishment{
    // TODO RottenFood not being used yet

    public Whiskey(int x, int y){
        super(x, y, PunishmentType.WHISKEY, 25);
                    try {
            image = ImageIO.read(getClass().getResource("/Whiskey.png"));;
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
        return new Rectangle(x,y,32,32);
    }

    public void tick() {

    }

    public void render(Graphics g) {
        if (!collected) {
            g.drawImage(image, x, y, null);
        }
    }
}
