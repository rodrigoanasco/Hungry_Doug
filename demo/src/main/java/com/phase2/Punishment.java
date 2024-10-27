package com.phase2;

// Punishment.java
public abstract class Punishment {
    protected int penaltyPoints;
    protected String type;

    public Punishment(String type, int penaltyPoints) {
        this.type = type;
        this.penaltyPoints = penaltyPoints;
    }

    public String getType() {
        return type;
    }

    public int getPenaltyPoints() {
        return penaltyPoints;
    }

    // Abstract method to apply penalty
    public abstract void applyPenalty(Doug doug);
}

