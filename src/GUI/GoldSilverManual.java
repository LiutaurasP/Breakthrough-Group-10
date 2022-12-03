package GUI;

import GameLogic.Team;
import Menu.Breakthru;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class GoldSilverManual extends SetUp{
    JFrame frame;
    JButton confirmBtn;
    JComboBox<String> teamChoiceBox, p2GoldSilverBox;

    public GoldSilverManual(){
        frame = GoldSilverChoice.choiceMenu.frame;
        LayoutSetUp();

        TextSetUp("<html><br/><br/>WHICH PLAYER PLAYS <br/>AS <em>GOLD / SILVER</em>?<br/><html/>");

        //player combo boxes
        String[] p1GoldOrSilver = {"Gold", "Silver"};
        teamChoiceBox = new JComboBox<>(p1GoldOrSilver);
        teamChoiceBox.setBorder(BorderFactory.createTitledBorder("Player 1"));

        String[] p2GoldOrSilver = {"Gold", "Silver"};
        p2GoldSilverBox = new JComboBox<>(p2GoldOrSilver);
        p2GoldSilverBox.setBorder(BorderFactory.createTitledBorder("Player 2"));
        p2GoldSilverBox.setSelectedItem(p1GoldOrSilver[1]);

        //action listeners for setting the opposite team for the other player automagically
        teamChoiceBox.addActionListener(e -> {
            if(teamChoiceBox.getSelectedItem() == p1GoldOrSilver[1]){
                p2GoldSilverBox.setSelectedItem(p1GoldOrSilver[0]);
            } else {
                p2GoldSilverBox.setSelectedItem(p1GoldOrSilver[1]);
            }
        });

        p2GoldSilverBox.addActionListener(e -> {
            if(p2GoldSilverBox.getSelectedItem() == p1GoldOrSilver[1]){
                teamChoiceBox.setSelectedItem(p1GoldOrSilver[0]);
            } else {
                teamChoiceBox.setSelectedItem(p1GoldOrSilver[1]);
            }
        });


        confirmBtn = new JButton("CONFIRM");
        ButtonSetUp(confirmBtn);
        AdjustButtonSize(confirmBtn, 140);
        confirmBtn.addActionListener(e -> {
            frame.dispose();
                if(Objects.equals(teamChoiceBox.getSelectedItem(), "Silver")){
                    System.out.println("reached");
                    new Breakthru(Team.s,Team.g);
                }
                else {
                    new Breakthru(Team.g, Team.s);
                }
        });

        backBtn = new JButton("BACK");
        ButtonSetUp(backBtn);
        backBtn.addActionListener(e -> {frame.dispose(); choiceMenu = new GoldSilverChoice();});

        textPanel.add(description);
        optionsPanel.add(teamChoiceBox);
        optionsPanel.add(p2GoldSilverBox);
        optionsPanel.add(Box.createRigidArea(new Dimension(0, 15)));
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

