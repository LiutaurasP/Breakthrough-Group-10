package GUI;

import javax.swing.*;
import java.awt.*;
import static javax.swing.BorderFactory.createEmptyBorder;

public class GameRules extends SetUp{
     JScrollPane scrollPane;
     JButton backBtn;
     JFrame frame;
     InputMap keyScroll;

    public GameRules() {
        backBtn = new JButton("BACK");
        ButtonSetUp(backBtn);
        backBtn.addActionListener(e -> {frame.dispose(); menu = new MainMenu();});
        backBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        backBtn.setMargin(new Insets(10,10,10,10));

        Rules();

        scrollPane = new JScrollPane();
        scrollPane.setViewportView(gameRules);
        scrollPane.setBorder(createEmptyBorder(0,0,10,0));
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(createEmptyBorder(10,10,10,10));
        buttonPanel.add(scrollPane);
        buttonPanel.add(backBtn);

        frame = new JFrame("BREAKTHRU - press the Page Down/Up key to scroll");
        ImageSetUp();
        IconSetUp();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(513,513);
        frame.setIconImage(icon.getImage());
        frame.setLocationRelativeTo(null);
        frame.add(buttonPanel);
        frame.setVisible(true);

        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        keyScroll = scrollPane.getInputMap(JComponent.WHEN_FOCUSED);
        keyScroll.put(KeyStroke.getKeyStroke("DOWN"), "positiveUnitIncrement");
        keyScroll.put(KeyStroke.getKeyStroke("UP"), "negativeUnitIncrement");
    }
}
