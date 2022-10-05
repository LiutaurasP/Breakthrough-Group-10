package GUI;

import javax.swing.JFrame;
import javax.swing.ImageIcon;

public class Menu {
    static JFrame frame;
    static ImageIcon icon;
    public static void main(String[] args) {
        frame = new JFrame("BREAKTHRU"); //create image icon

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit application
        frame.setResizable(false); //prevents frame from being resized
        frame.setSize(513,513); //sets x-dimension and y-dimension of frame
        frame.setVisible(true); //make frame visible

        icon = new ImageIcon("src/imgs/MaastrichtUniversity.png"); //create icon for Titlebar
        frame.setIconImage(icon.getImage()); //change icon of frame
    }

    public void ButtonSetUp() {}

    public void LayoutSetUp() {}
}
