package com.kiosk.app.ui;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.Map;
import com.kiosk.app.enums.DiningSpot;

public class ConfirmPage extends Page {
    private JPanel orderSummaryPanel;
    private JLabel totalPriceLabel;
    private JLabel totalItemsLabel;

    public ConfirmPage(MainFrame mainFrame) {
        super(mainFrame);
        setLayout(new BorderLayout(0, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        initComponents();
    }

    private void initComponents() {
        // 상단 타이틀
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("주문 확인");
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 24));
        titlePanel.add(titleLabel);

        // 주문 요약 패널
        orderSummaryPanel = new JPanel();
        orderSummaryPanel.setLayout(new BoxLayout(orderSummaryPanel, BoxLayout.Y_AXIS));
        orderSummaryPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 스크롤 패널
        JScrollPane scrollPane = new JScrollPane(orderSummaryPanel);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230)));

        // 하단 정보 패널
        JPanel bottomPanel = new JPanel(new BorderLayout(0, 10));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        // 총 수량과 금액 정보
        JPanel infoPanel = new JPanel(new GridLayout(2, 1, 0, 5));
        totalItemsLabel = new JLabel("총 수량: 0개");
        totalItemsLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        totalItemsLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        totalPriceLabel = new JLabel("총 금액: 0원");
        totalPriceLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        totalPriceLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        infoPanel.add(totalItemsLabel);
        infoPanel.add(totalPriceLabel);

        // 버튼 패널
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        
        JButton cancelButton = new JButton("취소");
        cancelButton.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        cancelButton.setPreferredSize(new Dimension(150, 50));
        cancelButton.addActionListener(e -> mainFrame.attachPage("MENU"));

        JButton confirmButton = new JButton("결제하기");
        confirmButton.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        confirmButton.setPreferredSize(new Dimension(150, 50));
        confirmButton.addActionListener(e -> mainFrame.attachPage("PAYMENT_CARD"));

        buttonPanel.add(cancelButton);
        buttonPanel.add(confirmButton);

        bottomPanel.add(infoPanel, BorderLayout.NORTH);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

        // 전체 레이아웃 구성
        add(titlePanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    @Override
    public void display() {
        showOrderSummary();
    }

    private void showOrderSummary() {
        orderSummaryPanel.removeAll();
        Map<String, Integer> menuList = mainFrame.getOrderData().getMenuList();
        BigDecimal totalPrice = BigDecimal.ZERO;
        int totalItems = 0;

        for (Map.Entry<String, Integer> entry : menuList.entrySet()) {
            if (entry.getValue() > 0) {
                JPanel itemPanel = createOrderItemPanel(entry.getKey(), entry.getValue());
                orderSummaryPanel.add(itemPanel);
                orderSummaryPanel.add(Box.createVerticalStrut(5));
                
                // 총 수량과 금액 계산
                totalItems += entry.getValue();
                totalPrice = totalPrice.add(new BigDecimal("5000").multiply(new BigDecimal(entry.getValue())));
            }
        }

        totalItemsLabel.setText("총 수량: " + totalItems + "개");
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
} 