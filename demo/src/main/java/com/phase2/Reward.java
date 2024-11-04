package com.phase2;

/**
 * The Reward class represents a type of object that applies a specific reward to the player.
 * It is an abstract class requiring subclasses to define the specific reward behavior.
 */
public abstract class Reward extends GameObject{
    protected int rewardAmount;
    protected RewardType type;

    /**
     * Constructor for a Reward.
     * 
     * @param x the x-coordinate of the reward
     * @param y the y-coordinate of the reward
     * @param type the type of reward (RewardType)
     * @param penaltyPoints the reward awarded to the player
     */
    public Reward(int x, int y, RewardType type, int rewardAmount) {
        super(x, y, ID.ENEMY);
        this.type = type;
        this.rewardAmount = rewardAmount;
    }

    /**
     * Gets the type of the reward.
     * 
     * @return the RewardType
     */
    public RewardType getType() {
        return type;
    }

    /**
     * Gets the reward awarded by the reward.
     * 
     * @return the number of rewardAmount
     */
    public int getRewardAmount() {
        return rewardAmount;
    }

    /**
     * Abstract method to apply the reward to the player character (Doug).
     * 
     * @param doug the player character
     */
    public abstract void applyReward(Doug doug);
}

