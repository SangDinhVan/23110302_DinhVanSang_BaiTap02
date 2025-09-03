package controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Product;
import service.ProductService;
import service.impl.ProductServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/admin/product")
public class ProductList extends HttpServlet {
    private final ProductService service = new ProductServiceImpl();

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Product> list = service.list(1, Integer.MAX_VALUE); // liệt kê hết, đơn giản
        req.setAttribute("list", list);
        req.getRequestDispatcher("/view/admin/product-list.jsp").forward(req, resp);
    }
}
