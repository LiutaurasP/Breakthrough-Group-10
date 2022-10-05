package GUI;

import GameLogic.PlayingBoard;
import GameLogic.Square;
import GameLogic.TheGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BoardUI extends JPanel implements MouseListener {
    TileUI[][] tiles = new TileUI[11][11];
    private final Object lock;
    public int[] click = new int[2];

    Square currPiece;
    BoardUI(Object lock){
        this.lock = lock;
        setBackground(Color.WHITE);
        setSize(500,500);
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
    }
    public void updateBoard(PlayingBoard board){
        for (int y = 0; y < 11; y++) {
            for (int x = 0; x < 11; x++) {
                tiles[y][x].setSquare(board.getSquare(y,x));
            }

    }}

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = (e.getX()-3)/45;
        int y = (e.getY()-3)/45;
        click[0]=x;
        click[1]=y;
        System.out.println("x:"+x+", y:"+y +"//" +tiles[y][x].getSquare().getCurrentPiece());
        synchronized (lock) {
            lock.notifyAll();
        }

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
