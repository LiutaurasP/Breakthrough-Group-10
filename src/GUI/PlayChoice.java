package GUI;

import javax.swing.*;
import java.awt.*;

public class PlayChoice {
    static JLabel backgroundImg;
    static ImageIcon icon;
    static JPanel buttonPanel;
    static JLayeredPane layeredPane;
    static JButton PvP, PvE, backBtn;
    static JFrame frame;

   PlayChoice() {
        PvP = new JButton("PvP");
        PvE = new JButton("PvE");
        backBtn = new JButton("BACK");
        ButtonSetUp(PvP);
        ButtonSetUp(PvE);
        ButtonSetUp(backBtn);

        frame = new JFrame("BREAKTHRU");
        LayoutSetUp();
        buttonPanel.add(PvP);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        buttonPanel.add(PvE);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        buttonPanel.add(backBtn);
        layeredPane.add(buttonPanel);
        ImageSetUp();
        layeredPane.add(backgroundImg);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setSize(513,513);
        frame.setIconImage(icon.getImage());
        frame.add(layeredPane);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void ImageSetUp() {
        backgroundImg = new JLabel(new ImageIcon("src/imgs/warship.png"));
        backgroundImg.setBounds(0, 0, 513, 513);
        icon = new ImageIcon("src/imgs/MU.jpg");
    }
    public static void ButtonSetUp(JButton button) {
        Font buttonFont = new Font("Lucida Sans Typewriter", Font.ITALIC, 25);

        button.setMinimumSize(new Dimension(100, 40));
        button.setMaximumSize(new Dimension(100, 40));
        button.setLayout(null);
        button.setFocusPainted(false);
        button.setContentAreaFilled(true);
//        button.setBorder(new RoundedBorder(5));

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
}
