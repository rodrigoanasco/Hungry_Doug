package com.phase2;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics;

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

    /**
     * Gets the type of the punishment.
     * 
     * @return the PunishmentType
     */
    public PunishmentType getType() {
        return type;
    }

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

}

