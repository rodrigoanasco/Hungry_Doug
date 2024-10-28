package com.phase2;

import java.awt.Graphics;


<<<<<<< HEAD

=======
/**
 * Class for the non-moving enemy type Onion
 * @param x the x position the objects appears in
 * @param y the y position the objects appears in
 * @param id the type of object the object should be treated as
*/
>>>>>>> enemy
public class Onion extends GameObject {
    public Onion(int x, int y, ID id) {
        super(x,y,id);
public class Onion extends Punishment {
    public Onion(int x, int y) {
        super(x,y, PunishmentType.ONION, 10);
    }
<<<<<<< HEAD
    public void tick() {

    }
=======
    /**
     * What the object should do on each tick
     */
    public void tick() {

    }
    /**
     * How the object should look like
     */
>>>>>>> enemy
    public void render(Graphics g) {

    }
    @Override
    public void applyPenalty(Doug doug) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'applyPenalty'");
    }
}