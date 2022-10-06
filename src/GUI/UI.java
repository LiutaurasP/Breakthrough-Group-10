package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class UI extends JFrame{
    Object lock;
    BoardUI board;

    public JLabel getWhoseTurn() {
        return whoseTurn;
    }

    JLabel whoseTurn;

    public UI(Object lock) {
        this.lock = lock;
        board = new BoardUI(lock);
        setTitle("Menu.Breakthru");
        setSize(700, 537);
        getContentPane().setBackground(Color.BLACK);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setResizable(false);
        setVisible(true);
        getContentPane().add(board);
        JPanel menu = new JPanel();
        whoseTurn = new JLabel("");
        whoseTurn.setForeground(Color.GREEN);
        menu.add(whoseTurn);
        menu.setBorder(BorderFactory.createEmptyBorder(250,500,500,0));
        menu.add(new JButton("Button1"));
        menu.add(new JButton("Button2"));
        menu.add(new JButton("Button3"));
        menu.add(new JButton("Button4"));
        menu.add(new JButton("Button5"));
        menu.add(new JButton("Button6"));
        menu.add(new JButton("Button7"));
        menu.add(new JButton("Button8"));


        menu.setBackground(Color.darkGray);
        menu.setSize(100,100);
        getContentPane().add(menu);

    }

    public BoardUI getBoard() {
        return board;
    }
}
