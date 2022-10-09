package Pieces;

import GameLogic.Team;

/**
 * A class that is used for regular playing piece creation.
 */
public class RegularPiece extends AbstractPiece {

    /**
     * Constructor for a playing piece.
     * @param color_ Whose piece it is (Gold/Silver).
     * @param x X coordinates of a piece.
     * @param y Y coordinates of a piece.
     */
    public RegularPiece(Team color_, int x, int y) {
        super(color_, x, y);
    }
}
