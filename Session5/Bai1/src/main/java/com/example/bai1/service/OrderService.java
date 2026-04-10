package com.example.bai1.service;

import com.example.bai1.model.dto.OrderSummary;
import com.example.bai1.model.dto.PaginationResponse;
import com.example.bai1.model.entity.Order;
import com.example.bai1.repository.Orderrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private Orderrepository orderrepository;
    // Bài  1
    public List<Order> findByStatus(String status) {
        return  orderrepository.findByStatus(status);
    }

    public List<Order> findByCustomerName(String customerName) {
        return orderrepository.findByCustomerNameContaining(customerName);
    }

    // Bài 2

    public List<Order> getAllOrdersSorted(String field, String direction) {
        Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by(field).ascending() : Sort.by(field).descending();
        return orderrepository.findAll(sort);
    }

    // Bài 3

    public Page<Order> getOrdersPaged(int page, int size) {
        Pageable pageable = (Pageable) PageRequest.of(page, size, Sort.by("createdAt").descending());
        return orderrepository.findAll((org.springframework.data.domain.Pageable) pageable);
    }
    public List<Order> findOrderHighPrice(){
        return orderrepository.findOrderHighPrice();
    };

    public PaginationResponse<OrderSummary> findAllAndPagination(Pageable pageable){
        Page<OrderSummary> page = orderrepository.findAllAndPagination(pageable);
        PaginationResponse paginationResponse = new PaginationResponse(page);

        return paginationResponse;
    }

    public PaginationResponse filterOrders(String status, Double minPrice, Pageable pageable) {
        // Gọi repository xử lý lọc, phân trang và projection
        Page<OrderSummary> page = orderrepository.filterOrders(status, minPrice, pageable);

        // Chuyển đổi Page thành PaginationResponse custom
        PaginationResponse paginationResponse = new PaginationResponse();
        paginationResponse.setData(page.getContent());
        paginationResponse.setTotalPage(page.getTotalPages());
        paginationResponse.setTotalElement(page.getTotalElements());
        paginationResponse.setCurrentPage(page.getNumber());

        return paginationResponse;
    }
}
