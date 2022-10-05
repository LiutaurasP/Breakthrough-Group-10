package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

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
        whoseTurn.setFont(new Font("Monospaced",Font.BOLD,20));
        menu.add(whoseTurn);
        menu.setBorder(BorderFactory.createEmptyBorder(20,500,500,0));
        JButton undo = new JButton("Undo Last");
        // TODO: UNDO LAST MOVE
        undo.addActionListener(e -> {

        });
        menu.add(undo);
        JButton quit = new JButton("Quit");
        quit.addActionListener(e -> {
            dispose();
        });
        menu.add(quit);
        menu.setBackground(Color.darkGray);
        getContentPane().add(menu);


    }

    public BoardUI getBoard() {
        return board;
    }
}
