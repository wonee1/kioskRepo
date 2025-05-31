package com.kiosk.app.ui;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.Map;

public class PaymentCardPage extends Page {
    private Timer progressTimer;
    private JProgressBar progressBar;
    private JLabel statusLabel;
    private JPanel orderSummaryPanel;
    private JLabel totalPriceLabel;

    public PaymentCardPage(MainFrame mainFrame) {
        super(mainFrame);
        setLayout(new BorderLayout(0, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        initComponents();
    }

    private void initComponents() {
        // 상단 패널
        JPanel topPanel = new JPanel(new BorderLayout(0, 20));
        
        // 상단 타이틀
        JLabel titleLabel = new JLabel("카드를 투입해 주세요");
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(titleLabel, BorderLayout.NORTH);

        // 진행 상태 바
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        progressBar.setString("카드 읽는 중...");
        progressBar.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        topPanel.add(progressBar, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);

        // 중앙 패널 (주문 요약)
        JPanel centerPanel = new JPanel(new BorderLayout(0, 10));
        centerPanel.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230)));
        
        // 주문 목록 패널
        orderSummaryPanel = new JPanel();
        orderSummaryPanel.setLayout(new BoxLayout(orderSummaryPanel, BoxLayout.Y_AXIS));
        orderSummaryPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        orderSummaryPanel.setBackground(Color.WHITE);

        // 스크롤 패널
        JScrollPane scrollPane = new JScrollPane(orderSummaryPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setBackground(Color.WHITE);
        
        // 총 금액 패널
        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalPanel.setBackground(Color.WHITE);
        totalPriceLabel = new JLabel("총 금액: 0원");
        totalPriceLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        totalPanel.add(totalPriceLabel);

        centerPanel.add(scrollPane, BorderLayout.CENTER);
        centerPanel.add(totalPanel, BorderLayout.SOUTH);
        
        add(centerPanel, BorderLayout.CENTER);

        // 하단 상태 레이블
        statusLabel = new JLabel("결제를 진행해 주세요");
        statusLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(statusLabel, BorderLayout.SOUTH);

        // 결제 취소 버튼
        JButton cancelButton = new JButton("결제 취소");
        cancelButton.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        cancelButton.addActionListener(e -> {
            if (progressTimer != null && progressTimer.isRunning()) {
                progressTimer.stop();
            }
            mainFrame.attachPage("CONFIRM");
        });
        add(cancelButton, BorderLayout.SOUTH);
    }

    @Override
    public void display() {
        showOrderSummary();
        startPaymentProcess();
    }

    private void showOrderSummary() {
        orderSummaryPanel.removeAll();
        Map<String, Integer> menuList = mainFrame.getOrderData().getMenuList();
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (Map.Entry<String, Integer> entry : menuList.entrySet()) {
            if (entry.getValue() > 0) {
                JPanel itemPanel = createOrderItemPanel(entry.getKey(), entry.getValue());
                orderSummaryPanel.add(itemPanel);
                orderSummaryPanel.add(Box.createVerticalStrut(5));
                
                // 총 금액 계산
                totalPrice = totalPrice.add(new BigDecimal("5000").multiply(new BigDecimal(entry.getValue())));
            }
        }

        totalPriceLabel.setText("총 금액: " + totalPrice + "원");
        orderSummaryPanel.revalidate();
        orderSummaryPanel.repaint();
    }

    private JPanel createOrderItemPanel(String menuName, int quantity) {
        JPanel panel = new JPanel(new BorderLayout(10, 0));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(230, 230, 230)),
            BorderFactory.createEmptyBorder(8, 8, 8, 8)
        ));
        panel.setBackground(Color.WHITE);
        
        // 왼쪽 패널 (메뉴 이름)
        JLabel nameLabel = new JLabel(menuName);
        nameLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        
        // 중앙 패널 (수량)
        JLabel quantityLabel = new JLabel("x " + quantity);
        quantityLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        quantityLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        // 오른쪽 패널 (가격)
        JLabel priceLabel = new JLabel(String.valueOf(5000 * quantity) + "원");
        priceLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        priceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        
        panel.add(nameLabel, BorderLayout.WEST);
        panel.add(quantityLabel, BorderLayout.CENTER);
        panel.add(priceLabel, BorderLayout.EAST);
        
        return panel;
    }

    private void startPaymentProcess() {
        progressBar.setValue(0);
        progressTimer = new Timer(50, e -> {
            int value = progressBar.getValue();
            if (value < 100) {
                progressBar.setValue(value + 1);
                updateStatus(value);
            } else {
                progressTimer.stop();
                completePayment();
            }
        });
        progressTimer.start();
    }

    private void updateStatus(int progress) {
        if (progress < 30) {
            statusLabel.setText("카드를 읽는 중입니다...");
        } else if (progress < 60) {
            statusLabel.setText("결제를 진행 중입니다...");
        } else if (progress < 90) {
            statusLabel.setText("결제를 완료하고 있습니다...");
        }
    }

    private void completePayment() {
        JOptionPane.showMessageDialog(this, 
            "결제가 완료되었습니다.\n이용해 주셔서 감사합니다.", 
            "결제 완료", 
            JOptionPane.INFORMATION_MESSAGE);
        mainFrame.getOrderData().clearOrder();
        mainFrame.attachPage("START");
    }
} 