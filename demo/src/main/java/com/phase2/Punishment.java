package com.phase2;

// Punishment.java
public abstract class Punishment {
    protected int penaltyPoints;
    protected PunishmentType type;

    public Punishment(PunishmentType type, int penaltyPoints) {
        this.type = type;
        this.penaltyPoints = penaltyPoints;
    }

    public PunishmentType getType() {
        return type;
    }

    public int getPenaltyPoints() {
        return penaltyPoints;
    }

    // Abstract method to apply penalty
    public abstract void applyPenalty(Doug doug);
}

