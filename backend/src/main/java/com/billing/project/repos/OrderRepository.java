package com.billing.project.repos;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.billing.project.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
    
    @Query("SELECT o FROM Order o WHERE DATE(o.createdAt) = :date")
    List<Order> findOrdersByDate(@Param("date") LocalDate date);
    
    @Query("SELECT SUM(o.totalAmount) FROM Order o WHERE DATE(o.createdAt) = :date")
    Double getTotalSalesForDate(@Param("date") LocalDate date);
}
