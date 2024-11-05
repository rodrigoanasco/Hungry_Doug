package com.phase2;


/**
 * The Reward class represents a type of object that applies a specific reward to the player.
 * It is an abstract class requiring subclasses to define the specific reward behavior.
 */
public abstract class Reward extends GameObject{

    protected int rewardAmount;
    protected RewardType type;
    protected boolean collected = false; // Track if reward has been collected

    /**
     * Constructor for a Reward.
     * 
     * @param x the x-coordinate of the reward
     * @param y the y-coordinate of the reward
     * @param type the type of reward (RewardType)
     * @param rewardPoints the reward awarded to the player
     */
    public Reward(int x, int y, RewardType type,int rewardAmount) {
        super(x, y, ID.REWARD);
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
     * Determines if the reward has already been collected.
     * 
     * @return true if the reward has been collected, false otherwise.
     */
    public boolean isCollected() {
        return collected;
    }

    /**
     * Sets the collected status of the reward.
     * 
     * @param collected True if the reward has been collected, false otherwise.
     */
    public void setCollected(boolean collected) {
        this.collected = collected;
    }

    // public Rectangle getBounds() {
    //     return new Rectangle(x,y,30,30);

    // }

}

