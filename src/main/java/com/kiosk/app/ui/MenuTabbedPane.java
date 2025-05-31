package com.kiosk.app.ui;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import com.kiosk.app.model.Menu;
import java.util.HashMap;
import java.util.Map;

public class MenuTabbedPane extends JTabbedPane {
    private MainFrame mainFrame;
    private Map<String, JPanel> categoryPanels;
    private OrderListPanel orderListPanel;

    public MenuTabbedPane(MainFrame mainFrame, OrderListPanel orderListPanel) {
        this.mainFrame = mainFrame;
        this.orderListPanel = orderListPanel;
        this.categoryPanels = new HashMap<>();
        setFont(new Font("맑은 고딕", Font.PLAIN, 16));
        initializeSampleMenus();
    }

    private void initializeSampleMenus() {
        // 샘플 메뉴 카테고리 추가
        addMenuCategory("버거", createSampleBurgers());
        addMenuCategory("음료", createSampleDrinks());
        addMenuCategory("사이드", createSampleSides());
    }

    private JPanel createSampleBurgers() {
        JPanel panel = new JPanel(new GridLayout(0, 3, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        addMenuItem(panel, new Menu(1L, "치즈버거", new BigDecimal("5000")));
        addMenuItem(panel, new Menu(2L, "불고기버거", new BigDecimal("5500")));
        addMenuItem(panel, new Menu(3L, "더블버거", new BigDecimal("6500")));
        
        return panel;
    }

    private JPanel createSampleDrinks() {
        JPanel panel = new JPanel(new GridLayout(0, 3, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        addMenuItem(panel, new Menu(4L, "콜라", new BigDecimal("2000")));
        addMenuItem(panel, new Menu(5L, "사이다", new BigDecimal("2000")));
        addMenuItem(panel, new Menu(6L, "밀크쉐이크", new BigDecimal("3500")));
        
        return panel;
    }

    private JPanel createSampleSides() {
        JPanel panel = new JPanel(new GridLayout(0, 3, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        addMenuItem(panel, new Menu(7L, "감자튀김", new BigDecimal("2500")));
        addMenuItem(panel, new Menu(8L, "치즈스틱", new BigDecimal("3000")));
        addMenuItem(panel, new Menu(9L, "치킨너겟", new BigDecimal("3000")));
        
        return panel;
    }

    private void addMenuItem(JPanel panel, Menu menu) {
        JPanel menuItemPanel = new JPanel(new BorderLayout(0, 5));
        menuItemPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        menuItemPanel.setBackground(Color.WHITE);
        
        // 메뉴 이미지 (임시로 빈 패널로 대체)
        JPanel imagePanel = new JPanel();
        imagePanel.setPreferredSize(new Dimension(150, 150));
        imagePanel.setBackground(Color.WHITE);
        
        // 메뉴 정보
        JPanel infoPanel = new JPanel(new GridLayout(3, 1, 0, 5));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        JLabel nameLabel = new JLabel(menu.getName(), SwingConstants.CENTER);
        nameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        
        JLabel priceLabel = new JLabel(menu.getPrice() + "원", SwingConstants.CENTER);
        priceLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        
        // 수량 선택 패널
        JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        quantityPanel.setBackground(Color.WHITE);
        
        JButton minusButton = createQuantityButton("-");
        JLabel quantityLabel = new JLabel("0", SwingConstants.CENTER);
        quantityLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
        quantityLabel.setPreferredSize(new Dimension(30, 30));
        JButton plusButton = createQuantityButton("+");
        
        minusButton.addActionListener(e -> updateQuantity(menu, quantityLabel, false));
        plusButton.addActionListener(e -> updateQuantity(menu, quantityLabel, true));
        
        quantityPanel.add(minusButton);
        quantityPanel.add(quantityLabel);
        quantityPanel.add(plusButton);
        
        infoPanel.add(nameLabel);
        infoPanel.add(priceLabel);
        infoPanel.add(quantityPanel);
        
        menuItemPanel.add(imagePanel, BorderLayout.CENTER);
        menuItemPanel.add(infoPanel, BorderLayout.SOUTH);
        
        panel.add(menuItemPanel);
    }

    private JButton createQuantityButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
        button.setPreferredSize(new Dimension(30, 30));
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setFocusPainted(false);
        return button;
    }

    private void updateQuantity(Menu menu, JLabel quantityLabel, boolean increase) {
        int quantity = Integer.parseInt(quantityLabel.getText());
        if (increase) {
            quantity++;
        } else if (quantity > 0) {
            quantity--;
        }
        quantityLabel.setText(String.valueOf(quantity));
        addCartMenu(menu.getName(), quantity);
        
        // 주문 목록 업데이트
        orderListPanel.showOrderList(mainFrame.getOrderData().getMenuList());
    }

    public void addMenuCategory(String categoryName, JPanel menuPanel) {
        categoryPanels.put(categoryName, menuPanel);
        addTab(categoryName, menuPanel);
    }

    public void addCartMenu(String name, int amount) {
        mainFrame.getOrderData().setMenuList(name, amount);
    }

    public void setMenuList(String name, int amount) {
        addCartMenu(name, amount);
    }
} 