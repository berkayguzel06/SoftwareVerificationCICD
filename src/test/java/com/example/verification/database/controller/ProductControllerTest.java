package com.example.verification.database.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.verification.database.model.Product;
import com.example.verification.database.services.ProductService;

@WebMvcTest(ProductController.class)  // Correct controller reference
public class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ProductService productService;  // Use @MockBean to mock the service

    @BeforeEach
    void setup() {
        // No need to manually set up MockMvc when using @WebMvcTest
    }

    @Test
    public void testGetProduct() throws Exception {
        Product mockProduct = new Product();
        mockProduct.setId(1L);
        mockProduct.setName("Laptops");
        mockProduct.setPrice(1500.0);

        when(productService.getProduct(1L)).thenReturn(mockProduct);

        mockMvc.perform(get("/products/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Laptops"))
                .andExpect(jsonPath("$.price").value(1500.0));
    }
}
