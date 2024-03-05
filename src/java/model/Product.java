/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author admin
 */
public class Product {

    private int productId;
    private String name;
    private String description;
    private String imageLink;
    private String category;
    private int categoryId;
    private float price;
    private int stock;

    public Product() {}

    public Product(int productId, String name, String description, String imageLink, String category, float price, int stock) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.imageLink = imageLink;
        this.category = category;
        this.price = price;
        this.stock = stock;
    }
    
    public Product(String name, String description, String imageLink, int categoryId, float price, int stock) {
        this.name = name;
        this.description = description;
        this.imageLink = imageLink;
        this.categoryId = categoryId;
        this.price = price;
        this.stock = stock;
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageLink() {
        return imageLink;
    }

    public String getCategory() {
        return category;
    }

    public float getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    public void modifyStock(int change) {
        this.stock += change;
        if (this.stock < 0) this.stock = 0;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    
    
}
