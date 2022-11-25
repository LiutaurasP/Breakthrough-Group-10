package GUI;

import javax.swing.*;
import java.awt.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 * A class that is the main frame of the UI. It contains board UI, menu (buttons,labels).
 */
public class UI extends SetUp{
    JFrame frame;
    Object lock;
    BoardUI board;
    JButton again;
    JLabel whoseTurn;

    /**
     * Constructor for main JFrame of GUI.
     * @param lock Thread lock that is used to wait for user input.
     */
    public UI(Object lock) {
        this.lock = lock;
        frame = new JFrame();
        board = new BoardUI(lock);
        frame.setTitle("Play Breakthru");
        frame.setSize(770, 587);
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        //setResizable(false);
        frame.setVisible(true);

        frame.add(board);
        frame.setBackground(Color.black);
        JPanel menu = new JPanel();
        whoseTurn = new JLabel("");
        whoseTurn.setFont(new Font("Monospaced",Font.BOLD,20));
        menu.add(whoseTurn);
        menu.setBorder(BorderFactory.createEmptyBorder(40,550,500,0));

        //undo button
        JButton undo = new JButton("Undo Last");
        // TODO: UNDO LAST MOVE
            undo.addActionListener(e -> {

            });
        menu.add(undo);

        //Quit button
        JButton quit = new JButton("Quit");
            quit.addActionListener(e -> {
                System.exit(0);
            });
        menu.add(quit);

        //play again button
        again = new JButton("Play Again?");
            again.addActionListener(e -> {
                frame.dispose();
                choiceMenu = new GoldSilverChoice();
            });
        again.setVisible(false);
        menu.add(again);

        menu.setBackground(Color.GRAY);
        frame.add(menu);
        frame.setIconImage(icon.getImage());
    }

    /**
     * Play again button getter.
     * @return
     */
    public JButton getAgain() {
        return again;
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