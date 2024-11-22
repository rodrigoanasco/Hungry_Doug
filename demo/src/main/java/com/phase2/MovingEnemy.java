package com.phase2;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * The MovingEnemy class represents enemies that move and apply penalties to the player.
 * It is an abstract class that requires subclasses to define the specific penalty behavior.
 */
public abstract class MovingEnemy extends GameObject{
    
    Handler handler;

    protected int penaltyPoints;
    protected EnemyType type;

    /**
     * Constructor for a MovingEnemy.
     * 
     * @param x the x-coordinate of the enemy
     * @param y the y-coordinate of the enemy
     * @param type the type of enemy (EnemyType)
     * @param penaltyPoints the penalty points applied to the player
     */
    public MovingEnemy(int x, int y, EnemyType type, int penaltyPoints) {
        super(x, y, ID.ENEMY);
        this.type = type;
        this.penaltyPoints = penaltyPoints;
    }

    /**
     * Gets the type of the enemy.
     * 
     * @return the EnemyType of the enemy
     */
    public EnemyType getType() {
        return type;
    }

    /**
     * Gets the penalty points applied by the enemy.
     * 
     * @return the number of penalty points
     */
    public int getPenaltyPoints() {
        return penaltyPoints;
    }

    /**
     * Defines behavior on each tick of the game loop
     */
    public void tick() {
        x = Game.clamp(x,0,Game.WIDTH - 30);
        y = Game.clamp(y,0,Game.HEIGHT - 30);
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