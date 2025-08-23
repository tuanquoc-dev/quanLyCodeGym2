package com.menu;

import com.lib.Input;
import com.managerment.ProductManagerment;
import com.managerment.ProductTypeManagerment;
import com.model.Product;
import com.model.ProductType;

import java.util.List;

public class MainMenu {
    private final ProductManagerment productManagerment;
    private final ProductTypeManagerment productTypeManagerment;

    public MainMenu() {
        this.productManagerment = new ProductManagerment();
        this.productTypeManagerment = new ProductTypeManagerment();
    }

    // ======================== MAIN MENU ========================
    public void showMainMenu() {
        int choice;
        do {
            System.out.println("----------- Menu -----------");
            System.out.println("1. Quản lý sản phẩm");
            System.out.println("2. Quản lý loại sản phẩm");
            System.out.println("0. Thoát");
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = Input.inputInt();
            switch (choice) {
                case 1:
                    showProductMenu();
                    break;
                case 2:
                    showProductTypeMenu();
                    break;
                case 0:
                    System.out.println("Tạm biệt!");
                    break;
                default:
                    System.out.println("❌ Không có lựa chọn này");
            }
        } while (choice != 0);
    }

    // =================== PRODUCT TYPE MENU =====================
    public void showProductTypeMenu() {
        int choice;
        do {
            System.out.println("------ Product Type Menu ------");
            System.out.println("1. Thêm loại sản phẩm");
            System.out.println("2. Hiển thị tất cả loại sản phẩm");
            System.out.println("3. Chi tiết loại sản phẩm");
            System.out.println("4. Xóa loại sản phẩm");
            System.out.println("5. Sửa loại sản phẩm");
            System.out.println("0. Quay lại");
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = Input.inputInt();
            switch (choice) {
                case 1:
                    showFormAddProductType();
                    break;
                case 2:
                    showAllProductType();
                    break;
                case 3:
                    showDetailProductType();
                    break;
                case 4:
                    showDeleteProductType();
                    break;
                case 5:
                    showUpdateProductType();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("❌ Không có lựa chọn này");
            }
        } while (choice != 0);
    }

    public void showAllProductType() {
        System.out.println("------ Danh sách loại sản phẩm ------");
        List<ProductType> list = this.productTypeManagerment.findAll();
        if (list.isEmpty()) {
            System.out.println("(Trống)");
            return;
        }
        for (ProductType productType : list) {
            System.out.println(productType); // toString() đã in Id & Name
        }
    }

    public void showFormAddProductType() {
        System.out.println("------ Thêm loại sản phẩm ------");
        System.out.print("1. Tên loại: ");
        String name = Input.inputString();
        System.out.print("2. Mô tả: ");
        String description = Input.inputString();
        ProductType productType = new ProductType(name, description);
        this.productTypeManagerment.add(productType);
        System.out.println("✅ Thêm loại sản phẩm thành công");
    }

    public void showDeleteProductType() {
        System.out.println("------ Xóa loại sản phẩm ------");
        System.out.print("Nhập Id loại sản phẩm cần xoá: ");
        Long id = (long) Input.inputInt();
        try {
            productTypeManagerment.delete(id);
            System.out.println("✅ Xóa thành công!");
        } catch (Exception e) {
            System.out.println("❌ Không tìm thấy loại sản phẩm với Id: " + id);
        }
    }

    public void showDetailProductType() {
        System.out.println("------ Chi tiết loại sản phẩm ------");
        System.out.print("Nhập Id loại sản phẩm: ");
        Long id = (long) Input.inputInt();
        try {
            ProductType productType = productTypeManagerment.findById(id);
            System.out.println("Id: " + productType.getId()
                    + " - Name: " + productType.getName()
                    + " - Description: " + productType.getDescription());
        } catch (Exception e) {
            System.out.println("❌ Không tìm thấy loại sản phẩm!");
        }
    }

    public void showUpdateProductType() {
        System.out.println("------ Sửa loại sản phẩm ------");
        System.out.print("Nhập Id loại sản phẩm cần sửa: ");
        Long id = (long) Input.inputInt();
        try {
            ProductType oldProductType = productTypeManagerment.findById(id);

            System.out.print("Tên loại mới (Enter để giữ [" + oldProductType.getName() + "]): ");
            String name = Input.inputString();
            if (name.trim().isEmpty()) name = oldProductType.getName();

            System.out.print("Mô tả mới (Enter để giữ [" + oldProductType.getDescription() + "]): ");
            String description = Input.inputString();
            if (description.trim().isEmpty()) description = oldProductType.getDescription();

            ProductType newProductType = new ProductType(name, description);
            // manager sẽ copy các trường (không đổi id)
            productTypeManagerment.update(id, newProductType);

            System.out.println("✅ Cập nhật loại sản phẩm thành công!");
        } catch (Exception e) {
            System.out.println("❌ Không tìm thấy loại sản phẩm!");
        }
    }


    // ======================= PRODUCT MENU ======================
    public void showProductMenu() {
        int choice;
        do {
            System.out.println("----------- Product Menu -----------");
            System.out.println("1. Thêm sản phẩm");
            System.out.println("2. Hiển thị tất cả sản phẩm");
            System.out.println("3. Chi tiết sản phẩm");
            System.out.println("4. Xóa sản phẩm");
            System.out.println("5. Sửa sản phẩm");
            System.out.println("0. Quay lại");
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = Input.inputInt();
            switch (choice) {
                case 1:
                    showFormAddProduct();
                    break;
                case 2:
                    showAllProduct();
                    break;
                case 3:
                    showDetailProduct();
                    break;
                case 4:
                    showDeleteProduct();
                    break;
                case 5:
                    showUpdateProduct();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("❌ Không có lựa chọn này");
            }
        } while (choice != 0);
    }

