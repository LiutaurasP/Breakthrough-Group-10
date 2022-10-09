package GUI;

import javax.swing.*;
import java.awt.*;

public class GameRules extends SetUp{
    static JLabel gameRules;
    static JScrollPane scrollPane;
    static JFrame frame;
    static JTextArea textArea;

    public static void main(String[] args) {
        GameRules();
    }

    public static void GameRules() {
        gameRules = new JLabel("THE OBJECTIVES\n" +
                "BREAKTHRU is a unique, new double-strategy game for two--with two\n" +
                "different objectives. One player, commanding a gold fleet consisting of one\n" +
                "large flagship and twelve escorts, has the objective of evading capture while\n" +
                "aggressively attempting to breakthru his opponent's blockade and escort\n" +
                "his flagship to safety (and victory !) on the outer perimeter of the game\n" +
                "board. His opponent, commanding a silver fleet of twenty ships, attempts\n" +
                "to create an impenetrable blockade and strategically out-maneuver and\n" +
                "destroy the gold fleet's escorts with the objective of capturing the flagship\n" +
                "(and winning the game!).\n\n"+
                "THE SETUP\n" +
                "Gold Fleet's commander (referred to as the \"gold player) is determined\n" +
                "by lot or coin-flip; his opponent automatically becomes the Silver Fleet's\n" +
                "commander (and is referred to as the \"silver\" player).\n" +
                "Gold player places his flagship (larger playing piece) on the center square\n" +
                "of the boldly-ruled central area of the game board, and positions his escort\n" +
                "ships (12 smaller playing pieces) anywhere on the remaining squares of\n" +
                "the central area of the board.\n" +
                "Silver player then positions his 20 ships (silver-colored playing pieces) on\n" +
                "as many squares of the peripheral (lightly-ruled) area of the board.\n\n" +
                "THE PLAY\n" +
                "Gold player decides who is to play first, and the game begins. Players move\n" +
                "alternately by making two motion-moves or one capture-move anywhere on\n" +
                "the board, (When the flagship is moved, only one motion-move or one\n" +
                "capture-move is made.)\n\n" +
                "THE MOTION-MOVE\n" +
                "A player must move two of the smaller playing pieces any number of vacant\n" +
                "squares either horizontally of vertically on the board (the same as a rock in\n" +
                "chess, except that no captures can be made with this move). Remember, if\n" +
                "the flagship is moved, the gold player may not move another playing piece.\n\n" +
                "THE CAPTURE-MOVE\n" +
                "A player may move any playing piece, including the flagship, one square\n" +
                "diagonally to capture (displace) one of his opponent's playing pieces.\n" +
                "(This move is similar to the capture-move of the pawn in chess, except\n" +
                "that captures can be made on any of the four diagonals.)\n" +
                "When a capture-move is made, the opponent's playing piece is removed\n" +
                "from the board and the vacated square is occupied by the captor.\n" +
                "Play continues until one player achieves his objective-and wins the game!\n" +
                "If the flagship of the gold fleet reaches one of the outermost squares on\n" +
                "the board~gold player wins, If the flagship is captured before it reaches\n" +
                "the outer edge of the board-silver player wins!");

        textArea = new JTextArea(510, 510);

        scrollPane = new JScrollPane();
        scrollPane.setViewportView(gameRules);

        frame = new JFrame("BREAKTHRU");
        ImageSetUp();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLayout(null);
        frame.setResizable(false);
        frame.setSize(513,513);
        frame.setIconImage(icon.getImage());
        frame.setLocationRelativeTo(null);
        frame.add(scrollPane);
        frame.setVisible(true);

        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    }

    public static void Rules() {
        Font fontR = new Font("Lucida Sans Unicode", Font.PLAIN, 14);

        gameRules = new JLabel("THE OBJECTIVES\n" +
                "BREAKTHRU is a unique, new double-strategy game for two--with two\n" +
                "different objectives. One player, commanding a gold fleet consisting of one\n" +
                "large flagship and twelve escorts, has the objective of evading capture while\n" +
                "aggressively attempting to breakthru his opponent's blockade and escort\n" +
                "his flagship to safety (and victory !) on the outer perimeter of the game\n" +
                "board. His opponent, commanding a silver fleet of twenty ships, attempts\n" +
                "to create an impenetrable blockade and strategically out-maneuver and\n" +
                "destroy the gold fleet's escorts with the objective of capturing the flagship\n" +
                "(and winning the game!).\n\n"+
                "THE SETUP\n" +
                "Gold Fleet's commander (referred to as the \"gold player) is determined\n" +
                "by lot or coin-flip; his opponent automatically becomes the Silver Fleet's\n" +
                "commander (and is referred to as the \"silver\" player).\n" +
                "Gold player places his flagship (larger playing piece) on the center square\n" +
                "of the boldly-ruled central area of the game board, and positions his escort\n" +
                "ships (12 smaller playing pieces) anywhere on the remaining squares of\n" +
                "the central area of the board.\n" +
                "Silver player then positions his 20 ships (silver-colored playing pieces) on\n" +
                "as many squares of the peripheral (lightly-ruled) area of the board.\n\n" +
                "THE PLAY\n" +
                "Gold player decides who is to play first, and the game begins. Players move\n" +
                "alternately by making two motion-moves or one capture-move anywhere on\n" +
                "the board, (When the flagship is moved, only one motion-move or one\n" +
                "capture-move is made.)\n\n" +
                "THE MOTION-MOVE\n" +
                "A player must move two of the smaller playing pieces any number of vacant\n" +
                "squares either horizontally of vertically on the board (the same as a rock in\n" +
                "chess, except that no captures can be made with this move). Remember, if\n" +
                "the flagship is moved, the gold player may not move another playing piece.\n\n" +
                "THE CAPTURE-MOVE\n" +
                "A player may move any playing piece, including the flagship, one square\n" +
                "diagonally to capture (displace) one of his opponent's playing pieces.\n" +
                "(This move is similar to the capture-move of the pawn in chess, except\n" +
                "that captures can be made on any of the four diagonals.)\n" +
                "When a capture-move is made, the opponent's playing piece is removed\n" +
                "from the board and the vacated square is occupied by the captor.\n" +
                "Play continues until one player achieves his objective-and wins the game!\n" +
                "If the flagship of the gold fleet reaches one of the outermost squares on\n" +
                "the board~gold player wins, If the flagship is captured before it reaches\n" +
                "the outer edge of the board-silver player wins!");
        gameRules.setFont(fontR);
    }

}
