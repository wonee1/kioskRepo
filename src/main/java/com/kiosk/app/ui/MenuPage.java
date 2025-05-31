package com.kiosk.app.ui;

import javax.swing.*;
import java.awt.*;

public class MenuPage extends Page {
    private MenuTabbedPane menuTabbedPane;
    private OrderListPanel orderListPanel;

    public MenuPage(MainFrame mainFrame) {
        super(mainFrame);
        setLayout(new BorderLayout());
        initComponents();
    }

    private void initComponents() {
        // 상단 타이틀
        JLabel titleLabel = new JLabel("메뉴를 선택해주세요");
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titleLabel, BorderLayout.NORTH);

        // 메뉴 탭과 주문 목록을 담을 메인 패널
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setResizeWeight(0.7); // 메뉴 탭이 70% 차지

        // 주문 목록 패널 먼저 생성
        orderListPanel = new OrderListPanel(mainFrame);
        
        // 메뉴 탭 패널 (OrderListPanel 참조 전달)
        menuTabbedPane = new MenuTabbedPane(mainFrame, orderListPanel);

        splitPane.setLeftComponent(menuTabbedPane);
        splitPane.setRightComponent(orderListPanel);

        add(splitPane, BorderLayout.CENTER);
    }

    @Override
    public void display() {
        // 페이지가 표시될 때마다 주문 목록 업데이트
        showMenuList();
    }

    private void showMenuList() {
        orderListPanel.showOrderList(mainFrame.getOrderData().getMenuList());
    }

    public void setNextPage(String pageType) {
        navigateToPage(pageType);
    }
} 