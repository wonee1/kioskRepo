package com.kiosk.app;

import javax.swing.*;
import java.awt.*;

public class KioskApplication extends JFrame {
    
    public KioskApplication() {
        // 기본 프레임 설정
        setTitle("키오스크 시스템");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // 화면 중앙에 위치
        
        // 메인 패널 설정
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        
        // 환영 메시지
        JLabel welcomeLabel = new JLabel("키오스크에 오신 것을 환영합니다!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("맑은 고딕", Font.BOLD, 24));
        mainPanel.add(welcomeLabel, BorderLayout.CENTER);
        
        // 프레임에 메인 패널 추가
        add(mainPanel);
    }
    
    public static void main(String[] args) {
        // Swing 애플리케이션은 EDT(Event Dispatch Thread)에서 실행
        SwingUtilities.invokeLater(() -> {
            KioskApplication app = new KioskApplication();
            app.setVisible(true);
        });
    }
} 