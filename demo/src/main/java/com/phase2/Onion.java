package main.java;

import java.awt.Graphics;


/**
 * Class for the non-moving enemy type Onion
 * @param x the x position the objects appears in
 * @param y the y position the objects appears in
 * @param id the type of object the object should be treated as
*/
public class Onion extends GameObject {
    public Onion(int x, int y, ID id) {
        super(x,y,id);
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

    }
}