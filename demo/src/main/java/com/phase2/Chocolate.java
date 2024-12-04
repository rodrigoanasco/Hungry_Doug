package com.phase2;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Class for the non-moving enemy type Chocolate
 * @param x the x position the objects appears in
 * @param y the y position the objects appears in
 * @param id the type of object the object should be treated as
*/
public class Chocolate extends Punishment {

    public Chocolate(int x, int y){
        super(x,y, PunishmentType.CHOCOLATE, 50);
        this.WIDTH = 32;
        this.HEIGHT = 32;
        /*
        PREVIOUS
            try {
            image = ImageIO.read(getClass().getResource("/Brownie.png"));;
            image = image.getScaledInstance(OBJECT_SIZE[0], OBJECT_SIZE[1], Image.SCALE_DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        } */
       //REFACTORED
       image = ImageLoader.loadImage("/Brownie.png", WIDTH, HEIGHT);

    }
 
    /**
     * Renders the visual representation of the object on the screen.
     * 
     * @param g the {@link Graphics} object used to draw the sprite
     */
    @Override
    public void render(Graphics g) {
        if (!collected) {
            g.drawImage(image, x, y, null);
        }
    }

}
