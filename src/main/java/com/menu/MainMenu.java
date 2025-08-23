package com.menu;

import com.lib.Input;
import com.managerment.ProductManagerment;
import com.managerment.ProductTypeManagerment;
import com.model.Product;
import com.model.ProductType;

import java.util.List;

public class MainMenu {
    private ProductManagerment productManagerment;
    private ProductTypeManagerment productTypeManagerment;


    public MainMenu() {
        this.productManagerment = new ProductManagerment();
        this.productTypeManagerment = new ProductTypeManagerment();
    }

    public void showMainMenu() {
        int choice;
        do {
            System.out.println("----------- Menu -----------");
            System.out.println("1. Quản lý sản phẩm");
            System.out.println("2. Quản lý loại sản phẩm");
            System.out.println("0. Thoát");
            System.out.println("Nhập lựa chọn của bạn: ");
            choice = Input.inputInt();
            switch (choice) {
                case 1:
                    showProductMenu();
                    break;
                case 2:
                    showProductTypeMenu();
                    break;
                default:
                    System.out.println("không có lựa chọn này");
            }
        } while (choice != 0);
    }

    //------------------------- Show ProductTypeManagerment ---------------------

    public void showProductTypeMenu() {
        int choice;
        do {
            System.out.println("-----------  Product Type Menu -----------");
            System.out.println("1. Thêm loại sản phẩm");
            System.out.println("2. Hiển thị tất cả sản phẩm");
            System.out.println("3. Chi tiết sản phẩm");
            System.out.println("4. Xóa loại sản phẩm");
            System.out.println("0. Thoát");
            System.out.println("Nhập lựa chọn của bạn: ");
            choice = Input.inputInt();
            switch (choice) {
                case 1:
                    showFormAddProductType();
                    break;
                case 2:
                    showAllProductType();
                    break;
            }
        } while (choice != 0);
    }

    public void showAllProductType() {
        System.out.println("----------- All Product Type Menu -----------");
        List<ProductType> list = this.productTypeManagerment.findAll();
        for (ProductType productType : list) {
            System.out.println(productType);
        }
    }

    public void showFormAddProductType() {
        System.out.println("----------- Add Product Type Menu -----------");
        System.out.println("1. Tên sản phẩm: ");
        String name = Input.inputString();
        System.out.println("2. Nhập mô tả: ");
        String description = Input.inputString();
        ProductType productType = new ProductType(name, description);
        this.productTypeManagerment.add(productType);
        System.out.println("Thêm thành công");
    }


    // ------------------------ Show ProductManagerment -------------------------
    public void showProductMenu() {
        int choice;
        do {
            System.out.println("-----------  Product Menu -----------");
            System.out.println("1. Tên sản phẩm");
            System.out.println("2. Hiển thị tất cả sản phẩm");
            System.out.println("3. Chi tiết sản phẩm");
            System.out.println("4. Xóa sản phẩm");
            System.out.println("0. Thoát");
            System.out.println("Nhập lựa chọn của bạn: ");
            choice = Input.inputInt();
            switch (choice) {
                case 1:
                    showFormAddProduct();
                    break;
                case 2:
                    showAllProduct();
                    break;
            }
        } while (choice != 0);
    }

    public void showAllProduct() {
        System.out.println("----------- List Product Menu -----------");
        List<Product> list = this.productManagerment.findAll();
        for (Product product : list) {
            ProductType productType = getProductTypeById(product.getProductTypeId());
            String productTypeName = "";
            if (productType != null) {
                productTypeName = productType.getName();
            }
            System.out.println("Id: " + product.getId() + " Name: " + product.getName() + " Price: " + product.getPrice() + " ProductType: " + productTypeName);
        }

    }

    private ProductType getProductTypeById(Long id) {
        try {
            ProductType productType = this.productTypeManagerment.findById(id);
            return productType;
        } catch (Exception e) {

        }
        return null;
    }

    public void showFormAddProduct() {
        System.out.println("----------- Add Product Menu -----------");
        System.out.println("1. Thêm sản phẩm: ");
        String name = Input.inputString();
        System.out.println("2. Giá sản phẩm: ");
        double price = Input.inputDouble();
        List<ProductType> list = this.productTypeManagerment.findAll();
        System.out.println("Danh sách ProductType");
        for (ProductType productType : list) {
            System.out.println(productType);
        }
        System.out.println("Nhập Id loại sản phẩm: ");
        Long productTypeId = (long) Input.inputInt();

        Product product = new Product(name, price, productTypeId);
        productManagerment.add(product);
        System.out.println("Thêm thành công");
    }
}
