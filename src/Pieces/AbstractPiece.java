package Pieces;

import GameLogic.PlayingBoard;
import GameLogic.Team;
import GameLogic.Square;

import java.util.ArrayList;

abstract public class AbstractPiece {
    private Team color;
    public int x, y;

    public AbstractPiece(Team color_, int x, int y) {
        this.color = color_;
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return color.toString();
    }


}
