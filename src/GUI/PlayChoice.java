package GUI;

import javax.swing.*;
import java.awt.*;

public class PlayChoice extends SetUp{
     JButton PvP, PvE;
     JFrame frame;
   public PlayChoice() {
        PvP = new JButton("PvP");
        PvE = new JButton("PvE");
        backBtn = new JButton("BACK");
        ButtonSetUp(PvP);
        ButtonSetUp(PvE);
        ButtonSetUp(backBtn);
        PvE.setBackground(new Color(93, 101, 112));
        PvP.addActionListener(e -> {frame.dispose(); new GoldSilverChoice();});
        backBtn.addActionListener(e -> {frame.dispose(); menu = new MainMenu();});

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
        Tooltip();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setSize(513,513);
        frame.setIconImage(icon.getImage());
        frame.add(layeredPane);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
   }

   public void Tooltip(){
        JToolTip tTip = new JToolTip();
        PvE.setToolTipText("This option is not available at the moment");
        tTip.setComponent(PvE);
   }
}
