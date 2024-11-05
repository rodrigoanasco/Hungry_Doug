package com.phase2;

import java.awt.Color;
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
    protected int HITBOX[] = {OBJECT_SIZE[0]-16,OBJECT_SIZE[1]-16};


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
     * render hitbox for testing
     * @param g the Graphics object used for rendering
     */
    public void renderHitBox(Graphics g) {
        Rectangle hitbox = this.getBounds();
        int width = (int)hitbox.getWidth();
        int height = (int)hitbox.getHeight();
        g.setColor(Color.GREEN);
        g.drawRect(x, y, width, height);
    }

    //TODO docs
    // collision detector
    public abstract Rectangle getBounds();

    // getters and setters for position and velocity
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void setID(ID id){
        this.id = id;
    }
    public ID getId(){
        return id;
    }
    public void setVelX(int velX){
        this.velX = velX;
    }
    public void setVelY(int velY){
        this.velY = velY;
    }
    public int getVelX(){
        return velX;
    }
    public int getVelY(){
        return velY;
    }
}
