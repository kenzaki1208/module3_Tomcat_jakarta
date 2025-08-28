package org.example.servertime.week12.mcv_model.product_exercise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductServiceImpl implements ProductService{
    private static Map<Integer, Product> products;

    static {
        products = new HashMap<>();
        products.put(1, new Product(1, "Laptop", 1000000, "Laptop is a computer that is used for work", "Apple"));
        products.put(2, new Product(2, "Tablet", 500000, "Tablet is a computer that is used for personal", "Samsung"));
        products.put(3, new Product(3, "Phone", 200000, "Phone is a mobile phone", "Huawei"));
        products.put(4, new Product(4, "Watch", 50000, "Watch is a mobile watch", "Apple"));
        products.put(5, new Product(5, "Headphone", 10000, "Headphone is a mobile headphone", "Samsung"));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public Product findById(int id) {
        return products.get(id);
    }

    @Override
    public void save(Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public void update(int id, Product product) {
        products.put(id, product);
    }

    @Override
    public void remove(int id) {
        products.remove(id);
    }

    @Override
    public List<Product> searchByName(String name) {
        List<Product> result = new ArrayList<>();
        for (Product product : products.values()) {
            if (product.getName().contains(name)) {
                result.add(product);
            }
        }
        return result;
    }
}
