package com.model;

public class ProductType {
    private Long id;
    private String name;
    private String description;

    public ProductType(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    // 👉 Constructor không có id (sẽ được gán khi add trong Managerment)
    public ProductType(String name, String description) {
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ProductType {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
