package GameLogic;

import Pieces.AbstractPiece;

public class Square {
    AbstractPiece currentPiece;

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
        this.currentPiece = currentPiece;
    }
}
