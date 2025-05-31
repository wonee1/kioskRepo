package com.kiosk.app.model;

import java.util.Date;

public class MenuCategory {
    private Long menuCategoryId;
    private String name;
    private String description;
    private Date createdAt;

    public MenuCategory(Long menuCategoryId, String name) {
        this.menuCategoryId = menuCategoryId;
        this.name = name;
        this.createdAt = new Date();
    }

    // Getters and Setters
    public Long getMenuCategoryId() {
        return menuCategoryId;
    }

    public void setMenuCategoryId(Long menuCategoryId) {
        this.menuCategoryId = menuCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
} 