package GameLogic;
import Pieces.Flag;
import Pieces.RegularPiece;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class that contains logical board made out of logical squares (tiles).
 */
public class PlayingBoard {
    public final int SIZE_OF_BOARD = 11;
    private Square[][] board;
    String whichBoard = "src/DifferentKindOfBoards/square.txt";

    /**
     * Constructor for the logical board.
     */
    public PlayingBoard() {
        this.board = createBoard();
    }

    /**
     * Logical board getter.
     * @return Returns logical board.
     */
    public Square[][] getBoard() {
        return board;
    }

    /**
     * A function that returns square object of logical board in specified coordinates.
     * @param x X coordinate.
     * @param y Y coordinate.
     * @return Returns the square object in specified coordinates.
     */
    public Square getSquare(int x, int y){ return board[y][x];}

    /**
     * A function that builds a readable string for the current state of the logical board.
     * @return A nicely formatted string containing the state of the board.
     */
    @Override
    public String toString() {
        String stringBoard = "y/x 0 1 2 3 4 5 6 7 8 9 10 <br/>";
        for (int y = 0; y < SIZE_OF_BOARD; y++) {
            if (y != 10) stringBoard += y + "   ";
            else stringBoard += y + "  ";
            for (int x = 0; x < SIZE_OF_BOARD; x++) {
                stringBoard += board[y][x].toString() + " ";
            }
            stringBoard += "<br/>";
        }
        return stringBoard;
    }

    /**
     * A function that builds the logical board.
     * @return Redirects return to another helper function.
     */
    private Square[][] createBoard() {
        Square[][] tempBoard = new Square[SIZE_OF_BOARD][SIZE_OF_BOARD];
        for (int y = 0; y < SIZE_OF_BOARD; y++) {
            for (int x = 0; x < SIZE_OF_BOARD; x++) {
                tempBoard[y][x] = new Square(x,y);
            }
        }

        return readFile(tempBoard);
    }

    /**
     * A helper function for createBoard(). It puts pieces according to the specified .txt layout.
     * @param tempBoard Logical board.
     * @return Returns a logical board matching specified layout.
     */
    private Square[][] readFile(Square[][] tempBoard) {
        try {
            Scanner sc = new Scanner(new File(whichBoard));
            while (sc.hasNextLine()) {
                for (int y = 0; y < SIZE_OF_BOARD; y++) {
                    String[] line = sc.nextLine().trim().split(" ");
                    for (int x = 0; x < SIZE_OF_BOARD; x++) {
                        if (Integer.parseInt(line[x]) == 1) {
                            tempBoard[y][x].setCurrentPiece(new RegularPiece(Team.s,x,y));
                        }
                        if (Integer.parseInt(line[x]) == 2) {
                            tempBoard[y][x].setCurrentPiece(new RegularPiece(Team.g,x,y));
                        }
                        if (Integer.parseInt(line[x]) == 3) {
                            tempBoard[y][x].setCurrentPiece(new Flag(Team.g,x,y));
                        }

                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return tempBoard;

    }

    public int[][] boardToArray() {
        int[][] arrayBoard = new int[11][11];

        for(int i = 0; i < 11; i++) {
            for(int j = 0; j < 11; j++) {
                if(board[i][j].getCurrentPiece().getColor().equals(Team.s)){
                    arrayBoard[i][j] = 1;
                } else if(board[i][j].getCurrentPiece().getColor().equals(Team.g)) {
                    if(board[i][j].getCurrentPiece() instanceof Flag) {
                        arrayBoard[i][j] = 3;
                    } else {
                        arrayBoard[i][j] = 2;
                    }
                }
            }
        }

        return arrayBoard;
    }

}
