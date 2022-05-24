package com.example.groceryapp.Models;

public class CategoryItemDetail {

    private String name;
    private String type;
    private String img_url;
    private int price;

    public CategoryItemDetail() {
    }

    public CategoryItemDetail(String name, String type, String img_url, int price) {
        this.name = name;
        this.type = type;
        this.img_url = img_url;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
