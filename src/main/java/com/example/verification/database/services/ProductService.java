package com.example.verification.database.services;

import org.springframework.stereotype.Service;

import com.example.verification.database.model.Product;

@Service
public class ProductService {
    
    public Product getProduct(Long id) {
        // Mock veri dönecek
        Product product = new Product();
        product.setId(id);
        product.setName("Laptop");
        product.setPrice(1500.0);
        return product;
    }

    public Product createProduct(Product product) {
        // Mock veri dönecek
        product.setId(1L);
        return product;
    }
}