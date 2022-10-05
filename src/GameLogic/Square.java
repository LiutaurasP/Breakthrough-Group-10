package GameLogic;

import Pieces.AbstractPiece;

public class Square {
    AbstractPiece currentPiece;
    int x;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    int y;
    public Square(int x, int y) {
    this.x = x;
    this.y = y;
    }

    public AbstractPiece getCurrentPiece() {
        return currentPiece;
    }

    @Override
    public String toString() {
        if (currentPiece != null) {
            return currentPiece.toString();
        }
        return "-";
    }


    public void setCurrentPiece(AbstractPiece currentPiece) {
        if(currentPiece!=null) {
            currentPiece.setY(getY());
            currentPiece.setX(getX());
        }
        this.currentPiece = currentPiece;
    }
}
