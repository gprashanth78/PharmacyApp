package com.pharmacy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pharmacy.entity.Order;
import com.pharmacy.entity.User;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	public List<Order> findByUser(User user);
}
