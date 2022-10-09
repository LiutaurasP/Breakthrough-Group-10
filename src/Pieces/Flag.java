package Pieces;

import GameLogic.Team;
/**
 * A class that is used for flag piece creation.
 */
public class Flag extends AbstractPiece{
    /**
     * Constructor for flag piece.
     * @param color_ Whose piece it is (Flag can only be gold).
     * @param x X coordinates of a flag.
     * @param y Y coordinates of a flag.
     */
    public Flag(Team color_, int x, int y) {
        super(color_, x, y);
    }

    /**
     * A function that helps to distinguish flag piece from a regular one.
     * @return
     */
    @Override
    public String toString() {
        return "f";
    }
}
