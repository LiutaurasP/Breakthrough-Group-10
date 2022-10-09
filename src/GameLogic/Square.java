package GameLogic;

import Pieces.AbstractPiece;

/**
 * A class that is created to represent one logical tile in the logical board.
 */
public class Square {
    AbstractPiece currentPiece;
    int x;
    int y;
    /**
     * Constructor for a logical tile.
     * @param x X coordinate.
     * @param y Y coordinate.
     */
    public Square(int x, int y) {
        this.x = x;
        this.y = y;
    }
    /**
     * X-axis coordinate of a square getter.
     * @return Returns X-axis coordinate of a square.
     */
    public int getX() {
        return x;
    }

    /**
     * Y-axis coordinate of a square getter.
     * @return Returns Y-axis coordinate of a square.
     */
    public int getY() {
        return y;
    }

    /**
     * Piece that is in the square getter.
     * @return Returns a piece that is in the square.
     */
    public AbstractPiece getCurrentPiece() {
        return currentPiece;
    }

    /**
     * A function that prints out a string of a current state of the square.
     * g - contains gold piece.
     * s - contains silver piece.
     * f - contains flag piece.
     * @return
     */
    @Override
    public String toString() {
        if (currentPiece != null) {
            return currentPiece.toString();
        }
        return "-";
    }

    /**
     * Current piece of the square setter.
     * @param currentPiece A piece that is to be placed in the square.
     */
    public void setCurrentPiece(AbstractPiece currentPiece) {
        if(currentPiece!=null) {
            currentPiece.setY(getY());
            currentPiece.setX(getX());
        }
        this.currentPiece = currentPiece;
    }
}
