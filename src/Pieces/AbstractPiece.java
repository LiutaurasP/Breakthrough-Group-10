package Pieces;

import GameLogic.Team;

/**
 * Abstract class that shapes how the playing pieces should be.
 */
abstract public class AbstractPiece {
    public Team color;
    public int x;
    public int y;

    /**
     * Typical piece constructor.
     * @param color_ Whose piece it is (Gold/Silver).
     * @param x X coordinates of a piece.
     * @param y Y coordinates of a piece.
     */
    public AbstractPiece(Team color_, int x, int y) {
        this.color = color_;
        this.x = x;
        this.y = y;
    }

    /**
     * X coordinate of a piece setter.
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }
    /**
     * Y coordinate of a piece setter.
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * A function that helps to distinguish the color of pieces.
     * @return Returns a string that represents a piece.
     */
    @Override
    public String toString() {
        return color.toString();
    }

    /**
     * Color of the piece getter.
     * @return Returns color of the piece.
     */
    public Team getColor() {
        return color;
    }
}
