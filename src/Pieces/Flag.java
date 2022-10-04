package Pieces;

import GameLogic.Team;

public class Flag extends AbstractPiece{
    public Flag(Team color_, int x, int y) {
        super(color_, x, y);
    }

    @Override
    public String toString() {
        return "f";
    }
}
