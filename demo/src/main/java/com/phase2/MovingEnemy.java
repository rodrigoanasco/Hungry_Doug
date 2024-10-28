package com.phase2;

// Punishment.java
public abstract class MovingEnemy extends GameObject{
    protected int penaltyPoints;
    protected EnemyType type;

    public MovingEnemy(int x, int y, EnemyType type, int penaltyPoints) {
        super(x, y, ID.ENEMY);
        this.type = type;
        this.penaltyPoints = penaltyPoints;
    }

    public EnemyType getType() {
        return type;
    }

    public int getPenaltyPoints() {
        return penaltyPoints;
    }

    // Abstract method to apply penalty
    public abstract void applyPenalty(Doug doug);
}