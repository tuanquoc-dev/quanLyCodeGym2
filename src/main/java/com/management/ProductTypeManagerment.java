package com.management;

import com.repository.ProductTypeRepository;
import com.model.ProductType;

import java.util.ArrayList;
import java.util.List;

public class ProductTypeManagerment implements IManagerment<ProductType> {
    private List<ProductType> list;
    private ProductTypeRepository productTypeDatabase;
    private Long currentId = 1L; // Auto-increment ID

    public ProductTypeManagerment() {
        this.productTypeDatabase = new ProductTypeRepository();
        this.list = productTypeDatabase.readData();

        if (this.list == null) {
            this.list = new ArrayList<>();
        }

        // Xác định currentId = maxId + 1
        Long maxId = 0L;
        for (ProductType pt : this.list) {
            if (pt.getId() != null && pt.getId() > maxId) {
                maxId = pt.getId();
            }
        }
        this.currentId = maxId + 1;
    }

    @Override
    public void add(ProductType productType) {
        // Auto-increment ID
        productType.setId(currentId++);
        this.list.add(productType);
        productTypeDatabase.writeData(this.list);
    }

    @Override
    public void delete(Long id) throws Exception {
        int index = this.findIndexById(id);
        this.list.remove(index);
        productTypeDatabase.writeData(this.list);
    }

    @Override
    public void update(Long id, ProductType newProductType) throws Exception {
        int index = this.findIndexById(id);
        ProductType old = this.list.get(index);

        // Chỉ cập nhật name & description
        old.setName(newProductType.getName());
        old.setDescription(newProductType.getDescription());

        productTypeDatabase.writeData(this.list);
    }

    @Override
    public ProductType findById(Long id) throws Exception {
        int index = this.findIndexById(id);
        return this.list.get(index);
    }

    @Override
    public List<ProductType> findAll() {
        return this.list;
    }

    @Override
    public int findIndexById(Long id) throws Exception {
        for (int i = 0; i < this.list.size(); i++) {
            if (this.list.get(i).getId().equals(id)) {
                return i;
            }
        }
        throw new Exception("ProductType with id " + id + " not found");
    }
}
