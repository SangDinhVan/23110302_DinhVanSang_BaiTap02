package service.impl;

import dao.CategoryDAO;
import dao.impl.CategoryDAOImpl;
import model.Category;
import service.CategoryService;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private final CategoryDAO dao = new CategoryDAOImpl();

    @Override public List<Category> getAll() { return dao.findAll(); }
    @Override public Category get(int id) { return dao.findById(id); }
    @Override public int save(Category c) {
        return (c.getCategoryID() == 0) ? dao.create(c) : dao.update(c);
    }
    @Override public int delete(int id) { return dao.delete(id); }
}
