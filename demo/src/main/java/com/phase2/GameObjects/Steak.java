package com.phase2.GameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import com.phase2.ImageLoader;
import com.phase2.Reward;
import com.phase2.Rewards.RewardType;

/**
 * The {@code Steak} class represents a timed reward in the game.
 * <p>
 * The Steak appears for a limited time and can be collected by the player for points. 
 * It has a spawn time and a death time, determining its active period on the screen.
 * </p>
 * 
 * @see Reward
 */
public class Steak extends Reward {

    /**
     * The sprite image representing the steak.
     */
    private Image steakSprite;

    /**
     * The lifetime of the Steak in game ticks.
     */
    private int lifetime;

    /**
     * The spawn time in seconds before the Steak becomes collectible.
     */
    private int spawntime;

    /**
     * The death time in seconds after which the Steak disappears.
     */
    private int deathtime;

    /**
     * Indicates whether the Steak is active and collectible.
     */
    private Boolean isAlive = true;

    /**
     * A random number generator used to determine spawn and death times.
     */
    private Random r;

    /**
     * Constructs a {@code Steak} object at the specified position.
     * <p>
     * The Steak is initialized with its type, reward value, sprite image, and random spawn and death times.
     * </p>
     * 
     * @param x the x-coordinate where the Steak will appear
     * @param y the y-coordinate where the Steak will appear
     */
    public Steak(int x, int y) {
        super(x, y, RewardType.STEAK, 20);
        r = new Random();
        this.WIDTH = 32;
        this.HEIGHT = 32;
        this.lifetime = 0;
        this.spawntime = r.nextInt(5) + 1; // Random spawn time between 1-5 seconds
        this.deathtime = r.nextInt(25 + spawntime) + 10 + spawntime; // Random death time based on spawn time
        steakSprite = ImageLoader.loadImage("/Steak.png", WIDTH, HEIGHT);
    }

    /**
     * Sets the alive status of the Steak, determining if it is active and collectible.
     * 
     * @param b {@code true} if the Steak is alive and collectible, {@code false} otherwise
     */
    public void setAlive(Boolean b) {
        this.isAlive = b;
    }

    /**
     * Gets the alive status of the Steak, determining if it is active and collectible.
     * 
     * @return {@code true} if the Steak is alive and collectible, {@code false} otherwise
     */
    public boolean getAlive() {
        return this.isAlive;
    }

    /**
     * Updates the state of the Steak on each tick of the game loop.
     * <p>
     * The Steak becomes collectible after its spawn time and disappears after its death time.
     * If the Steak is not alive, it becomes uncollectible immediately.
     * </p>
     */
    @Override
    public void tick() {
        if (this.lifetime < this.spawntime * 60 || this.lifetime > this.deathtime * 60) {
            this.collected = true; // Steak is not collectible outside of spawn-death window
            lifetime++;
        } else {
            if (isAlive) {
                lifetime++;
                this.collected = false; // Steak is collectible during its active period
            } else {
                this.collected = true; // Steak is no longer collectible if not alive
            }
        }
    }

    /**
     * Renders the visual representation of the Steak on the screen.
     * <p>
     * If the Steak is collectible, it displays the remaining time until it disappears.
     * </p>
     * 
     * @param g the {@link Graphics} object used to draw the sprite and time
     */
    @Override
    public void render(Graphics g) {
        if (!this.collected) {
            g.drawImage(steakSprite, x, y, null);
            g.setColor(Color.BLACK);
            g.drawString("" + (this.deathtime - this.lifetime / 60), x, y); // Display remaining time
        }
    }
}
