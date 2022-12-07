package Bots;

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
    PlayingBoard board;

    public Random randMove, randPiece; // random variables to
    public int upperMove, upperPiece, intMove;
    private ArrayList<AbstractPiece> availablePieces;
    int oldX, oldY, newX, newY;

    /**
     * Typical player constructor.
     *
     * @param team Which team does the player play for.
     */
    public RandomBot(Team team) {
        super(team);
        randMove = new Random();
        randPiece = new Random();
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
    public PlayingBoard getAMove(PlayingBoard boardP) {
        Square[][] arr = board.getBoard();
        updatePieces();

        // generate a random piece out of the available pieces to move
        AbstractPiece currentPiece = availablePieces.get(randPiece.nextInt(availablePieces.size()));
        oldX = currentPiece.x;
        oldY = currentPiece.y;

        // get all possible moves and choose a random one to make
        ArrayList<Square> possibleMoves = getAllPossibleMoves(currentPiece, false);
        upperMove = possibleMoves.size();
        intMove = randMove.nextInt(upperMove);

        newX = possibleMoves.get(intMove).getX();
        newY = possibleMoves.get(intMove).getY();

        // update the board with the new positions of the moved piece
        arr = updateBoard(arr, oldX, oldY, newX, newY);

        if(!(currentPiece instanceof Flag)) {
            if((newX == oldX + 1 && (newY == oldY + 1 || newY == oldY - 1)) ||
                    (newX == oldX - 1 && (newY == oldY + 1 || newY == oldY - 1))) {
                return new PlayingBoard(arr);
            }

            // get new random piece out of the available pieces
            currentPiece = availablePieces.get(randPiece.nextInt(availablePieces.size()));
            // get new random piece until you don't get the flag
            while(currentPiece instanceof Flag) {
                currentPiece = availablePieces.get(randPiece.nextInt(availablePieces.size()));
            }
            oldX = currentPiece.x;
            oldY = currentPiece.y;

            // get all possible moves and choose a random one to make
            possibleMoves = getAllPossibleMoves(currentPiece, true);
            upperMove = possibleMoves.size();
            intMove = randMove.nextInt(upperMove);

            newX = possibleMoves.get(intMove).getX();
            newY = possibleMoves.get(intMove).getY();

            // update the board with the new positions of the moved piece
            arr = updateBoard(arr, oldX, oldY, newX, newY);
        }

        // return a new PlayingBoard with the moves made
        return new PlayingBoard(arr);
    }

    @Override
    public Move getHMove(){
        return null;
    }

    /**
     * Method that updates the array of available pieces we can move.
     */
    private void updatePieces() {
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (board.getSquare(i, j).getCurrentPiece() != null && this.getTeam().equals(board.getSquare(i, j).getCurrentPiece().getColor())) {
                    availablePieces.add(board.getSquare(i, j).getCurrentPiece());
                }
            }
        }
    }

    private Square[][] updateBoard(Square[][] arr, int oldX, int oldY, int newX, int newY) {
        if (arr[oldY][oldX].getCurrentPiece() != null) {
            AbstractPiece piece = arr[oldY][oldX].getCurrentPiece();

            arr[oldY][oldX].setCurrentPiece(null);
            arr[newY][newX].setCurrentPiece(piece);
            piece.setX(newX);
            piece.setY(newY);
        }

        return arr;
    }

    /**
     * A function that gets all legal moves for a specified piece.
     * @param piece A piece for which it gets all the possible moves.
     * @param isSecondMove - A logical switch to prevent player from performing illegal moves.
     *                     - 'True' when you're making the second move,
     *                     - 'false' when you're moving the flag or when you made no move yet.
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
                theBoard[i][j] = board.getSquare(i, j);
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
