package com.example.bai1.Service;

import com.example.bai1.DTO.BorrowResponseDTO;
import com.example.bai1.Entity.Book;
import com.example.bai1.Entity.BorrowTicket;
import com.example.bai1.InterfaceRepository.BookRepository;
import com.example.bai1.InterfaceRepository.BorrowTicketRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BorrowService {
    private final BorrowTicketRepository borrowTicketRepository;
    private final BookRepository bookRepository;

    public BorrowService(BorrowTicketRepository borrowTicketRepository, BookRepository bookRepository) {
        this.borrowTicketRepository = borrowTicketRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional
    public BorrowResponseDTO borrowBook(int bookId, String studentName) {
        // 1. Kiểm tra sách có tồn tại không
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("404 - Không tìm thấy sách"));

        // 2. Kiểm tra sách đã có người mượn chưa
        boolean isAlreadyBorrowed = borrowTicketRepository.existsByBookIdAndStatus(bookId, "BORROWED");
        if (isAlreadyBorrowed) {
            throw new RuntimeException("400 - Sách hiện đã có người mượn, vui lòng chọn cuốn khác");
        }

        // 3. Tạo phiếu mượn mới
        BorrowTicket ticket = new BorrowTicket();
        ticket.setStudentName(studentName);
        ticket.setBook(book);
        ticket.setBorrowDate(LocalDate.now());
        ticket.setStatus("BORROWED");

        borrowTicketRepository.save(ticket);

        // 4. Chuyển đổi sang DTO để trả về
        return new BorrowResponseDTO(
                ticket.getStudentName(),
                book.getTitle(),
                book.getAuthor().getFullName(), // Truy cập qua quan hệ ManyToOne
                ticket.getBorrowDate()
        );
    }
}
