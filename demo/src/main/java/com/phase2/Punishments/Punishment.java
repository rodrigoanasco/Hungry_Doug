package com.phase2.Punishments;

import java.awt.Graphics;
import java.awt.Image;

import com.phase2.GameObjects.GameObject;
import com.phase2.Trackers.ID;

/**
 * The {@code Punishment} class represents a type of enemy or harmful object in the game
 * that applies a penalty to the player upon interaction.
 * <p>
 * This is an abstract class that serves as a base for all specific punishment types.
 * Subclasses must define the specific behavior and rendering logic for the punishment.
 * </p>
 * 
 * @see GameObject
 */
public abstract class Punishment extends GameObject {

    /**
     * The penalty points applied to the player when the punishment is collected.
     */
    protected int penaltyPoints;

    /**
     * The type of punishment represented by a {@link PunishmentType}.
     */
    protected PunishmentType type;

    /**
     * The image or sprite representing the punishment on the screen.
     */
    protected Image image;

    /**
     * Tracks whether the punishment has been collected by the player.
     */
    protected boolean collected = false;

    /**
     * Constructs a {@code Punishment} object with the specified position, type, and penalty points.
     * 
     * @param x            the x-coordinate of the punishment
     * @param y            the y-coordinate of the punishment
     * @param type         the type of punishment represented by {@link PunishmentType}
     * @param penaltyPoints the penalty points applied to the player
     */
    public Punishment(int x, int y, PunishmentType type, int penaltyPoints) {
        super(x, y, ID.ENEMY);
        this.type = type;
        this.penaltyPoints = penaltyPoints;
    }

    /**
     * Checks if the punishment has been collected by the player.
     * 
     * @return {@code true} if the punishment has been collected, {@code false} otherwise
     */
    public boolean isCollected() {
        return collected;
    }

    /**
     * Sets the collected status of the punishment.
     * 
     * @param collected {@code true} if the punishment has been collected, {@code false} otherwise
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
     * Renders the visual representation of the punishment on the screen.
     * Subclasses must implement this method to define their specific rendering logic.
     * 
     * @param g the {@link Graphics} object used to draw the sprite
     */
    public abstract void render(Graphics g);
}
