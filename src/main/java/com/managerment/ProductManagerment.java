package com.managerment;

import com.model.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductManagerment implements IManagerment<Product> {
    private List<Product> list;

    public ProductManagerment() {
        list = new ArrayList<>();
    }


    @Override
    public void add(Product product) {
        this.list.add(product);
    }

    @Override
    public void delete(Long id) throws Exception {
        int index = this.findIndexById(id);
        this.list.remove(index);
    }

    @Override
    public void update(Long id, Product newProduct) throws Exception {
        int index = this.findIndexById(id);
        Product oldProduct = this.list.get(index);
        oldProduct.setId(newProduct.getId());
        this.list.set(index, newProduct);
    }

    @Override
    public Product findById(Long id)  throws Exception {
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
        throw new Exception("Data not found");
    }
}
