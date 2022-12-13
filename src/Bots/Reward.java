package Bots;

/*
source: https://www.baeldung.com/java-monte-carlo-tree-search
*/ 


import java.util.HashMap;

public class Reward {

    HashMap<Integer, Integer> rewards = new HashMap<>();

    public Reward(int reward1, int reward2){
        rewards.put(0, reward1);
        rewards.put(1, reward2);
    }

    public HashMap<Integer, Integer> getReward(){
        return rewards;
    }
    /*  
     * Method to get reward for a given player
    */
    public int getRewardForPlayer(int playerNumber){
        return rewards.get(playerNumber);
    }

    /*  
     * Setter method for reward object
    */
    public void addReward(Reward reward) {
        rewards.put(0, rewards.get(0) + reward.getRewardForPlayer(0));
        rewards.put(1, rewards.get(1) + reward.getRewardForPlayer(1));
    }
}
