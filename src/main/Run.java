package main;

import chatform.Start;
import javax.swing.SwingUtilities;

public class Run {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new Start();
        });

    }
}