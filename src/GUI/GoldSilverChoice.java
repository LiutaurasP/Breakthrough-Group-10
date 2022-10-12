package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class GoldSilverChoice extends SetUp {
    JFrame frame;
    JButton confirmBtn;
    JComboBox<String> manualRandomBox;

    public GoldSilverChoice() {
        frame = new JFrame("Gold or Silver?");
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0,0,513,513);
        LayoutSetUp();

        TextSetUp("<html><br/>Choose <b>manually</b> to play <br/>as <em>GOLD / SILVER</em><br/>"+
                "or determine <br/><b>randomly</b> (coinflip)?<br/><html/>");
        textPanel.add(description);

        String[] manualOrRandom = {"Choose manually", "Choose randomly"};
        manualRandomBox = new JComboBox<>(manualOrRandom);
        comboBoxPanel.add(manualRandomBox);

        // open new frame according to choice (manual or random) of user
        confirmBtn = new JButton("Confirm");
        ButtonSetUp(confirmBtn);
        buttonPanel.setLocation(170, 370);
        confirmBtn.setMinimumSize(new Dimension(150, 40));
        confirmBtn.setMaximumSize(new Dimension(150, 40));
        confirmBtn.addActionListener(e -> {
            if (Objects.equals(manualRandomBox.getSelectedItem(), "Choose manually")){
                   frame.dispose(); new GoldSilverManual(); }
            else { frame.dispose(); new GoldSilverRandom(); }
        });
        buttonPanel.add(confirmBtn);

        layeredPane.add(textPanel);
        layeredPane.add(comboBoxPanel);
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
