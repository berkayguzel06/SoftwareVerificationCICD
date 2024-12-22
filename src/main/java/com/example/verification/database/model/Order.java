package com.example.verification.database.model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class Order {
    private Long id;
    private User user;
    private List<Product> products;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Order() {}

    public Order(User user, List<Product> products) {
        this.user = user;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
    }

    public void clearProducts() {
        this.products.clear();
    }

    public void updateProducts(List<Product> products) {
        this.products = products;
    }

    public void updateProduct(Product oldProduct, Product newProduct) {
        int index = this.products.indexOf(oldProduct);
        this.products.set(index, newProduct);
    }

    public void updateProduct(int index, Product newProduct) {
        this.products.set(index, newProduct);
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public void setCreatedAt(java.sql.Date createdAt) {
        this.createdAt = createdAt.toLocalDate().atStartOfDay();
    }
}
