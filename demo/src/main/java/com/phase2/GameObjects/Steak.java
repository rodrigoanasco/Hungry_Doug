package com.phase2.GameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import com.phase2.ImageLoader;
import com.phase2.Reward;
import com.phase2.Rewards.RewardType;

/**
 * Represents a Steak reward in the game.
 * The Steak appears for a limited time, and the player can collect it for points before it disappears.
 */
public class Steak extends Reward {

    private Image steakSprite;
    private int lifetime;
    private int spawntime;
    private int deathtime;
    private Boolean isAlive = true;
    Random r;

    /**
     * Constructs a Steak object at the specified coordinates.
     * Initializes the steak sprite and sets random spawn and death times.
     * 
     * @param x The x-coordinate of the Steak.
     * @param y The y-coordinate of the Steak.
     */
    public Steak(int x, int y) {
        super(x,y, RewardType.STEAK, 20);
        r = new Random();
        this.WIDTH = 32;
        this.HEIGHT = 32;
        this.lifetime = 0;
        this.spawntime = r.nextInt(5)+1;
        this.deathtime = r.nextInt(25+spawntime)+10+spawntime;
        /* BEFORE
        try {
            steakSprite = ImageIO.read(getClass().getResource("/Steak.png"));
            steakSprite = steakSprite.getScaledInstance(OBJECT_SIZE[0], OBJECT_SIZE[1], Image.SCALE_DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        } */
       //REFACTORED
       steakSprite = ImageLoader.loadImage("/Steak.png", WIDTH, HEIGHT);

    }
    

    /**
     * gets the alive status of the Steak, determining if it is active and collectible.
     * 
     * @param b
     */
    public void setAlive(Boolean b) {
        this.isAlive = b;
    }

    /**
     * Sets the alive status of the Steak, determining if it is active and collectible.
     * 
     * @param b True if the Steak is alive and collectible, false otherwise.
     */
    public boolean getAlive() {
        return this.isAlive;
    }

    /**
     * Updates the state of the Steak.
     * 
     * The Steak becomes collectible after its spawn time and disappears after its death time.
     */
    @Override
    public void tick() {
        if (this.lifetime < this.spawntime*60 || this.lifetime > this.deathtime*60) {
            this.collected = true; // Steak is not collectible outside of spawn-death window
            lifetime++;
        }
        else {
            if (isAlive) {
                lifetime++;
                this.collected =false; // Steak is collectible during its active period
            }
            else {
                this.collected = true; // Steak is no longer collectible if not alive
            }
        }
    }
    
    /**
     * Renders the Steak on the screen if it is currently collectible.
     * Displays the remaining time until the Steak disappears.
     * 
     * @param g The Graphics object used to draw the Steak.
     */
    @Override
    public void render(Graphics g) {
        if (!this.collected) {
            g.drawImage(steakSprite, x, y, null);
            g.setColor(Color.BLACK);
            g.drawString(""+((this.deathtime)-this.lifetime/60), x, y);
        }
    }

}