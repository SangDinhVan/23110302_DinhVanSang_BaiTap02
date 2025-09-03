package service;
import java.util.List;
import model.Category;

public interface CategoryService {
    List<Category> getAll();
    Category get(int id);
    int save(Category c);  // create/update tùy có ID hay chưa
    int delete(int id);
}

