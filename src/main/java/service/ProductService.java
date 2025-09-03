package service;
import java.util.List;
import model.Product;

public interface ProductService {
    List<Product> list(int page, int pageSize);
    List<Product> listByCategory(int categoryId, int page, int pageSize);
    Product get(int id);
    int countAll();
    int countByCategory(int categoryId);
    int save(Product p);
    int delete(int id);
}
