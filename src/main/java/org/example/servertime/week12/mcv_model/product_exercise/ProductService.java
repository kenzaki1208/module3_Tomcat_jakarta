package org.example.servertime.week12.mcv_model.product_exercise;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(int id);
    void save(Product product);
    void update(int id, Product product);
    void remove(int id);
    List<Product> searchByName(String name);
}
