package Bots;

import GameLogic.PlayingBoard;
import GameLogic.Square;
import GameLogic.Team;
import Players.Move;
import Players.Player;

public class MctsPlayer extends Player{
    
    private Node n = new Node(null, false, null);
    private TripleT ttt = new TripleT(Team.g);
    public int maxIterations = 3;
    public boolean goldTurn;

    public MctsPlayer(Team team) {
        super(team);
        if(team == Team.g){
            goldTurn = true;
        }else{
            goldTurn = false;
        }
    }

    public int[][] getMove(Board game) {
        MctsNode rootNode = new MctsNode(null, null, game);

        for (int iteration = 0; iteration < maxIterations; iteration++) {
            // Getting a copy of the board
            Board gameCopy = game.getCopy();

            // Node selection 
            MctsNode node = select(rootNode, gameCopy);

            // Node expansion
            node = node.expand(gameCopy);

            // Getting the delta value from rollout
            Reward reward = rollout(gameCopy);

            // Backpropagating the rollut result
            node.backPropagate(reward);
        }

        // Returning the optimal child of the root node 
        MctsNode mostVisitedChild = rootNode.getMostVisitedNode();
        return mostVisitedChild.getMoveUsedToGetToNode();
    }

    /*  
     * Method for selection process of MCTS
    */
    private MctsNode select(MctsNode node, Board game) {
        while (!node.canExpand() && !ttt.gameOver(game.getBoard())) {
            node = node.select();
            int[][] move = node.getMoveUsedToGetToNode(); 
            if (move != null) {
                game.setBoard(move);
                game.setXTurn(!game.getxTurn());        
            }
        }

        return node;
    }

    /*  
     * Method for simulation process of MCTS
    */
    public Reward rollout(Board game){
       int counter = 0;
        while(true){

            if(ttt.gameOver(game.getBoard()) || counter > 20){
                return getReward(game.getBoard());
            }
            else{
                game.setBoard(ttt.randomBot(game.getBoard(), game.getxTurn())); 
                game.setXTurn(!game.getxTurn());
                counter++;
            }
        }
    }

    /*  
     * Method for getting result of simulation process 
    */
    public Reward getReward(int[][] board) {
        if (n.gameOverValue(board) == 1) {
            return new Reward(1, -1);
        } else if (n.gameOverValue(board) == -1) {  
            return new Reward(-1, 1);
        }else if (n.gameOverValue(board) == 0){
            if(ttt.evaluation(board) > 4){
                return new Reward(1, -1);
            }else{
                return new Reward(-1, 1);
            }
        }
        return new Reward(0, 0);
    }

    @Override
    public Move getHMove() {
        return null;
    }

    @Override
    public PlayingBoard getAMove(PlayingBoard board) {
        Square[][] arr = board.getBoard();
        int[][] game = TripleT.squareToArray(arr);
        Board gameBoard = new Board(game, goldTurn);

        MctsNode rootNode = new MctsNode(null, null, gameBoard);

        for (int iteration = 0; iteration < maxIterations; iteration++) {
            // Getting a copy of the board
            Board gameCopy = gameBoard.getCopy();

            // Node selection 
            MctsNode node = select(rootNode, gameCopy);

            // Node expansion
            node = node.expand(gameCopy);

            // Getting the delta value from rollout
            Reward reward = rollout(gameCopy);

            // Backpropagating the rollut result
            node.backPropagate(reward);
        }
        MctsNode mostVisitedChild = rootNode.getMostVisitedNode();
        int[][] bestNode = mostVisitedChild.getMoveUsedToGetToNode();
        Square[][] bestSquare = TripleT.intToSquare(bestNode);
        PlayingBoard finalBoard = new PlayingBoard(bestSquare);

        return finalBoard;
        
    }
}
