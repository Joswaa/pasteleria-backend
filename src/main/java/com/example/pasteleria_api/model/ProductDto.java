package com.example.pasteleria_api.model;

public class ProductDto {
    private int id;
    private String name;
    private String description;
    private double price;
    private String category;
    private String imageUrl;
    public ProductDto(int id, String name, String description, double price, String category, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.imageUrl = imageUrl;
    }

    public int getId() {return id;}
    public String getName() {return name;}
    public String getDescription() {return description;}
    public double getPrice() {return price;}
    public String getCategory() {return category;}

    public String getImageUrl() {return imageUrl;}
}
