package com.model;

public class Product {
    private Long id;
    private String name;
    private double price;
    private int quantity;
    private Long typeId;

    public Product() {
    }

    public Product(Long id, String name, double price, int quantity, Long typeId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.typeId = typeId;
    }

    // Getter & Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getProductTypeId() {
        return typeId;
    }

    public void setProductTypeId(Long typeId) {
        this.typeId = typeId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", typeId=" + typeId +
                '}';
    }
}
