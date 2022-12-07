package Bots;

import GUI.BoardUI;
import GUI.UI;
import GameLogic.*;
import Pieces.*;
import Players.*;

import java.util.ArrayList;
import java.util.Random;

/** questions:
 * 1. should the random bot be able to play against human players or just against itself? - yes
 * 2. play against intelligent AI? - no
 * 3. should we also update the position of the moved piece?
 * 4.
 * 5.
 **/
public class RandomBot extends Player {
    private final Object lock;
    UI ui;
    BoardUI board;

    public Random randMove, randPiece; // random variables to
    public int upperMove, upperPiece, intMove;
    private ArrayList<AbstractPiece> availablePieces;
    int oldX, oldY, newX, newY;

    /**
     * Typical player constructor.
     *
     * @param team Which team does the player play for.
     */
    public RandomBot(Team team, Object lock, UI ui) {
        super(team);
        this.ui= ui;
        this.lock = lock;
        board = ui.getBoard();
        randMove = new Random();
        randPiece = new Random();
//        randPlayer = new Random();
//        intTile = randTile.nextInt(upperTile);
//        intPlayer = randPlayer.nextInt(upperPlayer);
        if(this.getTeam().equals(Team.s)) {
            availablePieces = new ArrayList<>(20);
            upperPiece = 20;
        } else {
            availablePieces = new ArrayList<>(13);
            upperPiece = 13;
        }
    }

    /**
     * Check whether it is a normal player(silver or gold) or the flag
     * IF
     * flag - gold team has 1 move
     */
        /**
         * 1. get size of arrayList of possible moves
         *    getAllPossibleMoves(currentPiece, true)
         *    piece.toString().equals("f")
         * 2. assign upperMove = size
         * 3. assign intMove = randMove.nextInt(upperMove)
         * 4. make the intMove^th move
         */
    /**
     * ELSE
     * other pieces - corresponding team has either 1 or 2 moves
     */
        /**
         * 1. get size of arrayList of possible moves
         * 2. assign upperMove = size
         * 3. assign intMove = randTile.nextInt(upperMove)
         * 4. make the intMove^th move
         *      IF (capture move)
         *          team has only 1 move in total -> 0 moves left
         *          getAllPossibleMoves(currentPiece, false)
         *      ELSE
         *          team has 2 moves in total -> 1 move left
         *          select a random piece again(from the same team - handled in updatePieces())
         *          getAllPossibleMoves(currentPiece, true)
         */

    /**
     * Method for moving a piece randomly.
     * @return a Move object contiaining the old and new coordinates of a piece.
     */
    @Override
    public Move getMove() {
        updatePieces();

        // generate a random piece out of the available pieces to move
        AbstractPiece currentPiece = availablePieces.get(randPiece.nextInt(availablePieces.size()));
        oldX = currentPiece.x;
        oldY = currentPiece.y;

        ArrayList<Square> possibleMoves = getAllPossibleMoves(currentPiece, true);
        upperMove = possibleMoves.size(); System.out.println("possible moves: " + possibleMoves.size());
        intMove = randMove.nextInt(upperMove);

        newX = possibleMoves.get(intMove).getX();
        newY = possibleMoves.get(intMove).getY();
        System.out.println("x: " + newX + " y: " + newY);
//        currentPiece.setX(newX);
//        currentPiece.setY(newY); // should we do this?

        return new Move(oldX, oldY, newX, newY);
    }

    /**
     * Method that updates the array of available pieces we can move.
     */
    private void updatePieces() {
        board = ui.getBoard();

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (board.getTile(i, j).getSquare().getCurrentPiece() != null && this.getTeam().equals(board.getTile(i, j).getSquare().getCurrentPiece().getColor())) {
                    availablePieces.add(board.getTile(i, j).getSquare().getCurrentPiece());
                }
            }
        }
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

        Square[][] theBoard = new Square[11][11];

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                theBoard[i][j] = board.getTile(i, j).getSquare();
            }
        }

        // Right
        for (int i = x; i < 10; i++) {
            if (theBoard[y][i + 1].toString().equals("-")) allPossibleMoves.add(theBoard[y][i + 1]);
            else break;
        }
        // Left
        for (int i = x; i > 0; i--) {
            if (theBoard[y][i - 1].toString().equals("-")) allPossibleMoves.add(theBoard[y][i - 1]);
            else break;
        }
        // Down
        for (int i = y; i < 10; i++) {
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

}
