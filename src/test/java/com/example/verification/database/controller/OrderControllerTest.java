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

import com.example.verification.database.model.Order;
import com.example.verification.database.services.OrderService;

@WebMvcTest(OrderController.class)  // Correct controller reference
public class OrderControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean  // Use @MockBean to mock the OrderService
    private OrderService orderService;

    @BeforeEach
    void setup() {
        // No need for manual MockMvc setup when using @WebMvcTest
    }

    @Test
    public void testGetOrder() throws Exception {
        Order mockOrder = new Order();
        mockOrder.setId(1L);

        when(orderService.getOrder(1L)).thenReturn(mockOrder);

        mockMvc.perform(get("/orders/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));  // Check the ID in the response
    }
}
