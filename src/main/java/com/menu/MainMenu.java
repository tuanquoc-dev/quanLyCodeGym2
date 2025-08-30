package com.menu;

import com.lib.Input;
import com.management.ProductManagerment;
import com.management.ProductTypeManagerment;
import com.model.Product;
import com.model.ProductType;

import java.util.List;

public class MainMenu {
    private final ProductManagerment productManagerment;
    private final ProductTypeManagerment productTypeManagerment;

    public MainMenu() {
        this.productTypeManagerment = new ProductTypeManagerment();
        this.productManagerment = new ProductManagerment(productTypeManagerment);
    }

    // ======================== MAIN MENU ========================
    public void showMainMenu() {
        int choice;
        do {
            UI.printBox("🌟 MAIN MENU 🌟", new String[]{
                    "1. 📦 Quản lý sản phẩm",
                    "2. 🏷️ Quản lý loại sản phẩm",
                    "3. 🔎 Tìm kiếm sản phẩm",
                    "0. ❌ Thoát"
            });
            System.out.print("👉 Nhập lựa chọn của bạn: ");
            choice = Input.inputInt();
            switch (choice) {
                case 1 -> showProductMenu();
                case 2 -> showProductTypeMenu();
                case 3 -> showSearchProductMenu();
                case 0 -> System.out.println("👋 Tạm biệt!");
                default -> UI.printError("Không có lựa chọn này!");
            }
        } while (choice != 0);
    }

    // =================== PRODUCT TYPE MENU =====================
    public void showProductTypeMenu() {
        int choice;
        do {
            UI.printBox("🏷️ PRODUCT TYPE MENU", new String[]{
                    "1. ➕ Thêm loại sản phẩm",
                    "2. 📋 Hiển thị tất cả loại sản phẩm",
                    "3. 🔍 Chi tiết loại sản phẩm",
                    "4. ❌ Xóa loại sản phẩm",
                    "5. ✏️ Sửa loại sản phẩm",
                    "0. 🔙 Quay lại"
            });
            System.out.print("👉 Nhập lựa chọn của bạn: ");
            choice = Input.inputInt();
            switch (choice) {
                case 1 -> showFormAddProductType();
                case 2 -> showAllProductType();
                case 3 -> showDetailProductType();
                case 4 -> showDeleteProductType();
                case 5 -> showUpdateProductType();
                case 0 -> {}
                default -> UI.printError("Không có lựa chọn này!");
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
            System.out.println(productType);
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
            ProductType productType = productTypeManagerment.findById(id);
            if (productType == null) {
                System.out.println("❌ Không tìm thấy loại sản phẩm với Id: " + id);
                return;
            }
            List<Product> products = productManagerment.findAll();
            boolean inUse = products.stream().anyMatch(p -> p.getProductTypeId().equals(id));
            if (inUse) {
                System.out.println("⚠️ Không thể xóa. Vẫn còn sản phẩm tham chiếu đến loại này.");
                return;
            }
            System.out.printf("Bạn có chắc chắn muốn xoá loại sản phẩm '%s' (Id: %d)? (y/n): ",
                    productType.getName(), productType.getId());
            String confirm = Input.inputString();
            if (confirm.equalsIgnoreCase("y")) {
                productTypeManagerment.delete(id);
                System.out.println("✅ Xóa thành công!");
            } else {
                System.out.println("❌ Hủy xoá.");
            }
        } catch (Exception e) {
            System.out.println("❌ Lỗi: " + e.getMessage());
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
            ProductType newProductType = new ProductType(id, name, description);
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
            UI.printBox("📦 PRODUCT MENU", new String[]{
                    "1. ➕ Thêm sản phẩm",
                    "2. 📋 Hiển thị tất cả sản phẩm",
                    "3. 🔍 Chi tiết sản phẩm",
                    "4. ❌ Xóa sản phẩm",
                    "5. ✏️ Sửa sản phẩm",
                    "6. 🔎 Tìm kiếm nâng cao",
                    "0. 🔙 Quay lại"
            });
            System.out.print("👉 Nhập lựa chọn của bạn: ");
            choice = Input.inputInt();
            switch (choice) {
                case 1 -> showFormAddProduct();
                case 2 -> showAllProduct();
                case 3 -> showDetailProduct();
                case 4 -> showDeleteProduct();
                case 5 -> showUpdateProduct();
                case 6 -> showSearchProductMenu();
                case 0 -> {}
                default -> UI.printError("Không có lựa chọn này!");
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
                    + " | Price: " + product.getPrice()
                    + " | Quantity: " + product.getQuantity()
                    + " | Type: " + productTypeName);
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
        System.out.print("3. Số lượng: ");
        int quantity = Input.inputInt();

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
        Long typeId = (long) Input.inputInt();

        try {
            Product product = new Product(null, name, price, quantity, typeId);
            productManagerment.add(product);
            System.out.println("✅ Thêm sản phẩm thành công");
        } catch (Exception e) {
            System.out.println("❌ Không thể thêm sản phẩm: " + e.getMessage());
        }
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
                    + " | Quantity: " + product.getQuantity()
                    + " | Type: " + productTypeName);
        } catch (Exception e) {
            System.out.println("❌ Không tìm thấy sản phẩm!");
        }
    }

