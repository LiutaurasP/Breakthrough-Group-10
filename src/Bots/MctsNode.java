package Bots;

import java.util.ArrayList;
import java.util.Random;

import GameLogic.Square;
import GameLogic.Team;

public class MctsNode {
    private TripleT ttt = new TripleT(Team.g);

    private final MctsNode parent;
    private int numSimulations = 0;
    private Reward reward;
    private final ArrayList<MctsNode> children = new ArrayList<>();
    private final ArrayList<int[][]> unexploredMoves;
    private final int player;
    private final int[][] moveUsedToGetToNode;

    public MctsNode(MctsNode parent, int[][] move, Board game) {
        this.parent = parent;
        moveUsedToGetToNode = move;
        Square[][] arr = TripleT.intToSquare(game.getBoard());
        Team team = Team.g;
        if(!game.getxTurn()){
            team = Team.s;
        }
        unexploredMoves = ttt.getAllPossibleBoards(arr, team); 
        reward = new Reward(0, 0);
        if(game.getxTurn()){
            player = 0;                      
        }else{
            player = 1;
        }
        
    }
    /*  
     * Method for selecting highest UCT value child of a Node
    */
    public MctsNode select() {
        MctsNode selectedNode = this;
        double max = Integer.MIN_VALUE;

        for (MctsNode child : getChildren()) {
            double uctValue = getUctValue(child);

            if (uctValue > max) {
                max = uctValue;
                selectedNode = child;
            }
        }

        return selectedNode;
    }

    /*  
     * Method for calculating UCT value of a Node
    */
    private double getUctValue(MctsNode child) {
        double uctValue;

        if (child.getNumberOfSimulations() == 0) {
            uctValue = 1;
        } else {
            uctValue
                = (1.0 * child.getRewardForPlayer(getPlayer())) / (child.getNumberOfSimulations() * 1.0) + (Math.sqrt(2 * (Math.log(getNumberOfSimulations() * 1.0) / child.getNumberOfSimulations())));
        }

        Random r = new Random();
        uctValue += (r.nextDouble() / 10000000);
        return uctValue;
    }

    /*  
     * Method for expanding a Node
    */
    public MctsNode expand(Board game) {
        if (!canExpand()) {
            return this;
        }
        Random random = new Random();
        int moveIndex = random.nextInt(unexploredMoves.size());

        int[][] move = unexploredMoves.remove(moveIndex);
        game.setBoard(move);
        game.setXTurn(!game.getxTurn());
        MctsNode child = new MctsNode(this, move, game);
        children.add(child);
        return child;
    }

    /*  
     * Method for backpropagation of simulation result
    */
    public void backPropagate(Reward reward) {
        this.reward.addReward(reward);
        this.numSimulations++;
        if (parent != null) {
            parent.backPropagate(reward);
        }
    }

    public ArrayList<MctsNode> getChildren() {
        return children;
    }

    public int getNumberOfSimulations() {
        return numSimulations;
    }

    public int getPlayer() {
        return player;
    }

    public double getRewardForPlayer(int player) {
        return reward.getRewardForPlayer(player);
    }

    public boolean canExpand() {
        return unexploredMoves.size() > 0;
    }

    /*  
     * Method for selecting the most visited child of a Node
    */
    public MctsNode getMostVisitedNode() {
        int mostVisitCount = 0;
        MctsNode bestChild = null;

        for (MctsNode child : getChildren()) {
            if (child.getNumberOfSimulations() > mostVisitCount) {
                bestChild = child;
                mostVisitCount = child.getNumberOfSimulations();
            }
        }

        return bestChild;
    }

    public int[][] getMoveUsedToGetToNode() {
        return moveUsedToGetToNode;
    }

}
