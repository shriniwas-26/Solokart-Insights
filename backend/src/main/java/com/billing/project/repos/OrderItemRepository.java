package com.billing.project.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.billing.project.entities.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    // Find all order items for a specific order
    List<OrderItem> findByOrderId(Long orderId);
    
    // Find all order items for a specific item (useful for sales analysis)
    List<OrderItem> findByItemId(Long itemId);
    
    // Custom query to find top-selling items
    @Query("SELECT oi.item.id, SUM(oi.quantity) as totalQuantity " +
           "FROM OrderItem oi " +
           "GROUP BY oi.item.id " +
           "ORDER BY totalQuantity DESC")
    List<Object[]> findTopSellingItems();
    
    // Query to get order items with their associated item details
    @Query("SELECT oi FROM OrderItem oi JOIN FETCH oi.item WHERE oi.order.id = :orderId")
    List<OrderItem> findOrderItemsWithDetails(@Param("orderId") Long orderId);
    
    // Query to calculate total sales for a specific item
    @Query("SELECT SUM(oi.quantity * oi.priceAtOrderTime) " +
           "FROM OrderItem oi " +
           "WHERE oi.item.id = :itemId")
    Double getTotalSalesForItem(@Param("itemId") Long itemId);
}