    public void showAllProduct() {
        System.out.println("----------- Danh sách sản phẩm -----------");
        List<Product> list = this.productManagerment.findAll();
        if (list.isEmpty()) {
            System.out.println("(Trống)");
            return;
        }
        for (Product product : list) {
            ProductType productType = getProductTypeById(product.getProductTypeId());
            String productTypeName = (productType != null) ? productType.getName() : "N/A";
            System.out.println("Id: " + product.getId()
                    + " | Name: " + product.getName()
                    + " | Price: " + product.getPrice());
        }
    }

    private ProductType getProductTypeById(Long id) {
        try {
            return this.productTypeManagerment.findById(id);
        } catch (Exception e) {
            return null;
        }
    }

    public void showFormAddProduct() {
        System.out.println("----------- Thêm sản phẩm -----------");
        System.out.print("1. Tên sản phẩm: ");
        String name = Input.inputString();
        System.out.print("2. Giá sản phẩm: ");
        double price = Input.inputDouble();

        List<ProductType> list = this.productTypeManagerment.findAll();
        if (list.isEmpty()) {
            System.out.println("⚠️ Chưa có loại sản phẩm. Hãy tạo loại trước.");
            return;
        }

        System.out.println("Danh sách loại sản phẩm:");
        for (ProductType productType : list) {
            System.out.println(productType);
        }
        System.out.print("Nhập Id loại sản phẩm: ");
        Long productTypeId = (long) Input.inputInt();

        Product product = new Product(name, price, productTypeId);
        productManagerment.add(product);
        System.out.println("✅ Thêm sản phẩm thành công");
    }

    public void showDetailProduct() {
        System.out.println("----------- Chi tiết sản phẩm -----------");
        System.out.print("Nhập Id sản phẩm: ");
        Long id = (long) Input.inputInt();
        try {
            Product product = productManagerment.findById(id);
            ProductType productType = getProductTypeById(product.getProductTypeId());
            String productTypeName = (productType != null) ? productType.getName() : "N/A";
            System.out.println("Id: " + product.getId()
                    + " | Name: " + product.getName()
                    + " | Price: " + product.getPrice()
                    + " | ProductType: " + productTypeName);
        } catch (Exception e) {
            System.out.println("❌ Không tìm thấy sản phẩm!");
        }
    }

    public void showDeleteProduct() {
        System.out.println("----------- Xóa sản phẩm -----------");
        System.out.print("Nhập Id sản phẩm cần xoá: ");
        Long id = (long) Input.inputInt();
        try {
            productManagerment.delete(id);
            System.out.println("✅ Xóa thành công!");
        } catch (Exception e) {
            System.out.println("❌ Không tìm thấy sản phẩm với Id: " + id);
        }
    }

    public void showUpdateProduct() {
        System.out.println("----------- Sửa sản phẩm -----------");
        System.out.print("Nhập Id sản phẩm cần sửa: ");
        Long id = (long) Input.inputInt();
        try {
            Product oldProduct = productManagerment.findById(id);

            System.out.print("Tên sản phẩm mới (Enter để giữ [" + oldProduct.getName() + "]): ");
            String name = Input.inputString();
            if (name.trim().isEmpty()) name = oldProduct.getName();

            System.out.print("Giá mới (Enter để giữ [" + oldProduct.getPrice() + "]): ");
            String priceStr = Input.inputString();
            double price;
            if (priceStr.trim().isEmpty()) {
                price = oldProduct.getPrice();
            } else {
                try {
                    price = Double.parseDouble(priceStr);
                } catch (NumberFormatException ex) {
                    System.out.println("Sai định dạng giá. Giữ giá cũ.");
                    price = oldProduct.getPrice();
                }
            }

            List<ProductType> list = productTypeManagerment.findAll();
            if (list.isEmpty()) {
                System.out.println("⚠️ Chưa có loại sản phẩm. Hãy tạo loại trước.");
                return;
            }

            System.out.println("Danh sách loại sản phẩm:");
            for (ProductType productType : list) {
                System.out.println(productType);
            }
            System.out.print("Nhập Id loại sản phẩm mới (Enter để giữ [" + oldProduct.getProductTypeId() + "]): ");
            String typeIdStr = Input.inputString();
            Long productTypeId;
            if (typeIdStr.trim().isEmpty()) {
                productTypeId = oldProduct.getProductTypeId();
            } else {
                try {
                    productTypeId = Long.valueOf(Long.parseLong(typeIdStr));
                } catch (NumberFormatException ex) {
                    System.out.println("Sai định dạng Id loại. Giữ loại cũ.");
                    productTypeId = oldProduct.getProductTypeId();
                }
            }

            Product newProduct = new Product(name, price, productTypeId);
            productManagerment.update(id, newProduct);

            System.out.println("✅ Cập nhật sản phẩm thành công!");
        } catch (Exception e) {
            System.out.println("❌ Không tìm thấy sản phẩm!");
        }
    }

}