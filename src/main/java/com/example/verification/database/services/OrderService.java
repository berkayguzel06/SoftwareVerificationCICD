package com.example.verification.database.services;

import org.springframework.stereotype.Service;

import com.example.verification.database.model.Order;

@Service
public class OrderService {
    public Order getOrder(Long id) {
        // Mock veri dönecek
        Order order = new Order();
        order.setId(id);
        return order;
    }

    public Order createOrder(Order order) {
        // Mock veri dönecek
        order.setId(1L);
        return order;
    }
}
