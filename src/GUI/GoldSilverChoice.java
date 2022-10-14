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
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0,0,513,513);
        LayoutSetUp();

        TextSetUp("<html><br/>Choose <b>manually</b> to play <br/>as <em>GOLD / SILVER</em><br/>"+
                "or determine <br/><b>randomly</b> (coinflip)?<br/><html/>");
        choicePanel.add(description);
        choicePanel.add(Box.createRigidArea(new Dimension(413, 30)));

        String[] manualOrRandom = {"Choose manually", "Choose randomly"};
        manualRandomBox = new JComboBox<>(manualOrRandom);
        choicePanel.add(manualRandomBox);

        // open new frame according to choice (manual or random) of user
        confirmBtn = new JButton("Confirm");
        ButtonSetUp(confirmBtn);
        confirmBtn.setMinimumSize(new Dimension(150, 40));
        confirmBtn.setMaximumSize(new Dimension(150, 40));
        confirmBtn.addActionListener(e -> {
            if (Objects.equals(manualRandomBox.getSelectedItem(), "Choose manually")){
                   frame.getContentPane().removeAll(); manual = new GoldSilverManual(); }
            else { frame.getContentPane().removeAll(); random = new GoldSilverRandom(); }
        });
        choicePanel.add(confirmBtn);

        layeredPane.add(choicePanel);

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
