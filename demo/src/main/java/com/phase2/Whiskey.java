package com.phase2;

import java.awt.Graphics;


public class Whiskey extends Punishment{

    public Whiskey(int x, int y){
        super(x, y, PunishmentType.WHISKEY, 25);
        this.WIDTH = 32;
        this.HEIGHT = 32;
        
        /* BEFORE
        try {
            image = ImageIO.read(getClass().getResource("/Whiskey.png"));;
            image = image.getScaledInstance(OBJECT_SIZE[0], OBJECT_SIZE[1], Image.SCALE_DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
        //REFACTORED
        image = ImageLoader.loadImage("/Whiskey.png", WIDTH, HEIGHT);

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
