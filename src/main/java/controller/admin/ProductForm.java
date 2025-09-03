package controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Product;
import service.ProductService;
import service.CategoryService;
import service.impl.ProductServiceImpl;
import service.impl.CategoryServiceImpl;

import java.io.IOException;

@WebServlet(urlPatterns = "/admin/product/form")
public class ProductForm extends HttpServlet {
    private final ProductService productService = new ProductServiceImpl();
    private final CategoryService categoryService = new CategoryServiceImpl();

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String id = req.getParameter("id");
        if (id != null && !id.isBlank()) {
            try {
                Product p = productService.get(Integer.parseInt(id));
                req.setAttribute("p", p);
            } catch (NumberFormatException ignored) {}
        }
        // load categories cho dropdown
        req.setAttribute("categories", categoryService.getAll());
        req.getRequestDispatcher("/view/admin/product-form.jsp").forward(req, resp);
    }
}
