package com.example.bai1.DTO;

import com.example.bai1.model.entity.Patient;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PaginationResponse<T> {
    private List<T> data;
    private int totalPage;
    private long totalElement;
    private int currentPage;
    public PaginationResponse(){}
    public PaginationResponse(List<T> data, int totalPage, long totalElement, int currentPage) {
        this.data = data;
        this.totalPage = totalPage;
        this.totalElement = totalElement;
        this.currentPage = currentPage;
    }
}
