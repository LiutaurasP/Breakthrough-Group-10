package GUI;

import GameLogic.Square;

import javax.swing.*;
import java.awt.*;

public class TileUI extends JPanel {
    Square square;
TileUI(){
    setBackground(Color.BLACK);
}

    public void setSquare(Square square) {
        this.square = square;
    }

    public Square getSquare() {
        return square;
    }
}