    public void showDeleteProduct() {
        System.out.println("----------- Xóa sản phẩm -----------");
        System.out.print("Nhập Id sản phẩm cần xoá: ");
        Long id = (long) Input.inputInt();
        try {
            Product product = productManagerment.findById(id);
            if (product == null) {
                System.out.println("❌ Không tìm thấy sản phẩm với Id: " + id);
                return;
            }
            System.out.printf("Bạn có chắc chắn muốn xoá sản phẩm '%s' (Id: %d)? (y/n): ",
                    product.getName(), product.getId());
            String confirm = Input.inputString();
            if (confirm.equalsIgnoreCase("y")) {
                productManagerment.delete(id);
                System.out.println("✅ Xóa thành công!");
            } else {
                System.out.println("❌ Hủy xoá.");
            }
        } catch (Exception e) {
            System.out.println("❌ Lỗi: " + e.getMessage());
        }
    }

    public void showUpdateProduct() {
        System.out.println("----------- Sửa sản phẩm -----------");
        System.out.print("Nhập Id sản phẩm cần sửa: ");
        Long id = (long) Input.inputInt();
        try {
            Product oldProduct = productManagerment.findById(id);
            if (oldProduct == null) {
                System.out.println("❌ Không tìm thấy sản phẩm với Id: " + id);
                return;
            }
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
                    System.out.println("⚠️ Sai định dạng giá. Giữ giá cũ.");
                    price = oldProduct.getPrice();
                }
            }
            System.out.print("Số lượng mới (Enter để giữ [" + oldProduct.getQuantity() + "]): ");
            String quantityStr = Input.inputString();
            int quantity;
            if (quantityStr.trim().isEmpty()) {
                quantity = oldProduct.getQuantity();
            } else {
                try {
                    quantity = Integer.parseInt(quantityStr);
                } catch (NumberFormatException ex) {
                    System.out.println("⚠️ Sai định dạng số lượng. Giữ cũ.");
                    quantity = oldProduct.getQuantity();
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
            Long typeId;
            if (typeIdStr.trim().isEmpty()) {
                typeId = oldProduct.getProductTypeId();
            } else {
                try {
                    typeId = Long.parseLong(typeIdStr);
                    ProductType pt = productTypeManagerment.findById(typeId);
                    if (pt == null) {
                        System.out.println("⚠️ Id loại không tồn tại. Giữ loại cũ.");
                        typeId = oldProduct.getProductTypeId();
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("⚠️ Sai định dạng Id loại. Giữ loại cũ.");
                    typeId = oldProduct.getProductTypeId();
                }
            }
            Product newProduct = new Product(id, name, price, quantity, typeId);
            productManagerment.update(id, newProduct);
            System.out.println("✅ Cập nhật sản phẩm thành công!");
        } catch (Exception e) {
            System.out.println("❌ Lỗi: " + e.getMessage());
        }
    }

    // ======================= SEARCH MENU ======================
    public void showSearchProductMenu() {
        int choice;
        do {
            UI.printBox("🔎 SEARCH MENU", new String[]{
                    "1. 🔤 Tìm theo tên",
                    "2. 💲 Tìm theo khoảng giá",
                    "3. 🏷️ Tìm theo loại sản phẩm",
                    "0. 🔙 Quay lại"
            });
            System.out.print("👉 Nhập lựa chọn của bạn: ");
            choice = Input.inputInt();
            switch (choice) {
                case 1 -> {
                    System.out.print("Nhập từ khóa tên sản phẩm: ");
                    String keyword = Input.inputString();
                    List<Product> results = productManagerment.searchByName(keyword);
                    showProducts(results);
                }
                case 2 -> {
                    System.out.print("Nhập giá min: ");
                    double min = Input.inputDouble();
                    System.out.print("Nhập giá max: ");
                    double max = Input.inputDouble();
                    List<Product> results = productManagerment.searchByPriceRange(min, max);
                    showProducts(results);
                }
                case 3 -> {
                    System.out.print("Nhập Id loại sản phẩm: ");
                    Long typeId = (long) Input.inputInt();
                    List<Product> results = productManagerment.findByProductType(typeId);
                    showProducts(results);
                }
                case 0 -> {}
                default -> UI.printError("Không có lựa chọn này!");
            }
        } while (choice != 0);
    }

    private void showProducts(List<Product> list) {
        if (list.isEmpty()) {
            System.out.println("⚠️ Không tìm thấy sản phẩm nào.");
            return;
        }
        for (Product product : list) {
            ProductType productType = getProductTypeById(product.getProductTypeId());
            String productTypeName = (productType != null) ? productType.getName() : "N/A";
            System.out.println("Id: " + product.getId()
                    + " | Name: " + product.getName()
                    + " | Price: " + product.getPrice()
                    + " | Quantity: " + product.getQuantity()
                    + " | Type: " + productTypeName);
        }
    }
}
