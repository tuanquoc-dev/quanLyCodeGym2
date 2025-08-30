package com.management;

import com.repository.ProductDatabase;
import com.model.Product;
import com.model.ProductType;

import java.util.ArrayList;
import java.util.List;

public class ProductManagerment implements IManagerment<Product> {
    private List<Product> list;
    private ProductTypeManagerment productTypeManagerment;
    private ProductDatabase productDatabase;

    public ProductManagerment(ProductTypeManagerment productTypeManagerment) {
        this.productTypeManagerment = productTypeManagerment;
        this.productDatabase = new ProductDatabase();
        this.list = new ArrayList<>();

        // ✅ Load dữ liệu từ file khi khởi tạo
        List<Product> data = productDatabase.readData();
        if (data != null) {
            this.list = data;
        }
    }

    // 🔎 Tìm kiếm theo tên
    public List<Product> searchByName(String keyword) {
        List<Product> result = new ArrayList<>();
        for (Product p : list) {
            if (p.getName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(p);
            }
        }
        return result;
    }

    // 🔎 Tìm kiếm theo khoảng giá
    public List<Product> searchByPriceRange(double min, double max) {
        List<Product> result = new ArrayList<>();
        for (Product p : list) {
            if (p.getPrice() >= min && p.getPrice() <= max) {
                result.add(p);
            }
        }
        return result;
    }

    // 🔎 Lọc theo loại sản phẩm
    public List<Product> findByProductType(Long productTypeId) {
        List<Product> result = new ArrayList<>();
        for (Product p : list) {
            if (p.getProductTypeId().equals(productTypeId)) {
                result.add(p);
            }
        }
        return result;
    }

    @Override
    public void add(Product product) throws Exception {
        // ✅ Check ProductType tồn tại
        ProductType type = productTypeManagerment.findById(product.getProductTypeId());
        if (type == null) {
            throw new Exception("❌ ProductType ID " + product.getProductTypeId() + " không tồn tại");
        }

        // ✅ Auto-increment ID
        long newId = list.isEmpty() ? 1 : list.get(list.size() - 1).getId() + 1;
        product.setId(newId);

        this.list.add(product);
        productDatabase.writeData(this.list); // ✅ ghi lại file
    }

    @Override
    public void delete(Long id) throws Exception {
        int index = this.findIndexById(id);
        this.list.remove(index);
        productDatabase.writeData(this.list); // ✅ ghi lại file
    }

    @Override
    public void update(Long id, Product newProduct) throws Exception {
        int index = this.findIndexById(id);
        Product oldProduct = this.list.get(index);

        // ✅ Check ProductType tồn tại
        ProductType type = productTypeManagerment.findById(newProduct.getProductTypeId());
        if (type == null) {
            throw new Exception("❌ ProductType ID " + newProduct.getProductTypeId() + " không tồn tại");
        }

        // ✅ Cập nhật dữ liệu
        oldProduct.setName(newProduct.getName());
        oldProduct.setPrice(newProduct.getPrice());
        oldProduct.setQuantity(newProduct.getQuantity());
        oldProduct.setProductTypeId(newProduct.getProductTypeId());

        productDatabase.writeData(this.list);
    }

    @Override
    public Product findById(Long id) throws Exception {
        int index = this.findIndexById(id);
        return this.list.get(index);
    }

    @Override
    public List<Product> findAll() {
        return this.list;
    }

    @Override
    public int findIndexById(Long id) throws Exception {
        for (int i = 0; i < this.list.size(); i++) {
            if (this.list.get(i).getId().equals(id)) {
                return i;
            }
        }
        throw new Exception("❌ Product ID " + id + " không tồn tại");
    }
}
