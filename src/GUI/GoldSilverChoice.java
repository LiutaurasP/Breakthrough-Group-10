package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class GoldSilverChoice extends SetUp {
    JFrame frame;
    JButton confirmBtn;
    JComboBox<String> manualRandomBox;
    GoldSilverManual manual;
    GoldSilverRandom random;

    public GoldSilverChoice() {
        frame = new JFrame("BREAKTHRU - Gold or Silver?");
        LayoutSetUp();

        TextSetUp("<html><br/><br/>CHOOSE <b>MANUALLY</b> OR <br/><b>RANDOMLY</b> (coinflip)"+
                " TO <br/> PLAY AS <em>GOLD / SILVER</em> ?<br/><html/>");

        String[] manualOrRandom = {"Choose manually", "Choose randomly"};
        manualRandomBox = new JComboBox<>(manualOrRandom);
        manualRandomBox.setMaximumSize(new Dimension(200,50));

        // open new frame according to choice (manual or random) of user
        confirmBtn = new JButton("CONFIRM");
        ButtonSetUp(confirmBtn);
        AdjustButtonSize(confirmBtn, 140);
        confirmBtn.addActionListener(e -> {
            if (Objects.equals(manualRandomBox.getSelectedItem(), "Choose manually")){
                   frame.getContentPane().removeAll(); manual = new GoldSilverManual(); }
            else { frame.getContentPane().removeAll(); random = new GoldSilverRandom(); }
        });

        backBtn = new JButton("BACK");
        ButtonSetUp(backBtn);
        backBtn.addActionListener(e -> {frame.dispose(); playChoice = new PlayChoice();});

        textPanel.add(description);
        optionsPanel.add(Box.createRigidArea(new Dimension(0, 35)));
        optionsPanel.add(manualRandomBox);
        optionsPanel.add(Box.createRigidArea(new Dimension(0, 45)));
        optionsPanel.add(confirmBtn);
        optionsPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        optionsPanel.add(backBtn);
        layeredPane.add(textPanel);
        layeredPane.add(optionsPanel);
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
