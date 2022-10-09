package Menu;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.*;

public class SelectGoldSilver{
    private JFrame frame;
    private JComboBox<String> comboBox;

    public final int FRAME_WIDTH = 400;
    public final int FRAME_HEIGHT = 300;

    private ImageIcon bg = new ImageIcon("src/imgs/bg.jpg");

    public SelectGoldSilver() {
        frame = new JFrame("Gold or Silver?");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setResizable(false);

        JLabel background = new JLabel(bg);
        background.setLayout(new FlowLayout());
        frame.add(background);

        String text = "<br/>Choose manually to <br/>play as GOLD / SILVER<br/>"+
                "or<br/> determine randomly (coinflip)?<br/>";
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
        try {
            doc.insertString(doc.getLength(), text, style);
        } catch (BadLocationException e1) {
            e1.printStackTrace();
        }
        background.add(txt, BorderLayout.CENTER);

        String manualOrRandom[] = {"Choose manually", "Choose randomly"};
        comboBox = new JComboBox<String>(manualOrRandom);
        background.add(comboBox);

        // open new frame according to choice (manual or random) of user
        JButton button = new JButton("Confirm");
        background.add(button);
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (comboBox.getSelectedItem().equals("Choose manually")){
                    frame.dispose();
                    new SelectManually();
                }
                else{
                    frame.dispose();
                    new SelectRandom();
                }
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SelectGoldSilver selectGoldSilver = new SelectGoldSilver();
    }
}
