package GameLogic;

import GUI.UI;
import Pieces.AbstractPiece;
import Players.Move;
import Players.Player;
import java.awt.*;
import java.util.ArrayList;

/**
 * The main class containing game loop and helper functions for it.
 */
public class TheGame {
    public ArrayList<Player> players = new ArrayList<>();
    PlayingBoard board;
    UI ui;

    /**
     * Constructor for the main class of the entire game.
     * @param board Logical game board.
     * @param gold  Player1.
     * @param silver Player2.
     * @param ui User Interface.
     */
    public TheGame(PlayingBoard board, Player gold, Player silver, UI ui) {
        this.board = board;
        this.ui = ui;
        players.add(gold);
        players.add(silver);
    }

    /**
     * The main function of the game that contains the game loop.
     */
    public void play() {
        // Loads up the GUI for the first time.
        ui.getBoard().updateBoard(board);

        // Logical switches used in a game loop.
        Boolean moved = false;
        Boolean flagMove = false;
        Boolean silverWon = false;
        Boolean attackMove = false;

        gameloop:
        while (true) {
            for (Player player : players) {
                // Resetting logical switches that might have been altered with from previous iterations.
                flagMove = false;
                moved = false;
                attackMove = false;

                // Changing the label depending on whose turn it is.
                if (player.getTeam() == Team.g) {
                    ui.getWhoseTurn().setForeground(Color.YELLOW);
                    ui.getWhoseTurn().setText("Gold's turn!");
                } else {
                    ui.getWhoseTurn().setForeground(Color.WHITE);
                    ui.getWhoseTurn().setText("Silver's turn!");
                }

                Move move;
                // First move
                while (!moved) {
                    attackMove = false;
                    flagMove = false;
                    silverWon = false;

                    // Getting input from the player.
                    move = player.getMove();

                    // Checking whether the player tried moving the flag.
                    if (board.getBoard()[move.getOldY()][move.getOldX()].getCurrentPiece().toString().equals("f")) {
                        flagMove = true;
                    }

                    // Checking whether the player tried to perform flag capture.
                    if (board.getBoard()[move.getNewY()][move.getNewX()].getCurrentPiece() != null &&
                            board.getBoard()[move.getNewY()][move.getNewX()].getCurrentPiece().toString().equals("f")) {
                        silverWon = true;
                    }

                    // Checking whether the player tried to perform an attack move.
                    if (board.getBoard()[move.getNewY()][move.getNewX()].getCurrentPiece() != null && (board.getBoard()[move.getOldY()][move.getOldX()].getCurrentPiece().color != board.getBoard()[move.getNewY()][move.getNewX()].getCurrentPiece().color)) {
                        attackMove = true;
                    }

                    // This function tries to move the piece from the user inputs, if it fails to do so
                    // it means that the move wasn't legal, so it goes to the start of the loop again and
                    // waits for a legal first move.
                    moved = movePiece(board.getBoard(), move.getOldX(), move.getOldY(), move.getNewX(), move.getNewY(), false);
                }

                // From the previous loop we have information whether flag capture was performed, so if it
                // was, it means the silver player won.
                if (silverWon) {
                    System.out.println("Silver won");
                    break gameloop;
                }
                // Second move
                moved = false;
                // If player didn't move a flag or attack it means that they are eligible for a second move.
                if (!flagMove && !attackMove) {
                    while (!moved) {
                        move = player.getMove();
                        moved = movePiece(board.getBoard(), move.getOldX(), move.getOldY(), move.getNewX(), move.getNewY(), true);
                    }
                }
                if (isFlagAtBorder(board.getBoard())){
                    System.out.println("Gold won");
                    break gameloop;
                }
            }
        }
        if (silverWon) {
            ui.getWhoseTurn().setForeground(Color.WHITE);
            ui.getWhoseTurn().setText("Silver Won!");
            ui.getAgain().setVisible(true);
        }
        else {
            ui.getWhoseTurn().setForeground(Color.YELLOW);
            ui.getWhoseTurn().setText("Gold Won!");
        }
        System.out.println("Reached the end of the game loop!");
    }

    /**
     * A function that gets all legal moves for a specified piece.
     * @param piece A piece for which it gets all the possible moves.
     * @param isSecondMove A logical switch to prevent player from performing illegal moves.
     * @return Returns a list of all legal moves for the piece.
     */
    public ArrayList<Square> getAllPossibleMoves(AbstractPiece piece, boolean isSecondMove) {
        ArrayList<Square> allPossibleMoves = new ArrayList<>();
        if (isSecondMove && piece.toString().equals("f")) {
            return allPossibleMoves;
        }
        if (piece == null) {
            return null;
        }

        int x = piece.x;
        int y = piece.y;
        Team teamOfPiece = piece.color;

        Square[][] theBoard = board.getBoard();


        // Right
        for (int i = x; i < board.SIZE_OF_BOARD - 1; i++) {
            if (theBoard[y][i + 1].toString().equals("-")) allPossibleMoves.add(theBoard[y][i + 1]);
            else break;
        }
        // Left
        for (int i = x; i > 0; i--) {
            if (theBoard[y][i - 1].toString().equals("-")) allPossibleMoves.add(theBoard[y][i - 1]);
            else break;
        }
        // Down
        for (int i = y; i < board.SIZE_OF_BOARD - 1; i++) {
            if (theBoard[i + 1][x].toString().equals("-")) allPossibleMoves.add(theBoard[i + 1][x]);
            else break;
        }
        // Up
        for (int i = y; i > 0; i--) {
            if (theBoard[i - 1][x].toString().equals("-")) allPossibleMoves.add(theBoard[i - 1][x]);
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

    /**
     * A function that tries to perform a move according to the player's input.
     * @param arr The current logical playing board.
     * @param oldX First click.
     * @param oldY First click.
     * @param newX Second click.
     * @param newY Second click.
     * @param isSecondMove A logical switch to prevent player from performing illegal moves.
     * @return Returns a boolean whether the move was made or not.
     */
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
