package com.model;

public class ProductType {
    private Long id;
    private String name;
    private String description;
    private static Long countId = 100L;

    public ProductType(String name, String description) {
        this.id = countId;
        this.description = description;
        this.name = name;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static Long getCountId() {
        return countId;
    }

    public static void setCountId(Long countId) {
        ProductType.countId = countId;
    }

    public String toString() {
        return "Id: " + id + " - " + "Name: " + name;
    }
}
