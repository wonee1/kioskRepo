package com.kiosk.app.ui;

import javax.swing.*;
import java.awt.*;
import com.kiosk.app.model.KioskOrderData;

public class MainFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private CardLayout cardLayout;
    private JPanel contentPanel;
    private KioskOrderData orderData;

    public MainFrame() {
        setTitle("키오스크 시스템");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1024, 768);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        add(contentPanel);

        orderData = new KioskOrderData();
        
        // 초기 페이지 설정
        attachPage("START");
    }

    public void attachPage(String pageType) {
        // 기존 페이지 제거
        if (contentPanel.getComponentCount() > 0) {
            contentPanel.removeAll();
        }
        
        // 새 페이지 생성 및 추가
        Page page = createPage(pageType);
        if (page != null) {
            contentPanel.add(page, pageType);
            cardLayout.show(contentPanel, pageType);
            page.display(); // 페이지 표시 시 초기화
        }
        
        // 컴포넌트 갱신
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private Page createPage(String pageType) {
        switch (pageType) {
            case "START":
                return new StartPage(this);
            case "EAT_PLACE":
                return new EatPlacePage(this);
            case "MENU":
                return new MenuPage(this);
            case "CONFIRM":
                return new ConfirmPage(this);
            case "PAYMENT_CARD":
                return new PaymentCardPage(this);
            default:
                return null;
        }
    }

    public KioskOrderData getOrderData() {
        return orderData;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
} 