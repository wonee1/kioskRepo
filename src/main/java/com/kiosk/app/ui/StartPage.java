package com.kiosk.app.ui;

import javax.swing.*;
import java.awt.*;

public class StartPage extends Page {
    private JLabel welcomeLabel;
    private JButton startButton;

    public StartPage(MainFrame mainFrame) {
        super(mainFrame);
        setLayout(new BorderLayout());
        initComponents();
    }

    private void initComponents() {
        // 중앙 패널 생성
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        // 환영 메시지
        welcomeLabel = new JLabel("키오스크에 오신 것을 환영합니다");
        welcomeLabel.setFont(new Font("맑은 고딕", Font.BOLD, 36));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        // 시작 버튼
        startButton = new JButton("주문 시작하기");
        startButton.setFont(new Font("맑은 고딕", Font.PLAIN, 24));
        startButton.setPreferredSize(new Dimension(300, 80));
        startButton.addActionListener(e -> setNextPage("EAT_PLACE"));

        // 컴포넌트 배치
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 50, 0);
        centerPanel.add(welcomeLabel, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        centerPanel.add(startButton, gbc);

        add(centerPanel, BorderLayout.CENTER);
    }

    @Override
    public void display() {
        // 페이지가 표시될 때 필요한 초기화 작업
        showText("주문을 시작하려면 화면을 터치하세요");
    }

    public void showText(String text) {
        welcomeLabel.setText(text);
    }

    public void setNextPage(String pageType) {
        navigateToPage(pageType);
    }
} 