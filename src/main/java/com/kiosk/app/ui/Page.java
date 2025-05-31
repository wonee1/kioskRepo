package com.kiosk.app.ui;

import javax.swing.JPanel;

public abstract class Page extends JPanel {
    protected MainFrame mainFrame;

    public Page(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public abstract void display();

    protected void navigateToPage(String pageType) {
        mainFrame.attachPage(pageType);
    }
} 