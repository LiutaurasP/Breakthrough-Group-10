package GameLogic;

import GUI.UI;
import Pieces.AbstractPiece;
import Players.Move;
import Players.Player;

import java.util.ArrayList;

public class TheGame {
    public ArrayList<Player> players = new ArrayList<>();
    PlayingBoard board;
 //   public Boolean makingMove= false;
    UI ui;

    public TheGame(PlayingBoard board, Player gold, Player silver, UI ui) {
        this.board = board;
        this.ui = ui;
        players.add(gold);
        players.add(silver);

    }

    public void play() {
        ui.getBoard().updateBoard(board);
        Boolean moved = false;

        while(true) {
            for (Player player: players) {
                if(player.getTeam()==Team.g){
                    ui.getWhoseTurn().setText("Gold's turn!");}
                else {
                    ui.getWhoseTurn().setText("Silver's turn!");
                }
                // First move
                while (!moved){
                    Move move = player.getMove();
                    moved = movePiece(board.getBoard(), move.getOldX(), move.getOldY(), move.getNewX(), move.getNewY());
                                        }
                moved = false;
                // Second move
                while (!moved){
                    Move move = player.getMove();
                    moved = movePiece(board.getBoard(), move.getOldX(), move.getOldY(), move.getNewX(), move.getNewY());
                }
                moved = false;

            }

        }
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
            if(theBoard[y][i + 1].toString().equals("-"))allPossibleMoves.add(theBoard[y][i+1]);
            else break;
        }
        // Left
        for (int i = x; i > 0; i--) {
            if(theBoard[y][i - 1].toString().equals("-"))allPossibleMoves.add(theBoard[y][i-1]);
            else break;
        }
        // Down
        for (int i = y; i < board.SIZE_OF_BOARD-1; i++) {
            if(theBoard[i + 1][x].toString().equals("-"))allPossibleMoves.add(theBoard[i+1][x]);
            else break;
        }
        // Up
        for (int i = y; i > 0; i--) {
            if(theBoard[i - 1][x].toString().equals("-"))allPossibleMoves.add(theBoard[i-1][x]);
            else break;
        }

        // Capture moves

            // South-West
            if(y+1<11 && x-1>0 && theBoard[y+1][x-1].getCurrentPiece()!=null && theBoard[y+1][x-1].getCurrentPiece().color!=teamOfPiece){
                allPossibleMoves.add(theBoard[y+1][x-1]);
            }
            // South-East
            if(y+1<11 && x+1<11 && theBoard[y+1][x+1].getCurrentPiece()!=null && theBoard[y+1][x+1].getCurrentPiece().color!=teamOfPiece){
                allPossibleMoves.add(theBoard[y+1][x+1]);
            }
            // North-West
            if(y-1>0 && x-1>0 && theBoard[y-1][x-1].getCurrentPiece()!=null && theBoard[y-1][x-1].getCurrentPiece().color!=teamOfPiece){
                allPossibleMoves.add(theBoard[y-1][x-1]);
            }
            // North-East
            if(y-1>0 && x+1<11 && theBoard[y-1][x+1]!=null && theBoard[y-1][x+1].getCurrentPiece()!=null && theBoard[y-1][x+1].getCurrentPiece().color!=teamOfPiece){
                allPossibleMoves.add(theBoard[y-1][x+1]);

            }




        return allPossibleMoves;
    }

    public boolean movePiece(Square[][] arr, int oldX, int oldY, int newX, int newY) {
        if (arr[oldY][oldX].getCurrentPiece() != null) {
            AbstractPiece piece = arr[oldY][oldX].getCurrentPiece();
            //System.out.println(piece.getColor().toString());
            ArrayList<Square> possibleMoves = getAllPossibleMoves(piece);
            if (possibleMoves.isEmpty()) {
                System.out.println("This piece has no valid moves, pick another one!");
                return false;
            }
            for (int i = 0; i < possibleMoves.size(); i++) {
                if (possibleMoves.get(i).getX() == newX && possibleMoves.get(i).getY() == newY) {
                    arr[oldY][oldX].setCurrentPiece(null);
                    arr[newY][newX].setCurrentPiece(piece);
                    ui.getBoard().updateBoard(board);
                    System.out.println(board);
                    return true;
                }
            }
            return false;
        }
        return false;
    }
}
