package com.kiosk.app.model;

import com.kiosk.app.enums.DiningSpot;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KioskOrderData {
    private Map<String, Integer> menuList;
    private List<Menu> orderList;
    private DiningSpot diningSpot;

    public KioskOrderData() {
        menuList = new HashMap<>();
        orderList = new ArrayList<>();
    }

    public void setMenuList(String name, int amount) {
        if (amount > 0) {
            menuList.put(name, amount);
        } else {
            menuList.remove(name);
        }
    }

    public void setOrderList(List<Menu> menuList) {
        this.orderList = new ArrayList<>(menuList);
    }

    public Map<String, Integer> getMenuList() {
        return new HashMap<>(menuList);
    }

    public List<Menu> getOrderList() {
        return new ArrayList<>(orderList);
    }

    public void clearOrder() {
        menuList.clear();
        orderList.clear();
        diningSpot = null;
    }

    public void setDiningSpot(DiningSpot diningSpot) {
        this.diningSpot = diningSpot;
    }

    public DiningSpot getDiningSpot() {
        return diningSpot;
    }
} 