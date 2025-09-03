package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.Product;
import service.ProductService;
import service.CategoryService;
import service.impl.ProductServiceImpl;
import service.impl.CategoryServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/products")
public class ProductList extends HttpServlet {

    private final ProductService productService = new ProductServiceImpl();
    private final CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // ---- Parse & validate query params an toàn
        int page = parseInt(req.getParameter("page"), 1, 1);        // tối thiểu 1
        int size = parseInt(req.getParameter("size"), 12, 1);       // tối thiểu 1
        Integer cid = parseNullableInt(req.getParameter("cid"));    // null = ALL

        // ---- Lấy dữ liệu
        List<Product> products = (cid == null)
                ? productService.list(page, size)
                : productService.listByCategory(cid, page, size);

        int total = (cid == null)
                ? productService.countAll()
                : productService.countByCategory(cid);

        int totalPages = (int) Math.ceil((double) total / Math.max(size, 1));

        // ---- Gắn thuộc tính cho view
        req.setAttribute("categories", categoryService.getAll());
        req.setAttribute("products", products);
        req.setAttribute("cid", cid);
        req.setAttribute("page", page);
        req.setAttribute("size", size);
        req.setAttribute("totalPages", totalPages);
        req.setAttribute("total", total);

        // ---- Render
        req.getRequestDispatcher("/view/product-list.jsp").forward(req, resp);
    }

    // Helper: parse int với giá trị mặc định & min
    private int parseInt(String s, int dft, int min) {
        try {
            int v = Integer.parseInt(s);
            return Math.max(v, min);
        } catch (Exception e) {
            return dft;
        }
    }

    // Helper: parse nullable Integer (rỗng/không hợp lệ -> null)
    private Integer parseNullableInt(String s) {
        if (s == null || s.isBlank()) return null;
        try {
            return Integer.valueOf(s);
        } catch (NumberFormatException e) {
            return null; // rơi về ALL
        }
    }
}
