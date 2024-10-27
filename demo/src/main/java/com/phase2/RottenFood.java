package com.phase2;

public class RottenFood extends Punishment{
    public RottenFood(){
        super("Rotten_Food", 10);
    }

    @Override
    public void applyPenalty(Doug doug){
        doug.setHealth(doug.getHealth() - penaltyPoints);
    }
}
