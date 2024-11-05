package com.phase2;

/**
 * The MovingEnemy class represents enemies that move and apply penalties to the player.
 * It is an abstract class that requires subclasses to define the specific penalty behavior.
 */
public abstract class MovingEnemy extends GameObject{
    
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

    public void tick() {
        x = Game.clamp(x,0,Game.WIDTH - 30);
        y = Game.clamp(y,0,Game.HEIGHT - 30);
    }

}