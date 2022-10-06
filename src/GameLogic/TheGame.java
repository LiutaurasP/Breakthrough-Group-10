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
        Boolean moved;
        Boolean flagMove;
        Boolean silverWon = false;

        gameloop:
        while(true) {
            for (Player player: players) {
                flagMove = false;
                moved = false;

                if(player.getTeam()==Team.g){
                    ui.getWhoseTurn().setText("Gold's turn!");}
                else {
                    ui.getWhoseTurn().setText("Silver's turn!");
                }

                Move move;
                // First move
                while (!moved){
                    flagMove=false;
                    silverWon=false;
                    move = player.getMove();
                    if(board.getBoard()[move.getOldY()][move.getOldX()].getCurrentPiece().toString().equals("f")) flagMove=true;
                    if(board.getBoard()[move.getNewY()][move.getNewX()].getCurrentPiece() != null &&
                            board.getBoard()[move.getNewY()][move.getNewX()].getCurrentPiece().toString().equals("f")){
                        silverWon=true;}
                    moved = movePiece(board.getBoard(), move.getOldX(), move.getOldY(), move.getNewX(), move.getNewY(),false);
                }
                if (silverWon){
                    System.out.println("Silver won");
                    break gameloop;
                }
                moved = false;
                // Second move
                if(!flagMove) {
                    while (!moved) {
                        move = player.getMove();
                        moved = movePiece(board.getBoard(), move.getOldX(), move.getOldY(), move.getNewX(), move.getNewY(), true);
                    }
                    moved = false;
                }
                if (isFlagAtBorder(board.getBoard())){
                    System.out.println("Gold won");
                    break gameloop;
                }
            }
        }
        System.out.println("Reached the end of the game loop!");
    }


    public ArrayList<Square> getAllPossibleMoves(AbstractPiece piece, boolean isSecondMove) {
        ArrayList<Square> allPossibleMoves = new ArrayList<>();
        if(isSecondMove && piece.toString().equals("f")){
            return allPossibleMoves;
        }
        if(piece==null){
            return null;
        }

        int x = piece.x;
        int y = piece.y;
        Team teamOfPiece = piece.color;

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
        if (!isSecondMove) {
            // South-West
            if (y + 1 < 11 && x - 1 > 0 && theBoard[y + 1][x - 1].getCurrentPiece() != null && theBoard[y + 1][x - 1].getCurrentPiece().color != teamOfPiece) {
                allPossibleMoves.add(theBoard[y + 1][x - 1]);
            }
            // South-East
            if (y + 1 < 11 && x + 1 < 11 && theBoard[y + 1][x + 1].getCurrentPiece() != null && theBoard[y + 1][x + 1].getCurrentPiece().color != teamOfPiece) {
                allPossibleMoves.add(theBoard[y + 1][x + 1]);
            }
            // North-West
            if (y - 1 > 0 && x - 1 > 0 && theBoard[y - 1][x - 1].getCurrentPiece() != null && theBoard[y - 1][x - 1].getCurrentPiece().color != teamOfPiece) {
                allPossibleMoves.add(theBoard[y - 1][x - 1]);
            }
            // North-East
            if (y - 1 > 0 && x + 1 < 11 && theBoard[y - 1][x + 1] != null && theBoard[y - 1][x + 1].getCurrentPiece() != null && theBoard[y - 1][x + 1].getCurrentPiece().color != teamOfPiece) {
                allPossibleMoves.add(theBoard[y - 1][x + 1]);

            }


        }
        return allPossibleMoves;
    }

    public boolean movePiece(Square[][] arr, int oldX, int oldY, int newX, int newY, boolean isSecondMove) {
        if (arr[oldY][oldX].getCurrentPiece() != null) {
            AbstractPiece piece = arr[oldY][oldX].getCurrentPiece();
            //System.out.println(piece.getColor().toString());

            ArrayList<Square> possibleMoves = getAllPossibleMoves(piece, isSecondMove);
            if (possibleMoves.isEmpty()) {
                System.out.println("This piece has no possible movement!");
                return false;
            }
            for (Square possibleMove : possibleMoves) {
                if (possibleMove.getX() == newX && possibleMove.getY() == newY) {
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

    public boolean isFlagAtBorder(Square[][] arr) {
        int sizeBoard = board.SIZE_OF_BOARD;
        for (int i = 0; i < sizeBoard; i++) {
            // flag piece touches:
            // bottom outer border
            if(arr[sizeBoard-1][i].getCurrentPiece()!=null && arr[sizeBoard-1][i].getCurrentPiece().toString().equals("f"))
                return true;

            // right outer border
            if(arr[i][sizeBoard-1].getCurrentPiece()!=null && arr[i][sizeBoard-1].getCurrentPiece().toString().equals("f"))
                return true;

            // top outer border
            if(arr[0][i].getCurrentPiece()!=null && arr[0][i].getCurrentPiece().toString().equals("f"))
                return true;

            // left outer border
            if(arr[i][0].getCurrentPiece()!=null && arr[i][0].getCurrentPiece().toString().equals("f"))
                return true;
        }
        return false;
    }
}
