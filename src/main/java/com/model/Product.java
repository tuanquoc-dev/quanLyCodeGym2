package com.model;

public class Product {
    private Long id;
    private String name;
    private double price;
    private Long productTypeId;
    private static  Long countId = 100L;

    public Product( String name, double price, Long productTypeId) {
        this.id = countId;
        this.name = name;
        this.price = price;
        this.productTypeId = productTypeId;
        countId++;
    }

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

    public Long getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Long productTypeId) {
        this.productTypeId = productTypeId;
    }

    public static Long getCountId() {
        return countId;
    }

    public static void setCountId(Long countId) {
        Product.countId = countId;
    }

}
