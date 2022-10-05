package Menu;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.*;

public class SelectManually extends JFrame{
    private JFrame frame;
    private JLabel background;

    private ImageIcon bg = new ImageIcon("src/imgs/bg.jpg");

    public final int FRAME_WIDTH = 400;
    public final int FRAME_HEIGHT = 300;

    public SelectManually(){
        frame = new JFrame("Manual Selection");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setResizable(false);

        background = new JLabel(bg);
        background.setLayout(new FlowLayout());
        frame.add(background);

        String text = "\nSelect which player will play \nas gold / silver.\n"+
                "If an invalid choice is made;\nPlayer 1: GOLD\nPlayer 2: SILVER.";
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

        String p1GoldOrSilver[] = {"Player 1", "Gold", "Silver"};
        JComboBox<String> p1ComboBox = new JComboBox<String>(p1GoldOrSilver);
        background.add(p1ComboBox);

        String p2GoldOrSiler[] = {"Player 2", "Gold", "Silver"};
        JComboBox<String> p2ComboBox = new JComboBox<String>(p2GoldOrSiler);
        background.add(p2ComboBox);

        JButton continueBtn = new JButton("press to continue...");
        background.add(continueBtn);
        continueBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                Breakthru breakthru = new Breakthru();
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SelectManually selectManually = new SelectManually();
    }
}

