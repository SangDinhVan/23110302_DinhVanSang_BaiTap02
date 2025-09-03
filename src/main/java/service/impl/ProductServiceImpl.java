package service.impl;

import dao.ProductDAO;
import dao.impl.ProductDAOImpl;
import model.Product;
import service.ProductService;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    private final ProductDAO dao = new ProductDAOImpl();

    @Override public List<Product> list(int page,int size){ return dao.findAll(page,size); }
    @Override public List<Product> listByCategory(int cid,int page,int size){ return dao.findByCategory(cid,page,size); }
    @Override public Product get(int id){ return dao.findById(id); }
    @Override public int countAll(){ return dao.countAll(); }
    @Override public int countByCategory(int cid){ return dao.countByCategory(cid); }
    @Override public int save(Product p){ return (p.getProductID()==0)? dao.create(p): dao.update(p); }
    @Override public int delete(int id){ return dao.delete(id); }
}
