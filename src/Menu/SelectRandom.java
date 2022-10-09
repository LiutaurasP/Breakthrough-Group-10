package Menu;

import java.awt.*;
import java.util.Random;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.*;

public class SelectRandom extends JFrame {
    private JFrame frame;
    private JLabel background;
    private JComboBox<String> comboBox;
    private String playersGuess;

    private ImageIcon HPoof = new ImageIcon("src/imgs/HeadsPoof.gif");
    private ImageIcon TPoof = new ImageIcon("src/imgs/TailsPoof.gif");
    private ImageIcon HFloat = new ImageIcon("src/imgs/Heads.gif");
    private ImageIcon TFloat = new ImageIcon("src/imgs/Tails.gif");
    private ImageIcon gif = new ImageIcon("src/imgs/CoinFlipping.gif");
    private ImageIcon bg = new ImageIcon("src/imgs/bg.jpg");

    public final int FRAME_WIDTH = gif.getIconWidth();
    public final int FRAME_HEIGHT = 650;

    public SelectRandom() {
        frame = new JFrame("Random Selection - Coinflip");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT-250);
        frame.setResizable(false);

        background = new JLabel(bg);
        background.setLayout(new FlowLayout());
        frame.add(background);

        String text = "\nPlayer 1: \nchoose either heads or tails for a coinflip.\n\n"+
                "If you guess the outcome right,\nyou will play as GOLD. \nOtherwise, you will play as SILVER\n"+
                "and player 2 vice versa.\n";

        JTextPane txt = new JTextPane();
        StyledDocument doc = txt.getStyledDocument();
        Style style = txt.addStyle("Color Style", null);
        SimpleAttributeSet attribs = new SimpleAttributeSet();
        StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_CENTER);
        StyleConstants.setFontSize(style, 20);
        StyleConstants.setForeground(style, Color.WHITE);
        StyleConstants.setBold(style, true);
        txt.setOpaque(false);
        txt.setEditable(false);
        txt.setParagraphAttributes(attribs, true);
        txt.setPreferredSize(new Dimension(FRAME_WIDTH-50, 250));
        try {
            doc.insertString(doc.getLength(), text, style);
        } catch (BadLocationException e1) {
            e1.printStackTrace();
        }
        background.add(txt, BorderLayout.CENTER);

        String headOrTail[] = {"Heads", "Tails"};
        comboBox = new JComboBox<String>(headOrTail);
        background.add(comboBox);

        // button to start actual coin flip
        JButton button = new JButton("Start coin flip");
        background.add(button);
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (comboBox.getSelectedItem().equals("Heads"))
                    playersGuess = "Heads";
                else
                    playersGuess = "Tails";
                frame.getContentPane().removeAll();
                startCoinFlip();
            }
        });
        frame.setVisible(true);
    }

    public void startCoinFlip(){
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);

        background = new JLabel(bg);
        background.setLayout(new FlowLayout());
        frame.add(background);

        JLabel headerLabel = new JLabel("Flipping coin ...", JLabel.CENTER);
        headerLabel.setFont(new Font("Monospaced", Font.BOLD, 30));
        headerLabel.setForeground(Color.WHITE);
        background.add(headerLabel);

        JPanel imgPanel = new JPanel();
        JLabel imgLabel = new JLabel(gif);
        imgPanel.add(imgLabel);
        background.add(imgPanel);

        JLabel statusLabel = new JLabel("", JLabel.CENTER);
        statusLabel.setFont(new Font("Monospaced", Font.BOLD, 20));
        statusLabel.setForeground(Color.WHITE);
        background.add(statusLabel);

        JLabel ready = new JLabel("Please wait", JLabel.CENTER);
        ready.setFont(new Font("Monospaced", Font.BOLD, 17));
        ready.setForeground(Color.WHITE);
        background.add(ready);

        // Random variable to determine heads/tails - true = heads, false = tails
        Random random = new Random();
        boolean rnd = random.nextBoolean();

        // timer waits until 'coin tossing'-gif has ended, replaced with 'poof'-gif
        Timer timerPoof = new Timer(3000, event -> {
            if (rnd)
                imgLabel.setIcon(HPoof);
            else
                imgLabel.setIcon(TPoof);
        });
        timerPoof.setRepeats(false);
        timerPoof.start();

        // after 'poof'-gif is displayed, replaced with 'floating coin'-gif
        Timer timerFloating = new Timer(4320, event -> {
            if (rnd){
                imgLabel.setIcon(HFloat);
                headerLabel.setText("Heads!");
                if(playersGuess.equals("Heads"))
                    statusLabel.setText("Player 1 plays as GOLD. Player 2 plays as SILVER.");
                else
                    statusLabel.setText("Player 1 plays as SILVER. Player 2 plays as GOLD.");
            }
            else{
                imgLabel.setIcon(TFloat);
                headerLabel.setText("Tails!");
                if(playersGuess.equals("Tails"))
                    statusLabel.setText("Player 1 plays as GOLD. Player 2 plays as SILVER.");
                else
                    statusLabel.setText("Player 1 plays as SILVER. Player 2 plays as GOLD.");
            }
            ready.setText("Ready to play?");
            JButton continueBtn = new JButton("press to continue...");
            background.add(continueBtn);
            continueBtn.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    frame.dispose();
                    new Breakthru();
                }
            });

        });
        timerFloating.setRepeats(false);
        timerFloating.start();

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new SelectRandom();
    }

}
