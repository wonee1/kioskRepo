package com.kiosk.app;

import javax.swing.SwingUtilities;
import com.kiosk.app.ui.MainFrame;

public class KioskApplication {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}
