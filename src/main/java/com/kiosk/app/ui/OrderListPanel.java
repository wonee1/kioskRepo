package com.kiosk.app.ui;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.kiosk.app.model.Menu;

public class OrderListPanel extends JPanel {
    private MainFrame mainFrame;
    private JPanel orderItemsPanel;
    private JLabel totalPriceLabel;
    private JButton orderButton;

    public OrderListPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout(0, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        initComponents();
    }

    private void initComponents() {
        // 주문 목록 패널
        orderItemsPanel = new JPanel();
        orderItemsPanel.setLayout(new BoxLayout(orderItemsPanel, BoxLayout.Y_AXIS));
        orderItemsPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        
        // 스크롤 패널에 주문 목록 패널 추가
        JScrollPane scrollPane = new JScrollPane(orderItemsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        
        // 상단 타이틀 패널
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel titleLabel = new JLabel("주문 목록");
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        titlePanel.add(titleLabel);
        
        // 하단 패널 (총 금액 + 주문 버튼)
        JPanel bottomPanel = new JPanel(new BorderLayout(0, 10));
        
        // 총 금액 패널
        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalPriceLabel = new JLabel("총 금액: 0원");
        totalPriceLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        totalPanel.add(totalPriceLabel);
        
        // 주문 버튼
        orderButton = new JButton("주문하기");
        orderButton.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        orderButton.setPreferredSize(new Dimension(280, 50));
        orderButton.addActionListener(e -> proceedToOrder());
        
        bottomPanel.add(totalPanel, BorderLayout.NORTH);
        bottomPanel.add(orderButton, BorderLayout.SOUTH);
        
        // 전체 레이아웃 구성
        add(titlePanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void showOrderList(Map<String, Integer> menuList) {
        orderItemsPanel.removeAll();
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (Map.Entry<String, Integer> entry : menuList.entrySet()) {
            if (entry.getValue() > 0) {
                JPanel itemPanel = createOrderItemPanel(entry.getKey(), entry.getValue());
                orderItemsPanel.add(itemPanel);
                orderItemsPanel.add(Box.createVerticalStrut(5)); // 아이템 간 간격 추가
                // TODO: 실제 메뉴 가격 계산 필요
                totalPrice = totalPrice.add(new BigDecimal("5000").multiply(new BigDecimal(entry.getValue())));
            }
        }

        totalPriceLabel.setText("총 금액: " + totalPrice + "원");
        orderItemsPanel.revalidate();
        orderItemsPanel.repaint();
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
        // TODO: 실제 메뉴 가격 표시 필요
        JLabel priceLabel = new JLabel(String.valueOf(5000 * quantity) + "원");
        priceLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        priceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        
        panel.add(nameLabel, BorderLayout.WEST);
        panel.add(quantityLabel, BorderLayout.CENTER);
        panel.add(priceLabel, BorderLayout.EAST);
        
        return panel;
    }

    private void proceedToOrder() {
        if (mainFrame.getOrderData().getMenuList().isEmpty()) {
            JOptionPane.showMessageDialog(this, "메뉴를 선택해주세요.", "알림", JOptionPane.WARNING_MESSAGE);
            return;
        }
        mainFrame.attachPage("CONFIRM");
    }

    public void setOrderList(List<Menu> menuList) {
        // TODO: Implement if needed
    }
} 