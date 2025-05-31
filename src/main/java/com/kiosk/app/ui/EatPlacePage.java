package com.kiosk.app.ui;

import javax.swing.*;
import java.awt.*;
import com.kiosk.app.enums.DiningSpot;

public class EatPlacePage extends Page {
    private JLabel titleLabel;
    private JPanel buttonPanel;

    public EatPlacePage(MainFrame mainFrame) {
        super(mainFrame);
        setLayout(new BorderLayout(0, 50));
        initComponents();
    }

    private void initComponents() {
        // 타이틀 레이블
        titleLabel = new JLabel("식사 장소를 선택해 주세요");
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 36));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // 버튼 패널
        buttonPanel = new JPanel(new GridLayout(1, 2, 50, 0));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));

        // 매장식사 버튼
        JButton dineInButton = createPlaceButton("매장에서 식사", "store.png", DiningSpot.DINE_IN);
        buttonPanel.add(dineInButton);

        // 포장주문 버튼
        JButton takeOutButton = createPlaceButton("포장 주문", "takeout.png", DiningSpot.TAKE_OUT);
        buttonPanel.add(takeOutButton);

        // 버튼 패널을 중앙에 배치
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.add(buttonPanel);
        add(centerPanel, BorderLayout.CENTER);
    }

    private JButton createPlaceButton(String text, String iconPath, DiningSpot diningSpot) {
        JButton button = new JButton(text);
        button.setFont(new Font("맑은 고딕", Font.PLAIN, 24));
        button.setPreferredSize(new Dimension(300, 400));
        
        // TODO: 아이콘 추가
        // ImageIcon icon = new ImageIcon(getClass().getResource("/images/" + iconPath));
        // button.setIcon(icon);
        
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        
        button.addActionListener(e -> {
            setUserEatPlace(diningSpot);
            setNextPage("MENU");
        });
        
        return button;
    }

    @Override
    public void display() {
        titleLabel.setText("식사 장소를 선택해 주세요");
    }

    public void setUserEatPlace(DiningSpot diningSpot) {
        // TODO: 주문 데이터에 식사 장소 설정
        mainFrame.getOrderData().setDiningSpot(diningSpot);
    }

    public void setNextPage(String pageType) {
        navigateToPage(pageType);
    }
} 