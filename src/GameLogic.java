public class GameLogic {
    public static boolean goldTurn = Launcher.goldTurn;

    /**
     * Checks if the diagonal capturing of a piece is possible.
     * 
     * @param piece - piece that needs to be moved
     * @param x     - x position of move
     * @param y     - y position of move
     * @return 'true' if move is valid | 'false' if move is invalid
     */
    public static boolean checkCapture(Piece piece, int x, int y) {
        // check if the piece is actually a piece
        // don't know if we need this part tho...
        if (piece.getId() == 0) {
            return false;
        }

        // check if the given coordinates are valid (one square on the diagonal)
        if ((piece.getPositionX() - x == 1 && (piece.getPositionY() - y == 1 || piece.getPositionY() - y == -1))
                || (piece.getPositionX() - x == -1 && (piece.getPositionY() - y == 1 || piece.getPositionY() - y == -1))) {

            // check if the piece is gold
            if ((piece.getId() == 2 || piece.getId() == 3)) {

                // check if the new position has a silver piece
                if (Launcher.playingBoard[x][y] == 1) {
                    return true;
                }
            }

            // check if the new position has a gold piece
            if (Launcher.playingBoard[x][y] == 2 || Launcher.playingBoard[x][y] == 3) {
                return true;
            }
        }

        return false;
    }

    // Andra: if we use the method above we might not need / we need to change this
    // one:
    public static void goldCapture(int initX, int initY, int x, int y) {
        if ((initX - x == 1 && (initY - y == 1 || initY - y == -1)) || (initX - x == -1 && (initY - y == 1 || initY - y == -1))) {
            if (goldTurn) {
                if (Launcher.playingBoard[initX][initY] == 2) {
                    if (Launcher.playingBoard[x][y] == 1) { // TODO: if Andra has time make method for this
                        Launcher.playingBoard[x][y] = 2;
                        Launcher.playingBoard[initX][initY] = 0;
                    } else {
                        System.out.println("Capturing is not possible");
                        return;
                    }
                } else {
                    System.out.println("Clicked piece is not gold");
                }

            } else {
                System.out.println("It is not gold's turn");
            }
        }
    }

    /**
     * Checks if the piece can be moved to the given position. This means there's no
     * other pawn between the given piece and said position.
     * 
     * @param piece - the pawn to be moved
     * @param x     - x position of move
     * @param y     - y position of move
     * @return 'true' if move is valid | 'false' if move is invalid
     */
    public static boolean checkValidMove(Piece piece, int x, int y) {
        // check if the new position is on the x or y axis
        if (piece.getPositionX() != x && piece.getPositionY() != y) {
            return false;
        }

        // check if the move is on the x-axis
        if (piece.getPositionX() == x) {

            // new position is on the left
            if (piece.getPositionY() > y) {
                for (int i = y; i < piece.getPositionY(); i++) {
                    if (Launcher.playingBoard[x][i] != 0) {
                        return false; // if the spot is not empty
                    }
                }
            
            // new position is on the right
            } else {
                for (int i = piece.getPositionY() + 1; i <= y; i++) {
                    if (Launcher.playingBoard[x][i] != 0) {
                        return false; // if the spot is not empty
                    }
                }
            }

        // new position is on the y-axis
        } else {

            // new position is above
            if (piece.getPositionX() > y) {
                for (int i = x; i < piece.getPositionX(); i++) {
                    if (Launcher.playingBoard[i][y] != 0) {
                        return false; // if the spot is not empty
                    }
                }

            // new position is under
            } else {
                for (int i = piece.getPositionX() + 1; i <= x; i++) {
                    if (Launcher.playingBoard[i][y] != 0) {
                        return false; // if the spot is not empty
                    }
                }
            }
        }

        return true; // move can be made
    }

    /**
     * Method to move the piece to the given coordinates.
     * 
     * @param piece - the pawn to be moved
     * @param x     - x position of move
     * @param y     - y position of move
     */
    public static void move(Piece piece, int x, int y) {
        if (checkValidMove(piece, x, y)) {
            // update board
            Launcher.playingBoard[piece.getPositionX()][piece.getPositionY()] = 0;
            Launcher.playingBoard[x][y] = piece.getId();

            // update piece
            piece.setPositionX(x);
            piece.setPositionY(y);
        } else {
            // TODO: we can have a popup that says the move is invalid
            System.out.println("Invalid move!");
        }
    }
}
