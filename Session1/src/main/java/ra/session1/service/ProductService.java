package ra.session1.service;

import org.springframework.stereotype.Service;
import ra.session1.model.entity.Product;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private List<Product> productList =  new ArrayList<>();
    public ProductService(){
        productList.add(new Product(1, "Laptop Dell", 1500.0));
        productList.add(new Product(2, "iPhone 15", 1200.0));
        productList.add(new Product(3, "Chuột Logitech", 150.0));
    }
    public List<Product> getProductList() {
        return productList;
    }
    public Product addProduct(Product product){
        productList.add(product);
        return product;
    }
    // 3. Cập nhật
    public Product updateProduct(int id, Product newInfo) {
        for (Product p : productList) {
            if (p.getId() == id) {
                p.setName(newInfo.getName());
                p.setPrice(newInfo.getPrice());
                return p;
            }
        }
        return null; // Hoặc ném Exception nếu không tìm thấy
    }

    // 4. Xóa
    public boolean deleteProduct(int id) {
        // removeIf trả về true nếu xóa được phần tử
        return productList.removeIf(p -> p.getId( ) == id);
    }
}
