package Bots;

import java.util.ArrayList;

import GameLogic.Team;

public class Node {
    public static TripleT ttt = new TripleT(Team.g);
    double visits = 0;
    double netWins;
    double net_Wins_By_Visits;
    double ucb;
    ArrayList<Node> children = new ArrayList<Node>();
    Node parent = null;
    int[][] board;
    boolean xTurn;
    int parentVisits;

    public Node(int[][] gameBoard, boolean xTurn, Node parent){
        this.board = gameBoard;
        this.parent = parent;
        this.visits = 0;
        this.xTurn = xTurn;
    }

    /*  
     * Method for copying a 2D int array
    */
    public int[][] copy2DArray(int[][] array){
        int[][] newArray = new int[array.length][array.length];
        for (int i = 0; i < newArray.length; i++) {
            for (int j = 0; j < newArray.length; j++) {
                newArray[i][j] = array[i][j];
            }
        }
        return newArray;

    }
    /*  
     * Method to return the "game over value" of a board
     * Gold win = 1
     * Silver win = -1
     * Draw or in progress = 0
    */
    public int gameOverValue(int[][] board){ 
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != 0) {                  
                }
            }
        }
        if (ttt.goldWin(board)){
            return 1;
        }else if (ttt.silverWin(board)){
            return -1;
        }else {
            return 0;
        } 
    }

}
