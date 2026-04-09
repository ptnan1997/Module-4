package com.example.bai1.InterfaceRepository;

import com.example.bai1.Entity.BorrowTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BorrowTicketRepository extends JpaRepository<BorrowTicket, Integer> {
    // Tìm kiếm xem sách này có phiếu mượn nào đang ở trạng thái "BORROWED" không
    @Query("select count(bt.id) > 0 from BorrowTicket bt where bt.id = :bookId and bt.status = :status")
    boolean existsByBookIdAndStatus(@Param("bookId") int bookId, @Param("status") String status);
}
