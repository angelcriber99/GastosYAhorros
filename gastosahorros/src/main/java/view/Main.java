package view;

import javax.swing.SwingUtilities;

import gui.UserInterface;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UserInterface.createAndShowGUI();
        });
    }

}
