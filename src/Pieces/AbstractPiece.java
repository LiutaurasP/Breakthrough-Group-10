package Pieces;

import GameLogic.PlayingBoard;
import GameLogic.Team;
import GameLogic.Square;

import java.util.ArrayList;

abstract public class AbstractPiece {
    public Team color;
    public int x;

    public void setColor(Team color) {
        this.color = color;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int y;

    public AbstractPiece(Team color_, int x, int y) {
        this.color = color_;
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return color.toString();
    }

    public Team getColor() {
        return color;
    }
}
