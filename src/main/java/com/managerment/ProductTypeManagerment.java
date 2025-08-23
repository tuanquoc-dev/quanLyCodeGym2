package com.managerment;
import com.model.ProductType;

import java.util.ArrayList;
import java.util.List;

public class ProductTypeManagerment implements IManagerment<ProductType> {
    private List<ProductType> list;

    public ProductTypeManagerment() {
        list = new ArrayList<>();
    }


    @Override
    public void add(ProductType productType) {
        this.list.add(productType);
    }

    @Override
    public void delete(Long id) throws Exception {
        int index = this.findIndexById(id);
        this.list.remove(index);
    }

    @Override
    public void update(Long id, ProductType newProductType) throws Exception {
        int index = this.findIndexById(id);
        ProductType oldProductType = this.list.get(index);
        oldProductType.setId(newProductType.getId());
        this.list.set(index, newProductType);
    }

    @Override
    public ProductType findById(Long id)  throws Exception {
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
        throw new Exception("Data not found");
    }
}
