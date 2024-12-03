package com.phase2;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Whiskey extends Punishment{

    public Whiskey(int x, int y){
        super(x, y, PunishmentType.WHISKEY, 25);
        
        /* BEFORE
        try {
            image = ImageIO.read(getClass().getResource("/Whiskey.png"));;
            image = image.getScaledInstance(OBJECT_SIZE[0], OBJECT_SIZE[1], Image.SCALE_DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
        //REFACTORED RODRIGO
        image = ImageLoader.loadImage("/Whiskey.png", OBJECT_SIZE[0], OBJECT_SIZE[1]);

    }

    /**
     * Gets the bounding rectangle of the steak for collision detection.
     * 
     * @return A Rectangle representing the steak's bounds.
     */
    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,32,32);
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
