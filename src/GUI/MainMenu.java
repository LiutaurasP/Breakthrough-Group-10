package GUI;

import javax.swing.*;
import java.awt.*;


public class MainMenu {
    static JFrame frame;
    static JPanel buttonPanel;
    static ImageIcon icon;
    static JButton playBtn, helpBtn;

    static JLabel backgroundImg;
    static JLayeredPane layeredPane;
    static PlayChoice play;
    public static void main(String[] args) {
       MainMenu();
    }

    public static void MainMenu() {
        playBtn = new JButton("PLAY");
        helpBtn = new JButton("HELP");
        ButtonSetUp(playBtn);
        ButtonSetUp(helpBtn);
        playBtn.addActionListener(e -> {frame.dispose(); play = new PlayChoice();});
        helpBtn.addActionListener(e -> {});

        frame = new JFrame("BREAKTHRU");
        LayoutSetUp();
        buttonPanel.add(playBtn);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        buttonPanel.add(helpBtn);
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
        button.setFocusable(false);
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
        buttonPanel.setSize(250, 100);
        buttonPanel.setOpaque(false);

        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 513, 513);
    }

}

//class RoundedBorder implements Border {
//    int radius;
//    RoundedBorder(int radius) {
//        this.radius = radius;
//    }
//    public Insets getBorderInsets(Component c) {
//        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
//    }
//    public boolean isBorderOpaque() {
//        return true;
//    }
//    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
//        g.drawRoundRect(x,y,width-1,height-1,radius,radius);
//    }
//}
