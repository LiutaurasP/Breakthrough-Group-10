package GUI;

import javax.swing.*;
import java.awt.*;

public abstract class SetUp {

    static JLabel backgroundImg;
    static ImageIcon icon;
    static JPanel buttonPanel;
    static JLayeredPane layeredPane;


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
        button.setFocusable(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(true);

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
