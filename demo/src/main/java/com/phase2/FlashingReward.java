package com.phase2;

/**
 * The Reward class represents a type of object that applies a specific reward to the player.
 * It is an abstract class requiring subclasses to define the specific reward behavior.
 */
public abstract class FlashingReward extends GameObject{
    protected int rewardAmount;
    protected FlashingRewardType type;

    /**
     * Constructor for a Reward.
     * 
     * @param x the x-coordinate of the reward
     * @param y the y-coordinate of the reward
     * @param type the type of reward (RewardType)
     * @param rewardPoints the reward awarded to the player
     */
    public FlashingReward(int x, int y,FlashingRewardType type,int rewardAmount) {
        super(x, y, ID.REWARD);
        this.type = type;
        this.rewardAmount = rewardAmount;
    }

    /**
     * Gets the type of the reward.
     * 
     * @return the RewardType
     */
    public FlashingRewardType getType() {
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
     * method to apply the reward to the player character (Doug).
     * 
     * @param doug the player character
     */
    public void applyReward(Doug doug) {
        doug.addScore(rewardAmount);
    }
}

