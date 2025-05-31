package com.kiosk.app.model;

import com.kiosk.app.enums.DiningSpot;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    private Long orderId;
    private Long menuStackId;
    private DiningSpot diningSpot;
    private Date createdAt;
    private Date updatedAt;
    private List<Menu> menuList;

    public Order() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
        this.menuList = new ArrayList<>();
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = new ArrayList<>(menuList);
        this.updatedAt = new Date();
    }

    public void setOrderList(List<Menu> menuList) {
        setMenuList(menuList);
    }

    public void setDiningSpot(DiningSpot spot) {
        this.diningSpot = spot;
        this.updatedAt = new Date();
    }

    public void cancelOrder() {
        this.menuList.clear();
        this.updatedAt = new Date();
    }

    // Getters and Setters
    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }

    public Long getMenuStackId() { return menuStackId; }
    public void setMenuStackId(Long menuStackId) { this.menuStackId = menuStackId; }

    public DiningSpot getDiningSpot() { return diningSpot; }

    public Date getCreatedAt() { return createdAt; }
    public Date getUpdatedAt() { return updatedAt; }

    public List<Menu> getMenuList() { return new ArrayList<>(menuList); }
} 