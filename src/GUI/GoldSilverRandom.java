package GUI;
import Menu.Breakthru;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Random;

public class GoldSilverRandom extends SetUp{
    JFrame frame;
    JComboBox<String> headsTailsBox;
    JButton startButton;
    String playersGuess;

    ImageIcon HPoof = new ImageIcon("src/imgs/HeadsPoof.gif");
    ImageIcon TPoof = new ImageIcon("src/imgs/TailsPoof.gif");
    ImageIcon HFloat = new ImageIcon("src/imgs/Heads.gif");
    ImageIcon TFloat = new ImageIcon("src/imgs/Tails.gif");
    ImageIcon gif = new ImageIcon("src/imgs/CoinFlipping.gif");
    ImageIcon bg = new ImageIcon("src/imgs/bg.jpg");

    final int GIF_WIDTH = gif.getIconWidth();
    final int GIF_HEIGHT = 650;

    public GoldSilverRandom() {
        frame = GoldSilverChoice.choiceMenu.frame;
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0,0,513,513);
        LayoutSetUp();
        choicePanel.setSize(413, 380);

        TextSetUp("<html>Player 1: choose <b>heads</b> or <br/><b>tails</b> for a coinflip."+
                " If <br/>outcome is guessed right: <br/><br/><b>Player 1</b>: <em>GOLD</em><br/> " +
                "<b>Player 2</b>: <em>SILVER</em><br/>"+
                "<br/>Vice versa if <br/>guessed incorrectly<br/><html/>");
        choicePanel.add(description);
        choicePanel.add(Box.createRigidArea(new Dimension(413, 20)));

        String[] headOrTail = {"Heads", "Tails"};
        headsTailsBox = new JComboBox<>(headOrTail);
        choicePanel.add(headsTailsBox);

        // button to start actual coin flip
        startButton = new JButton("Start coin flip");
        ButtonSetUp(startButton);
        startButton.setMinimumSize(new Dimension(300, 40));
        startButton.setMaximumSize(new Dimension(300, 40));
        startButton.addActionListener(e -> {
            if (Objects.equals(headsTailsBox.getSelectedItem(), "Heads"))
                playersGuess = "Heads";
            else
                playersGuess = "Tails";
            frame.dispose();
            new Coinflip();
        });
        choicePanel.add(startButton);

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

    public class Coinflip {
        public Coinflip() {
            JFrame newFrame = new JFrame();
            newFrame.setSize(GIF_WIDTH, GIF_HEIGHT);

            JLabel background = new JLabel(bg);
            background.setLayout(new FlowLayout());
            newFrame.add(background);

            JLabel headerLabel = new JLabel("Flipping coin ...", JLabel.CENTER);
            headerLabel.setFont(new Font("Lucida Sans Typewriter", Font.ITALIC, 30));
            headerLabel.setForeground(Color.WHITE);
            background.add(headerLabel);

            JPanel imgPanel = new JPanel();
            JLabel imgLabel = new JLabel(gif);
            imgPanel.add(imgLabel);
            background.add(imgPanel);

            JLabel statusLabel = new JLabel("", JLabel.CENTER);
            statusLabel.setFont(new Font("Lucida Sans Typewriter", Font.ITALIC,  20));
            statusLabel.setForeground(Color.WHITE);
            background.add(statusLabel);

            JLabel ready = new JLabel("Please wait", JLabel.CENTER);
            ready.setFont(new Font("Lucida Sans Typewriter", Font.ITALIC,  17));
            ready.setForeground(Color.WHITE);
            background.add(ready);

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

            // after 'poof'-gif is displayed, replaced with 'floating coin'-gif
            Timer timerFloating = new Timer(4320, event -> {
                if (rnd) {
                    imgLabel.setIcon(HFloat);
                    headerLabel.setText("Heads!");
                    if (playersGuess.equals("Heads"))
                        statusLabel.setText("Player 1 plays as GOLD. Player 2 plays as SILVER.");
                    else
                        statusLabel.setText("Player 1 plays as SILVER. Player 2 plays as GOLD.");
                } else {
                    imgLabel.setIcon(TFloat);
                    headerLabel.setText("Tails!");
                    if (playersGuess.equals("Tails"))
                        statusLabel.setText("Player 1 plays as GOLD. Player 2 plays as SILVER.");
                    else
                        statusLabel.setText("Player 1 plays as SILVER. Player 2 plays as GOLD.");
                }
                ready.setText("Ready to play?");
                JButton continueBtn = new JButton("press to continue...");
                background.add(continueBtn);
                continueBtn.addActionListener(e -> { newFrame.dispose(); new Breakthru(); });
            });
            timerFloating.setRepeats(false);
            timerFloating.start();

            newFrame.setVisible(true);
            newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            newFrame.setLayout(null);
            newFrame.setResizable(false);
            newFrame.setIconImage(icon.getImage());
            newFrame.setLocationRelativeTo(null);
            newFrame.setVisible(true);
        }
    }
}
