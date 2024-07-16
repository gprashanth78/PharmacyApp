package com.pharmacy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pharmacy.entity.Order;
import com.pharmacy.entity.User;
import com.pharmacy.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
    private OrderRepository orderRepository;
    
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }
    
    public List<Order> findOrdersByUser(User user) {
        return orderRepository.findByUser(user);
    }

}
