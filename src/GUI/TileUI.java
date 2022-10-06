package GUI;

import GameLogic.Square;
import GameLogic.Team;
import Pieces.AbstractPiece;

import javax.swing.*;
import java.awt.*;

/**
 * A class used to create a single UI tile.
 */
public class TileUI extends JPanel {
    Square square;

    /**
     * Constructor for UI tile.
     */
    TileUI(){
        setBackground(Color.BLACK);
    }

    /**
     * A function used to assign logical tile to UI tile.
     * @param square Logical tile.
     */
    public void setSquare(Square square) {
        this.square = square;
        repaint();
    }

    /**
     * A function used to retrieve logical tile from UI tile.
     * @return Returns logical tile corresponding to UI tile.
     */
    public Square getSquare() {
        return square;
    }

    /**
     * A function that takes care of drawing the pieces.
     * @param g the <code>Graphics</code> helper object.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        AbstractPiece piece = square.getCurrentPiece();
        ((Graphics2D) g).setStroke(new BasicStroke(2));
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
