package com.phase2.Trackers;

/**
 * The {@code ID} enum defines the different types of game objects in the game.
 * <p>
 * Each ID represents a category of objects with specific behavior or characteristics.
 * These IDs are used to differentiate and manage various entities during gameplay.
 * </p>
 */
public enum ID {

    /**
     * Represents an enemy object in the game.
     */
    ENEMY,

    /**
     * Represents the main character, Doug.
     */
    DOUG,

    /**
     * Represents an obstacle that blocks movement in the game.
     */
    OBSTACLE,

    /**
     * Represents a reward collectible by the player.
     */
    REWARD,

    /**
     * Represents the exit point of the game or a level.
     */
    EXIT;
}
