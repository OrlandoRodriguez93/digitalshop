package com.example.store.product.service;

import com.example.store.product.entity.Category;
import com.example.store.product.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    public List<Product> listAll();
    public Product get(Long id);
    public Product create(Product product);
    public Product update(Product product);
    public Product delete(Long id);
    public List<Product> findByCategory(Category category);
    public Product updateStock(Long id, Double quantity);
}
