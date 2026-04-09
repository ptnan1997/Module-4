package com.example.bai1.InterfaceRepository;

import com.example.bai1.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface BookRepository extends JpaRepository<Book,Integer> {
    // 1. Query Method: Tìm sách theo tiêu đề (chứa từ khóa, không phân biệt hoa thường)
    List<Book> findByTitleContainingIgnoreCase(String keyword);

    // 2. @Query (JPQL): Lấy tất cả sách có giá cao hơn mức trung bình
    // Lưu ý: JPQL truy vấn trên Entity (Book) và thuộc tính (price)
    @Query("SELECT b FROM Book b WHERE b.price > (SELECT AVG(b2.price) FROM Book b2)")
    List<Book> findBooksAboveAveragePrice();

    // 3. @Query (Native SQL): Thống kê mỗi tác giả có bao nhiêu cuốn sách
    // Sử dụng Native SQL để thao tác trực tiếp với bảng trong DB
    @Query(value = "SELECT a.name AS authorName, COUNT(b.id) AS bookCount " +
            "FROM authors a LEFT JOIN books b ON a.id = b.author_id " +
            "GROUP BY a.id, a.name", nativeQuery = true)
    List<Map<String, Object>> countBooksByAuthor();
}
