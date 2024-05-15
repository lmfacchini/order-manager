package com.order.manager.repository;

import com.order.manager.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Stream;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Stream<Order> findByOrderNumber(String orderNumber);

    Stream<Order> findByCreated(LocalDateTime created);
}
