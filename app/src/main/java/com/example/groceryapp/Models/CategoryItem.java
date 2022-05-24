package com.example.groceryapp.Models;

public class CategoryItem {
    private String description;
    private String discount;
    private String name;
    private String img_url;
    private String type;

    public CategoryItem() {

    }

    public CategoryItem(String description, String discount, String name, String img_url, String type) {
        this.description = description;
        this.discount = discount;
        this.name = name;
        this.img_url = img_url;
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
