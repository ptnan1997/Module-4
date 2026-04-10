package com.example.bai1.repository;

import com.example.bai1.model.dto.OrderSummary;
import com.example.bai1.model.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;

public interface Orderrepository extends JpaRepository<Order, Long> {
    List<Order> findByStatus(String status);
    List<Order> findByCustomerNameContaining(String name);

    @Query("from Order o where o.totalPrice > (select avg(o2.totalPrice) from Order o2 where month(o2.createdAt) = month(now()))")
    List<Order> findOrderHighPrice();

    // Spring Data JPA sẽ tự động hiểu chỉ cần SELECT 3 cột dựa trên OrderSummary
    @Query("select new com.example.bai1.model.dto.OrderSummary(o.orderCode,o.customerName,o.totalPrice) from Order o")
    Page<OrderSummary> findAllAndPagination(Pageable pageable);

    @Query("SELECT new com.example.bai1.model.dto.OrderSummary(o.orderCode, o.customerName, o.totalPrice) " +
            "FROM Order o " +
            "WHERE (:status IS NULL OR o.status = :status) " +
            "AND (:minPrice IS NULL OR o.totalPrice >= :minPrice)")
    Page<OrderSummary> filterOrders(@Param("status") String status,
                                    @Param("minPrice") Double minPrice,
                                    Pageable pageable);

}
