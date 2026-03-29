package ra.session1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ra.session1.model.entity.Product;
import ra.session1.service.ProductService;

import java.util.List;

@RestController // Đánh dấu đây là nơi xử lý API
@RequestMapping("/api/products") // Đường dẫn gốc cho API trong class này
public class ProductController {
    @Autowired // Tiêm (Inject) ProductService vào Controller
    private ProductService productService;

    // GET: Lấy danh sách
    @GetMapping("/listproducts")
    public List<Product> getProducts() {
        return productService.getProductList();
    }

    // POST: Thêm mới
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    // PUT: Cập nhật theo ID
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    // DELETE: Xóa theo ID
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) {
        boolean isDeleted = productService.deleteProduct(id);
        if (isDeleted) {
            return "Xóa thành công sản phẩm ID: " + id;
        } else {
            return "Không tìm thấy sản phẩm để xóa";
        }
    }
}
