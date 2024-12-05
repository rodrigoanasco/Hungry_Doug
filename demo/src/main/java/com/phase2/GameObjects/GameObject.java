package com.phase2.GameObjects;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.phase2.Trackers.ID;

/**
 * The {@code GameObject} class is an abstract base class representing all objects in the game.
 * <p>
 * It defines common properties such as position, velocity, and size for game objects.
 * Subclasses are required to implement specific behavior and rendering logic.
 * </p>
 */
public abstract class GameObject {

    /**
     * The x-coordinate of the object's position.
     */
    protected int x;

    /**
     * The y-coordinate of the object's position.
     */
    protected int y;

    /**
     * The identifier for the type of the object.
     */
    protected ID id;

    /**
     * The horizontal velocity of the object.
     */
    protected int velX;

    /**
     * The vertical velocity of the object.
     */
    protected int velY;

    /**
     * The width of the object (size of the image).
     */
    protected int WIDTH;

    /**
     * The height of the object (size of the image).
     */
    protected int HEIGHT;

    /**
     * Constructs a {@code GameObject} with the specified position and identifier.
     * 
     * @param x  the x-coordinate of the object's position
     * @param y  the y-coordinate of the object's position
     * @param id the type of the object, represented as an {@link ID}
     */
    public GameObject(int x, int y, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    /**
     * Updates the state of the object.
     * <p>
     * This method is called on each tick of the game loop. Subclasses may override this
     * method to define specific behaviors.
     * </p>
     */
    public void tick() {}

    /**
     * Renders the visual representation of the object on the screen.
     * 
     * @param g the {@link Graphics} object used to draw the object
     */
    public abstract void render(Graphics g);

    /**
     * Gets the bounding rectangle of the object for collision detection.
     * 
     * @return a {@link Rectangle} representing the bounds of the object
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    // Setters and Getters

    /**
     * Sets the x-coordinate of the object.
     * 
     * @param x the new x-coordinate
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets the x-coordinate of the object.
     * 
     * @return the current x-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the y-coordinate of the object.
     * 
     * @param y the new y-coordinate
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Gets the y-coordinate of the object.
     * 
     * @return the current y-coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the ID of the object.
     * 
     * @param id the new {@link ID} of the object
     */
    public void setID(ID id) {
        this.id = id;
    }

    /**
     * Gets the ID of the object.
     * 
     * @return the {@link ID} of the object
     */
    public ID getId() {
        return id;
    }

    /**
     * Sets the horizontal velocity of the object.
     * 
     * @param velX the new horizontal velocity
     */
    public void setVelX(int velX) {
        this.velX = velX;
    }

    /**
     * Gets the horizontal velocity of the object.
     * 
     * @return the current horizontal velocity
     */
    public int getVelX() {
        return velX;
    }

    /**
     * Sets the vertical velocity of the object.
     * 
     * @param velY the new vertical velocity
     */
    public void setVelY(int velY) {
        this.velY = velY;
    }

    /**
     * Gets the vertical velocity of the object.
     * 
     * @return the current vertical velocity
     */
    public int getVelY() {
        return velY;
    }
}
