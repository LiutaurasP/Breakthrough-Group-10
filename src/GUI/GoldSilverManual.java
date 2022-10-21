package GUI;

import GameLogic.Team;
import Menu.Breakthru;

import javax.swing.*;
import java.awt.*;

public class GoldSilverManual extends SetUp{
    JFrame frame;
    JButton confirmBtn;
    JComboBox<String> p1GoldSilverBox, p2GoldSilverBox;

    public GoldSilverManual(){
        frame = GoldSilverChoice.choiceMenu.frame;
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0,0,513,513);
        LayoutSetUp();

        TextSetUp("<html><br/>Select which player will <br/>play as <em>GOLD / SILVER</em>.<br/>"+
                "If invalid choice is made;<br/><b>Player 1</b>: <em>GOLD</em><br/><b>Player 2</b>: <em>SILVER</em>.<html/>");
        choicePanel.add(description);
        choicePanel.add(Box.createRigidArea(new Dimension(413, 10)));

        String[] p1GoldOrSilver = {"Player 1", "Gold", "Silver"};
        p1GoldSilverBox = new JComboBox<>(p1GoldOrSilver);
        choicePanel.add(p1GoldSilverBox);

        String[] p2GoldOrSilver = {"Player 2", "Gold", "Silver"};
        p2GoldSilverBox = new JComboBox<>(p2GoldOrSilver);
        choicePanel.add(p2GoldSilverBox);

        confirmBtn = new JButton("Confirm");
        ButtonSetUp(confirmBtn);
        confirmBtn.setMinimumSize(new Dimension(150, 40));
        confirmBtn.setMaximumSize(new Dimension(150, 40));
        confirmBtn.addActionListener(e -> {
            frame.dispose();
                if(p1GoldSilverBox.getSelectedItem().toString()=="Silver"){
                    System.out.println("reached");
                    new Breakthru(Team.s,Team.g);
                }
                else {
                    new Breakthru(Team.g, Team.s);
                }
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

