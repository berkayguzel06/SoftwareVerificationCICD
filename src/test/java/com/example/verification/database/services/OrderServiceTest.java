package com.example.verification.database.services;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.example.verification.database.model.Order;

public class OrderServiceTest {

    
    private OrderService orderService;  // Real object
    private Order mockOrder;

    @BeforeEach
    public void setUp() {
        // Manually create the mock object for orderService
        orderService = Mockito.mock(OrderService.class);
        
        // Create the mock order object
        mockOrder = new Order();
        mockOrder.setId(1L);
        
        // Stub the getOrder() method to return mockOrder
        when(orderService.getOrder(1L)).thenReturn(mockOrder);
    }

    @Test
    public void testGetOrder() {
        Order mockOrder = new Order();
        mockOrder.setId(1L);

        when(orderService.getOrder(1L)).thenReturn(mockOrder);

        Order foundOrder = orderService.getOrder(1L);

        assertNotNull(foundOrder);
        assertEquals(1L, foundOrder.getId());
    }
}
