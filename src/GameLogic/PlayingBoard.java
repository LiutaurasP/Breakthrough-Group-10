package GameLogic;

import Pieces.AbstractPiece;
import Pieces.Flag;
import Pieces.RegularPiece;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PlayingBoard {
    public final int SIZE_OF_BOARD = 11;
    private Square[][] board;
    String whichBoard = "src/DifferentKindOfBoards/diamond.txt";

    public PlayingBoard() {
        this.board = createBoard();
    }

    public Square[][] getBoard() {
        return board;
    }
    public AbstractPiece getPiece(int x, int y){
        return board[y][x].currentPiece;
    }
    public Square getSquare(int y, int x){ return board[y][x];}

    @Override
    public String toString() {
        String stringBoard = "y/x 0 1 2 3 4 5 6 7 8 9 10 \n";
        for (int y = 0; y < SIZE_OF_BOARD; y++) {
            if (y != 10) stringBoard += y + "   ";
            else stringBoard += y + "  ";
            for (int x = 0; x < SIZE_OF_BOARD; x++) {
                stringBoard += board[y][x].toString() + " ";
            }
            stringBoard += "\n";
        }
        return stringBoard;
    }

    private Square[][] createBoard() {
        Square[][] tempBoard = new Square[SIZE_OF_BOARD][SIZE_OF_BOARD];
        for (int y = 0; y < SIZE_OF_BOARD; y++) {
            for (int x = 0; x < SIZE_OF_BOARD; x++) {
                tempBoard[y][x] = new Square();
            }
        }

        return readFile(tempBoard);
    }

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

}
