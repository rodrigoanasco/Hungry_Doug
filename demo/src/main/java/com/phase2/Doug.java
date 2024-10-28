package com.phase2;

import java.awt.Color;
import java.awt.Graphics;


/**
 * Represents the main character, Doug, in the game.
 * Doug has health, a score, and can be rendered and updated 
 * (ticked) within the game.
 */
public class Doug extends GameObject{

    protected int health;
    protected int score;

    /**
     * Initializes Doug's position, ID, health, and score.
     * Sets default velocity for testing purposes.
     *
     * @param x  The initial x-coordinate of Doug's position.
     * @param y  The initial y-coordinate of Doug's position.
     * @param id The ID that identifies this GameObject as Doug.
     */
    public Doug(int x, int y, ID id){
        super(x,y,id);

        this.health = 100;
        this.score = 0;
        // used for testing only
        velX = 1;
        velY = 1;
        //
    }

    /**
     * Updates Doug's state for each tick of the game loop.
     */
    public void tick(){
        // used for testing only
        // moves +1 in x and y direction each tick of the game
        // x += velX;
        // y += velY;
        //
    }

    /**
     * Renders Doug on the screen as a green rectangle at his current position.
     * This is primarily used for testing.
     *
     * @param g The Graphics object used to render Doug.
     */
    public void render(Graphics g){

        // used for testing only
        g.setColor(Color.GREEN);
        g.fillRect(x, y, 32, 32);
        //
    }

    /**
     * Gets Doug's current score.
     *
     * @return The score Doug has accumulated.
     */
    public int getScore(){
        return score;
    }

    /**
     * Sets Doug's score.
     *
     * @param score The score value to set for Doug.
     */
    public void setScore(int score){
        this.score = score;
    }

     /**
     * Gets Doug's current health level.
     *
     * @return The health level of Doug.
     */
    public int getHealth(){
        return health;
    }

    /**
     * Sets Doug's health level.
     *
     * @param health The health value to set for Doug.
     */
    public void setHealth(int health){
        this.health = health;
    }
}
