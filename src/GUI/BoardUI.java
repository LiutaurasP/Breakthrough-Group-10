package GUI;

import GameLogic.PlayingBoard;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * A class that contains all the little tiles. In other words, a UI board.
 */
public class BoardUI extends JPanel implements MouseListener {
    TileUI[][] tiles = new TileUI[11][11];
    private final Object lock;
    public int[] click = new int[2];

    /**
     * Constructor for a UI board.
     * @param lock Thread lock that is used to wait for user input
     */
    BoardUI(Object lock){

        this.lock = lock;
        //this.setLocation();
        setBackground(Color.darkGray);

        addMouseListener(this);

        GridLayout grid = new GridLayout(11, 11,3,3);

        setLayout(grid);
        for (int y = 0; y < 11; y++) {
            for (int x = 0; x < 11; x++) {
                TileUI temp = new TileUI();
                add(temp);
                tiles[y][x] = temp;
            }
        }
        setSize(551,550);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(255, 255, 255));
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));

        g.drawRect( 151, 149, 249,251);
    }
    /**
     * A function that refreshes the UI board to the most current logical board.
     * @param board Current board.
     */
    public void updateBoard(PlayingBoard board){
        for (int y = 0; y < 11; y++) {
            for (int x = 0; x < 11; x++) {
                tiles[y][x].setSquare(board.getSquare(x,y));
            }

    }}


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * A function that reads mouse input.
     * @param e The mouse event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println(e.getX()+" "+e.getY());
            int x = (e.getX() - 3) / 50;
            int y = (e.getY() - 3) / 50;
            click[0] = x;
            click[1] = y;

            System.out.println("x:" + x + ", y:" + y + "//" + tiles[y][x].getSquare().getCurrentPiece());
            synchronized (lock) {
                lock.notifyAll();
            }
        }




    public TileUI getTile(int x, int y) {
        return tiles[y][x];
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
