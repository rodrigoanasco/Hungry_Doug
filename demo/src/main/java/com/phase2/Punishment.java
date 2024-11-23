package com.phase2;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 * The Punishment class represents a type of enemy that applies a specific punishment to the player.
 * It is an abstract class requiring subclasses to define the specific punishment behavior.
 */
public abstract class Punishment extends GameObject{
    protected int penaltyPoints;
    protected PunishmentType type;
    protected Image image;
    protected boolean collected = false; // Track if punishment has been collected


    /**
     * Constructor for a Punishment.
     * 
     * @param x the x-coordinate of the punishment
     * @param y the y-coordinate of the punishment
     * @param type the type of punishment (PunishmentType)
     * @param penaltyPoints the penalty points applied to the player
     */
    public Punishment(int x, int y, PunishmentType type, int penaltyPoints) {
        super(x, y, ID.ENEMY);
        this.type = type;
        this.penaltyPoints = penaltyPoints;
    }

    // /**
    //  * Gets the type of the punishment.
    //  * 
    //  * @return the PunishmentType
    //  */
    // public PunishmentType getType() {
    //     return type;
    // }

    /**
     * Determines if the punishments has already been collected.
     * 
     * @return true if the punishments has been collected, false otherwise.
     */
    public boolean isCollected() {
        return collected;
    }

    /**
     * Sets the collected status of the punishments.
     * 
     * @param collected True if the punishments has been collected, false otherwise.
     */
    public void setCollected(boolean collected) {
        this.collected = collected;
    }

    /**
     * Gets the penalty points applied by the punishment.
     * 
     * @return the number of penalty points
     */
    public int getPenaltyPoints() {
        return penaltyPoints;
    }

    /**
     * Gets the bounding rectangle of the reward for collision detection.
     */
    public abstract Rectangle getBounds();

    /**
     * Renders the visual representation of the object on the screen.
     * 
     * @param g the {@link Graphics} object used to draw the sprite
     */
    public abstract void render(Graphics g);


}

