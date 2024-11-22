package com.phase2;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * The GameObject class is an abstract class representing all objects in the game.
 * It defines common properties like position (x, y) and velocity (velX, velY) for game objects.
 * Subclasses must define how the object behaves and renders.
 */
public abstract class GameObject {

    protected int x, y; // sets initial spawn x, y coordinates
    protected ID id;
    protected int velX, velY; // controls speed in x, y direction
    protected int OBJECT_SIZE[] = {32,32}; //size of image

    /**
     * Constructor for creating a game object.
     * 
     * @param x the x-coordinate of the object
     * @param y the y-coordinate of the object
     * @param id the type of the object (ID)
     */
    public GameObject(int x, int y, ID id){
        this.x = x;
        this.y = y;
        this.id = id;
    }
    
    /**
     * Abstract method that defines the object's behavior on each tick of the game loop.
     */
    public abstract void tick();
    
    /**
     * Abstract method to render the game object.
     * 
     * @param g the Graphics object used for rendering
     */
    public abstract void render(Graphics g);

    /**
     * Abstract method to get the bounding rectangle of the object for collision detection.
     *
     * @return A {@link Rectangle} representing the bounds of the object.
     */
    public abstract Rectangle getBounds();

    /**
     * Sets the x-coordinate of the object.
     *
     * @param x The new x-coordinate of the object.
     */
    public void setX(int x){
        this.x = x;
    }

    /**
     * Sets the y-coordinate of the object.
     *
     * @param y The new y-coordinate of the object.
     */
    public void setY(int y){
        this.y = y;
    }

    /**
     * Gets the x-coordinate of the object.
     *
     * @return The current x-coordinate of the object.
     */
    public int getX(){
        return x;
    }

    /**
     * Gets the y-coordinate of the object.
     *
     * @return The current y-coordinate of the object.
     */
    public int getY(){
        return y;
    }

    /**
     * Sets the ID of the object.
     *
     * @param id The new {@link ID} of the object.
     */
    public void setID(ID id){
        this.id = id;
    }

    /**
     * Gets the ID of the object.
     *
     * @return The {@link ID} of the object.
     */
    public ID getId(){
        return id;
    }

    /**
     * Sets the horizontal velocity of the object.
     *
     * @param velX The new horizontal velocity of the object.
     */
    public void setVelX(int velX){
        this.velX = velX;
    }

    /**
     * Sets the vertical velocity of the object.
     *
     * @param velY The new vertical velocity of the object.
     */
    public void setVelY(int velY){
        this.velY = velY;
    }

    /**
     * Gets the horizontal velocity of the object.
     *
     * @return The current horizontal velocity of the object.
     */
    public int getVelX(){
        return velX;
    }

    /**
     * Gets the vertical velocity of the object.
     *
     * @return The current vertical velocity of the object.
     */
    public int getVelY(){
        return velY;
    }
}
