package GUI;

import javax.swing.*;
import java.awt.*;


public class MainMenu extends SetUp{
    JFrame frame;
    JButton playBtn, helpBtn;
    PlayChoice play;
    GameRules rules;

    public MainMenu() {

        playBtn = new JButton("PLAY");
        helpBtn = new JButton("HELP");
        ButtonSetUp(playBtn);
        ButtonSetUp(helpBtn);
        playBtn.addActionListener(e -> {frame.dispose(); play = new PlayChoice();});
        helpBtn.addActionListener(e -> {frame.dispose(); rules = new GameRules();});

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
}

