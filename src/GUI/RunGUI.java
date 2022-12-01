package GUI;

import com.formdev.flatlaf.FlatDarculaLaf;

import javax.swing.*;


public class RunGUI {
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        new MainMenu();
    }
}
