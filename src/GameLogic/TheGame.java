package GameLogic;

import GUI.UI;
import Pieces.AbstractPiece;
import Players.Player;

import java.util.ArrayList;

public class TheGame {

    ArrayList<Player> players = new ArrayList<>();
    PlayingBoard board;
    UI ui;


    public TheGame(PlayingBoard board, Player gold, Player silver, UI ui) {
        this.board = board;
        this.ui = ui;
        players.add(gold);
        players.add(silver);

    }

    public void play() {

    }
    //TODO: ALL POSSIBLE MOVES (PIECES MOVE LIKE ROOKS IN CHESS)
    public ArrayList<Square> getAllPossibleMoves(AbstractPiece piece) {

        if(piece==null){
            return null;
        }

        int x = piece.x;
        int y = piece.y;
        Team teamOfPiece = piece.color;

        ArrayList<Square> allPossibleMoves = new ArrayList<>();
        Square[][] theBoard = board.getBoard();


        // Right
        for (int i = x; i < board.SIZE_OF_BOARD-1 ; i++) {
            if(theBoard[y][i+1].toString()=="-")allPossibleMoves.add(theBoard[y][i+1]);
            else break;
        }
        // Left
        for (int i = x; i > 0; i--) {
            if(theBoard[y][i-1].toString()=="-")allPossibleMoves.add(theBoard[y][i-1]);
            else break;
        }
        // Down
        for (int i = y; i < board.SIZE_OF_BOARD-1; i++) {
            if(theBoard[i+1][x].toString()=="-")allPossibleMoves.add(theBoard[i+1][x]);
            else break;
        }
        // Up
        for (int i = y; i > 0; i--) {
            if(theBoard[i-1][x].toString()=="-")allPossibleMoves.add(theBoard[i-1][x]);
            else break;
        }

        // Capture moves

            if(theBoard[y+1][x-1].getCurrentPiece()!=null && theBoard[y+1][x-1].getCurrentPiece().color!=teamOfPiece){
                allPossibleMoves.add(theBoard[y+1][x-1]);
            }
            if(theBoard[y+1][x+1].getCurrentPiece()!=null && theBoard[y+1][x+1].getCurrentPiece().color!=teamOfPiece){
                allPossibleMoves.add(theBoard[y+1][x+1]);System.out.println("hit");
            }
            if(theBoard[y-1][x-1].getCurrentPiece()!=null && theBoard[y-1][x-1].getCurrentPiece().color!=teamOfPiece){
                allPossibleMoves.add(theBoard[y-1][x-1]);

            }
            if(theBoard[y-1][x+1].getCurrentPiece()!=null && theBoard[y-1][x+1].getCurrentPiece().color!=teamOfPiece){
                allPossibleMoves.add(theBoard[y-1][x+1]);

            }




        return allPossibleMoves;
    }
}
