package GUI;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;


public class RunGUI {
    public static void main(String[] args) {

        try {
//            UIManager.setLookAndFeel(new FlatDarculaLaf());
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        new MainMenu();
    }
}
