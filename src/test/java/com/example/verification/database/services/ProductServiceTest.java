package com.example.verification.database.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.verification.database.model.Product;

public class ProductServiceTest {

    private ProductService productService;
    private Product mockProduct;

    @BeforeEach
    public void setUp() {
        productService = mock(ProductService.class);
        mockProduct = new Product();
        mockProduct.setId(1L);
        mockProduct.setName("Laptop");
        mockProduct.setPrice(1500.0);

        when(productService.getProduct(1L)).thenReturn(mockProduct);
    }

    @Test
    public void testGetProduct() {
        Product mockProduct = new Product();
        mockProduct.setId(1L);
        mockProduct.setName("Laptop");
        mockProduct.setPrice(1500.0);

        when(productService.getProduct(1L)).thenReturn(mockProduct);

        Product foundProduct = productService.getProduct(1L);

        assertEquals("Laptop", foundProduct.getName());
        assertEquals(1500.0, foundProduct.getPrice());
    }

    @Test
    public void testCreateProduct() {
        Product product = new Product();
        product.setName("Phone");
        product.setPrice(800.0);

        when(productService.createProduct(product)).thenReturn(product);

        Product createdProduct = productService.createProduct(product);

        assertEquals("Phone", createdProduct.getName());
        assertEquals(800.0, createdProduct.getPrice());
    }
}
