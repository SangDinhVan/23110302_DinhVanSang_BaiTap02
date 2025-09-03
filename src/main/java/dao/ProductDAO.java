package dao;
import java.util.List;
import model.Product;

public interface ProductDAO {
    List<Product> findAll(int page, int pageSize);
    List<Product> findByCategory(int categoryId, int page, int pageSize);
    Product findById(int id);
    int countAll();
    int countByCategory(int categoryId);
    int create(Product p);
    int update(Product p);
    int delete(int id);
}
