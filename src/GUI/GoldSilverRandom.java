package GUI;
import GameLogic.Team;
import Menu.Breakthru;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

public class GoldSilverRandom extends SetUp{
    JFrame frame;
    JComboBox<String> headsTailsBox;
    JButton startButton, confirmBtn;
    String playersGuess;
    JLabel headerLabel, statusLabel, ready;

    ImageIcon HPoof = new ImageIcon("src/imgs/HeadsPoof.gif");
    ImageIcon TPoof = new ImageIcon("src/imgs/TailsPoof.gif");
    ImageIcon HFloat = new ImageIcon("src/imgs/Heads.gif");
    ImageIcon TFloat = new ImageIcon("src/imgs/Tails.gif");
    ImageIcon gif = new ImageIcon("src/imgs/CoinFlipping.gif");


    public GoldSilverRandom() {
        frame = GoldSilverChoice.choiceMenu.frame;
        LayoutSetUp();

        TextSetUp("<html><b>HEADS</b> OR <b>TAILS</b>? <br/>If outcome = guess: <br/><b>Player 1</b>: <em>GOLD</em><br/>" +
                "<b>Player 2</b>: <em>SILVER</em><br/>Contrarily if outcome ≠ guess<br/><html/>");

        String[] headOrTail = {"Heads", "Tails"};
        headsTailsBox = new JComboBox<>(headOrTail);
        headsTailsBox.setMaximumSize(new Dimension(200,50));

        // button to start actual coin flip
        startButton = new JButton("FLIP");
        ButtonSetUp(startButton);
        startButton.addActionListener(e -> {
            if (Objects.equals(headsTailsBox.getSelectedItem(), "Heads"))
                playersGuess = "Heads";
            else
                playersGuess = "Tails";
            frame.getContentPane().removeAll();
            coinflip();
        });

        backBtn = new JButton("BACK");
        ButtonSetUp(backBtn);
        backBtn.addActionListener(e -> {frame.dispose(); choiceMenu = new GoldSilverChoice();});

        textPanel.add(description);
        optionsPanel.add(Box.createRigidArea(new Dimension(0, 35)));
        optionsPanel.add(headsTailsBox);
        optionsPanel.add(Box.createRigidArea(new Dimension(0, 45)));
        optionsPanel.add(startButton);
        optionsPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        optionsPanel.add(backBtn);
        layeredPane.add(textPanel);
        layeredPane.add(optionsPanel);
        ImageSetUp();
        IconSetUp();
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
    public void specialTextSetUp(JLabel label, int fontSize){
        label.setFont(new Font("Lucida Sans Typewriter", Font.ITALIC, fontSize));
        label.setForeground(Color.WHITE);
        label.setBackground(new Color(0, 0, 0, 100));
        setCentre(label);
    }
    public void coinflip() {
        LayoutSetUp();

        headerLabel = new JLabel("Flipping coin ...", JLabel.CENTER);
        specialTextSetUp(headerLabel, 30);
        optionsPanel.add(headerLabel);

        JLabel imgLabel = new JLabel(gif);
        setCentre(imgLabel);
        optionsPanel.add(imgLabel);

        statusLabel = new JLabel("", JLabel.CENTER);
        specialTextSetUp(statusLabel, 20);
        optionsPanel.add(statusLabel);

        ready = new JLabel("Please wait", JLabel.CENTER);
        specialTextSetUp(ready,  17);
        optionsPanel.add(ready);

        // Random variable to determine heads/tails - true = heads, false = tails
        Random random = new Random();
        boolean rnd = random.nextBoolean();

        // timer waits until 'coin tossing'-gif has ended, replaced with 'poof'-gif
        Timer timerPoof = new Timer(3000, event -> {
            if (rnd) imgLabel.setIcon(HPoof);
            else imgLabel.setIcon(TPoof);
        });
        timerPoof.setRepeats(false);
        timerPoof.start();

        AtomicReference<Team> player1 = new AtomicReference<>(Team.s);
        // after 'poof'-gif is displayed, replaced with 'floating coin'-gif
        Timer timerFloating = new Timer(4320, event -> {
            if (rnd) {
                imgLabel.setIcon(HFloat);
                headerLabel.setText("Heads!");
                if (playersGuess.equals("Heads")){
                    statusLabel.setText("<html>Player 1: <b>GOLD</b>  Player 2: <b>SILVER<b/>.<html/>");
                    player1.set(Team.g);}
                else {
                    statusLabel.setText("<html>Player 1: <b>SILVER</b>  Player 2: <b>GOLD<b/>.<html/>");
                    player1.set(Team.s);}
            } else {
                imgLabel.setIcon(TFloat);
                headerLabel.setText("Tails!");
                if (playersGuess.equals("Tails")){
                    statusLabel.setText("<html>Player 1: <b>GOLD<b/>  Player 2: <b>SILVER.<html/>");
                    player1.set(Team.g);}
                else{
                    statusLabel.setText("<html>Player 1: <b>SILVER<b/>  Player 2: <b>GOLD<b/>.<html/>");
                    player1.set(Team.s);}
            }
            ready.setText("Ready to play?");
            confirmBtn = new JButton("START GAME!");
            ButtonSetUp(confirmBtn);
            AdjustButtonSize(confirmBtn,200);
            confirmBtn.addActionListener(e -> { frame.dispose();
                if(player1.equals(Team.g)){ new Breakthru(Team.g,Team.s); }
                else { new Breakthru(Team.s,Team.g); }
            });
            optionsPanel.add(Box.createRigidArea(new Dimension(0, 15)));
            optionsPanel.add(confirmBtn);
            frame.repaint();
        });
        timerFloating.setRepeats(false);
        timerFloating.start();

        optionsPanel.setLocation(50,20);
        optionsPanel.setSize(400,440);
        optionsPanel.setOpaque(true);
        optionsPanel.setBackground(new Color(0, 0, 0, 100));
        layeredPane.add(optionsPanel);
        ImageSetUp();
        IconSetUp();
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
