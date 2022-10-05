package GUI;

import GameLogic.Square;
import GameLogic.Team;
import Pieces.AbstractPiece;

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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        AbstractPiece piece = square.getCurrentPiece();
        if (piece!=null){
            if(piece.getColor().equals(Team.g)) {
                if(piece.toString().equals("f")){
                    g.setColor(Color.CYAN);
                    g.drawOval(6,6,30,30);
                }
                else{
                    g.setColor(Color.YELLOW);
                    g.drawOval(6,6,30,30);
                }
            } if(piece.getColor().equals(Team.s)) {
                g.setColor(Color.WHITE);
                g.drawOval(6,6,30,30);
            }
            }
    }

}
