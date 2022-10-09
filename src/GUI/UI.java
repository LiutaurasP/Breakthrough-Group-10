package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * A class that is the main frame of the UI. It contains board UI, menu (buttons,labels).
 */
public class UI extends JFrame{
    Object lock;
    BoardUI board;

    JLabel whoseTurn;

    /**
     * Constructor for main JFrame of GUI.
     * @param lock Thread lock that is used to wait for user input.
     */
    public UI(Object lock) {
        this.lock = lock;
        board = new BoardUI(lock);
        setTitle("Menu.Breakthru");
        setSize(770, 587);
        getContentPane().setBackground(Color.BLACK);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //setResizable(false);
        setVisible(true);

        getContentPane().add(board);
        setBackground(Color.black);
        JPanel menu = new JPanel();
        whoseTurn = new JLabel("");
        whoseTurn.setFont(new Font("Monospaced",Font.BOLD,20));
        menu.add(whoseTurn);
        menu.setBorder(BorderFactory.createEmptyBorder(40,550,500,0));
        JButton undo = new JButton("Undo Last");
        // TODO: UNDO LAST MOVE
        undo.addActionListener(e -> {

        });
        menu.add(undo);
        JButton quit = new JButton("Quit");
        quit.addActionListener(e -> {
            System.exit(0);
        });
        menu.add(quit);
        menu.setBackground(Color.GRAY);
        getContentPane().add(menu);


    }
    /**
     * Turn label getter.
     * @return Returns turn label.
     */
    public JLabel getWhoseTurn() {
        return whoseTurn;
    }

    /**
     * UI board getter.
     * @return Returns UI board.
     */
    public BoardUI getBoard() {
        return board;
    }
}
