package Bots;

public class Board {
    
    private int[][] board;
    private boolean xTurn;

    public Board(int[][] game, boolean xTurn){
        this.board = game;
        this.xTurn = xTurn;
    }

    public boolean getxTurn() {
        return this.xTurn;
    }

    public int[][] getBoard(){
        return this.board;
    }

    public void setBoard(int[][] board){
        this.board = board;
        // this.xTurn = !this.xTurn;
    }

    public void setXTurn(boolean xTurn){
        this.xTurn = xTurn;
    }

    public Board getCopy(){
        Board copy = new Board(this.board, this.xTurn);
        return copy;
    }
}
