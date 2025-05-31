package com.kiosk.app.model;

import java.math.BigDecimal;

public class Menu {
    private Long menuId;
    private Long menuCategoryId;
    private Long ingredientMenuId;
    private BigDecimal price;
    private String menuType;
    private String image;
    private String name;

    public Menu(Long menuId, String name, BigDecimal price) {
        this.menuId = menuId;
        this.name = name;
        this.price = price;
    }

    public MenuCategory getMenuCategory() {
        // TODO: 실제 구현에서는 menuCategoryId를 사용하여 MenuCategory를 조회해야 함
        return null;
    }

    public void updatePrice(BigDecimal newPrice) {
        this.price = newPrice;
    }

    public boolean isSetMenu() {
        return "SET".equals(menuType);
    }

    // Getters and Setters
    public Long getMenuId() { return menuId; }
    public void setMenuId(Long menuId) { this.menuId = menuId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
    
    public Long getMenuCategoryId() { return menuCategoryId; }
    public void setMenuCategoryId(Long menuCategoryId) { this.menuCategoryId = menuCategoryId; }
    
    public Long getIngredientMenuId() { return ingredientMenuId; }
    public void setIngredientMenuId(Long ingredientMenuId) { this.ingredientMenuId = ingredientMenuId; }
    
    public String getMenuType() { return menuType; }
    public void setMenuType(String menuType) { this.menuType = menuType; }
} 