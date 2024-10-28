package com.phase2;

import java.awt.Graphics;

public abstract class GameObject {
    protected int x, y; // sets initial spawn x, y coordinates
    protected ID id;
    protected int velX, velY; // controls speed in x, y direction

    public GameObject(int x, int y, ID id){
        this.x = x;
        this.y = y;
        this.id = id; 
    }

    // subclasses will determine what object does each 'tick' of the game (automated movement, etc)
    public abstract void tick();
    public abstract void render(Graphics g);

    // getters and setters
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
