package GUI;

import javax.swing.*;
import java.awt.*;

public abstract class SetUp {

    static JLabel backgroundImg, gameRules;
    static ImageIcon icon;
    static JPanel buttonPanel;
    static JLayeredPane layeredPane;
    static JButton backBtn;
    static MainMenu menu;



    public static void ImageSetUp() {
        backgroundImg = new JLabel(new ImageIcon("src/imgs/warship.png"));
        backgroundImg.setBounds(0, 0, 513, 513);
        icon = new ImageIcon("src/imgs/MU.jpg");
    }

    public static void ButtonSetUp(JButton button) {
        Font buttonFont = new Font("Lucida Sans Typewriter", Font.ITALIC, 25);

        button.setMinimumSize(new Dimension(100, 40));
        button.setMaximumSize(new Dimension(100, 40));
//        button.setLayout(null);
        button.setFocusable(false);
        button.setFocusPainted(false);
        button.setRolloverEnabled(false);
//        button.setContentAreaFilled(true);

        button.setFont(buttonFont);
        button.setBackground(new Color(88, 136, 164));
        button.setForeground(Color.WHITE);
    }

    public static void LayoutSetUp() {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setLocation( 210, 170);
        buttonPanel.setSize(250, 200);
        buttonPanel.setOpaque(false);

        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 513, 513);
    }

    public static void Rules() {
        Font fontR = new Font("Lucida Sans Unicode", Font.PLAIN, 13);

        gameRules = new JLabel("<html>THE OBJECTIVES<br/>BREAKTHRU is a unique, new double-strategy game for " +
                "two--with two<br/>different objectives. One player, commanding a gold fleet consisting of " +
                "one<br/>large flagship and twelve escorts, has the objective of evading capture " +
                "while<br/>aggressively attempting to breakthru his opponent's blockade and escort<br/>his flagship " +
                "to safety (and victory !) on the outer perimeter of the game<br/>board. His opponent, commanding a " +
                "silver fleet of twenty ships, attempts<br/>to create an impenetrable blockade and strategically " +
                "out-maneuver and<br/>destroy the gold fleet's escorts with the objective of capturing the " +
                "flagship<br/>(and winning the game!).<br/><br/>"+
                "THE SETUP<br/>Gold Fleet's commander (referred to as the \"gold player) is determined<br/>by lot or " +
                "coin-flip; his opponent automatically becomes the Silver Fleet's<br/>commander (and is referred to" +
                " as the \"silver\" player).<br/>Gold player places his flagship (larger playing piece) on the " +
                "center square<br/>of the boldly-ruled central area of the game board, and positions his " +
                "escort<br/>ships (12 smaller playing pieces) anywhere on the remaining squares of<br/>the " +
                "central area of the board.<br/>Silver player then positions his 20 ships (silver-colored " +
                "playing pieces) on<br/>as many squares of the peripheral (lightly-ruled) area of the board." +
                "<br/><br/>THE PLAY<br/>Gold player decides who is to play first, and the game begins. Players " +
                "move<br/>alternately by making two motion-moves or one capture-move anywhere on<br/>the board, " +
                "(When the flagship is moved, only one motion-move or one<br/>capture-move is made.)<br/><br/>THE" +
                " MOTION-MOVE<br/>A player must move two of the smaller playing pieces any number of vacant<br/>" +
                "squares either horizontally of vertically on the board (the same as a rock in<br/>chess, except" +
                " that no captures can be made with this move). Remember, if<br/>the flagship is moved, the gold " +
                "player may not move another playing piece.<br/><br/>THE CAPTURE-MOVE<br/>A player may move any" +
                " playing piece, including the flagship, one square<br/>diagonally to capture (displace) one of his " +
                "opponent's playing pieces.<br/>(This move is similar to the capture-move of the pawn in chess, " +
                "except<br/>that captures can be made on any of the four diagonals.)<br/>When a capture-move is made," +
                " the opponent's playing piece is removed<br/>from the board and the vacated square is occupied by the" +
                " captor.<br/>Play continues until one player achieves his objective-and wins the game!<br/>If the " +
                "flagship of the gold fleet reaches one of the outermost squares on<br/>the board~gold player wins," +
                " If the flagship is captured before it reaches<br/>the outer edge of the board-silver player wins!<html/>");
        gameRules.setFont(fontR);
    }
}
