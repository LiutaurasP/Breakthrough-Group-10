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
        frame.setTitle("BREAKTHRU");
        IconSetUp();
        frame.setSize(770, 587);
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel menu = new JPanel();
        whoseTurn = new JLabel("");
        whoseTurn.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 22));
        menu.add(whoseTurn);
        menu.setBorder(BorderFactory.createEmptyBorder(40,550,500,0));


        //undo button
        JButton undo = new JButton("UNDO");
        undo.setBackground(new Color(88, 136, 164));
        undo.setFocusable(false);
        undo.setFocusPainted(false);
        undo.setRolloverEnabled(false);

        // TODO: UNDO LAST MOVE
        undo.addActionListener(e -> {

        });
        menu.add(undo);

        //Quit button
        JButton quit = new JButton("QUIT");
        quit.setBackground(new Color(88, 136, 164));
        quit.setFocusable(false);
        quit.setFocusPainted(false);
        quit.setRolloverEnabled(false);

        quit.addActionListener(e -> {
            System.exit(0);
        });
        menu.add(quit);

        //play again button
        again = new JButton("Play Again?");
        ButtonSetUp(again);
        again.addActionListener(e -> {
            frame.dispose();
            choiceMenu = new GoldSilverChoice();
        });
        again.setVisible(false);
        menu.add(again);

        menu.setBackground(Color.DARK_GRAY);

        frame.setBackground(Color.black);
        frame.add(board);
        frame.add(menu);
        frame.setIconImage(icon.getImage());
        frame.setVisible(true);
        frame.setResizable(false);
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